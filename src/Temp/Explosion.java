package Temp;

import Block.Brick;
import Block.Floor;
import Core.Board;
import Entity.Enemy;
import java.awt.Color;
import java.awt.Graphics;

public class Explosion extends Temporary {

    //Verificador se atingiu um brick
    private boolean hitBrick;

    public Explosion(int x, int y, Board board) {
        super(x, y, 1000, "/Graphics/fire.png", board);

        //Se atingiu um brick
        if (board.getBlocksItem(x, y) instanceof Brick) {
            hitBrick = true;
            //Decrementa o nr de bricks que faltam na board
            board.removeBricksRemaining();
        }
        // remove enemy e nasce outro
        // jogador ganha 1 bomba
        if (x == board.getEnemy().getX() && y == board.getEnemy().getY()) {
            //Adiciona o novo inimigo na board
            board.setEnemy(new Enemy(board));
            //Impede que o jogador tenha mais de 20 bombas
            if (board.getPlayer().getNumBombs() + 1 > 20) {
                board.getPlayer().setNumBombs(20);
            } else {
                //Ao matar um inimigo, incrementa o nr de bombas
                board.getPlayer().setNumBombs(board.getPlayer().getNumBombs() + 1);
            }
        }
        //Se o jogador tiver as mesmas coordenadas da explosao
        if (x == board.getPlayer().getX() && y == board.getPlayer().getY()) {
            //Jogador perde todas as suas vidas
            board.getPlayer().setLifes(0);
        }
    }

    @Override
    public void isDone() {
        //Se atingir um brick
        if (hitBrick) {
            //Jogador ganha 5 pontos
            board.setPoints(board.getPoints() + 5);
            //Desenha um floor no lugar do brick
            board.setDrawable(new Floor(x, y));
            //Gera um nr aleatorio entre [0,1[ de modo a o brick largue um
            //powerup ou nao
            //50% de probabilidade de dropar um powerup
            if (Math.random() >= 0.5) {
                //50% de probabilidade de dropar um powerup que incremente o 
                //nr de bombas
                if (Math.random() >= 0.5) {
                    board.setDrawable(new PowerupBomb(x, y, 5000, board));
                } else {
                    //50% de probabilidade de dropar um powerup que incremente o 
                    //raio da bomba
                    board.setDrawable(new PowerupRadius(x, y, 5000, board));
                }
            }
        } else {
            board.setDrawable(new Floor(x, y));
        }
    }

    @Override
    public void draw(Graphics gr) {
        if (image != null) {
            super.draw(gr);
        } else {
            gr.setColor(Color.red);
            gr.fillOval(x * size, y * size, size, size);
        }
    }

    @Override
    public boolean isDestructible() {
        return false;
    }

    @Override
    public boolean isSolid() {
        return false;
    }

}
