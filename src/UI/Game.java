package UI;

import Core.Board;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;

public class Game extends JPanel implements Runnable {

    Board board;

    static int fps = 60;

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

    @Override
    public void run() {
        while (true) {
            repaint();
            try {
                Thread.sleep(1000 / fps);
            } catch (InterruptedException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
