package co.neo32.deepdivetreasures.entities;

import co.neo32.deepdivetreasures.components.PositionComponent;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Chest extends Actor {

    private final Sprite sprite;

    public final PositionComponent position;

    public final Rectangle boundingRectangle;


    public Chest(PositionComponent position, Texture texture) {
        this.sprite = new Sprite(texture);
        this.sprite.setPosition(position.x, position.y);
        this.position = position;
        this.boundingRectangle = new Rectangle(position.x, position.y, this.sprite.getWidth(), this.sprite.getHeight());
    }

    public void render(Batch batch) {
        sprite.draw(batch);
    }
}
