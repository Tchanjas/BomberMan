/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Core;

import java.awt.Color;
import java.util.ArrayList;

/**
 *
 * @author l0cust
 */
public class Player extends Moveable {
    
    ArrayList<Bomb> arrBomb = new ArrayList<>();

    Color color;

    public Player(int x, int y, Board board) {
        super(x, y,Color.GREEN,board);
        for (int i = 0; i < 20; i++) {
            arrBomb.add(new Bomb(x, y, board)); 
        }
    }  
        
    public void putBomb(){
        if(!arrBomb.isEmpty()){
            board.setDrawable(new Bomb(x, y, board));
            arrBomb.remove(0);
            arrBomb.trimToSize();
            System.out.println(arrBomb.size()+"");
        }
    }

    @Override
    public boolean isDestructible() {
        return true;
    }

    @Override
    public boolean isSolid() {
        return true;
    }
    
}
