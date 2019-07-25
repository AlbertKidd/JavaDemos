package com.kidd.demos.image.exif;

import com.drew.imaging.ImageMetadataReader;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.exif.ExifIFD0Directory;

import java.io.File;

/**
 * @author Kidd
 */
public class ImageMetadataReaderDemo {

    public static void main(String[] args) throws Exception{
        File file = new File(ImageMetadataReaderDemo.class.getResource("/image/no_exif.jpg").getFile());
        Metadata metadata = ImageMetadataReader.readMetadata(file);
        if (metadata.containsDirectoryOfType(ExifIFD0Directory.class)) {
            Directory directory = metadata.getFirstDirectoryOfType(ExifIFD0Directory.class);
            if (directory.containsTag(ExifIFD0Directory.TAG_ORIENTATION)){
                int directoryInt = directory.getInt(ExifIFD0Directory.TAG_ORIENTATION);
            }
        }
    }
}
