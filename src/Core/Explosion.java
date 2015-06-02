/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Core;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author l0cust
 */
public class Explosion extends Temporary{
    
    int expRadius;

    public Explosion(int x, int y, Board board) {
        super(x, y, 1000, board);
    }
    
    @Override
    public void isDone() {
        board.setDrawable(new Floor(x, y));   
    }

    @Override
    public void draw(Graphics gr) {
        gr.setColor(Color.red);
        gr.fillOval(x * size, y * size, size, size);
    }

    @Override
    public boolean isDestructible() {
       return false;
    }

    @Override
    public boolean isSolid() {
       return false; 
    }
    
}
