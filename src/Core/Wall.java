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
public class Wall extends Block{

    public Wall(int x, int y) {
        super(x, y, Color.DARK_GRAY);
    }

    @Override
    public boolean isDestructible() {
        return false;
    }

    @Override
    public boolean isSolid() {
        return true;
    }
    
}
