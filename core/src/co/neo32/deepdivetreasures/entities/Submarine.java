package co.neo32.deepdivetreasures.entities;

import co.neo32.deepdivetreasures.components.PositionComponent;
import co.neo32.deepdivetreasures.components.TextureComponent;
import co.neo32.deepdivetreasures.components.VelocityComponent;
import com.badlogic.gdx.graphics.Texture;

import java.awt.*;

public class Submarine extends Entity{
    public Submarine(PositionComponent position, VelocityComponent velocity, Texture texture) {
        super(position, velocity, texture);
    }
}
