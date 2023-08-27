package co.neo32.deepdivetreasures.entities;

import co.neo32.deepdivetreasures.DeepDiveTreasures;
import co.neo32.deepdivetreasures.components.Gauge;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.math.MathUtils;

public class BatteryGauge extends Gauge {
    private float batteryPercentage;

    private float ratio = 12f;

    public BatteryGauge(float x, float y, float radius, Music alarm) {
        super(x, y, radius, alarm);
        this.batteryPercentage = 100f;
        this.angle = 360f;
    }

    @Override
    public void update(float delta, DeepDiveTreasures game) {
        batteryPercentage -= delta * ratio;
        batteryPercentage = MathUtils.clamp(batteryPercentage, 0, 100);
        angle = 360f * batteryPercentage / 100f;

        if(!danger && batteryPercentage < 30) {
            super.setDanger(true);
        }

        if(danger && batteryPercentage > 30) {
            super.setDanger(false);
        }

        if(batteryPercentage <= 0) {
            game.gameOver();
        }
    }

    public void setBatteryPercentage(float batteryPercentage) {
        this.batteryPercentage = MathUtils.clamp(batteryPercentage, 0, 100);
    }

    public void reset() {
        this.batteryPercentage = 100f;
        this.angle = 360f;
    }

    public void setRatio() {
        ratio -= 2;
    }

    public float getBatteryPercentage() {
        return batteryPercentage;
    }
}
