package Block;

import Core.Board;

public class Brick extends Block {
    
    protected Board board;

    public Brick(int x, int y) {
        super(x, y, "/Graphics/brick.png");
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
