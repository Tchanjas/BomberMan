package Entity;

import Core.Board;
import Core.Drawable;
import java.awt.Color;
import java.awt.Graphics;

public abstract class Entity extends Drawable {

    //Cor caso a img seja null
    Color color;
    //Board onde colocar as entidades
    Board board;

    public Entity(int x, int y, Color color, String imgFile, Board board) {
        super(x, y, imgFile);
        this.color = color;
        this.board = board;
    }

    public void draw(Graphics gr) {
        if (image != null) {
            super.draw(gr);
        }else {
            gr.setColor(color);
            gr.fillArc(x * size, y * size, size, size, 45, 270);
        }
    }

    //Move o objeto para cima
    public void up() {
        //Impede que objeto saia pelo topo da board
        if (y != 0) {
            //Se o objeto que estiver em cima nao for solido
            if (!board.getBlocksItem(x, y - 1).isSolid()) {
                //Decrementa a coordenada y do objeto de forma a deslocar-se
                //para cima
                y--;
            }
        }
    }

    //Move o objeto para a esquerda
    public void left() {
        //Impede que saia pela esquerda da board
        if (x != 0) {
            //Se o objeto a esquerda nao for solido
            if (!board.getBlocksItem(x - 1, y).isSolid()) {
                //Decrementa a coordenada x do objeto de forma a deslocar-se
                //para a esquerda
                x--;
            }
        }
    }

    //Mova para a direita
    public void right() {
        //Impede que saia pela direita da board
        if (x != board.getBlocks().length - 1) {
            if (!board.getBlocksItem(x + 1, y).isSolid()) {
                //Incrementa a coordenada x do objeto de forma a deslocar-se
                //para a direita
                x++;
            }
        }
    }
    //Move para baixo
    public void down() {
        if (y != board.getBlocks()[0].length - 1) {
            //Se o objeto abaixo nao for solido
            if (!board.getBlocksItem(x, y + 1).isSolid()) {
                //Incrementa a coordenada y do objeto de forma a deslocar-se
                //para baixo
                y++;
            }
        }
    }

}
