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

    public Block(int x, int y, String imgFile) {
        super(x, y, imgFile);
    }

    public void draw(Graphics gr) {
        if (image != null) {
            super.draw(gr);
        } else {
            // preenche um rectangulo com a cor do objeto
            gr.setColor(color);
            gr.fillRect(x * size, y * size, size, size);
            //desenha a borda a preto
            gr.setColor(Color.BLACK);
            gr.drawRect(x * size, y * size, size, size);
        }
    }

}
