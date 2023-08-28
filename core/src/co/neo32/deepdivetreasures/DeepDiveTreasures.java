package co.neo32.deepdivetreasures;

import co.neo32.deepdivetreasures.components.*;
import co.neo32.deepdivetreasures.entities.*;
import co.neo32.deepdivetreasures.screens.DeepWaterScreen;
import co.neo32.deepdivetreasures.screens.GameOverScreen;
import co.neo32.deepdivetreasures.screens.IntroScreen;
import co.neo32.deepdivetreasures.screens.ShallowWaterScreen;
import co.neo32.deepdivetreasures.systems.CollisionSystem;
import co.neo32.deepdivetreasures.systems.InputSystem;
import co.neo32.deepdivetreasures.systems.MovementSystem;
import co.neo32.deepdivetreasures.systems.RenderingSystem;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class DeepDiveTreasures extends Game {
    public SpriteBatch batch;
    public OrthographicCamera camera;
    Viewport viewport;

    public MovementSystem movementSystem;

    public InputSystem inputSystem;

    public Submarine player;

    public RenderingSystem renderingSystem;
    public ParticleEffect effect = new ParticleEffect();


    public MapRenderer mapRenderer;

    public TextureComponent textures;
    public Map map;

    public ShapeRenderer shapeRenderer;

    public CollisionSystem collisionSystem;
    public CollisionRenderer collisionRenderer;

    public Shark shark;

    public Chest chest;

    private ShaderProgram shader;

    public float time;

    public Group chestGroup;
    public Group sharkGroup;

    public BitmapFont font;
    public BitmapFont font100;

    public BatteryGauge batteryGauge;
    public PressureGauge pressureGauge;
    public OxygenGauge oxygenGauge;

    public OrthographicCamera hudCamera;

    public Boolean gameOver = false;
    public Boolean shalow = true;

    public Music alarm;
    public Music underwater;
    public Music theme;
    public Music buy;
    public Music getChest;


    public Sprite store;
    public Sprite black;
    public Sprite palm;
    public Sprite palm2;
    public Sprite palm3;
    public Sprite palm4;
    public Sprite palm5;
    public Sprite palm6;

    public float maxPressure;

    public float maxBattery;

    public float maxOxygen;

    public Integer money = 300;

    @Override
    public void create() {

        store = new Sprite(new Texture(Gdx.files.internal("sprites/store.png")));
        black = new Sprite(new Texture(Gdx.files.internal("sprites/black.png")));
        palm = new Sprite(new Texture(Gdx.files.internal("sprites/palm.png")));
        palm2 = new Sprite(new Texture(Gdx.files.internal("sprites/palm.png")));
        palm3 = new Sprite(new Texture(Gdx.files.internal("sprites/palm.png")));
        palm4 = new Sprite(new Texture(Gdx.files.internal("sprites/palm.png")));
        palm5 = new Sprite(new Texture(Gdx.files.internal("sprites/palm.png")));
        palm6 = new Sprite(new Texture(Gdx.files.internal("sprites/palm.png")));

        palm.setPosition(800, 222);
        palm2.setPosition(740, 222);
        palm2.flip(true, false);
        palm3.setPosition(700, 222);
        palm4.setPosition(670, 222);
        palm5.setPosition(800, 222);
        palm6.setPosition(800, 222);

        alarm = Gdx.audio.newMusic(Gdx.files.internal("sfx/alarm.mp3"));
        underwater = Gdx.audio.newMusic(Gdx.files.internal("sfx/underwater.mp3"));
        theme = Gdx.audio.newMusic(Gdx.files.internal("sfx/theme.mp3"));
        buy = Gdx.audio.newMusic(Gdx.files.internal("sfx/buy.mp3"));
        getChest = Gdx.audio.newMusic(Gdx.files.internal("sfx/getChest.mp3"));


        batteryGauge = new BatteryGauge(50, 50, 30, alarm);
        pressureGauge = new PressureGauge(50, 150, 30, alarm);
        oxygenGauge = new OxygenGauge(50, 250, 30, alarm);
        pressureGauge.setPressure(0, 1000);

        hudCamera = new OrthographicCamera();
        chestGroup = new Group();
        sharkGroup = new Group();
        shapeRenderer = new ShapeRenderer();
        batteryGauge = new BatteryGauge(50, 50, 30, alarm);
        pressureGauge = new PressureGauge(50, 150, 30, alarm);
        oxygenGauge = new OxygenGauge(50, 250, 30, alarm);
        font = new BitmapFont(Gdx.files.internal("fonts/font.fnt"), false);
        font100 = new BitmapFont(Gdx.files.internal("fonts/font100.fnt"), false);

        textures = new TextureComponent();

        Gdx.input.setInputProcessor(this.inputSystem);
//        shader = new ShaderProgram(Gdx.files.internal("shaders/waterVertex.glsl"), Gdx.files.internal("shaders/waterFragment.glsl"));
//        if (!shader.isCompiled()) {
//            throw new GdxRuntimeException("Shader compilation failed:\n" + shader.getLog());
//        }
//        batch.setShader(shader);
//        System.out.println(shader.getLog());


//        shark.update();


        TextureAtlas particleAtlas; //<-load some atlas with your particle assets in
        effect.load(Gdx.files.internal("bubbleParticles.p"), Gdx.files.internal("sprites"));

        camera = new OrthographicCamera();
        viewport = new FitViewport(320 * 2, 224 * 2, camera); // Set your desired window size here
        viewport.apply();
        hudCamera.setToOrtho(false, viewport.getWorldWidth(), viewport.getWorldHeight());


        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);

        for (int i = 0; i < 1; i++) {
            Texture texture = new Texture(Gdx.files.internal("sprites/shark.png"));
            VelocityComponent velocity = new VelocityComponent();

            this.sharkGroup.addActor(new Shark(new PositionComponent(200, 100), velocity, texture, 75.0f));
            this.sharkGroup.addActor(new Shark(new PositionComponent(150, 500), velocity, texture, 500));
            this.sharkGroup.addActor(new Shark(new PositionComponent(50, 300), velocity, texture, 250.0f));
            this.sharkGroup.addActor(new Shark(new PositionComponent(300, 700), velocity, texture, 170));
            this.sharkGroup.addActor(new Shark(new PositionComponent(400, 950), velocity, texture, 230));
            this.sharkGroup.addActor(new Shark(new PositionComponent(670, 800), velocity, texture, 230));
            this.sharkGroup.addActor(new Shark(new PositionComponent(840, 870), velocity, texture, 190));
            this.sharkGroup.addActor(new Shark(new PositionComponent(900, 650), velocity, texture, 190));
            this.sharkGroup.addActor(new Shark(new PositionComponent(770, 240), velocity, texture, 190));
            this.sharkGroup.addActor(new Shark(new PositionComponent(620, 50), velocity, texture, 350));

        }


        for (int i = 0; i < 1; i++) {
            this.chestGroup.addActor(new Chest(new PositionComponent(480, 450), new Texture(Gdx.files.internal("sprites/chest.png"))));
            this.chestGroup.addActor(new Chest(new PositionComponent(230, 194), new Texture(Gdx.files.internal("sprites/chest.png"))));
            this.chestGroup.addActor(new Chest(new PositionComponent(390, 800), new Texture(Gdx.files.internal("sprites/chest.png"))));
            this.chestGroup.addActor(new Chest(new PositionComponent(550, 897), new Texture(Gdx.files.internal("sprites/chest.png"))));
            this.chestGroup.addActor(new Chest(new PositionComponent(710, 993), new Texture(Gdx.files.internal("sprites/chest.png"))));
            this.chestGroup.addActor(new Chest(new PositionComponent(615, 800), new Texture(Gdx.files.internal("sprites/chest.png"))));
            this.chestGroup.addActor(new Chest(new PositionComponent(645, 672), new Texture(Gdx.files.internal("sprites/chest.png"))));
            this.chestGroup.addActor(new Chest(new PositionComponent(900, 768), new Texture(Gdx.files.internal("sprites/chest.png"))));
            this.chestGroup.addActor(new Chest(new PositionComponent(1110, 608), new Texture(Gdx.files.internal("sprites/chest.png"))));
            this.chestGroup.addActor(new Chest(new PositionComponent(840, 192), new Texture(Gdx.files.internal("sprites/chest.png"))));
            this.chestGroup.addActor(new Chest(new PositionComponent(890, 416), new Texture(Gdx.files.internal("sprites/chest.png"))));
            this.chestGroup.addActor(new Chest(new PositionComponent(1010, 192), new Texture(Gdx.files.internal("sprites/chest.png"))));
            this.chestGroup.addActor(new Chest(new PositionComponent(200, 287), new Texture(Gdx.files.internal("sprites/chest.png"))));
            this.chestGroup.addActor(new Chest(new PositionComponent(350, 287), new Texture(Gdx.files.internal("sprites/chest.png"))));
            this.chestGroup.addActor(new Chest(new PositionComponent(200, 448), new Texture(Gdx.files.internal("sprites/chest.png"))));
            this.chestGroup.addActor(new Chest(new PositionComponent(180, 640), new Texture(Gdx.files.internal("sprites/chest.png"))));
            this.chestGroup.addActor(new Chest(new PositionComponent(220, 896), new Texture(Gdx.files.internal("sprites/chest.png"))));

        }

        this.setScreen(new ShallowWaterScreen(this));

        getChest.setVolume(.2f);
        buy.setVolume(.5f);
        underwater.setVolume(.5f);
        underwater.setLooping(true);
        underwater.play();

        theme.setVolume(.1f);
        theme.setLooping(true);
        theme.play();
        alarm.setVolume(.3f);
        alarm.setLooping(true);
    }

    public void restart() {
        sharkGroup.clear();
        chestGroup.clear();
        money = 300;

        for (int i = 0; i < 1; i++) {
            Texture texture = new Texture(Gdx.files.internal("sprites/shark.png"));
            VelocityComponent velocity = new VelocityComponent();

            this.sharkGroup.addActor(new Shark(new PositionComponent(200, 100), velocity, texture, 75.0f));
            this.sharkGroup.addActor(new Shark(new PositionComponent(150, 500), velocity, texture, 500));
            this.sharkGroup.addActor(new Shark(new PositionComponent(50, 300), velocity, texture, 250.0f));
            this.sharkGroup.addActor(new Shark(new PositionComponent(300, 700), velocity, texture, 170));
            this.sharkGroup.addActor(new Shark(new PositionComponent(400, 950), velocity, texture, 230));
            this.sharkGroup.addActor(new Shark(new PositionComponent(670, 800), velocity, texture, 230));
            this.sharkGroup.addActor(new Shark(new PositionComponent(840, 870), velocity, texture, 190));
            this.sharkGroup.addActor(new Shark(new PositionComponent(900, 650), velocity, texture, 190));
            this.sharkGroup.addActor(new Shark(new PositionComponent(770, 240), velocity, texture, 190));
            this.sharkGroup.addActor(new Shark(new PositionComponent(620, 50), velocity, texture, 350));

        }


        for (int i = 0; i < 1; i++) {
            this.chestGroup.addActor(new Chest(new PositionComponent(480, 450), new Texture(Gdx.files.internal("sprites/chest.png"))));
            this.chestGroup.addActor(new Chest(new PositionComponent(230, 194), new Texture(Gdx.files.internal("sprites/chest.png"))));
            this.chestGroup.addActor(new Chest(new PositionComponent(390, 800), new Texture(Gdx.files.internal("sprites/chest.png"))));
            this.chestGroup.addActor(new Chest(new PositionComponent(550, 897), new Texture(Gdx.files.internal("sprites/chest.png"))));
            this.chestGroup.addActor(new Chest(new PositionComponent(710, 993), new Texture(Gdx.files.internal("sprites/chest.png"))));
            this.chestGroup.addActor(new Chest(new PositionComponent(615, 800), new Texture(Gdx.files.internal("sprites/chest.png"))));
            this.chestGroup.addActor(new Chest(new PositionComponent(645, 672), new Texture(Gdx.files.internal("sprites/chest.png"))));
            this.chestGroup.addActor(new Chest(new PositionComponent(900, 768), new Texture(Gdx.files.internal("sprites/chest.png"))));
            this.chestGroup.addActor(new Chest(new PositionComponent(1110, 608), new Texture(Gdx.files.internal("sprites/chest.png"))));
            this.chestGroup.addActor(new Chest(new PositionComponent(840, 192), new Texture(Gdx.files.internal("sprites/chest.png"))));
            this.chestGroup.addActor(new Chest(new PositionComponent(890, 416), new Texture(Gdx.files.internal("sprites/chest.png"))));
            this.chestGroup.addActor(new Chest(new PositionComponent(1010, 192), new Texture(Gdx.files.internal("sprites/chest.png"))));
            this.chestGroup.addActor(new Chest(new PositionComponent(200, 287), new Texture(Gdx.files.internal("sprites/chest.png"))));
            this.chestGroup.addActor(new Chest(new PositionComponent(350, 287), new Texture(Gdx.files.internal("sprites/chest.png"))));
            this.chestGroup.addActor(new Chest(new PositionComponent(200, 448), new Texture(Gdx.files.internal("sprites/chest.png"))));
            this.chestGroup.addActor(new Chest(new PositionComponent(180, 640), new Texture(Gdx.files.internal("sprites/chest.png"))));
            this.chestGroup.addActor(new Chest(new PositionComponent(220, 896), new Texture(Gdx.files.internal("sprites/chest.png"))));

        }

        batteryGauge = new BatteryGauge(50, 50, 30, alarm);
        pressureGauge = new PressureGauge(50, 150, 30, alarm);
        oxygenGauge = new OxygenGauge(50, 250, 30, alarm);
    }

//    @Override
//    public void render() {
//        if (gameOver) {
//            alarm.stop();
//            underwater.stop();
//            return;
//        }
//
//        float deltaTime = Gdx.graphics.getDeltaTime();
//        time += deltaTime;// Update time for animation in the shader
//
//        float clampedX = Math.min(Math.max(player.position.x + player.size.x / 2, 325), 863);
//        float clampedy = Math.min(Math.max(player.position.y + player.size.y / 2, 225), 863);
//        camera.position.set(clampedX, clampedy, 0);
//
//
//        // Update the camera
//        camera.update();
//        hudCamera.update();
//        batch.setProjectionMatrix(camera.combined);
//        shapeRenderer.setProjectionMatrix(camera.combined);
//        inputSystem.update(deltaTime);
//        collisionSystem.handleCollision(player, chestGroup, sharkGroup, this);
//        movementSystem.processEntity(player, deltaTime, effect, sharkGroup);
//
//        renderingSystem.update(effect, deltaTime);
//        if (player.velocity.y != 0 || player.velocity.x != 0)
//            batteryGauge.update(deltaTime, this);
//        pressureGauge.update(player.position.y, this);
//        oxygenGauge.update(deltaTime, this);
//        for (Actor actor : sharkGroup.getChildren()) {
//            Shark shark = (Shark) actor;
//
//            shark.debugCollision(shapeRenderer);
//        }
//        player.debugCollision(shapeRenderer);
//        shapeRenderer.setProjectionMatrix(hudCamera.combined);
//        batch.setProjectionMatrix(hudCamera.combined);
//
//        batch.begin();
//        font.draw(batch, "Oxygen", 10, 300);
//        font.draw(batch, "Pressure", 3, 200);
//        font.draw(batch, "Battery", 10, 100);
//        batch.end();
//
//        batch.begin(); // End the sprite batch
//        batteryGauge.render(shapeRenderer);
//        pressureGauge.render(shapeRenderer);
//        oxygenGauge.render(shapeRenderer);
//        batch.end(); // End the sprite batch
//
//    }

    public void gameOver() {
        gameOver = true;
        alarm.stop();
        this.setScreen(new GameOverScreen(this));
    }

    public void goToDeepWater() {
        alarm.stop();
        shalow = false;
        this.setScreen(new DeepWaterScreen(this));
    }

    public void goToShallowWater() {
        shalow = true;
        alarm.stop();
        this.setScreen(new ShallowWaterScreen(this));
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
