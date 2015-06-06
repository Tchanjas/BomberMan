/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import static UI.Game.board;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Clara
 */
public class FrmEndGame extends JFrame {

    JPanel panel;
    JLabel lblMsg;
    JButton btConfirm;

    public FrmEndGame(String title) {
        panel = new JPanel();
        lblMsg = new JLabel("Your points: " + board.getPoints());
        btConfirm = new JButton("OK");
        
        btConfirm.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        this.panel.add(lblMsg);
        this.panel.add(btConfirm);
        this.add(panel);

        this.setTitle(title);
        this.setPreferredSize(new Dimension(400, 150));
        this.pack();
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setVisible(true);
    }
}
