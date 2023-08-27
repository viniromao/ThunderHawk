package co.neo32.deepdivetreasures.screens;

import co.neo32.deepdivetreasures.DeepDiveTreasures;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameOverScreen implements Screen {
    private DeepDiveTreasures game;
    float time = 0;

    public GameOverScreen(DeepDiveTreasures game) {
        this.game = game;
        game.batch = new SpriteBatch();
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        time += Gdx.graphics.getDeltaTime();  // Update time

        float alpha = (float) Math.abs(Math.sin(time * 2));  // 2 is the frequency

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();
        game.font100.draw(game.batch, "Game Over", 138, 300);

        game.font.getColor().a = alpha;
        game.font.draw(game.batch, "Click ESC to Restart", 205, 200);

        game.font.getColor().a = 1;

        game.batch.end();

        // Check for ESC key press
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            game.setScreen(new ShallowWaterScreen(game));
            game.gameOver = false;
            game.restart();
        }
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
    }
}