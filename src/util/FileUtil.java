package util;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.shape.SVGPath;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileUtil {

    public static boolean saveImage(Image image, File outputFile ) {
        BufferedImage bImage = SwingFXUtils.fromFXImage(image, null);
        try {
            ImageIO.write(bImage, "png", outputFile);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public static boolean saveImage(File inputFile, File outputFile ) {
        Image image = new Image(inputFile.toURI().toString());
        return saveImage(image, outputFile);
    }

    public static boolean saveFile(File origen, File dest) {
        try {
            FileInputStream fileInputStream = new FileInputStream(origen);
            FileOutputStream outputStream = new FileOutputStream(dest);
            byte[] buf = new byte[1024];
            int len;
            while ((len = fileInputStream.read(buf)) > 0) {
                outputStream.write(buf, 0, len);
            }
            outputStream.close();
            fileInputStream.close();
            return  true;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return  false;
    }
}
