package co.neo32.deepdivetreasures.components;

import co.neo32.deepdivetreasures.entities.Entity;
import co.neo32.deepdivetreasures.entities.Submarine;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class CollisionRenderer {
    private final Map map;

    public CollisionRenderer(Map map) {
        this.map = map;
    }

    public void debugRenderCollisionBox(Entity player, ShapeRenderer shapeRenderer) {
        float nextX = player.position.x + player.velocity.x;
        float nextY = player.position.y + player.velocity.y;

        int startI = (int) (nextY / map.tileSize);
        int endI = (int) ((nextY + player.size.y) / map.tileSize);
        int startJ = (int) (nextX / map.tileSize);
        int endJ = (int) ((nextX + player.size.x) / map.tileSize);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(1, 0, 0, 1);
        // Check all the tiles that the player might collide with
        for (int i = startI; i <= endI; i++) {
            for (int j = startJ; j <= endJ; j++) {
                shapeRenderer.rect(j * map.tileSize, i * map.tileSize, map.tileSize, map.tileSize);
                if (i >= 0 && i < map.height && j >= 0 && j < map.width && map.coordinates[i][j]) {
                    // Collision detected
                    shapeRenderer.end();
                    return; // Exit the function early since a collision was detected
                }
            }
        }
        shapeRenderer.end();
    }

//    public void handleCollision(Submarine player, ShapeRenderer shapeRenderer) {
//        float nextX = player.position.x + player.velocity.x;
//        float nextY = player.position.y + player.velocity.y;
//
//        int startI = (int) (nextY / map.tileSize);
//        int endI = (int) ((nextY + player.size) / map.tileSize);
//        int startJ = (int) (nextX / map.tileSize);
//        int endJ = (int) ((nextX + player.size) / map.tileSize);
//
//        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
//        shapeRenderer.setColor(1, 0, 0, 1); // Red color
//
//        for (int i = startI; i <= endI; i++) {
//            for (int j = startJ; j <= endJ; j++) {
//                shapeRenderer.rect(j * map.tileSize, i * map.tileSize, map.tileSize, map.tileSize);
//
//                if (i >= 0 && i < map.height && j >= 0 && j < map.width && map.coordinates[i][j]) {
//                    // Draw collision box
//                    player.velocity.x = 0f;
//                    player.velocity.y = 0f;
//                    shapeRenderer.end();
//                    return;
//                }
//            }
//        }
//        shapeRenderer.end();
//    }
}

