package Core;

import java.awt.Color;

public class Wall extends Block{
    
    public Wall(int x, int y, Color color) {
        super(x, y, color);
    }

    @Override
    public boolean isSolid() {
        return true;
    }

    @Override
    public boolean isDestroyable() {
        return false;
    }
    
}
