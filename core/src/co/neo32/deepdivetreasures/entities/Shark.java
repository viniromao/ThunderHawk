package co.neo32.deepdivetreasures.entities;

import co.neo32.deepdivetreasures.components.PositionComponent;
import co.neo32.deepdivetreasures.components.VelocityComponent;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.Texture;

public class Shark extends Entity {

    private final Sprite[] frames;
    private float elapsedTime;
    private final float frameDuration;

    public Shark(PositionComponent position, VelocityComponent velocity, Texture texture) {
        super(position, velocity, texture);

        // Create Sprites
        int spriteWidth = 64;
        int spriteHeight = 32;
        frames = new Sprite[2];
        for (int i = 0; i < 2; i++) {
            frames[i] = new Sprite(texture, i * spriteWidth, 0, spriteWidth, spriteHeight);
        }

        // Initialize elapsed time and frame duration
        elapsedTime = 0f;
        frameDuration = 0.5f; // Duration for each frame in seconds
    }

    public void update() {
        // Update position based on velocity (optional)
        position.x += velocity.x * Gdx.graphics.getDeltaTime();
        position.y += velocity.y * Gdx.graphics.getDeltaTime();
    }

    public void render(Batch batch) {
        elapsedTime += Gdx.graphics.getDeltaTime(); // Accumulate elapsed animation time

        // Determine the current frame based on elapsed time
        int frameIndex = (int) (elapsedTime / frameDuration) % frames.length;
        Sprite currentFrame = frames[frameIndex];

        currentFrame.setPosition(position.x, position.y);
        currentFrame.draw(batch);
    }
}
