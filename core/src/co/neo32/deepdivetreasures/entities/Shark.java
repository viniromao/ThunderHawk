package co.neo32.deepdivetreasures.entities;

import co.neo32.deepdivetreasures.components.PositionComponent;
import co.neo32.deepdivetreasures.components.SizeComponent;
import co.neo32.deepdivetreasures.components.VelocityComponent;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Shark extends Entity {

    private final Sprite[] frames;
    private float elapsedTime;
    private final float frameDuration;
    private final float minX; // Minimum x value for movement
    private final float maxX; // Maximum x value for movement
    private boolean movingRight; // Whether the shark is currently moving right

    public Shark(PositionComponent position, VelocityComponent velocity, Texture texture, float length) {
        super(position, velocity, texture);

        // Set movement range
        minX = position.x;
        maxX = position.x + length;
        velocity.x = 50.0f;
        // Initialize other properties
        movingRight = false; // Start moving to the left

        // Create Sprites
        int spriteWidth = 48;
        int spriteHeight = 32;
        frames = new Sprite[2];
        for (int i = 0; i < 2; i++) {
            frames[i] = new Sprite(texture, i * spriteWidth, 0, spriteWidth, spriteHeight);
        }

        // Initialize elapsed time and frame duration
        elapsedTime = 0f;
        frameDuration = 0.5f; // Duration for each frame in seconds
        this.boundingRectangle = new Rectangle(position.x+5, position.y+5, (sprite.getWidth() /2)-5 , sprite.getHeight()-5 );
        this.size = new SizeComponent((sprite.getWidth() /2)-5, sprite.getHeight()-5);



    }

    public void update() {

        if (movingRight) {
            position.x += velocity.x * Gdx.graphics.getDeltaTime();
            if (position.x >= maxX) {
                movingRight = false;
                flipSprite();
            }
        } else {
            position.x -= velocity.x * Gdx.graphics.getDeltaTime();
            if (position.x <= minX) {
                movingRight = true;
                flipSprite();
            }
        }
    }

    public void render(Batch batch) {

        elapsedTime += Gdx.graphics.getDeltaTime(); // Accumulate elapsed animation time

        // Determine the current frame based on elapsed time
        int frameIndex = (int) (elapsedTime / frameDuration) % frames.length;
        Sprite currentFrame = frames[frameIndex];

        currentFrame.setPosition(position.x, position.y);
        currentFrame.draw(batch);
    }

    private void flipSprite() {
        for (int i = 0; i < 2; i++) {
            frames[i].flip(true, false);
        }
    }
}
