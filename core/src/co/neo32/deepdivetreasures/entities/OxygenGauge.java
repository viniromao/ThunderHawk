package co.neo32.deepdivetreasures.entities;

import co.neo32.deepdivetreasures.DeepDiveTreasures;
import co.neo32.deepdivetreasures.components.Gauge;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.math.MathUtils;

public class OxygenGauge extends Gauge {

    private float oxygenPercentage;

    public float ratio = 8;

    public OxygenGauge(float x, float y, float radius, Music alarm) {
        super(x, y, radius, alarm);
        this.oxygenPercentage = 100f;
        this.angle = 360f;
    }

    @Override
    public void update(float delta, DeepDiveTreasures game) {
        oxygenPercentage -= delta * ratio;
        oxygenPercentage = MathUtils.clamp(oxygenPercentage, 0, 100);
        angle = 360f * oxygenPercentage / 100f;

        if (!danger && oxygenPercentage < 30) {
            super.setDanger(true);
        }

        if (danger && oxygenPercentage > 30) {
            super.setDanger(false);
        }

        if (oxygenPercentage <= 0) {
            game.gameOver();
        }
    }

    public void setBatteryPercentage(float batteryPercentage) {
        this.oxygenPercentage = MathUtils.clamp(batteryPercentage, 0, 100);
    }

    public void reset() {
        this.oxygenPercentage = 100f;
        this.angle = 360f;
    }

    public void setRatio() {
        if (ratio >= 1) {
            ratio -= 2;
        }
    }
}
