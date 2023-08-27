package co.neo32.deepdivetreasures.components;


public class Map {

    public int width;
    public int height;

    public Integer tileSize = 32;


    public boolean[][] coordinates;

    public Map() {
        coordinates = new boolean[][]{
                {true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true},
                {true, true, false, false, false, false, false, false, false, false, false, false, false, false, false, true, true, true, true, false, false, false, false, false, false, false, false, false, false, false, false, false, true, true, true, true, true},
                {true, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true, true},
                {true, false, false, false, false, true, false, false, false, false, false, true, true, false, false, false, false, false, true, false, false, false, false, false, false, false, false, true, false, false, false, false, false, false, false, true, true},
                {true, false, false, false, false, true, true, false, false, false, false, true, true, true, false, false, false, false, true, false, false, false, false, false, true, false, true, true, false, false, false, true, true, false, false, true, true},
                {true, false, false, false, false, true, true, true, false, false, false, true, true, true, true, true, true, true, true, false, false, false, false, false, true, true, true, true, false, false, false, false, true, true, false, false, true},
                {true, false, false, false, false, true, true, false, false, false, false, false, true, true, true, true, true, true, false, false, false, true, true, true, true, true, false, false, false, false, false, false, false, true, true, false, true},
                {true, false, false, false, true, true, true, true, false, false, false, false, true, true, true, true, true, false, false, false, false, true, true, false, false, false, false, false, false, false, false, false, false, false, true, true, true},
                {true, false, false, false, true, true, true, false, false, false, false, true, true, true, true, true, true, false, false, false, false, true, true, false, false, false, false, false, false, false, false, false, false, false, false, true, true},
                {true, false, false, false, false, false, false, false, false, false, false, false, false, true, true, false, false, false, false, true, true, true, false, false, false, false, false, false, true, false, false, false, false, false, false, true, true},
                {true, false, false, false, false, false, false, false, false, false, false, false, false, true, true, false, false, false, false, true, true, false, false, false, false, false, false, true, true, true, false, false, false, false, false, false, true},
                {true, false, false, false, true, true, false, false, false, false, false, true, true, true, true, true, true, false, false, false, false, false, false, false, true, false, false, false, true, true, false, true, true, false, false, false, true},
                {true, false, false, false, true, true, true, false, false, false, false, false, true, true, true, true, true, false, false, false, false, false, false, false, true, false, false, false, true, true, true, true, true, true, false, false, true},
                {true, true, false, false, true, true, true, true, false, false, false, false, false, true, true, true, false, false, false, false, false, false, false, false, true, true, false, false, false, false, true, false, false, true, false, false, true},
                {true, true, false, true, true, true, false, false, false, false, false, false, false, true, true, false, false, false, false, false, false, false, false, true, true, true, false, false, false, false, true, false, false, true, false, false, true},
                {true, true, true, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true, true, false, false, false, false, false, false, false, false, true, true, false, false, true},
                {true, true, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true, false, false, false, false, false, false, false, false, false, true, true, false, false, true},
                {true, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true, true, false, true},
                {true, false, false, false, false, true, true, false, false, false, false, false, false, false, false, false, false, true, true, false, false, false, false, false, false, false, false, true, false, false, false, false, false, true, true, true, true},
                {true, false, false, false, false, true, true, false, false, false, false, false, false, false, false, false, false, false, true, true, false, false, false, false, false, false, false, true, false, false, false, false, false, false, false, false, true},
                {true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true, true, true, false, false, false, false, false, true, true, false, false, false, false, false, false, false, false, true},
                {true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true, false, false, false, false, false, false, true, false, false, false, false, false, false, false, false, false, true},
                {true, false, false, false, false, false, false, true, true, false, false, false, false, false, false, false, false, false, true, true, false, false, false, false, false, false, true, true, true, true, true, false, false, false, false, false, true},
                {true, false, false, false, false, false, false, true, false, false, false, false, false, false, false, false, false, false, true, true, false, false, false, false, false, false, false, false, true, true, true, false, true, true, false, false, true},
                {true, true, true, false, false, false, false, false, false, false, false, true, true, false, false, false, true, true, true, true, false, false, false, false, false, false, false, false, false, false, false, true, true, true, false, false, true},
                {true, true, true, false, false, false, false, false, false, false, true, true, false, false, false, false, true, true, true, false, false, false, false, false, false, false, false, false, false, false, false, true, true, false, false, false, true},
                {true, true, false, false, false, false, false, true, true, false, true, false, false, false, false, false, false, true, true, false, false, false, false, false, false, false, false, false, false, false, false, false, true, false, false, false, true},
                {true, true, false, false, false, false, false, true, true, true, true, false, false, false, false, false, false, true, true, false, false, false, false, false, true, true, false, false, false, false, false, false, false, false, false, false, true},
                {true, false, false, false, false, false, false, false, false, true, true, false, false, false, false, false, false, false, false, false, false, false, false, true, true, false, false, false, false, false, false, false, false, false, false, false, true},
                {true, false, false, false, false, false, false, false, false, true, true, false, false, false, false, false, false, false, false, false, false, false, false, true, false, false, false, false, false, false, false, false, false, false, false, false, true},
                {true, false, false, false, false, false, false, false, false, false, true, true, false, false, false, false, false, false, false, false, false, false, true, true, false, false, false, false, false, false, false, false, false, false, false, false, true},
                {true, false, false, false, false, false, false, false, false, false, false, true, true, false, false, false, false, false, false, false, false, false, false, true, true, true, true, true, false, false, false, false, false, false, false, false, true},
                {true, false, false, false, false, false, false, false, false, false, false, false, true, false, false, false, false, false, false, false, false, false, false, false, true, true, true, true, true, false, false, false, false, false, false, false, true},
                {true, false, false, false, false, false, false, false, false, false, false, false, true, false, false, false, false, false, false, false, false, false, false, false, true, true, true, true, true, false, false, false, false, false, false, false, true},

        };

        this.width = coordinates[0].length;
        this.height =  coordinates.length;
    }

    public void shallowWater() {
        coordinates = new boolean[][]{
                {true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
                {true, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true, true, true, true, true},
                {true, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true, true},
                {true, false, false, false, false, true, false, false, false, false, false, true, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true, false, false, false, false, false, false, false, true, true},
                {true, false, false, false, false, true, true, false, false, false, false, true, false, false, false, false, false, false, false, false, false, false, false, false, true, false, true, true, false, false, false, true, true, false, false, true, true},
                {true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true, true, true, true, false, false, false, false, true, true, false, false, true},
                {true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true, true, true, true, true, false, false, false, false, false, false, false, true, true, false, true},
        };

        this.width = coordinates[0].length;
        this.height =  coordinates.length;
    }
}
