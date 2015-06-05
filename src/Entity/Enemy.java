package Entity;

import Core.Board;
import java.awt.Color;

public class Enemy extends Entity implements Runnable {

    Color color;

    public Enemy(int x, int y, Board board) {
        super(x, y, Color.RED, board);
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
