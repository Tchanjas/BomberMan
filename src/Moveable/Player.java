/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Moveable;

import Core.Board;
import Temporary.Bomb;
import java.awt.Color;
import java.util.ArrayList;

/**
 *
 * @author l0cust
 */
public class Player extends Moveable{
    
    ArrayList<Bomb> arrBomb = new ArrayList<>();

    public Player(int x, int y,Board board) {
        super(x, y, Color.CYAN, board);
        for (int i = 0; i < 5; i++) {
            arrBomb.add(new Bomb(x, y, board)); 
        }
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
        return false;
    }
    
    public void putBomb(){
        if(!arrBomb.isEmpty()){
            board.setDrawable(new Bomb(x, y, board));
            arrBomb.remove(0);
            arrBomb.trimToSize();
            System.out.println(arrBomb.size()+"");
        }
    }
    
}
