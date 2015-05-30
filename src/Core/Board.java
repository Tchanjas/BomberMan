package Core;

import java.awt.Graphics;

public class Board {

    protected Drawable[][] matrix;

    public Board() {
        matrix = new Drawable[20][20];
        cleanBoard();
        buildLevel();
    }

    public void cleanBoard() {
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                matrix[i][j] = new Floor(i, j);
            }
        }
    }
    
    public void buildLevel(){
        setDrawable(new Brick(1, 1));
        setDrawable(new Brick(1, 2));
        setDrawable(new Brick(19, 15));
        setDrawable(new Brick(17, 19));     
    }
    
    public void setDrawable(Drawable dr){
        matrix[dr.y][dr.x] = dr; 
    }

    public void draw(Graphics gr) {
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                matrix[i][j].draw(gr);
            }
        }
    }
    
    public Drawable get(int x, int y){
        return matrix[y][x];
    }
    
}
