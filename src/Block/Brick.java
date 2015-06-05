package Block;

import Core.Board;
import Temp.PowerupRadius;
import java.awt.Color;

public class Brick extends Block {
    
    protected Board board;

    public Brick(int x, int y) {
        super(x, y, Color.ORANGE);
    }
    
    public void dropPowerup(){
        if(Math.random() >= 0.5){
            new PowerupRadius(this.x, this.y, 10000, board);
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
