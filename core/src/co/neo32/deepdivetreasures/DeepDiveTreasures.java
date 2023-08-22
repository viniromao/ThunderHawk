package co.neo32.deepdivetreasures;

import co.neo32.deepdivetreasures.components.*;
import co.neo32.deepdivetreasures.entities.Submarine;
import co.neo32.deepdivetreasures.systems.CollisionSystem;
import co.neo32.deepdivetreasures.systems.InputSystem;
import co.neo32.deepdivetreasures.systems.MovementSystem;
import co.neo32.deepdivetreasures.systems.RenderingSystem;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class DeepDiveTreasures extends ApplicationAdapter {
    SpriteBatch batch;
    OrthographicCamera camera;
    Viewport viewport;

    MovementSystem movementSystem;

    InputSystem inputSystem;

    Submarine player;

    RenderingSystem renderingSystem;
    ParticleEffect effect = new ParticleEffect();

    private MapRenderer mapRenderer;

    public TextureComponent textures;
    public Map map;

    public ShapeRenderer shapeRenderer;

    public CollisionSystem collisionSystem;
    public CollisionRenderer collisionRenderer;


    @Override
    public void create() {
        shapeRenderer = new ShapeRenderer();
        map = new Map();
        collisionSystem = new CollisionSystem(map);
        collisionRenderer = new CollisionRenderer(map);
        batch = new SpriteBatch();
        mapRenderer = new MapRenderer(map);
        textures = new TextureComponent();
        renderingSystem = new RenderingSystem(batch, mapRenderer, shapeRenderer, collisionRenderer);
        player = new Submarine(new PositionComponent(400.0f, 0.0f), new VelocityComponent(), textures.texture);
        renderingSystem.addEntity(player);
        this.movementSystem = new MovementSystem();
        this.inputSystem = new InputSystem(player);

        Gdx.input.setInputProcessor(this.inputSystem);


        TextureAtlas particleAtlas; //<-load some atlas with your particle assets in
        effect.load(Gdx.files.internal("bubbleParticles.p"), Gdx.files.internal("sprites"));

        camera = new OrthographicCamera();
        viewport = new FitViewport(320 * 2, 224 * 2, camera); // Set your desired window size here
        viewport.apply();

        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
    }

    @Override
    public void render() {


        float deltaTime = Gdx.graphics.getDeltaTime();

        camera.update();
        batch.setProjectionMatrix(camera.combined);
        shapeRenderer.setProjectionMatrix(camera.combined);
        inputSystem.update(deltaTime);
        collisionSystem.handleCollision(player);
        movementSystem.processEntity(player, deltaTime, effect);
        renderingSystem.update(effect, deltaTime);
    }


    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
