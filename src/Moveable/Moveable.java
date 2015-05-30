package Moveable;

import Core.Board;
import Core.Drawable;
import java.awt.Color;
import java.awt.Graphics;

public abstract class Moveable extends Drawable {

    Color color;
    Board board;

    public Moveable(int x, int y, Color color, Board board) {
        super(x, y);
        this.color = color;
        this.board = board;
    }

    public void draw(Graphics gr) {
        gr.setColor(color);
        gr.fillArc(x * objSize, y * objSize, objSize, objSize, 0, 360);
    }

    public void up() {
        if (!board.get(x, y - 1).isSolid()) {
            y--;
        }
    }

    public void left() {
        if (!board.get(x - 1, y).isSolid()) {
            x--;
        }
    }

    public void right() {
        if (!board.get(x + 1, y).isSolid()) {
            x++;
        }
    }

    public void down() {
        if (!board.get(x, y + 1).isSolid()) {
            y++;
        }
    }

}
