package Entity;

import Core.Board;
import Temp.Bomb;
import java.awt.Color;

public class Player extends Entity {

    private int numBombs = 20;
    private int points;
    private int lifes;

    Color color;

    public Player(int x, int y, Board board) {
        super(x, y, Color.GREEN, board);
    }

    public void putBomb() {

        if (numBombs > 0) {
            board.setDrawable(new Bomb(x, y, board));
            numBombs--;
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

    public int getNumBombs() {
        return numBombs;
    }

    public void setNumBombs(int NumBombs) {
        this.numBombs = NumBombs;
    }
}
