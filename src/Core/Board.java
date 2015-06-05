package Core;

import Block.Wall;
import Block.Floor;
import Block.Brick;
import java.awt.Graphics;

public class Board {

    private Drawable[][] matrix;

    public Board() {
        matrix = new Drawable[20][20];
        cleanBoard();
        buildLevel();
    }

    public void cleanBoard() {
        for (int y = 0; y < 20; y++) {
            for (int x = 0; x < 20; x++) {
                if (x == 0 || y == 0 || x == matrix.length - 1 || y == matrix.length - 1) {
                    matrix[y][x] = new Wall(x, y);
                } else {
                    matrix[y][x] = new Floor(x, y);
                }
            }
        }
    }

    public void buildLevel() {
        setDrawable(new Brick(0, 7));
        setDrawable(new Brick(7, 0));
        setDrawable(new Brick(1, 1));
        setDrawable(new Brick(1, 2));
        setDrawable(new Brick(7, 7));
        setDrawable(new Brick(19, 15));
        setDrawable(new Brick(17, 19));
    }

    public void setDrawable(Drawable lm) {
        matrix[lm.y][lm.x] = lm;
    }

    public void draw(Graphics gr) {
        for (int y = 0; y < 20; y++) {
            for (int x = 0; x < 20; x++) {
                matrix[y][x].draw(gr);
            }
        }
    }

    public Drawable get(int x, int y) {
        return matrix[y][x];
    }

    public Drawable[][] getMatrix() {
        return matrix;
    }
}
