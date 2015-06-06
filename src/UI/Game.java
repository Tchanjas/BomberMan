package UI;

import Block.Floor;
import Core.Board;
import Temp.Bomb;
import Temp.PowerupBomb;
import Temp.PowerupRadius;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;

public class Game extends JPanel implements Runnable {

    public static Board board;

    static int fps = 30;

    public Game() {
        board = new Board();
        new Thread(this).start();
    }

    @Override
    public void paintComponent(Graphics gr) {
        board.draw(gr);
        board.getPlayer().draw(gr);
        board.getEnemy().draw(gr);
    }

    public void processKey(int key) {
        switch (key) {
            case KeyEvent.VK_DOWN:
                board.getPlayer().down();
                break;
            case KeyEvent.VK_UP:
                board.getPlayer().up();
                break;
            case KeyEvent.VK_LEFT:
                board.getPlayer().left();
                break;
            case KeyEvent.VK_RIGHT:
                board.getPlayer().right();
                break;
            case KeyEvent.VK_SPACE:
                board.getPlayer().putBomb();
                break;
        }
        
        repaint();
    }
    
    public void update() {
        if(board.getPlayer().getX() == board.getEnemy().getX() && board.getPlayer().getY() == board.getEnemy().getY()){
            if (board.getPlayer().getLifes() != 0) {
                board.getPlayer().setLifes(board.getPlayer().getLifes() - 1);
                board.getPlayer().setX(1);
                board.getPlayer().setY(1);
                board.setPoints(board.getPoints() - 5);
            }
        }
        if (board.getMatrixItem(board.getPlayer().getX(), board.getPlayer().getY()) instanceof PowerupBomb) {
            board.getPlayer().increaseBombs();
            board.setDrawable(new Floor(board.getPlayer().getX(), board.getPlayer().getY()));
        }
        if (board.getMatrixItem(board.getPlayer().getX(), board.getPlayer().getY()) instanceof PowerupRadius) {
            Bomb.setExpRadius(1);
            board.setDrawable(new Floor(board.getPlayer().getX(), board.getPlayer().getY()));
        }
    }

    @Override
    public void run() {
        while (true) {
            update();
            repaint();
            try {
                Thread.sleep(1000 / fps);
            } catch (InterruptedException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
