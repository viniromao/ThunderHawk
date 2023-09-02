package co.neo32.deepdivetreasures.systems;

import co.neo32.deepdivetreasures.DeepDiveTreasures;
import co.neo32.deepdivetreasures.components.CollisionRenderer;
import co.neo32.deepdivetreasures.components.Gauge;
import co.neo32.deepdivetreasures.components.MapRenderer;
import co.neo32.deepdivetreasures.components.PositionComponent;
import co.neo32.deepdivetreasures.entities.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;

import java.util.ArrayList;
import java.util.List;

public class RenderingSystem {
    private final SpriteBatch batch;
    private final List<Entity> entities;

    private final MapRenderer mapRenderer;

    private final ShapeRenderer shapeRenderer;

    private final Group chestGroup;
    private final Group sharkGroup;

    private final Gauge batteryGauge;

    private float amplitude = 5;
    private float baselineHeight = 215;

    public Boolean renderSea = false;

    DeepDiveTreasures game;


    public RenderingSystem(SpriteBatch batch, MapRenderer mapRenderer, ShapeRenderer shapeRenderer, CollisionRenderer collisionRenderer, Group chestGroup, Group sharkGroup, Gauge batteryGauge, DeepDiveTreasures game) {
        this.batch = batch;
        this.entities = new ArrayList<>();
        this.mapRenderer = mapRenderer;
        this.shapeRenderer = shapeRenderer;
        this.chestGroup = chestGroup;
        this.sharkGroup = sharkGroup;
        this.batteryGauge = batteryGauge;
        this.game = game;
    }

    public void addEntity(Entity entity) {
        entities.add(entity);
    }

    public void update(ParticleEffect particleEffect, float deltaTime, float time) {


        if(game.shalow) {
            Gdx.gl.glClearColor(0.7f, 0.9f, 1f, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            float oscillatingHeight = baselineHeight + amplitude * (float) Math.sin(time);

            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.setColor(0, 0.9f, 1f, 1);
            shapeRenderer.rect(0, 0, 1280, oscillatingHeight);
            shapeRenderer.end();
            renderArrow(time);
        } else {
            Gdx.gl.glClearColor(0, 0.9f, 1f, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        }

        mapRenderer.render(shapeRenderer);

        batch.begin();
        particleEffect.draw(batch, deltaTime);

        for (Entity entity : entities) {
            PositionComponent position = entity.position;
            Sprite sprite = entity.sprite;

            batch.draw(sprite, position.x, position.y);

//            chest.render(batch);
//            batch.end();
//            collisionRenderer.debugRenderCollisionBox(entity,shapeRenderer);
//            batch.begin();
        }

        if (!game.shalow) {
            for (Actor actor : game.seagrassGroup.getChildren()) {
                Seagrass seagrass = (Seagrass) actor; // Cast to the specific chest class
                seagrass.render(batch);
            }
            for (Actor actor : game.seagrassGroup2.getChildren()) {
                Seagrass2 seagrass = (Seagrass2) actor; // Cast to the specific chest class
                seagrass.render(batch);
            }
            for (Actor value : sharkGroup.getChildren()) {
                Shark shark = (Shark) value;
                shark.render(batch);
            }
            game.siphoon.draw(batch);

            for (Actor actor : chestGroup.getChildren()) {
                Chest chest = (Chest) actor; // Cast to the specific chest class
                chest.render(batch);
            }


        }

        for (Actor actor : game.sharkGroup.getChildren()) {
            Shark shark = (Shark) actor;

            shark.debugCollision(shapeRenderer);
        }
        game.sun.draw(batch);

        game.cloud.draw(batch);
        game.cloud2.draw(batch);
        game.cloud3.draw(batch);
        game.cloud4.draw(batch);
        game.cloud5.draw(batch);
        game.cloud6.draw(batch);

        game.player.debugCollision(shapeRenderer);
        game.palm.draw(batch);
        game.palm2.draw(batch);
        game.palm3.draw(batch);
        game.palm4.draw(batch);
        game.palm5.draw(batch);
        game.palm6.draw(batch);



        if (!game.shalow) {
            float min = 600;
            float max = 1000;
            float normalizedAlpha = (game.player.position.y - min) / (max - min);
            normalizedAlpha = MathUtils.clamp(normalizedAlpha, 0, 1);
            normalizedAlpha = 1 - normalizedAlpha;
            game.black.setAlpha(normalizedAlpha);
            game.black.setPosition(game.player.position.x - 485, game.player.position.y - 490);
            game.black.draw(batch);

            game.effect2.draw(batch, deltaTime);


        }

        batch.end();
    }

    private void renderArrow(float time) {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(1, 1, 1, 1);  // Set the color to white

        int oscillation = (int) (10 * Math.sin(time * 2));

        int centerX = 680;
        int centerY = 45 + oscillation; //oscillation;

        int stemLength = 50;
        int arrowHeadLength = 10;
        int thickness = 3;  // Thickness of the arrow

        // Draw arrow stem as a thick line
        shapeRenderer.rectLine(centerX, centerY + stemLength / 2, centerX, centerY - stemLength / 2, thickness);

        // Draw arrow head
        shapeRenderer.triangle(
                centerX, centerY - stemLength / 2,
                centerX - arrowHeadLength, centerY - stemLength / 2 + arrowHeadLength,
                centerX + arrowHeadLength, centerY - stemLength / 2 + arrowHeadLength
        );

        shapeRenderer.end();
    }
}
