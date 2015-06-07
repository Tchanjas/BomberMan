package Temp;

import Block.Floor;
import Core.Board;
import java.awt.Color;
import java.awt.Graphics;

public class PowerupRadius extends Temporary {

    public PowerupRadius(int x, int y, int time, Board board) {
        super(x, y, time, "/Graphics/powerupRadius.png", board);
    }

    @Override
    public void isDone() {
        board.setDrawable(new Floor(this.x, this.y));
    }

    @Override
    public void draw(Graphics gr) {
        if (image != null) {
            super.draw(gr);
        } else {
            gr.setColor(Color.CYAN);
            gr.fillRect(x * size, y * size, size, size);
        }
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
