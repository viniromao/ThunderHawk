package co.neo32.deepdivetreasures.systems;

import co.neo32.deepdivetreasures.components.PositionComponent;
import co.neo32.deepdivetreasures.components.VelocityComponent;
import co.neo32.deepdivetreasures.entities.Entity;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;

public class MovementSystem {

    public Boolean moving = false;

    public Boolean fliped = false;

    private Integer xBubbles = -8;

    private float floatingTime = 0;
    private float floatingOriginalY = 0;
    private float floatingAmplitude = 2;
    private float floatingFrequency = 0.5f;
    private Boolean stoped = true;

    public void processEntity(Entity entity, float deltaTime, ParticleEffect bubbles) {

        PositionComponent position = entity.position;
        Rectangle boundingRectangle = entity.boundingRectangle;
        VelocityComponent velocity = entity.velocity;

        position.x += velocity.x * deltaTime;
        position.y += velocity.y * deltaTime;
        boundingRectangle.x = position.x;
        boundingRectangle.y = position.y;


        bubbles.setPosition(entity.position.x + xBubbles, entity.position.y + 10);

        if (stoped) {
            floatingTime += deltaTime; // time is a class member variable that keeps track of the total elapsed time
            float yOffset = floatingAmplitude * MathUtils.sin(MathUtils.PI2 * floatingFrequency * floatingTime);
            entity.position.y = floatingOriginalY + yOffset;
        }

        if (!fliped && velocity.x < 0) {
            entity.sprite.flip(true, false);
            flipBubbles();
        }

        if (fliped && velocity.x > 0) {
            entity.sprite.flip(true, false);
            flipBubbles();
        }

        if (moving && (velocity.x == 0.0 && velocity.y == 0.0)) {
            bubbles.allowCompletion();
            floatingOriginalY = entity.position.y; // The original Y position of the submarine

            stoped = true;
        }
        if (!moving && (velocity.x != 0.0 || velocity.y != 0.0)) {
            stoped = false;
            bubbles.start();
        }

        if (velocity.x < 0) {
            fliped = true;
        }

        if (velocity.x > 0) {
            fliped = false;
        }

        moving = velocity.x != 0 || velocity.y != 0;
    }

    private void flipBubbles() {
        if (fliped)
            xBubbles = -8;
        else
            xBubbles = 50;

    }


}