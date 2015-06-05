package Block;

import java.awt.Color;

public class Wall extends Block {

    public Wall(int x, int y) {
        super(x, y, Color.DARK_GRAY);
    }

    @Override
    public boolean isDestructible() {
        return false;
    }

    @Override
    public boolean isSolid() {
        return true;
    }

}
