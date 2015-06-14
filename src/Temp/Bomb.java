package Temp;

import Block.Brick;
import Core.Board;
import Utils.GameUtils;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Graphics;

public class Bomb extends Temporary {

    //Raio de explosao
    private static int expRadius = 1;
    //Verificadores de explosao
    private static boolean leftHit, rightHit, upHit, downHit;
    //Ficheiro que ira tocar quando ocorrer uma explosao
    AudioClip boom;

    public Bomb(int x, int y, Board board) {
        super(x, y, 2000, "/Graphics/Bomb.png", board);
        //Cria o objeto que ira tocar o ficheiro boom.wav
        boom = GameUtils.loadSound("/Audio/boom.wav");
    }

    //Quando a bomba terminar
    @Override
    public void isDone() {
        //Toca o ficheiro boom.wav
        boom.play();
        //Provoca varias explosoes ate atingirem o raio definido
        for (int i = 1; i <= expRadius; i++) {
            //Impede que raio saia da board
            if (x + 1 - i > 0) {
                //Se nao tiver tocado nada a esquerda
                if (!leftHit) {
                    //Verifica se o objeto a esquerda e destrutivel
                    if (board.getBlocksItem(x - i, y).isDestructible()) {
                        //Se for um brick
                        if (board.getBlocksItem(x - i, y) instanceof Brick) {
                            //Acertou num objeto a esquerda
                            leftHit = true;
                        }
                        //Desenha a explosao
                        board.setDrawable(new Explosion(x - i, y, board));
                    }
                }
            }
            //Mesma coisa que para a esquerda so que no sentido contrario
            if (x - 1 + i < board.getBlocks().length - 1) {
                if (!rightHit) {
                    if (board.getBlocksItem(x + i, y).isDestructible()) {
                        if (board.getBlocksItem(x + i, y) instanceof Brick) {
                            rightHit = true;
                        }
                        board.setDrawable(new Explosion(x + i, y, board));
                    }
                }
            }
            //Provoca explosao nas coordenadas da bomba
            board.setDrawable(new Explosion(x, y, board));
            //Mesma coisa que a esquerda so que para baixo
            if (y - 1 + i < board.getBlocks()[0].length - 1) {
                if (!downHit) {
                    if (board.getBlocksItem(x, y + i).isDestructible()) {
                        if (board.getBlocksItem(x, y + i) instanceof Brick) {
                            downHit = true;
                        }
                        board.setDrawable(new Explosion(x, y + i, board));
                    }
                }
            }
            //Mesma coisa que para a esquerda so que para cima
            if (y + 1 - i > 0) {
                if (!upHit) {
                    if (board.getBlocksItem(x, y - i).isDestructible()) {
                        if (board.getBlocksItem(x, y - i) instanceof Brick) {
                            upHit = true;
                        }
                        board.setDrawable(new Explosion(x, y - i, board));
                    }
                }
            }
        }
        //Reseta os verificadores
        leftHit = false;
        rightHit = false;
        upHit = false;
        downHit = false;
    }

    //Desenha os objetos
    @Override
    public void draw(Graphics gr) {
        if (image != null) {
            super.draw(gr);
        } else {
            gr.setColor(Color.BLACK);
            gr.fillOval(x * size, y * size, size, size);
        }
    }

    @Override
    public boolean isDestructible() {
        return true;
    }

    @Override
    public boolean isSolid() {
        return true;
    }

    //Getters e setters
    public static int getExpRadius() {
        return expRadius;
    }

    public static void setExpRadius(int expRadius) {
        Bomb.expRadius = expRadius;
    }
}
