package co.neo32.deepdivetreasures.components;

import co.neo32.deepdivetreasures.DeepDiveTreasures;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;

public class Gauge {
    protected float angle;
    protected float x, y, radius;
    protected Boolean danger = false;

    protected Music alarm;

    public Gauge(float x, float y, float radius, Music alarm) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.angle = 360f;
        this.alarm = alarm;
    }

    public void update(float delta, DeepDiveTreasures game) {
        // Default update method can be overridden in subclasses
    }

    public void render(ShapeRenderer shapeRenderer) {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.WHITE);

        // Draw the circular gauge
        shapeRenderer.circle(x, y, radius);

        if (danger)
            shapeRenderer.setColor(Color.RED);
        else shapeRenderer.setColor(.5f, 1, .5f, 1);
        for (int i = 0; i < angle; i++) {
            float radian = i * MathUtils.degreesToRadians;
            float cos = MathUtils.cos(radian);
            float sin = MathUtils.sin(radian);
            shapeRenderer.line(x, y, x + radius * cos, y + radius * sin);
        }

        shapeRenderer.end();
    }

    protected void setDanger(Boolean danger) {
        if(danger) {
            alarm.play();
        } else alarm.stop();

        this.danger = danger;
    }
}
