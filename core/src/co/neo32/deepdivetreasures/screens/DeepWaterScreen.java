package co.neo32.deepdivetreasures.screens;

import co.neo32.deepdivetreasures.DeepDiveTreasures;
import co.neo32.deepdivetreasures.components.*;
import co.neo32.deepdivetreasures.entities.*;
import co.neo32.deepdivetreasures.systems.CollisionSystem;
import co.neo32.deepdivetreasures.systems.InputSystem;
import co.neo32.deepdivetreasures.systems.MovementSystem;
import co.neo32.deepdivetreasures.systems.RenderingSystem;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class DeepWaterScreen implements Screen {
    private DeepDiveTreasures game;

    public DeepWaterScreen(DeepDiveTreasures game) {
        this.game = game;

        game.map = new Map();
        game.collisionSystem = new CollisionSystem(game.map);
        game.collisionRenderer = new CollisionRenderer(game.map);
        game.batch = new SpriteBatch();
        game.mapRenderer = new MapRenderer(game.map);
        game.renderingSystem = new RenderingSystem(game.batch, game.mapRenderer, game.shapeRenderer, game.collisionRenderer, game.chestGroup, game.sharkGroup, game.batteryGauge, game);
        game.player = new Submarine(new PositionComponent(600, 1050), new VelocityComponent(), game.textures.texture);
        game.movementSystem = new MovementSystem(game.player.position.y);
        game.inputSystem = new InputSystem(game.player);
        game.renderingSystem.addEntity(game.player);
        game.effect.load(Gdx.files.internal("bubbleParticles.p"), Gdx.files.internal("sprites"));
        game.pressureGauge.reset();
        game.oxygenGauge.reset();
        game.batteryGauge.reset();
        this.game.shalow = false;

    }

    @Override
    public void show() {
        game.renderingSystem.renderSea = false;

    }

    @Override
    public void render(float delta) {
        if (game.gameOver) {
            game.alarm.stop();
            game.underwater.stop();
            return;
        }

        float deltaTime = Gdx.graphics.getDeltaTime();
        game.time += deltaTime;

        float clampedX = Math.min(Math.max(game.player.position.x + game.player.size.x / 2, 325), 863);
        float clampedy = Math.min(Math.max(game.player.position.y + game.player.size.y / 2, 225), 863);
        game.camera.position.set(clampedX, clampedy, 0);


        game.camera.update();
        game.hudCamera.update();
        game.batch.setProjectionMatrix(game.camera.combined);
        game.shapeRenderer.setProjectionMatrix(game.camera.combined);
        game.inputSystem.update(deltaTime);
        game.collisionSystem.handleCollision(game.player, game.chestGroup, game.sharkGroup, game);
        game.movementSystem.processEntity(game.player, deltaTime, game.effect, game.sharkGroup);

        game.renderingSystem.update(game.effect, deltaTime, game.time);
        if (game.player.velocity.y != 0 || game.player.velocity.x != 0)
            game.batteryGauge.update(deltaTime, game);
        game.pressureGauge.update(game.player.position.y, game);
        game.oxygenGauge.update(deltaTime, game);
        game.shapeRenderer.setProjectionMatrix(game.hudCamera.combined);
        game.batch.setProjectionMatrix(game.hudCamera.combined);

        game.batch.begin();
        game.font.draw(game.batch, "Oxygen", 10, 300);
        game.font.draw(game.batch, "Pressure", 3, 200);
        game.font.draw(game.batch, "Battery", 10, 100);
        game.batch.end();

        game.batch.begin();
        game.batteryGauge.render(game.shapeRenderer);
        game.pressureGauge.render(game.shapeRenderer);
        game.oxygenGauge.render(game.shapeRenderer);
        game.batch.end();

        if(game.player.position.y > 1080) {
            game.goToShallowWater();
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
        game.batch.dispose();
    }
}
