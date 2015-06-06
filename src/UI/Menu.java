package UI;

import static UI.Game.board;
import static UI.Game.fps;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Menu extends JPanel implements Runnable {
    
    JLabel label_points, label_lifes;

    public Menu() {
        label_points = new JLabel("Points: " + board.getPoints());
        label_points.setSize((int) label_points.getPreferredSize().getWidth() + 25,
                (int) label_points.getPreferredSize().getHeight());
        this.add(label_points);
        
        label_lifes = new JLabel("Lifes: " + board.getPlayer().getLifes());
        label_lifes.setSize((int) label_lifes.getPreferredSize().getWidth() + 25,
                (int) label_lifes.getPreferredSize().getHeight());
        label_lifes.setLocation(0, 25);
        this.add(label_lifes);
        
        this.revalidate();
        this.repaint();
        new Thread(this).start();
    }
    
    public void update() {
        label_points.setText("Points: " + board.getPoints());
        label_lifes.setText("Lifes: " + board.getPlayer().getLifes());
    }

    @Override
    public void run() {
        while (true) {
            update();
            try {
                Thread.sleep(1000 / fps);
            } catch (InterruptedException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

}
