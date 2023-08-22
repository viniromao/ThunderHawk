package co.neo32.deepdivetreasures.components;

import co.neo32.deepdivetreasures.components.Map;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class MapRenderer {

    public Map map;

    private Integer rows;
    private Integer columns;

    public MapRenderer(Map map) {
        this.map = map;
        this.columns = map.width;
        this.rows =  map.height;
    }

    public void render(ShapeRenderer shapeRenderer) {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(1, 1, 1, 1); // White color
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if(map.coordinates[i][j]) {
                    shapeRenderer.rect(map.tileSize*j, map.tileSize*i, map.tileSize, map.tileSize); // Position (100, 100) and size 50x50
                }
            }
        }
        shapeRenderer.end();

    }
}
