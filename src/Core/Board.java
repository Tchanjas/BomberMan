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
import javax.swing.JPanel;

public class Board extends JPanel implements Runnable, Serializable {

    private Drawable[][] blocks;
    private Object[] matrixBoard;
    private int points;
    private int bricksRemaining;
    private Player player;
    private Enemy enemy;
    private transient Thread thread;
    private boolean running = false; //indicar se o programa está a correr ou não
    public static final int fps = 30;
    private int numLevel;

    //Construtor quer ira construir a board
    public Board() {
        blocks = new Drawable[20][20];
        matrixBoard = new Object[7];
        //Limpa a board
        cleanBoard();
        //Constroi o primeiro nivel
        buildLevel(0);
        //Inicia a thread da board
        start();
    }

    //Pinta os componentes
    @Override
    public void paintComponent(Graphics gr) {
        this.draw(gr);
        player.draw(gr);
        enemy.draw(gr);
    }

    //Processa o evento das teclas e realiza uma acao
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
            //Volta a pintar os componentes
            repaint();
        }
    }

    //Atualiza a board
    public void update() {
        //Se as coordenadas do jogador e do inimigo foram as mesmas
        if (player.getX() == enemy.getX() && player.getY() == enemy.getY()) {
            if (player.getLifes() != 0) {
                //perde uma vida
                player.setLifes(player.getLifes() - 1);
                //Volta a colocar o player na posicao inicial (1,1)
                player.setX(1);
                player.setY(1);
                //Impede que os pontos sejam negativos
                if (getPoints() - 5 < 0) {
                    setPoints(0);
                } else {
                    //Perde 5 pontos
                    setPoints(getPoints() - 5);
                }
            }
        }
        //Se o jogador tiver as mesmas coordenadas do powerup
        if (getBlocksItem(player.getX(), player.getY()) instanceof PowerupBomb) {
            //Adiciona 2 bombas
            player.increaseBombs();
            //Pinta um floor no lugar do powerup
            setDrawable(new Floor(player.getX(), player.getY()));
        }
        if (getBlocksItem(player.getX(), player.getY()) instanceof PowerupRadius) {
            //Incrementa o raio da explosao
            Bomb.setExpRadius(Bomb.getExpRadius() + 1);
            setDrawable(new Floor(player.getX(), player.getY()));
        }
        //Se ja nao existir blocos e o utilizador estiver no primeiro nivel
        if(bricksRemaining == 0 && numLevel == 0){
            //Limpa a board
            cleanBoard();
            //Volta a pintar
            repaint();
            //Carrega o segundo nivel
            buildLevel(1);
        }
    }

    /**
     * Inciar a thread
     */
    public synchronized void start() {
        running = true; //ao inicar o jogo indicar que o jogo está a correr
        thread = new Thread(this);
        thread.start();
        //Inicia o inimigo
        enemy.start();
    }

    /**
     * Para a thread
     */
    public synchronized void stop() {
        running = false;
    }

    //Corre a thread da board
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

    //Limpa a board
    public void cleanBoard() {
        //Coloca so floors e walls
        for (int y = 0; y < 20; y++) {
            for (int x = 0; x < 20; x++) {
                if (x == 0 || y == 0 || x == blocks.length - 1 || y == blocks.length - 1) {
                    blocks[y][x] = new Wall(x, y);
                } else {
                    blocks[y][x] = new Floor(x, y);
                }
            }
        }
        //Reseta os bricks, pontos e o raio das explosoes
        bricksRemaining = 0;
        points = 0;
        Bomb.setExpRadius(1);
    }

    /**
     * 
     * @param lvl - the number of the level to be loaded
     */
    public void buildLevel(int lvl) {
        numLevel = lvl;
        if (lvl == 0) {
            //Coloca os tijolos na board
            for (int i = 2; i < 19; i++) {
                for (int j = 2; j < 19; j++) {
                    if (i % 4 == 0 && j % 4 == 0) {
                        //Incrementa ao nr de tijolos a serem destruidos para
                        //passar de nivel
                        bricksRemaining++;
                        setDrawable(new Brick(i, j));
                    }
                }
            }
            //Cria um novo jogador na posicao 1,1 na board
            player = new Player(1, 1, this);
            //Novo inimigo na board
            enemy = new Enemy(this);
            //Adiciona 3 vidas ao jogador
            player.setLifes(3);
            //Adicona 20 bombas ao jogador
            player.setNumBombs(20);
        }
        else if (lvl == 1) {
            for (int i = 2; i < 19; i++) {
                for (int j = 2; j < 19; j++) {
                    if (i % 2 == 0 && j % 2 == 0) {
                        bricksRemaining++;
                        setDrawable(new Brick(i, j));
                    }
                }
            }
        }
    }
    
    //Carrega o estado definido no save
    public void loadBoard () {
        Drawable[][] m = (Drawable[][]) matrixBoard[0];
        //Repoe os bricks
        for (int i = 0; i < 19; i++) {
            for (int j = 0; j < 19; j++) {
                if (m[j][i].getClass().equals(Brick.class)) {
                    bricksRemaining++;
                    setDrawable(new Brick(i, j));
                }
            }
        }
        //Coloca o jogador na ultima posicao antes do save
        player.setX((int) ((Dimension) matrixBoard[1]).getWidth());
        player.setY((int) ((Dimension) matrixBoard[1]).getHeight());
        //Nr de vidas antes do save
        player.setLifes((int) matrixBoard[2]);
        player.setNumBombs((int) matrixBoard[3]);
        //Posicao do inimigo antes do save
        enemy.setX((int) ((Dimension) matrixBoard[4]).getWidth());
        enemy.setY((int) ((Dimension) matrixBoard[4]).getHeight());
        //Pontos antes do save
        points = (int) matrixBoard[5];
        //Raio da explosao antes do save
        Bomb.setExpRadius((int) matrixBoard[6]);
    }
    
    //Desenha os blocos
    public void draw(Graphics gr) {
        for (int y = 0; y < 20; y++) {
            for (int x = 0; x < 20; x++) {
                blocks[y][x].draw(gr);
            }
        }
    }
    
    //Setters e getters
    public void setDrawable(Drawable lm) {
        blocks[lm.y][lm.x] = lm;
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

    //Guarda todas os dados necessarios para a realizacao do save dentro de um
    //array
    public void saveMatrixBoard() {
        matrixBoard[0] = blocks;
        matrixBoard[1] = new Dimension(player.getX(), player.getY());
        matrixBoard[2] = player.getLifes();
        matrixBoard[3] = player.getNumBombs();
        matrixBoard[4] = new Dimension(enemy.getX(), enemy.getY());
        matrixBoard[5] = points;
        matrixBoard[6] = Bomb.getExpRadius();
    }
    
    public int getBricksRemaining() {
        return bricksRemaining;
    }

    //Decrementa o nr de bombas disponiveis
    public void removeBricksRemaining() {
        bricksRemaining--;
    }

    public int getNumLevel() {
        return numLevel;
    }
    
    public boolean isRunning() {
        return running;
    }
}
