package Core;

import Block.Brick;
import Block.Wall;
import Block.Floor;
import Entity.Enemy;
import Entity.Player;
import Temp.Bomb;
import Temp.PowerupBomb;
import Temp.PowerupRadius;
import UI.FrmEndGame;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Objects;
import javax.swing.JPanel;

public class Board extends JPanel implements Runnable {

    private Drawable[][] blocks;
    private Drawable[][][] matrixBoard;
    private int points;
    private ArrayList<Brick> arrBricks = new ArrayList<>();
    private Player player;
    private Enemy enemy;
    private Thread thread;
    private boolean running = false; //indicar se o programa está a correr ou não
    public static final int fps = 30;

    public Board() {
        blocks = new Drawable[20][20];
        cleanBoard();
        buildLevel();
    }

    @Override
    public void paintComponent(Graphics gr) {
        this.draw(gr);
        player.draw(gr);
        enemy.draw(gr);
    }

    public void processKey(int key) {
        if (running) {
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
    }

    public void update() {
        if (player.getX() == enemy.getX() && player.getY() == enemy.getY()) {
            if (player.getLifes() != 0) {
                player.setLifes(player.getLifes() - 1);
                player.setX(1);
                player.setY(1);
                setPoints(getPoints() - 5);
            }
        }
        if (getBlocksItem(player.getX(), player.getY()) instanceof PowerupBomb) {
            player.increaseBombs();
            setDrawable(new Floor(player.getX(), player.getY()));
        }
        if (getBlocksItem(player.getX(), player.getY()) instanceof PowerupRadius) {
            Bomb.setExpRadius(1);
            setDrawable(new Floor(player.getX(), player.getY()));
        }
        if (player.getLifes() == 0) {
            cleanBoard();
            new FrmEndGame("Shame. You lost");
            repaint();
            stop();
        }
        if (getArrBricks().isEmpty()) {
            cleanBoard();
            new FrmEndGame("Congratulations. You won.");
            repaint();
            stop();
        }
    }

    /**
     * Inciar a thread
     */
    public synchronized void start() {
        running = true; //ao inicar o jogo indicar que o jogo está a correr
        thread = new Thread(this);
        thread.start();
    }

    /**
     * Parar a thread
     */
    public synchronized void stop() {
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (running) {
            update();
            repaint();
            try {
                Thread.sleep(1000 / fps);
            } catch (InterruptedException ex) {
                System.out.println(ex.getMessage());
            }
        }
        stop();
    }

    public void cleanBoard() {
        for (int y = 0; y < 20; y++) {
            for (int x = 0; x < 20; x++) {
                if (x == 0 || y == 0 || x == blocks.length - 1 || y == blocks.length - 1) {
                    blocks[y][x] = new Wall(x, y);
                } else {
                    blocks[y][x] = new Floor(x, y);
                }
            }
        }

    }

    public void buildLevel() {
        if (matrixBoard == null) {
            for (int i = 2; i < 19; i++) {
                for (int j = 2; j < 19; j++) {
                    if (i % 2 == 0 && j % 2 == 0) {
                        arrBricks.add(new Brick(i, j));
                        setDrawable(new Brick(i, j));
                    }
                }

            }
            player = new Player(1, 1, this);
        }
        else {
            blocks = matrixBoard[0];
            for (int i = 0; i < matrixBoard[1].length; i++) {
                for (int j = 0; j < matrixBoard[1][0].length; j++) {
                    if (matrixBoard[1][i][j].getClass().equals(player)) {
                        player = new Player(i, j, this);
                    }                    
                }
            }
        }
        enemy = new Enemy(this);
    }

    public ArrayList<Brick> getArrBricks() {
        return arrBricks;
    }

    public void removeArrBricksItem() {
        arrBricks.remove(0);
    }

    public void setDrawable(Drawable lm) {
        blocks[lm.y][lm.x] = lm;
    }

    public void draw(Graphics gr) {
        for (int y = 0; y < 20; y++) {
            for (int x = 0; x < 20; x++) {
                blocks[y][x].draw(gr);
            }
        }
    }

    public Drawable[][] getBlocks() {
        return blocks;
    }

    public Drawable getBlocksItem(int x, int y) {
        return blocks[y][x];
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Enemy getEnemy() {
        return enemy;
    }

    public void setEnemy(Enemy enemy) {
        this.enemy = enemy;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public Drawable[][][] getMatrixBoard() {
        return matrixBoard;
    }

    public void saveMatrixBoard() {
        matrixBoard = new Drawable[2][20][20];
        matrixBoard[0] = blocks;
        matrixBoard[1][player.getX()][player.getY()] = player;
    }
}
