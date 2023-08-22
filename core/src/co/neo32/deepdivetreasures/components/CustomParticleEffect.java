//package co.neo32.deepdivetreasures.components;
//
//import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.graphics.Texture;
//import com.badlogic.gdx.graphics.g2d.Batch;
//import com.badlogic.gdx.graphics.g2d.ParticleEffect;
//import com.badlogic.gdx.graphics.g2d.ParticleEmitter;
//import com.badlogic.gdx.graphics.g2d.Sprite;
//
//public class CustomParticleEffect {
//    public ParticleEffect particleEffect;
//
//    public CustomParticleEffect(String imagePath) {
//        Texture particleTexture = new Texture(Gdx.files.internal(imagePath));
//        particleEffect = new ParticleEffect();
//        ParticleEmitter emitter = new ParticleEmitter();
////        emitter.setSprite(new Sprite(particleTexture));
//        particleEffect.getEmitters().add(emitter);
//    }
//
//    public void setPosition(float x, float y) {
//        particleEffect.setPosition(x, y);
//    }
//
//    public void updateAndDraw(Batch batch, float deltaTime) {
//        particleEffect.update(deltaTime);
//        particleEffect.draw(batch);
//    }
//
//    public void dispose() {
//        for (ParticleEmitter emitter : particleEffect.getEmitters()) {
//            emitter.getSprite().getTexture().dispose();
//        }
//        particleEffect.dispose();
//    }
//}
