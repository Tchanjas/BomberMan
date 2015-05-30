package Moveable;

import Core.Board;
import Temporary.Bomb;
import java.awt.Color;
import java.util.ArrayList;

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
    
    public void putBomb(){
        if(!arrBomb.isEmpty()){
            board.setDrawable(new Bomb(x, y, board));
            arrBomb.remove(0);
            arrBomb.trimToSize();
            System.out.println(arrBomb.size()+"");
        }
    }
    
}
