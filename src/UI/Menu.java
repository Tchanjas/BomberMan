package UI;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Menu extends JPanel {

    JLabel label_points, label_lifes, label_numBombs;
    static JButton btStart, btQuit, btSave, btLoad;

    public Menu() {
        label_points = new JLabel("Points: ");
        label_lifes = new JLabel("Lifes: ");
        label_numBombs = new JLabel("Bombs: ");

        btStart = new JButton("Start");
        btQuit = new JButton("Quit");
        btSave = new JButton("Save");
        btLoad = new JButton("Load");

        setLayout(new java.awt.GridLayout(3, 3));

        add(label_points);
        add(btStart);
        add(btQuit);
        add(label_lifes);
        add(btSave);
        add(btLoad);
        add(label_numBombs);
    }
}
