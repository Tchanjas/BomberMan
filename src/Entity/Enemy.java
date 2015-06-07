package Entity;

import Core.Board;
import java.awt.Color;
import java.util.Random;

public class Enemy extends Entity implements Runnable {

    static int enemyRandX = new Random().nextInt((20));
    static int enemyRandY = new Random().nextInt((20));

    public Enemy(Board board) {
        super(enemyRandX, enemyRandY, Color.RED, board);
        while (board.getBlocksItem(enemyRandX, enemyRandY).isSolid()) {
            if (board.getBlocksItem(enemyRandX, enemyRandY).getX() == enemyRandX) {
                enemyRandX = new Random().nextInt((20));
            }
            if (board.getBlocksItem(enemyRandX, enemyRandY).getY() == enemyRandY) {
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
