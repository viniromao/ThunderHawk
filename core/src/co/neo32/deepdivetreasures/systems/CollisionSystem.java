package co.neo32.deepdivetreasures.systems;

import co.neo32.deepdivetreasures.DeepDiveTreasures;
import co.neo32.deepdivetreasures.components.Map;
import co.neo32.deepdivetreasures.entities.Chest;
import co.neo32.deepdivetreasures.entities.Shark;
import co.neo32.deepdivetreasures.entities.Submarine;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;

import java.util.Iterator;

public class CollisionSystem {
    private final Map map;

    public CollisionSystem(Map map) {
        this.map = map;
    }

    public void handleCollision(Submarine player, Group chestGroup, Group sharkGroup, DeepDiveTreasures game) {
        float nextX = player.position.x + player.velocity.x / 8;
        float nextY = player.position.y + player.velocity.y / 8;

        if (!game.shalow) {
            Iterator<Actor> iterator = chestGroup.getChildren().iterator();
            while (iterator.hasNext()) {
                Chest chest = (Chest) iterator.next();

                if (player.boundingRectangle.overlaps(chest.boundingRectangle)) {
                    iterator.remove();
                    game.money += 200;
                    game.getChest.play();
                    chest.remove();
                }
            }


            iterator = sharkGroup.getChildren().iterator();
            while (iterator.hasNext()) {
                Shark shark = (Shark) iterator.next();

                if (player.boundingRectangle.overlaps(shark.boundingRectangle)) {
                    game.gameOver();
                }
            }

        }


        // Check collisions in the X direction
        int startI = (int) (player.position.y / map.tileSize);
        int endI = (int) ((player.position.y + player.size.y) / map.tileSize);
        int startJ = (int) (nextX / map.tileSize);
        int endJ = (int) ((nextX + player.size.x) / map.tileSize);

        for (int i = startI; i <= endI; i++) {
            for (int j = startJ; j <= endJ; j++) {
                if (i >= 0 && i < map.height && j >= 0 && j < map.width && map.coordinates[i][j]) {
                    player.velocity.x = 0f; // Stop horizontal movement
                }
            }
        }

        // Check collisions in the Y direction
        startI = (int) (nextY / map.tileSize);
        endI = (int) ((nextY + player.size.y) / map.tileSize);
        startJ = (int) (player.position.x / map.tileSize);
        endJ = (int) ((player.position.x + player.size.y) / map.tileSize);

        for (int i = startI; i <= endI; i++) {
            for (int j = startJ; j <= endJ; j++) {
                if (i >= 0 && i < map.height && j >= 0 && j < map.width && map.coordinates[i][j]) {
                    player.velocity.y = 0f; // Stop vertical movement
                }
            }
        }
    }

}

