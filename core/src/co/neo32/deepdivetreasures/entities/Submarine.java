package co.neo32.deepdivetreasures.entities;

import co.neo32.deepdivetreasures.components.PositionComponent;
import co.neo32.deepdivetreasures.components.SizeComponent;
import co.neo32.deepdivetreasures.components.VelocityComponent;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;


public class Submarine extends Entity {
    public Submarine(PositionComponent position, VelocityComponent velocity, Texture texture) {
        super(position, velocity, texture);
        this.boundingRectangle = new Rectangle(position.x, position.y, sprite.getWidth() , sprite.getHeight()-10 );
        this.size = new SizeComponent(sprite.getWidth() , sprite.getHeight()-10 );

    }
}
