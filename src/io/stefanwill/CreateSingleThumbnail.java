package io.stefanwill;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by stefan on 11.01.16.
 */
public class CreateSingleThumbnail {



    public static void main(String[] args){

        if (args.length < 2){
            System.out.println("Usage:");
            System.out.println("CreateSingleThumbnail <sourcefile> <targetfile>");
            System.out.println("");

            return;
        }


        String sourceFilenameWithPath = args[0];

        String targetFilenameWithPath = args[1];

        //check if sourceFilename exists
        Path sourcePath = Paths.get(sourceFilenameWithPath);
        String sourceFilename = sourcePath.getFileName().toString();

        System.out.println(sourceFilename);

        File sourceFile = new File(sourceFilenameWithPath);
        if (sourceFile.exists() == false){
            System.out.println("File not found.");
            return;
        }

        //check if targetFilename exists (it should not)
        Path targetPath = Paths.get(targetFilenameWithPath);
        String targetFilename = targetPath.getFileName().toString();

        System.out.println(targetFilename);

        File targetFile = new File(targetFilenameWithPath);
        if (targetFile.exists() == true){
            System.out.println("Target File exists...will be overwritten");
        }





        BufferedImage img = null;
        BufferedImage targetImg = null;



        //load it
        try {
            img = ImageIO.read(new File(sourceFilenameWithPath));

            System.out.println("loaded");

        } catch (IOException e) {
            System.out.println("Loading failed.");
        }



        targetImg = Main.createThumbnail(img);


        //write the image to a file
        try {
            if (targetImg == null){
                System.out.println("defaulting targetImg to img");
                targetImg = img;
            }

            System.out.println("saving target file");
            File outputfile = new File(targetFilenameWithPath);
            ImageIO.write(targetImg, "jpg", outputfile);
            System.out.println("done");
        } catch (IOException e) {
            System.out.println("writing failed.");
        }



    }




}
