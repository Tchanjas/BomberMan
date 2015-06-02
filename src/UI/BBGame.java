/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Core.Board;
import Core.Player;
import Core.Enemy;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;

/**
 *
 * @author l0cust
 */
public class BBGame extends JPanel implements Runnable{

    Board board;
    Player player;
    Enemy enemy;
  
    static int fps = 60;

    public BBGame() {
        board = new Board();
        player = new Player(16, 16, board);
        enemy = new Enemy(3, 3, board);
        new Thread(this).start();
    }

    @Override
    public void paintComponent(Graphics gr) {
        board.draw(gr);
        player.draw(gr);
        enemy.draw(gr);
    }

    public void processKey(int key) {
        switch (key) {
            case KeyEvent.VK_DOWN:
                player.down();
                break;
            case KeyEvent.VK_UP:
                player.up();
                break;
            case KeyEvent.VK_LEFT:
                player.left();
                break;
            case KeyEvent.VK_RIGHT:
                player.right();
                break;
            case KeyEvent.VK_SPACE:
                player.putBomb();
                break;
        }
        repaint();
    }

    @Override
    public void run() {
        while(true){
            repaint();
            try {
                Thread.sleep(1000/fps);
            } catch (InterruptedException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

}
