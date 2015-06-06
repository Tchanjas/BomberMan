/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import static UI.Game.board;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Clara
 */
public class FrmEndGame extends JFrame {

    JPanel msg;
    JLabel lblMsg;

    public FrmEndGame(String title) {
        this.msg = new JPanel();
        lblMsg = new JLabel("Your points: " + board.getPoints());

        this.msg.add(lblMsg);
        this.add(msg);

        this.setTitle(title);
        this.setPreferredSize(new Dimension(400, 150));
        this.pack();
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setVisible(true);
    }

}
