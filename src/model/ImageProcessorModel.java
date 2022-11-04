package model;

import image.Image;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * An ImageProcessorModel that allows users to perform operations on Images.
 */
public interface ImageProcessorModel extends ImageProcessorModelState {
    /**
     * Saves the image with the given name to the filesystem.
     * @param name the name of the image to save
     * @param filepath the filepath of the image to save
     * @throws IOException if the iamge cannot be saved
     */
    void saveImageToFileSystem(String name, String filepath) throws IOException;

    /**
     * Stores an image in the model.
     * @param image the image to store
     * @param name the name to give this image
     */

    void saveImageToModel(Image image, String name);

    /**
     * Loads an image from the filesystem into the model with the given name.
     * @param filepath the filepath to read the image from
     * @param name the name to give the image
     * @throws FileNotFoundException if the file cannot be found
     */
    void loadImage(String filepath, String name) throws FileNotFoundException;

    /**
     * Removes the given image from this model.
     * @param name the name of the image to remove
     */

    void removeImage(String name);
}
