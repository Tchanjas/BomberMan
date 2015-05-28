/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Temporary;

import Core.Board;
import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author l0cust
 */
public class Bomb extends Temporary {

    public Bomb(int x, int y, Board board) {
        super(x, y, 2000, board);
    }

    @Override
    public void isDone() {
        board.setDrawable(new Explosion(x, y, board));
    }

    @Override
    public void draw(Graphics gr) {
        gr.setColor(Color.BLACK);
        gr.drawOval(x * objSize, y * objSize, objSize, objSize);
    }

    @Override
    public boolean isSolid() {
        return true;
    }

    @Override
    public boolean isDestroyable() {
        return true;
    }

    @Override
    public boolean isInteractable() {
        return false;
    }
    
}
