/**
 * Created by stefan on 08.01.16.
 */

package io.stefanwill.jcrop;

import java.awt.image.BufferedImage; // I think
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Graphics;


public class JCrop {

    public void sayHello(){

        System.out.println("hello");

    }


    public BufferedImage crop(BufferedImage src, Rectangle rect)
    {
        //BufferedImage dest = new BufferedImage((int) rect.getWidth(), (int) rect.getHeight(), BufferedImage.TYPE_4BYTE_ABGR_PRE);
        BufferedImage dest = new BufferedImage((int) rect.getWidth(), (int) rect.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics g = dest.getGraphics();
        g.drawImage(src, 0, 0, (int) rect.getWidth(), (int) rect.getHeight(), (int) rect.getX(), (int) rect.getY(), (int) rect.getX() + (int) rect.getWidth(), (int) rect.getY() + (int) rect.getHeight(), null);
        g.dispose();
        return dest;
    }

}
