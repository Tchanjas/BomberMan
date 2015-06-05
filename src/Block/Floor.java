package Block;

import java.awt.Color;

public class Floor extends Block {

    public Floor(int x, int y) {
        super(x, y, Color.LIGHT_GRAY);
    }

    @Override
    public boolean isDestructible() {
        return true;
    }

    @Override
    public boolean isSolid() {
        return false;
    }

}
