package Entity;

import Core.Board;
import java.awt.Color;

public class Enemy extends Entity {

    Color color;

    public Enemy(int x, int y, Board board) {
        super(x, y, Color.RED, board);
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
