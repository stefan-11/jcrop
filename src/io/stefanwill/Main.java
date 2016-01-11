package io.stefanwill;

import io.stefanwill.jcrop.JCrop;

import java.awt.*;
import java.awt.image.BufferedImage; // I think
import javax.imageio.*;
import java.io.*;

public class Main {



    public static void main(String[] args) {
	    // write your code here
        System.out.println("hello world!");

        BufferedImage img = null;
        BufferedImage targetImg = null;



        //load it
        try {
            //img = ImageIO.read(new File("landscape.jpg"));
            //img = ImageIO.read(new File("pano.jpg"));
            img = ImageIO.read(new File("portrait.jpg"));

            System.out.println("loaded");

        } catch (IOException e) {
            System.out.println("Loading failed.");
        }


/*
        //resize it
        try {
            System.out.println("resizing...");
            img = JCrop.resize(img, 2848, 2848);
            System.out.println("done");
        } catch (Exception e) {
            System.out.println("resizing failed.");
            e.printStackTrace();
        }


        //crop it
        try {

            Rectangle croppedRect = new Rectangle(500, 500);

            targetImg = JCrop.crop(img, croppedRect, 2100, 1400);

            System.out.println("cropped");

        } catch (Exception e) {
            System.out.println("cropping failed.");
        }

*/



        targetImg = Main.createThumbnail(img);


        //write the image to a file
        try {
            if (targetImg == null){
                System.out.println("defaulting targetImg to img");
                targetImg = img;
            }

            System.out.println("saving cropped image");
            File outputfile = new File("output.jpg");
            ImageIO.write(targetImg, "jpg", outputfile);
            System.out.println("done");
        } catch (IOException e) {
            System.out.println("writing failed.");
        }





    }


    public static BufferedImage createThumbnail(BufferedImage img){

        //widht/height of the original image
        int imageWidth = img.getWidth();
        int imageHeight = img.getHeight();

        //width/height of the thumbnail (thumbnail will be landscape even for portrait images)
        int thumbHeight = 200;
        int thumbWidth = 300;

        //widht/height of the (smaller) resized image
        double scalingFactor = 0.5;
        int resizedHeigth = 0;
        int resizedWidth = 0;


        /*
        System.out.println("width");
        System.out.println(imageWidth);
        System.out.println("height");
        System.out.println(imageHeight);
        */

        //if height is bigger than width we have a portrait image
        //calculate the scaling factor depending on the short edge of the image
        if (imageHeight > imageWidth){
            System.out.println("image is portrait");
            /*
            System.out.println("thumbWidth: ");
            System.out.println(thumbWidth);
            System.out.println("imageWidth: ");
            System.out.println(imageWidth);
            */
            scalingFactor = (double) thumbWidth / (double) imageWidth;

        } else {
            System.out.println("image is landscape");
            /*
            System.out.println("thumbHeight: ");
            System.out.println(thumbHeight);
            System.out.println("imageHeight: ");
            System.out.println(imageHeight);
            */
            scalingFactor = (double) thumbHeight / (double) imageHeight;

        }

        /*
        System.out.println("scalingFactor");
        System.out.println(scalingFactor);
        */

        //recalculate height/width of the thumbnail (should be same as thumbWidth/thumbHeight)
        resizedHeigth = (int) (imageHeight * scalingFactor);
        /*
        System.out.println("resizedHeight:");
        System.out.println(resizedHeigth);
        */

        resizedWidth = (int) (imageWidth * scalingFactor);
        /*
        System.out.println("resizedWidth:");
        System.out.println(resizedWidth);
        */

        //resize it
        try {
            //System.out.println("resizing...");
            //now resize the image
            img = JCrop.resize(img, resizedWidth, resizedHeigth);
            //System.out.println("done");
        } catch (Exception e) {
            System.out.println("Exception aufgetreten");
            e.printStackTrace();
        }


        //crop it
        try {

            Rectangle croppedRect = new Rectangle(thumbWidth, thumbHeight);
            /*
            System.out.println("croppedRect");
            System.out.println(croppedRect);
            */

            int originX = (resizedWidth / 2) - (thumbWidth / 2);
            /*
            System.out.println("originX");
            System.out.println(originX);
            */

            int originY = (resizedHeigth / 2) - (thumbHeight / 2);
            /*
            System.out.println("originY");
            System.out.println(originY);
            */

            //now crop the image
            img = JCrop.crop(img, croppedRect, originX, originY);

            //System.out.println("cropped");

        } catch (Exception e) {
            System.out.println("Exception aufgetreten");
        }


        return img;

    }
}
