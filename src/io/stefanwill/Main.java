package io.stefanwill;

import io.stefanwill.jcrop.JCrop;

import java.awt.image.BufferedImage; // I think
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Graphics;
import javax.imageio.*;
import java.io.*;

public class Main {



    public static void main(String[] args) {
	    // write your code here
        System.out.println("hello world!");

        JCrop cropper = new JCrop();
        cropper.sayHello();


        //load an image into a buffered image
        //SW_HI_2013-06-30_19-05-10_eos450d_IMG_1314.jpg

        BufferedImage img = null;
        BufferedImage targetImg = null;

        try {
            img = ImageIO.read(new File("SW_HI_2013-06-30_19-05-10_eos450d_IMG_1314.jpg"));

            System.out.println("got it");

            Rectangle croppedRect = new Rectangle(500, 500);

            targetImg = cropper.crop(img, croppedRect);

            System.out.println("cropped");

        } catch (IOException e) {
            System.out.println("IOException aufgetreten");
        }



        //write the image to a file
        try {

            File outputfile = new File("saved.jpg");
            ImageIO.write(targetImg, "jpg", outputfile);
        } catch (IOException e) {
            System.out.println("IOException aufgetreten während dem schreiben");
        }

    }
}
