package Core;

import Utils.GameUtils;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.Serializable;

public abstract class Drawable implements Serializable {

    //Tamanho dos objs em pixeis
    public final static int size = 20;

    //Coordenada x e y no tabuleiro
    protected int x, y;
    protected transient Image image;

    /**
     * Constroi um obj desenhava com coordenadas x,y no tabuleiro
     *
     * @param x coordenada x no tabuleiro
     * @param y coordenada y no tabuleiro
     */
    public Drawable(int x, int y) {
        this.x = x;
        this.y = y;
        image = null;
    }

    public Drawable(int x, int y, String imgFile) {
        this.x = x;
        this.y = y;
        image = GameUtils.loadImage(imgFile);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    /**
     * Metodo para desenhar objs
     *
     * @param gr Contexto grafico
     */
    public void draw(Graphics gr) {
        if (image != null) {
            gr.drawImage(image, x * size, y * size, size, size, null);
        }else {
            gr.setColor(Color.GREEN);
            gr.fillArc(x * size, y * size, size, size, 45, 270);
        }
    }

    ;

    //Verifica se o obj e destrutivel
    public abstract boolean isDestructible();

    //verifica se o obj e solido (se pode ser atravessado)
    public abstract boolean isSolid();
}
