/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Core;

import java.awt.Graphics;

/**
 *
 * @author l0cust
 */
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
