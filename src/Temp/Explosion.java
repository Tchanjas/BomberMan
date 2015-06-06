package Temp;

import Block.Brick;
import Block.Floor;
import Core.Board;
import java.awt.Color;
import java.awt.Graphics;

public class Explosion extends Temporary {

    public Explosion(int x, int y, Board board) {
        super(x, y, 1000, board);

        if (board.getMatrixItem(x, y).getClass().equals(new Brick(x, y).getClass())) {
            System.out.println(board.getPoints());
            board.setPoints(board.getPoints() + 5);
        }

        if (x == board.getEnemy().getX() && y == board.getEnemy().getY()) {
            // remove enemy
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
