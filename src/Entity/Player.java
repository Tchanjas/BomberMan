package Entity;

import Core.Board;
import Temp.Bomb;
import java.awt.Color;
import java.util.ArrayList;

public class Player extends Entity {

    ArrayList<Bomb> arrBomb = new ArrayList<>();

    Color color;

    public Player(int x, int y, Board board) {
        super(x, y, Color.GREEN, board);
        for (int i = 0; i < 20; i++) {
            arrBomb.add(new Bomb(x, y, board));
        }
    }

    public void putBomb() {
        if (!arrBomb.isEmpty()) {
            board.setDrawable(new Bomb(x, y, board));
            arrBomb.remove(0);
            arrBomb.trimToSize();
        }
    }

    @Override
    public boolean isDestructible() {
        return true;
    }

    @Override
    public boolean isSolid() {
        return true;
    }

}
