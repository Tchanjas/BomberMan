/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Temp;

import Block.Floor;
import Core.Board;
import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Clara
 */
public class PowerupRadius extends Temporary {
    
    public PowerupRadius(int x, int y, int time, Board board) {
        super(x, y, time, board);
    }
    
    @Override
    public void isDone() {
        board.setDrawable(new Floor(this.x, this.y));
    }
    
    public void increaseRadius() {
        Bomb.setExpRadius(1);
    }
    
    @Override
    public void draw(Graphics gr) {
        gr.setColor(Color.CYAN);
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
