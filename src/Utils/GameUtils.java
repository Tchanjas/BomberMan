/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import javax.swing.ImageIcon;

/**
 *
 * @author L0cust
 */
public class GameUtils {

    //Ler um ficheiro de audio e devolve um clip de audip
    public static AudioClip loadSound(String file) {
        URL url = GameUtils.class.getResource(file);
        return Applet.newAudioClip(url);
    }

    //Ler uma imagem a partir de um recurso da aplicacao
    public static ImageIcon loadIcon(String url) {
        return new ImageIcon(url);
    }

    //Retrona uma imagem
    public static Image loadImage(String url) {
        return Toolkit.getDefaultToolkit().createImage(GameUtils.class.getResource(url));
    }

}
