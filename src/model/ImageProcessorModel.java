package model;

import image.Image;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * An ImageProcessorModel that allows users to perform operations on Images.
 */
public interface ImageProcessorModel extends ImageProcessorModelState {
    void saveImageToFileSystem(String name, String filepath) throws IOException;

    void saveImageToModel(Image image, String name);

    void loadImage(String filepath, String name) throws FileNotFoundException;

    void removeImage(String name);
}
