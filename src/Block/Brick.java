package Block;

import Core.Board;
import java.awt.Color;

public class Brick extends Block {
    
    protected Board board;

    public Brick(int x, int y) {
        super(x, y, Color.ORANGE);
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
