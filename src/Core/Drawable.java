package Core;

import java.awt.Graphics;

public abstract class Drawable {
    
    protected int objSize = 20;
    protected int x,y;

    public Drawable(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public abstract void draw(Graphics gr);
    
    public abstract boolean isSolid();
    public abstract boolean isDestroyable();
    
}
