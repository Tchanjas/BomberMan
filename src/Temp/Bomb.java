package Temp;

import Block.Brick;
import Core.Board;
import java.awt.Color;
import java.awt.Graphics;

public class Bomb extends Temporary {

    private static int expRadius = 1;
    private static boolean leftHit, rightHit, upHit, downHit;

    public Bomb(int x, int y, Board board) {
        super(x, y, 2000, board);
    }

    @Override
    public void isDone() {
        for (int i = 1; i <= expRadius; i++) {
            if (x + 1 - i > 0) {
                if (!leftHit) {
                    if (board.getMatrixItem(x - i, y).isDestructible() ) {
                        if (board.getMatrixItem(x - i, y) instanceof Brick) {
                            leftHit = true;
                        }
                        board.setDrawable(new Explosion(x - i, y, board));
                    }
                }
            }
            if (x - 1 + i < board.getMatrix().length - 1) {
                if (!rightHit) {
                    if (board.getMatrixItem(x + i, y).isDestructible()) {
                        if (board.getMatrixItem(x + i, y) instanceof Brick) {
                            rightHit = true;
                        }
                        board.setDrawable(new Explosion(x + i, y, board));
                    }
                }
            }
            if (board.getMatrixItem(x, y).isDestructible()) {
                board.setDrawable(new Explosion(x, y, board));
            }
            if (y - 1 + i < board.getMatrix()[0].length - 1) {
                if (!downHit) {
                    if (board.getMatrixItem(x, y + i).isDestructible()) {
                        if (board.getMatrixItem(x, y + i) instanceof Brick) {
                            downHit = true;
                        }
                        board.setDrawable(new Explosion(x, y + i, board));
                    }
                }
            }
            if (y + 1 - i > 0) {
                if (!upHit) {
                    if (board.getMatrixItem(x, y - i).isDestructible()) {
                        if (board.getMatrixItem(x, y - i) instanceof Brick) {
                            upHit = true;
                        }
                        board.setDrawable(new Explosion(x, y - i, board));
                    }
                }
            }
        }
        leftHit = false;
        rightHit = false;
        upHit = false;
        downHit = false;
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

    public static void setExpRadius(int expRadius) {
        Bomb.expRadius += expRadius;
    }

}
