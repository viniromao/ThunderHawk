package co.neo32.deepdivetreasures.components;


public class Map {

    public int width;
    public int height;

    public Integer tileSize = 32;


    public boolean[][] coordinates = new boolean[height][width];

    public Map() {
        //height is 3*viewport, that is equal 1344, if one tile is 8x8 the height length is 168
        // width is 2*viewport, that is equal 1280, if one tile is 8x8 the width length is 160
        coordinates = new boolean[][]{
                {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
                {false, false, false, false, false, false, false, false, false, false, false, false, true, true, false, true, false, false, false},
                {false, false, false, false, false, false, false, false, false, false, false, true, true, true, true, true, true, true, false},
                {false, false, false, false, false, true, false, false, false, false, false, true, true, false, false, false, false, false, false},
                {false, false, false, false, false, true, true, false, false, false, false, true, true, true, false, false, false, false, false},
                {false, false, false, false, false, true, true, true, false, false, false, true, true, true, true, true, true, true, false},
                {false, false, false, false, false, true, true, false, false, false, false, true, true, true, true, true, true, true, false},
                {false, false, false, false, true, true, true, true, false, false, false, true, true, true, true, true, true, false, false},
                {false, false, false, false, true, true, true, false, false, false, false, true, true, true, true, true, true, false, false},
                {false, false, false, false, false, false, false, false, false, false, false, true, true, true, true, false, false, false, false},
                {false, false, false, false, false, false, false, false, false, false, false, true, true, true, true, false, false, false, false},
                {false, false, false, false, true, true, false, false, false, false, false, true, true, true, true, true, true, false, false},
                {false, false, false, false, true, true, true, false, false, false, false, true, true, true, true, true, true, false, false},
                {false, false, false, false, true, true, true, true, false, false, false, false, false, true, true, true, false, false, false},
                {false, false, false, false, true, true, false, false, false, false, false, false, false, true, true, false, false, false, false},
                {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},

        };

        this.width = coordinates[0].length;
        this.height =  coordinates.length;
    }
}
