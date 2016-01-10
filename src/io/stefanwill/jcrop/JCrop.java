/**
 * Created by stefan on 08.01.16.
 */

package io.stefanwill.jcrop;

import java.awt.*;
import java.awt.image.BufferedImage; // I think


public class JCrop {

    public static BufferedImage crop(BufferedImage src, Rectangle rect) {

        //crop with default origin
        BufferedImage dest = JCrop.crop(src, rect, 0, 0);
        /*
        BufferedImage dest = new BufferedImage((int) rect.getWidth(), (int) rect.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics g = dest.getGraphics();
        g.drawImage(src, 0, 0, (int) rect.getWidth(), (int) rect.getHeight(), (int) rect.getX(), (int) rect.getY(), (int) rect.getX() + (int) rect.getWidth(), (int) rect.getY() + (int) rect.getHeight(), null);
        //g.drawImage(src, 0, 0, (int) rect.getWidth(), (int) rect.getHeight(), 1000, 1000, (int) rect.getX() + (int) rect.getWidth(), (int) rect.getY() + (int) rect.getHeight(), null);
        g.dispose();
        */
        return dest;
    }

    public static BufferedImage crop(BufferedImage src, Rectangle rect, int originX, int originY) {
        BufferedImage dest = new BufferedImage((int) rect.getWidth(), (int) rect.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics g = dest.getGraphics();
        //g.drawImage(src, 0, 0, (int) rect.getWidth(), (int) rect.getHeight(), (int) rect.getX(), (int) rect.getY(), (int) rect.getX() + (int) rect.getWidth(), (int) rect.getY() + (int) rect.getHeight(), null);
        g.drawImage(src, 0, 0, (int) rect.getWidth(), (int) rect.getHeight(), originX, originY, (int) rect.getX() + (int) (originX + rect.getWidth()), (int) rect.getY() + (int) (originY + rect.getHeight()), null);
        g.dispose();
        return dest;
    }

    public static BufferedImage resize(BufferedImage img, int newW, int newH) {
        Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_RGB);

        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return dimg;
    }

    public static boolean isPortrait(BufferedImage img){

        return false;
    }

}
