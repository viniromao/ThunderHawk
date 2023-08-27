package co.neo32.deepdivetreasures.entities;

import co.neo32.deepdivetreasures.components.PositionComponent;
import co.neo32.deepdivetreasures.components.SizeComponent;
import co.neo32.deepdivetreasures.components.VelocityComponent;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

public abstract class Entity extends Actor {

    public PositionComponent position;
    public VelocityComponent velocity;
    public Texture texture;

    public Sprite sprite;

    public SizeComponent size;

    public Rectangle boundingRectangle;

    public ShapeRenderer debugCollision;

    Entity(PositionComponent position, VelocityComponent velocity, Texture texture) {
        this.position = position;
        this.velocity = velocity;
        this.texture = texture;
        this.sprite = new Sprite(texture);
        this.size = new SizeComponent(sprite.getWidth(), sprite.getHeight());
        this.boundingRectangle = new Rectangle(position.x, position.y, sprite.getWidth(), sprite.getHeight());

        debugCollision = new ShapeRenderer();
    }

    public void debugCollision(ShapeRenderer shape) {
        boundingRectangle.setPosition(position.x, position.y);
//        shape.begin(ShapeRenderer.ShapeType.Line);
//        shape.setColor(1, 0, 0, 1);
//        shape.rect(boundingRectangle.x, boundingRectangle.y, boundingRectangle.width, boundingRectangle.height);
//        shape.end();
    }

}
