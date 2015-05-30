package Moveable;

import Core.Board;
import java.awt.Color;

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

}
