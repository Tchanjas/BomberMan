package Entity;

import Core.Board;
import java.awt.Color;
import java.util.Random;

public class Enemy extends Entity implements Runnable {

    Color color;
    int enemyRandX = new Random().nextInt((20));
    int enemyRandY = new Random().nextInt((20));

    public Enemy(int x, int y, Board board) {
        super(x, y, Color.RED, board);
        while (board.getMatrixItem(enemyRandX, enemyRandY).isSolid()) {
            if(board.getMatrixItem(enemyRandX, enemyRandY).getX() == enemyRandX) {
                enemyRandX = new Random().nextInt((20));
            }
            if(board.getMatrixItem(enemyRandX, enemyRandY).getY() == enemyRandY) {
                enemyRandY = new Random().nextInt((20));
            }
        }
        this.x = enemyRandX;
        this.y = enemyRandY;
        new Thread(this).start();
    }

    @Override
    public boolean isDestructible() {
        return true;
    }

    @Override
    public boolean isSolid() {
        return true;
    }

    private void move() {
        if (y < board.getPlayer().getY()) {
            down();
        } else if (y > board.getPlayer().getY()) {
            up();
        } else if (x < board.getPlayer().getX()) {
            right();
        } else if (x > board.getPlayer().getX()) {
            left();
        } else if (x == board.getPlayer().getX() && y == board.getPlayer().getY()) {
            System.out.println(board.getPlayer().getLifes());
            board.getPlayer().setLifes(board.getPlayer().getLifes() - 1);
        }
        run();
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
            move();
        } catch (InterruptedException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
