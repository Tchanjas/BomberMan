package Entity;

import Core.Board;
import Temp.Bomb;
import java.awt.Color;

public class Player extends Entity {

    private int NumBombs = 20;

    Color color;

    public Player(int x, int y, Board board) {
        super(x, y, Color.GREEN, board);
    }

    public void putBomb() {
        if (NumBombs > 0) {
            board.setDrawable(new Bomb(x, y, board));
            NumBombs--;
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
        return NumBombs;
    }

    public void setNumBombs(int NumBombs) {
        this.NumBombs = NumBombs;
    }
}
