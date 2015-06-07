package Temp;

import Core.Board;
import Core.Drawable;

public abstract class Temporary extends Drawable implements Runnable {

    private int time;
    protected Board board;

    public Temporary(int x, int y, int time, String imgFile, Board board) {
        super(x, y, imgFile);
        this.time = time;
        this.board = board;
        new Thread(this).start();
    }

    public abstract void isDone();

    public void run() {
        try {
            Thread.sleep(time);
            isDone();
        } catch (InterruptedException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
