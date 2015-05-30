package Temporary;

import Core.Board;
import java.awt.Color;
import java.awt.Graphics;

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

}
