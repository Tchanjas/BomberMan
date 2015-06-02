/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Core;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author l0cust
 */
public class Bomb extends Temporary {

    int expRadius = 1;

    public Bomb(int x, int y, Board board) {
        super(x, y, 2000, board);
    }

    @Override
    public void isDone() {
        for (int i = 1; i <= expRadius; i++) {
            board.setDrawable(new Explosion(x, y, board));
            board.setDrawable(new Explosion(x - i, y, board));
            board.setDrawable(new Explosion(x + i, y, board));
            board.setDrawable(new Explosion(x, y - i, board));
            board.setDrawable(new Explosion(x, y + i, board));
        }
    }

    @Override
    public void draw(Graphics gr) {
        gr.setColor(Color.BLACK);
        gr.fillOval(x * size, y * size, size, size);
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
