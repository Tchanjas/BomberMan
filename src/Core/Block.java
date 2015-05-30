package Core;

import java.awt.Color;
import java.awt.Graphics;

public abstract class Block extends Drawable{
        
    Color color;

    public Block(int x, int y, Color color) {
        super(x, y);
        this.color = color;
    }
    
    @Override
    public void draw(Graphics gr) {
        gr.setColor(color);
        gr.fillRect(x * objSize, y * objSize, objSize, objSize);
        
        gr.setColor(Color.BLACK);
        gr.drawRect(x * objSize, y * objSize, objSize, objSize);
    }
}
