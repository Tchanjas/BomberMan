/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Core;

/**
 *
 * @author l0cust
 */
public abstract class Temporary extends Drawable implements Runnable{

    int time;
    Board board;
    
    public Temporary(int x, int y, int time, Board board) {
        super(x, y);
        this.time = time;
        this.board = board;
        new Thread(this).start();
    }
    
    public abstract void isDone();
    
    public void run(){
        try {
            Thread.sleep(time);
            isDone();
        } catch (InterruptedException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
