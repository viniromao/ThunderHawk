package co.neo32.deepdivetreasures.entities;

import co.neo32.deepdivetreasures.components.PositionComponent;
import co.neo32.deepdivetreasures.components.SizeComponent;
import co.neo32.deepdivetreasures.components.VelocityComponent;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

public abstract class Entity {

    public PositionComponent position;
    public VelocityComponent velocity;
    public Texture texture;

    public Sprite sprite;

    public SizeComponent size;

    public Rectangle boundingRectangle;

    Entity(PositionComponent position, VelocityComponent velocity, Texture texture) {
        this.position = position;
        this.velocity = velocity;
        this.texture = texture;
        this.sprite = new Sprite(texture);
        this.size = new SizeComponent(44, 32);
        this.boundingRectangle = new Rectangle(position.x, position.y, sprite.getWidth(), sprite.getHeight());
    }

}
