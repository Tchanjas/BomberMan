package Temp;

import Block.Brick;
import Block.Floor;
import Core.Board;
import Entity.Enemy;
import java.awt.Color;
import java.awt.Graphics;

public class Explosion extends Temporary {

    public Explosion(int x, int y, Board board) {
        super(x, y, 1000, board);

        if (board.getMatrixItem(x, y) instanceof Brick){
            System.out.println(board.getPoints());
            board.setPoints(board.getPoints() + 5);
            System.out.println(board.getPoints());
            board.setDrawable(new PowerupBomb(x, y, 2500, board));
        }
        // remove enemy e nasce outro
        // jogador ganha 1 bomba
        if (x == board.getEnemy().getX() && y == board.getEnemy().getY()) {
            board.setEnemy(null);
            board.setEnemy(new Enemy(5, 5, board));
            board.getPlayer().setNumBombs(board.getPlayer().getNumBombs() + 1);
        }
    }

    @Override
    public void isDone() {
        board.setDrawable(new Floor(x, y));
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
