/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Moveable;

import Core.Board;
import java.awt.Color;

/**
 *
 * @author l0cust
 */
public class Enemy extends Moveable{
    
    Color color;

    public Enemy(int x, int y, Board board) {
        super(x, y,Color.RED,board);
    }

    @Override
    public boolean isSolid() {
        return false;
    }

    @Override
    public boolean isDestroyable() {
        return true;
    }

    @Override
    public boolean isInteractable() {
        return true;
    }
}
