package co.neo32.deepdivetreasures.entities;

import co.neo32.deepdivetreasures.DeepDiveTreasures;
import co.neo32.deepdivetreasures.components.Gauge;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.math.MathUtils;

public class PressureGauge extends Gauge {

    private float pressurePercentage;

    private float minPressure =950;
    private float maxPressure = 1050;

    public float ratio = 400;

    public PressureGauge(float x, float y, float radius, Music alarm) {
        super(x, y, radius, alarm);
        this.pressurePercentage = 0f;
        this.angle = 360f;
    }

    @Override
    public void update(float positionY, DeepDiveTreasures game) {
        // Normalize positionY between 0 and 1, where minPressure maps to 0 and maxPressure maps to 1.
        float positionYNormalized = (positionY - maxPressure) / (minPressure - maxPressure);

        // Calculate the pressure percentage based on the normalized Y position.
        pressurePercentage = positionYNormalized * 100;
        pressurePercentage = MathUtils.clamp(pressurePercentage, 0, 100);

        // Calculate the angle for the gauge based on the pressure percentage.
        angle = 360f * pressurePercentage / 100f;

        // Check and update the danger flag based on pressure levels.
        if (!danger && pressurePercentage > 85) {
            super.setDanger(true);
        }

        if (danger && pressurePercentage < 85) {
            super.setDanger(false);
        }

        // End the game if the pressure is too high.
        if (pressurePercentage >= 100) {
            game.gameOver();
        }
    }

    public void reset() {
        this.pressurePercentage = 0f;
        this.angle = 360f;
    }

    public void setPressure(float min, float max) {
        this.minPressure = min;
        this.maxPressure = max;
    }

    public void setRatio() {
        minPressure -= 200;
    }
}
