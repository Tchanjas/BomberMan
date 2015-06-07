package Temp;

import Block.Brick;
import Block.Floor;
import Core.Board;
import Entity.Enemy;
import java.awt.Color;
import java.awt.Graphics;

public class Explosion extends Temporary {

    private boolean hitBrick;

    public Explosion(int x, int y, Board board) {
        super(x, y, 1000, board);

        if (board.getBlocksItem(x, y) instanceof Brick) {
            hitBrick = true;
            board.removeArrBricksItem();
        }
        // remove enemy e nasce outro
        // jogador ganha 1 bomba
        if (x == board.getEnemy().getX() && y == board.getEnemy().getY()) {
            board.setEnemy(null);
            board.setEnemy(new Enemy(board));
            board.getPlayer().setNumBombs(board.getPlayer().getNumBombs() + 1);
        }
    }

    @Override
    public void isDone() {
        if (x == board.getPlayer().getX() && y == board.getPlayer().getY()) {
            board.getPlayer().setLifes(0);
        }
        
        if (hitBrick) {
            board.setPoints(board.getPoints() + 5);
            board.setDrawable(new Floor(x, y));
            if (Math.random() >= 0.5) {
                if (Math.random() >= 0.5) {
                    board.setDrawable(new PowerupBomb(x, y, 5000, board));
                } else {
                    board.setDrawable(new PowerupRadius(x, y, 5000, board));
                }
            }
        } else {
            board.setDrawable(new Floor(x, y));
        }
    }

    @Override
    public void draw(Graphics gr) {
        gr.setColor(Color.red);
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
