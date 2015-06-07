package Core;

import Block.Brick;
import Block.Wall;
import Block.Floor;
import Entity.Enemy;
import Entity.Player;
import Temp.Bomb;
import Temp.PowerupBomb;
import Temp.PowerupRadius;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.JPanel;

public class Board extends JPanel implements Runnable, Serializable {

    private Drawable[][] blocks;
    private Object[] matrixBoard;
    private int points;
    private ArrayList<Brick> arrBricks = new ArrayList<>();
    private Player player;
    private Enemy enemy;
    private transient Thread thread;
    private boolean running = false; //indicar se o programa está a correr ou não
    public static final int fps = 30;

    public Board() {
        blocks = new Drawable[20][20];
        matrixBoard = new Object[7];
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
    }

    /**
     * Inciar a thread
     */
    public synchronized void start() {
        running = true; //ao inicar o jogo indicar que o jogo está a correr
        thread = new Thread(this);
        thread.start();
        enemy.start();
    }

    /**
     * Parar a thread
     */
    public synchronized void stop() {
        running = false;
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
        arrBricks.removeAll(arrBricks);
        points = 0;
    }

    public void buildLevel() {
        if (matrixBoard[0] == null) {
            for (int i = 2; i < 19; i++) {
                for (int j = 2; j < 19; j++) {
                    if (i % 2 == 0 && j % 2 == 0) {
                        arrBricks.add(new Brick(i, j));
                        setDrawable(new Brick(i, j));
                    }
                }
            }
            player = new Player(1, 1, this);
            enemy = new Enemy(this);
            player.setLifes(3);
            player.setNumBombs(20);
        } else {
            blocks = (Drawable[][]) matrixBoard[0];
            for (int i = 2; i < 19; i++) {
                for (int j = 2; j < 19; j++) {
                    if (i % 2 == 0 && j % 2 == 0) {
                        if (blocks[i][j].getClass().equals(Brick.class)) {
                            arrBricks.add(new Brick(i, j));
                        }
                    }
                }
            }
            player.setX((int) ((Dimension)matrixBoard[1]).getWidth());
            player.setY((int) ((Dimension)matrixBoard[1]).getHeight());

            player.setLifes((int) matrixBoard[2]);
            player.setNumBombs((int) matrixBoard[3]);
            
            enemy.setX((int) ((Dimension) matrixBoard[4]).getWidth());
            enemy.setY((int) ((Dimension) matrixBoard[4]).getHeight());
            
            points = (int) matrixBoard[5];
            Bomb.setExpRadius((int) matrixBoard[6]);
            matrixBoard[0] = null;
        }
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

    public Object[] getMatrixBoard() {
        return matrixBoard;
    }

    public void setMatrixBoard(Object[] matrixBoard) {
        this.matrixBoard = matrixBoard;
    }

    public void saveMatrixBoard() {
        matrixBoard[0] = blocks;
        matrixBoard[1] = new Dimension(player.getX(), player.getY());
        matrixBoard[2] = player.getLifes();
        matrixBoard[3] = player.getNumBombs();
        matrixBoard[4] = new Dimension(enemy.getX(), enemy.getY());
        matrixBoard[5] = points;
        matrixBoard[6] = Bomb.getExpRadius();
    }
    
    public boolean isRunning() {
        return running;
    }
}
