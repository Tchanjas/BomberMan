package UI;

import java.awt.GridBagConstraints;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Menu extends JPanel {

    JLabel label_points, label_lifes, label_numBombs, label_Controls;
    static JButton btStart, btQuit, btSave, btLoad;

    public Menu() {
        label_points = new JLabel("Points: ");
        label_lifes = new JLabel("Lifes: ");
        label_numBombs = new JLabel("Bombs: ");
        label_Controls = new JLabel("Controls: SpaceBar and Arrow Keys");

        btStart = new JButton("Start");
        btQuit = new JButton("Quit");
        btSave = new JButton("Save");
        btLoad = new JButton("Load");

        setLayout(new java.awt.GridBagLayout());
        
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        
        c.gridx = 0;
        c.gridy = 0;
        add(label_points, c);
        
        c.gridx = 1;
        c.gridy = 0;
        add(btStart, c);
        
        c.gridx = 2;
        c.gridy = 0;
        add(btQuit, c);
        
        c.gridx = 0;
        c.gridy = 1;
        add(label_lifes, c);
        
        c.gridx = 1;
        c.gridy = 1;
        add(btSave, c);
        
        c.gridx = 2;
        c.gridy = 1;
        add(btLoad, c);
        
        c.gridx = 0;
        c.gridy = 2;
        add(label_numBombs, c);
        
        c.gridx = 2;
        c.gridy = 2;
        
        add(label_Controls, c);
    }
}
