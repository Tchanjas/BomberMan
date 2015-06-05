package Temp;

import Block.Brick;
import Core.Board;
import java.awt.Color;
import java.awt.Graphics;

public class Bomb extends Temporary {

    private int expRadius = 2;

    public Bomb(int x, int y, Board board) {
        super(x, y, 2000, board);
    }

    @Override
    public void isDone() {
        for (int i = 1; i <= expRadius; i++) {
            if (x + 1 - i > 0) {
                if (board.getMatrixItem(x - i, y).isDestructible()) {
                    if(board.getMatrixItem(x - i, y).getClass().equals(new Brick(x, y).getClass())) {
                        board.setPoints(board.getPoints() + 5);
                    }
                    board.setDrawable(new Explosion(x - i, y, board));
                }
            }
            if (x - 1 + i < board.getMatrix().length - 1) {
                if (board.getMatrixItem(x + i, y).isDestructible()) {
                    if(board.getMatrixItem(x + i, y).getClass().equals(new Brick(x, y).getClass())) {
                        board.setPoints(board.getPoints() + 5);
                    }
                    board.setDrawable(new Explosion(x + i, y, board));
                }
            }
            if (board.getMatrixItem(x, y).isDestructible()) {
                board.setDrawable(new Explosion(x, y, board));
            }
            if (y - 1 + i < board.getMatrix()[0].length - 1) {
                if (board.getMatrixItem(x, y + i).isDestructible()) {
                    if(board.getMatrixItem(x, y + i).getClass().equals(new Brick(x, y).getClass())) {
                        board.setPoints(board.getPoints() + 5);
                    }
                    board.setDrawable(new Explosion(x, y + i, board));
                }
            }
            if (y + 1 - i > 0) {
                if (board.getMatrixItem(x, y - i).isDestructible()) {
                    if(board.getMatrixItem(x, y - i).getClass().equals(new Brick(x, y).getClass())) {
                        board.setPoints(board.getPoints() + 5);
                    }
                    board.setDrawable(new Explosion(x, y - i, board));
                }
            }
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

    public int getExpRadius() {
        return expRadius;
    }

    public void setExpRadius(int expRadius) {
        this.expRadius = expRadius;
    }
}
