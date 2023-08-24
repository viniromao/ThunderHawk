package co.neo32.deepdivetreasures;

import co.neo32.deepdivetreasures.components.*;
import co.neo32.deepdivetreasures.entities.Chest;
import co.neo32.deepdivetreasures.entities.Shark;
import co.neo32.deepdivetreasures.entities.Submarine;
import co.neo32.deepdivetreasures.systems.CollisionSystem;
import co.neo32.deepdivetreasures.systems.InputSystem;
import co.neo32.deepdivetreasures.systems.MovementSystem;
import co.neo32.deepdivetreasures.systems.RenderingSystem;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.GdxRuntimeException;
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

    public Shark shark;

    public Chest chest;

    private ShaderProgram shader;

    private float time;

    private Group chestGroup;

    @Override
    public void create() {
        chestGroup = new Group();
        shapeRenderer = new ShapeRenderer();

        for (int i = 0; i < 1; i++) {
            PositionComponent position = new PositionComponent(250, 250); // Define the position
            Texture chestTexture = new Texture(Gdx.files.internal("sprites/chest.png"));
            chest = new Chest(new PositionComponent(250, 205), chestTexture);
            chestGroup.addActor(chest); // Assuming Chest is an Actor or has an associated actor
        }

        map = new Map();
        collisionSystem = new CollisionSystem(map);
        collisionRenderer = new CollisionRenderer(map);
        batch = new SpriteBatch();
        mapRenderer = new MapRenderer(map);
        textures = new TextureComponent();
        Texture sharkTexture = new Texture(Gdx.files.internal("sprites/shark.png"));
        PositionComponent position = new PositionComponent(100, 100);
        VelocityComponent velocity = new VelocityComponent();
        shark = new Shark(position, velocity, sharkTexture);

        renderingSystem = new RenderingSystem(batch, mapRenderer, shapeRenderer, collisionRenderer, shark, chestGroup);
        player = new Submarine(new PositionComponent(400.0f, 0.0f), new VelocityComponent(), textures.texture);
        renderingSystem.addEntity(player);
        this.movementSystem = new MovementSystem();
        this.inputSystem = new InputSystem(player);

        Gdx.input.setInputProcessor(this.inputSystem);
        shader = new ShaderProgram(Gdx.files.internal("shaders/waterVertex.glsl"), Gdx.files.internal("shaders/waterFragment.glsl"));
        if (!shader.isCompiled()) {
            throw new GdxRuntimeException("Shader compilation failed:\n" + shader.getLog());
        }
//        batch.setShader(shader);
        System.out.println(shader.getLog());


//        shark.update();


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
        time += deltaTime;// Update time for animation in the shader

        camera.position.set(player.position.x + player.size.x / 2, player.position.y + player.size.y / 2, 0);

        // Update the camera
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        shapeRenderer.setProjectionMatrix(camera.combined);

        inputSystem.update(deltaTime);
        collisionSystem.handleCollision(player, chestGroup);
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
