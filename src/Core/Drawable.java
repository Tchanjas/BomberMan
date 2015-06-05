package Core;

import java.awt.Graphics;

public abstract class Drawable {

    //Tamanho dos objs em pixeis
    public final static int size = 20;

    //Coordenada x e y no tabuleiro
    protected int x, y;

    /**
     * Constroi um obj desenhava com coordenadas x,y no tabuleiro
     *
     * @param x coordenada x no tabuleiro
     * @param y coordenada y no tabuleiro
     */
    public Drawable(int x, int y) {
        this.x = x;
        this.y = y;
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
    public abstract void draw(Graphics gr);

    //Verifica se o obj e destrutivel
    public abstract boolean isDestructible();

    //verifica se o obj e solido (se pode ser atravessado)
    public abstract boolean isSolid();
}
