package Entity;

import Core.Board;
import Temp.Bomb;
import java.awt.Color;

public class Player extends Entity {

    //Nr vidas
    private int lifes;
    //Nr bombas
    private int numBombs;

    public Player(int x, int y, Board board) {
        super(x, y, Color.GREEN, "/Graphics/Bomberman.png", board);
        //20 bombas
        numBombs = 20;
        //3 vidas
        lifes = 3;
    }

    //Coloca uma bomba na posicao do player
    public void putBomb() {
        //Se o player tiver bombas
        if (getNumBombs() > 0) {
            board.setDrawable(new Bomb(x, y, board));
            //Retira uma bomba ao jogador
            setNumBombs(getNumBombs() - 1);
        }
    }

    //Adiciona bombas
    public void increaseBombs() {
        //Impede que o jogador tenha mais de 20 bombas
        if (numBombs + 2 > 20) {
            numBombs = 20;
        } else {
            //Adiciona 2 bombas
            numBombs = numBombs + 2;
        }
    }

    //Setters e getters
    public int getNumBombs() {
        return numBombs;
    }

    public void setNumBombs(int NumBombs) {
        this.numBombs = NumBombs;
    }

    public int getLifes() {
        return lifes;
    }

    public void setLifes(int lifes) {
        this.lifes = lifes;
    }

    @Override
    public boolean isDestructible() {
        return true;
    }

    @Override
    public boolean isSolid() {
        return true;
    }

}
