/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Core;

import java.awt.Color;

/**
 *
 * @author l0cust
 */
public class Brick extends Block {

    public Brick(int x, int y) {
        super(x, y, Color.ORANGE);
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
