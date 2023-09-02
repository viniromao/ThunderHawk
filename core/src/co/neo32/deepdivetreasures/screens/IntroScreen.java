package co.neo32.deepdivetreasures.screens;

import co.neo32.deepdivetreasures.DeepDiveTreasures;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;

public class IntroScreen implements Screen {
    private Texture image;
    private SpriteBatch batch;
    private float time;
    private float alpha;
    private boolean isFadingOut;
    private float fadeSpeed = .5f; // Smaller value will make it slower
    DeepDiveTreasures game;

    public IntroScreen(DeepDiveTreasures game) {
        image = new Texture("sprites/neo32Logo8Bit.png");
        batch = new SpriteBatch();
        time = 0;
        alpha = 0;
        isFadingOut = false;
        this.game = game;
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        time += delta;

        if (!isFadingOut) {
            alpha += delta * fadeSpeed;
            if (alpha >= 1) {
                alpha = 1;
                isFadingOut = true;
            }
        } else {
            alpha -= delta * fadeSpeed;
            if (alpha <= 0) {
                alpha = 0;
                game.goToMainScreen();
            }
        }

        alpha = MathUtils.clamp(alpha, 0, 1);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.setColor(1, 1, 1, alpha);
        batch.draw(image, Gdx.graphics.getWidth() / 2 - image.getWidth() / 2,
                Gdx.graphics.getHeight() / 2 - image.getHeight() / 2);
        batch.setColor(1, 1, 1, 1); // Reset to default
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
        image.dispose();
        batch.dispose();
    }
}
