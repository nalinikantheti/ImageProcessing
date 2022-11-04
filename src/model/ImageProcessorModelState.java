package model;

import java.util.Set;

import image.Image;

/**
 * An ImageProcessorModelState that only allows users to make observations of Images.
 */
public interface ImageProcessorModelState {
  /**
   * Gets the image with the given name stored in the model.
   *
   * @param name the name of the image
   * @return an image
   */
  Image getImage(String name);

  /**
   * Gets the names of all the images stored in this model.
   *
   * @return a Set of Strings containing all the names of the images
   */

  Set<String> getImageNames();

}
