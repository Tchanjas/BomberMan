package Entity;

import Core.Board;
import Temp.Bomb;
import java.awt.Color;

public class Player extends Entity {
    
    Color color;

    public Player(int x, int y, Board board) {
        super(x, y, Color.GREEN, board);
    }

    public void putBomb() {

        if (board.getNumBombs() > 0) {
            board.setDrawable(new Bomb(x, y, board));
            board.setNumBombs(board.getNumBombs() - 1);
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
}
