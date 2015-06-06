package Temp;

import Block.Floor;
import Core.Board;
import static Core.Drawable.size;
import java.awt.Color;
import java.awt.Graphics;

public class PowerupBomb extends Temporary{
    
    public PowerupBomb(int x, int y, int time, Board board) {
        super(x, y, time, board);
    }
    
    @Override
    public void isDone() {
        board.setDrawable(new Floor(this.x, this.y));
    }
    
    public void increaseBombs() {
        board.getPlayer().setNumBombs(board.getPlayer().getNumBombs() + 2);
    }
    
    @Override
    public void draw(Graphics gr) {
        gr.setColor(Color.PINK);
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
