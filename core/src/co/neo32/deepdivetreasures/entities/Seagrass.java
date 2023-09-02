package co.neo32.deepdivetreasures.entities;

import co.neo32.deepdivetreasures.components.PositionComponent;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.Random;

public class Seagrass extends Actor {

    private final Sprite[] frames;
    private final int firstFrame;
    private float elapsedTime;
    private final float frameDuration;

    private final PositionComponent position;

    public Seagrass(PositionComponent position) {

        this.position = position;

        int spriteWidth = 32;
        int spriteHeight = 128;
        frames = new Sprite[3];
        for (int i = 0; i < 3; i++) {
            frames[i] = new Sprite(new Texture(Gdx.files.internal("sprites/seagrass.png")), i * spriteWidth, 0, spriteWidth, spriteHeight);
        }

        for (Sprite sprite : frames) {
            sprite.setPosition(position.x, position.y);
        }

        Random rand = new Random();

        firstFrame = rand.nextInt(3);

        elapsedTime = 0f;
        frameDuration = 1f; // Duration for each frame in seconds
    }

    public void render(Batch batch) {

        elapsedTime += Gdx.graphics.getDeltaTime(); // Accumulate elapsed animation time

        // Determine the current frame based on elapsed time
        int frameIndex = (int) (firstFrame + (elapsedTime / frameDuration)) % frames.length;
        Sprite currentFrame = frames[frameIndex];

        currentFrame.setPosition(position.x, position.y);
        currentFrame.draw(batch);
    }
}
