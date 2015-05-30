package Core;

import java.awt.Color;

public class Floor extends Block{
    
    public Floor(int x, int y) {
        super(x, y, Color.LIGHT_GRAY);
    }

    @Override
    public boolean isSolid() {
        return false;
    }

    @Override
    public boolean isDestroyable() {
        return false;
    }
    
}
