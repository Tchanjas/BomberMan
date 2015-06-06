package Core;

import Block.Brick;
import Block.Wall;
import Block.Floor;
import Entity.Enemy;
import Entity.Player;
import java.awt.Graphics;
import java.io.Serializable;
import java.util.ArrayList;

public class Board implements Serializable {

    private Drawable[][] matrix;
    private int points;
    private ArrayList<Brick> arrBricks = new ArrayList<>();
    private Player player;
    private Enemy enemy;

    public Board() {
        matrix = new Drawable[20][20];
        cleanBoard();
        buildLevel();
        player = new Player(1, 1, this);
        enemy = new Enemy(this);
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

        for (int i = 2; i < 19; i++) {
            for (int j = 2; j < 19; j++) {
                if (i % 2 == 0 && j % 2 == 0) {
                    arrBricks.add(new Brick(i, j));
                    setDrawable(new Brick(i, j));
                }
            }

        }
    }

    public ArrayList<Brick> getArrBricks() {
        return arrBricks;
    }

    public void removeArrBricksItem() {
        arrBricks.remove(0);
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

    public Drawable[][] getMatrix() {
        return matrix;
    }

    public Drawable getMatrixItem(int x, int y) {
        return matrix[y][x];
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Enemy getEnemy() {
        return enemy;
    }

    public void setEnemy(Enemy enemy) {
        this.enemy = enemy;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
