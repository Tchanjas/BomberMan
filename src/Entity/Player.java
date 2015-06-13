package Entity;

import Core.Board;
import Temp.Bomb;
import java.awt.Color;

public class Player extends Entity {

    private int lifes;
    private int numBombs;

    public Player(int x, int y, Board board) {
        super(x, y, Color.GREEN, "/Graphics/Bomberman.png", board);
        numBombs = 20;
        lifes = 3;
    }

    public void putBomb() {
        if (getNumBombs() > 0) {
            board.setDrawable(new Bomb(x, y, board));
            setNumBombs(getNumBombs() - 1);
        }
    }

    public void increaseBombs() {
        if (numBombs + 2 > 20) {
            numBombs = 20;
        } else {
            numBombs = numBombs + 2;
        }
    }

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
