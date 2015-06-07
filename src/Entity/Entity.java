package Entity;

import Core.Board;
import Core.Drawable;
import java.awt.Color;
import java.awt.Graphics;

public abstract class Entity extends Drawable {

    Color color;
    Board board;

    public Entity(int x, int y, Color color, String imgFile, Board board) {
        super(x, y, imgFile);
        this.color = color;
        this.board = board;
    }

    public void draw(Graphics gr) {
        if (image != null) {
            super.draw(gr);
        } else {
            gr.setColor(color);
            gr.fillArc(x * size, y * size, size, size, 45, 270);
        }
    }

    public void up() {
        if (y != 0) {
            if (!board.getBlocksItem(x, y - 1).isSolid()) {
                y--;
            }
        }
    }

    public void left() {
        if (x != 0) {
            if (!board.getBlocksItem(x - 1, y).isSolid()) {
                x--;
            }
        }
    }

    public void right() {
        if (x != board.getBlocks().length - 1) {
            if (!board.getBlocksItem(x + 1, y).isSolid()) {
                x++;
            }
        }
    }

    public void down() {
        if (y != board.getBlocks()[0].length - 1) {
            if (!board.getBlocksItem(x, y + 1).isSolid()) {
                y++;
            }
        }
    }

}
