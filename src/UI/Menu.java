package UI;

import static UI.Game.board;
import static Core.Board.fps;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Menu extends JPanel implements Runnable {

    JLabel label_points, label_lifes;
    public static JButton btStart, btQuit, btSave, btLoad;

    public Menu() {
        label_points = new JLabel("Points: " + board.getPoints());
        label_points.setSize((int) label_points.getPreferredSize().getWidth() + 25,
                (int) label_points.getPreferredSize().getHeight());
        label_points.setLocation(15, 7);
        this.add(label_points);

        label_lifes = new JLabel("Lifes: " + board.getPlayer().getLifes());
        label_lifes.setSize((int) label_lifes.getPreferredSize().getWidth() + 25,
                (int) label_lifes.getPreferredSize().getHeight());
        label_lifes.setLocation(label_points.getX(), label_lifes.getHeight()*2 + 3);
        this.add(label_lifes);

        btStart = new JButton("Start");
        btStart.setSize((int) btStart.getPreferredSize().getWidth() + 25,
                (int) btStart.getPreferredSize().getHeight());
        btStart.setLocation(label_points.getX() + label_points.getWidth(), 0);
        this.add(btStart);

        btQuit = new JButton("Quit");
        btQuit.setSize((int) btQuit.getPreferredSize().getWidth() + 25,
                (int) btQuit.getPreferredSize().getHeight());
        btQuit.setLocation(btStart.getX(), btStart.getHeight());
        this.add(btQuit);

        btSave = new JButton("Save");
        btSave.setSize((int) btSave.getPreferredSize().getWidth() + 25,
                (int) btSave.getPreferredSize().getHeight());
        btSave.setLocation(btStart.getX() + btStart.getWidth(), 0);
        this.add(btSave);

        btLoad = new JButton("Load");
        btLoad.setSize((int) btLoad.getPreferredSize().getWidth() + 25,
                (int) btLoad.getPreferredSize().getHeight());
        btLoad.setLocation(btSave.getX(), btSave.getHeight());
        this.add(btLoad);

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
