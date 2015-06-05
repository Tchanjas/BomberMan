package Block;

import Core.Drawable;
import java.awt.Color;
import java.awt.Graphics;

public abstract class Block extends Drawable {

    private Color color;

    public Block(int x, int y, Color color) {
        super(x, y);
        this.color = color;
    }

    @Override
    public void draw(Graphics gr) {
        //Preenche o rect com uma cor
        gr.setColor(color);
        gr.fillRect(x * size, y * size, size, size);
        //desenha a borda a preto
        gr.setColor(Color.BLACK);
        gr.drawRect(x * size, y * size, size, size);
    }

}
