package co.neo32.deepdivetreasures.screens;

import co.neo32.deepdivetreasures.DeepDiveTreasures;
import co.neo32.deepdivetreasures.components.*;
import co.neo32.deepdivetreasures.entities.Shark;
import co.neo32.deepdivetreasures.entities.Submarine;
import co.neo32.deepdivetreasures.systems.CollisionSystem;
import co.neo32.deepdivetreasures.systems.InputSystem;
import co.neo32.deepdivetreasures.systems.MovementSystem;
import co.neo32.deepdivetreasures.systems.RenderingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class ShallowWaterScreen implements Screen {
    private DeepDiveTreasures game;

    public ShallowWaterScreen(DeepDiveTreasures game) {
        this.game = game;
        game.map = new Map();
        game.map.shallowWater();
        game.collisionSystem = new CollisionSystem(game.map);
        game.collisionRenderer = new CollisionRenderer(game.map);
        game.batch = new SpriteBatch();
        game.mapRenderer = new MapRenderer(game.map);
        game.player = new Submarine(new PositionComponent(700, 50), new VelocityComponent(), game.textures.texture);
        game.movementSystem = new MovementSystem(game.player.position.y);
        game.inputSystem = new InputSystem(game.player);

        game.renderingSystem = new RenderingSystem(game.batch, game.mapRenderer, game.shapeRenderer, game.collisionRenderer, game.chestGroup, game.sharkGroup, game.batteryGauge, game);
        game.renderingSystem.addEntity(game.player);

        game.store.setPosition(700, 220);

        game.effect.load(Gdx.files.internal("bubbleParticlesSurface.p"), Gdx.files.internal("sprites"));
        this.game.shalow = true;
    }

    @Override
    public void show() {
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

        if(game.player.position.y > 177) {
            game.player.position.y = 177;
        }

        game.renderingSystem.update(game.effect, deltaTime, game.time);
        game.batch.begin();
        game.font.draw(game.batch, "STORE", 685, 280);
        game.font.draw(game.batch, "GO DEEPER", 695, 50);
        game.store.draw(game.batch);

        if(game.player.position.y > 140 && game.player.position.x > 660) {
            game.font.draw(game.batch, "E to Upgrade Oxygen Capacity", 650, 310);
            game.font.draw(game.batch, "Q to Upgrade Pressure Capacity", 650, 330);
            game.font.draw(game.batch, "F to Upgrade Battery Capacity", 650, 350);

            if (Gdx.input.isKeyJustPressed(Input.Keys.E) && game.money >= 100) {
                game.oxygenGauge.setRatio();
                game.buy.play();
                game.money -= 100;
            }

            if (Gdx.input.isKeyJustPressed(Input.Keys.F) && game.money >= 100) {
                game.batteryGauge.setRatio();
                game.buy.play();
                game.money -= 100;
            }

            if (Gdx.input.isKeyJustPressed(Input.Keys.Q) && game.money >= 100) {
                game.pressureGauge.setRatio();
                game.buy.play();
                game.money -= 100;
            }
        }
        game.batch.end();





//        for (Actor actor : game.sharkGroup.getChildren()) {
//            Shark shark = (Shark) actor;
//
//            shark.debugCollision(game.shapeRenderer);
//        }


//        game.player.debugCollision(game.shapeRenderer);
        game.shapeRenderer.setProjectionMatrix(game.hudCamera.combined);
        game.batch.setProjectionMatrix(game.hudCamera.combined);
        game.batch.begin();
        game.font.draw(game.batch, game.money+"$", 580, 30);
        game.batch.end();

        if(game.player.position.y < -38) {
            game.goToDeepWater();
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
