/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Temporary;

import Core.Board;
import Core.Floor;
import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author l0cust
 */
public class Explosion extends Temporary {

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
        gr.drawRect(x * objSize, y * objSize, objSize, objSize);
    }

    @Override
    public boolean isSolid() {
        return false;
    }

    @Override
    public boolean isDestroyable() {
        return false;
    }

    @Override
    public boolean isInteractable() {
        return true;
    }

}
