package Entity;

import Core.Board;
import java.awt.Color;
import java.util.Random;

public class Enemy extends Entity implements Runnable {

    private boolean running = false;
    private transient Thread thread;
    
    public Enemy(Board board) {
        super(new Random().nextInt((20)), new Random().nextInt((20)), Color.RED, "/Graphics/enemy.png",board);
        while (board.getBlocksItem(x, y).isSolid()) {
            if (x == board.getBlocksItem(x, y).getX()) {
                x = new Random().nextInt((20));
            }
            if (board.getBlocksItem(x, y).getY() == y) {
                y = new Random().nextInt((20));
            }
        }
        start();
    }

    @Override
    public boolean isDestructible() {
        return true;
    }

    @Override
    public boolean isSolid() {
        return true;
    }
    
    public void move() {
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
    
    public synchronized void start() {
        running = true; //ao inicar o jogo indicar que o jogo está a correr
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        while (running) {
            try {
                Thread.sleep(750);
                move();
            } catch (InterruptedException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
