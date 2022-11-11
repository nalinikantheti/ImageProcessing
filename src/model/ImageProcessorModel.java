package model;

import image.Image;

/**
 * An ImageProcessorModel that allows users to perform operations on Images.
 */
public interface ImageProcessorModel extends ImageProcessorModelState {

  /**
   * Stores an image in the model.
   *
   * @param image the image to store
   * @param name  the name to give this image
   */

  void saveImageToModel(Image image, String name);


  /**
   * Removes the given image from this model.
   *
   * @param name the name of the image to remove
   */

  void removeImage(String name);
}
