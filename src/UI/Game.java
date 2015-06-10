package UI;

import Core.Board;
import static UI.Menu.btLoad;
import static UI.Menu.btQuit;
import static UI.Menu.btSave;
import static UI.Menu.btStart;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Game extends JFrame implements Runnable {

    private static Board board;
    private Menu menu;
    private static int fps = 30;

    public Game() {
        pack();
        setTitle("BomberMan");
        setSize(20 * 20 + 3, 20 * 21 + 6 + 90);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setLocationRelativeTo(null);
        board = new Board();
        board.setBorder(BorderFactory.createEmptyBorder(0, 0, 20 * 20 + 3, 20 * 21 + 6));
        board.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                boardKeyPressed(evt);
            }
        });
        getContentPane().add(board);
        board.setVisible(true);
        board.requestFocus();

        menu = new Menu();
        menu.setLocation(0, board.getHeight());
        menu.setBorder(BorderFactory.createEmptyBorder(0, 0, 90, 0));

        btStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                board.cleanBoard();
                board.buildLevel();
                board.start();
                board.requestFocus();
            }
        });

        btQuit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                System.exit(0);
            }
        });

        btSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                saveGame();
                board.requestFocus();
            }
        });

        btLoad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                loadGame();
                board.cleanBoard();
                board.buildLevel();
                board.start();
                board.requestFocus();
            }
        });

        getContentPane().add(menu);
        menu.setVisible(true);

        this.revalidate();
        this.repaint();
        new Thread(this).start();
    }

    private void boardKeyPressed(java.awt.event.KeyEvent evt) {
        board.processKey(evt.getKeyCode());
    }

    public static void saveGame() {
        try {
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
            FileOutputStream output = new FileOutputStream(timeStamp + ".dat");
            ObjectOutputStream objSave = new ObjectOutputStream(output);
            board.saveMatrixBoard();
            objSave.writeObject(board.getMatrixBoard());
            objSave.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.out.println("Error saving");
        }
    }

    public static void loadGame() {
        try {
            String path = new String();
            JFileChooser chooser = new JFileChooser();

            chooser.setCurrentDirectory(new java.io.File("."));
            chooser.setDialogTitle("Select save to load");

            if (chooser.showOpenDialog(chooser) == JFileChooser.APPROVE_OPTION) {
                path = chooser.getSelectedFile().getAbsolutePath();
            }

            FileInputStream input = new FileInputStream(path);
            ObjectInputStream inputSave = new ObjectInputStream(input);

            board.setMatrixBoard((Object[]) inputSave.readObject());
            inputSave.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Error loading");
        }
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.setVisible(true);
    }

    public void update() {
        menu.label_points.setText("Points: " + board.getPoints());
        menu.label_lifes.setText("Lifes: " + board.getPlayer().getLifes());
        menu.label_numBombs.setText("Bombs: " + board.getPlayer().getNumBombs());
        menu.repaint();

        if (board.getPlayer().getLifes() == 0 && board.isRunning()) {
            board.stop();
            JOptionPane.showMessageDialog(this, "Shame. You lost!\nYour score was " + board.getPoints() + " points",
                    "You lost!", JOptionPane.PLAIN_MESSAGE);
            board.cleanBoard();
            repaint();
        }
        if (board.getArrBricks().isEmpty() && board.isRunning()) {
            board.stop();
            JOptionPane.showMessageDialog(this, "Congratulations. You won!\nYour score was " + board.getPoints() + " points",
                    "You won!", JOptionPane.PLAIN_MESSAGE);
            board.cleanBoard();
            repaint();
        }
    }

    @Override
    public void run() {
        while (true) {
            update();
            try {
                Thread.sleep(1000 / board.fps);
            } catch (InterruptedException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
