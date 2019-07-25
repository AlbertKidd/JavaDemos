package com.kidd.demos.javax;

import com.kidd.demos.image.exif.ImageMetadataReaderDemo;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * @author Kidd
 */
public class ImageIODemo {

    public static void main(String[] args) throws Exception{
        File file = new File(ImageMetadataReaderDemo.class.getResource("/image/no_exif.jpg").getFile());
        BufferedImage read = ImageIO.read(file);
    }
}
