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
public abstract class Moveable extends Drawable{

    Color color;
    Board board;
    
    public Moveable(int x, int y, Color color, Board board) {
        super(x, y);
        this.color = color;
        this.board = board;
    }
    
    public void draw(Graphics gr) {  
        gr.setColor(color);
        gr.fillArc(x*size, y*size, size, size, 45, 270);   
    }
    
    public void up(){
        if(!board.get(x,y-1).isSolid() || board.get(x, y-1).y != 0){
            y--;
        }
    }
    public void left(){
        if(!board.get(x-1,y).isSolid() || board.get(x-1, y).x != 0){
            x--;
        }
    }
    public void right(){
        if(!board.get(x+1,y).isSolid()){
            x++;
        }
    }
    public void down(){
        if(!board.get(x,y+1).isSolid()){
            y++;
        }
    }
    
    
}
