package Block;

import java.awt.Color;

public class Brick extends Block {

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
