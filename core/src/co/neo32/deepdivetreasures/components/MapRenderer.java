package co.neo32.deepdivetreasures.components;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.ArrayList;
import java.util.Random;

public class MapRenderer {

    public Map map;

    private Integer rows;
    private Integer columns;
    private ArrayList<SubRectangle> subRectangles;
    private Random random = new Random();

    private final Integer chance = 5;

    public MapRenderer(Map map) {
        this.map = map;
        this.columns = map.width;
        this.rows = map.height;
        this.subRectangles = new ArrayList<>();

        int subdivisions = 4; // Number of rows and columns to subdivide into
        int smallTileSize = map.tileSize / subdivisions; // Size of the smaller rectangles

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (map.coordinates[i][j]) {
                    for (int k = 0; k < subdivisions; k++) {
                        for (int l = 0; l < subdivisions; l++) {

                            if (i-1 >= 0 && (k == 0 && random.nextInt(chance) == 0) && !map.coordinates[i - 1][j]) {
                                continue;
                            }
                            if (i+1 < rows && (k == subdivisions - 1 && random.nextInt(chance) == 0) && !map.coordinates[i + 1][j]) {
                                continue;
                            }
                            if (j+1 < columns && (l == subdivisions - 1 && random.nextInt(chance) == 0) && !map.coordinates[i][j + 1]) {
                                continue;
                            }
                            if (j-1 >= 0 && (l == 0 && random.nextInt(chance) == 0) && !map.coordinates[i][j - 1]) {
                                continue;
                            }

                            SubRectangle subRectangle = new SubRectangle(
                                    (map.tileSize * j) + (smallTileSize * l),
                                    (map.tileSize * i) + (smallTileSize * k),
                                    smallTileSize,
                                    smallTileSize
                            );
                            subRectangles.add(subRectangle);
                        }
                    }
                }
            }
        }
    }

    public void render(ShapeRenderer shapeRenderer) {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor((float) 0, (float) 0.3, (float) 0.3, 1); // White color
        for (SubRectangle subRectangle : subRectangles) {
            shapeRenderer.rect(subRectangle.x, subRectangle.y, subRectangle.width, subRectangle.height);
        }
        shapeRenderer.end();
    }

    private static class SubRectangle {
        float x, y, width, height;

        SubRectangle(float x, float y, float width, float height) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
        }
    }
}
