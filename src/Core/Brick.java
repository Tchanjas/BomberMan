package Core;

import java.awt.Color;

public class Brick extends Block {

    public Brick(int x, int y) {
        super(x, y, Color.ORANGE);
    }

    @Override
    public boolean isSolid() {
        return true;
    }

    @Override
    public boolean isDestroyable() {
        return true;
    }

}
