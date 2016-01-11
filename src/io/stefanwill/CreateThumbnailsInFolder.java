package io.stefanwill;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;


/**
 * Created by stefan on 11.01.16.
 *
 * Usage Example:
 * java io.stefanwill.CreateThumbnailsInFolder ../../../ ../../../
 *
 * Paths can be specified relative or absolute
 *
 *
 */
public class CreateThumbnailsInFolder {

    public static void main(String[] args){



        if (args.length < 2){
            System.out.println("Usage:");
            System.out.println("CreateThumbnailsInFolder <sourcedir> <targetdir>");
            System.out.println("");
            System.out.println("Example with relative paths:");
            System.out.println("java io.stefanwill.CreateThumbnailsInFolder ../../../ ../../../ ");

            return;
        }


        String sourceFilenameWithPath = args[0];

        String targetFilenameWithPath = args[1];

        //check if sourceFilename exists
        Path sourcePath = Paths.get(sourceFilenameWithPath);

        if (sourcePath.getFileName().toFile().isDirectory() == false){
            System.out.println("Sourcepath is not a directory.");
            return;
        }

        //check if targetFilename exists (it should not)
        Path targetPath = Paths.get(targetFilenameWithPath);

        if (targetPath.getFileName().toFile().isDirectory() == false){
            System.out.println("targetPath is not a directory.");
        }


        String[] sourceFileList = CreateThumbnailsInFolder.getImageFilesByDirectory(sourcePath.toString());

        for (int i=0; i<sourceFileList.length; i++){
            String currFilename = sourceFileList[i];

            if (currFilename.endsWith(".jpg") || currFilename.endsWith(".JPG") || currFilename.endsWith(".Jpg")){
                File currFile = new File(sourcePath.toString(), currFilename);

                System.out.println("sourceFile:");
                System.out.println(currFile.toString());

                //build targetFilenameWithPath

                String targetFilename = new String("thumb-").concat(currFilename);
                File targetFile = new File(targetPath.toString(), targetFilename);

                System.out.println("targetFile");
                System.out.println(targetFile.toString());

                System.out.println("creating thumbnail...");
                CreateThumbnailsInFolder.createThumb(currFile.toString(), targetFile.toString());
                System.out.println("...done");
            }

        }
    }




    private static String[] getImageFilesByDirectory(String directory){

        File folder = new File(directory);

        return folder.list();

    }


    private static void createThumb(String sourceFilenameWithPath, String targetFilenameWithPath){
        BufferedImage img = null;

        //load it
        try {
            img = ImageIO.read(new File(sourceFilenameWithPath));

            //System.out.println("loaded");

        } catch (IOException e) {
            System.out.println("Loading failed.");
        }



        BufferedImage targetImg = Main.createThumbnail(img);


        //write the image to a file
        try {
            if (targetImg == null){
                System.out.println("defaulting targetImg to img");
                targetImg = img;
            }

            //System.out.println("saving target file");
            File outputfile = new File(targetFilenameWithPath);
            ImageIO.write(targetImg, "jpg", outputfile);
            //System.out.println("done");
        } catch (IOException e) {
            System.out.println("writing failed.");
        }
    }


}
