package Entity;

import Core.Board;
import java.awt.Color;
import java.util.Random;

public class Enemy extends Entity implements Runnable {

    private boolean running = false;
    private transient Thread thread;
    
    public Enemy(Board board) {
        //Cria um inimigo com coordenadas aleatorias dentro da board
        super(new Random().nextInt((20)), new Random().nextInt((20)), Color.RED, "/Graphics/enemy.png",board);
        //enquanto as coordenadas do inimigo forem as mesmas de um objeto solido
        while (board.getBlocksItem(x, y).isSolid()) {
            if (x == board.getBlocksItem(x, y).getX()){
                //nova coordenada x
                x = new Random().nextInt((20));
            }
            if (board.getBlocksItem(x, y).getY() == y) { 
                //nova coordenada y
                y = new Random().nextInt((20));
            }
        }
        //Inicia o inimigo
        start();
    }

    @Override
    public boolean isDestructible() {
        return true;
    }

    @Override
    public boolean isSolid() {
        return true;
    }
    
    //Move o inimigo de forma a perseguir o jogador
    public void move() {
        //Se o a coordenada y do inimigo for menor que a do jogador
        //significa que o inimigo esta acima do jogador
        if (y < board.getPlayer().getY()) {
            //Incrementa a coordenada y de forma que o inimigo desca uma posicao
            down();
        } else if (y > board.getPlayer().getY()) {
            up();
        } else if (x < board.getPlayer().getX()) {
            right();
        } else if (x > board.getPlayer().getX()) {
            left();
        }
        //Corre este metodo indefinidamente
        run();
    }
    
    //Inicia o inimigo
    public synchronized void start() {
        running = true; //ao inicar o jogo indicar que o jogo está a correr
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        while (running) {
            try {
                Thread.sleep(1000);
                move();
            } catch (InterruptedException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
