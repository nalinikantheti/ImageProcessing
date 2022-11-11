package model;

import java.util.HashMap;
import java.util.Set;

import image.Image;

import static util.ImageProcessorUtils.ensureNotNull;

/**
 * A model that allows users to make observations about images and
 * perform operations on them such as load and save. All other
 * operations are handled by the command function objects.
 */
public class ImageProcessorModelImpl implements ImageProcessorModel {
  private HashMap<String, Image> imageNames;


  /**
   * A constructor for an ImageProcessorModelImpl
   * that just initializes this Hashmap of ImageNames
   * to a new empty HashMap<>().
   */
  public ImageProcessorModelImpl() {
    imageNames = new HashMap<>();
  }

  /**
   * Saves a given image to this model by adding it to this
   * Hashmap<\String,Image>, and ensures given image is not null.
   *
   * @param image image to be saved.
   * @param name  name by which image will be saved.
   */
  @Override
  public void saveImageToModel(Image image, String name) {
    ensureNotNull(image, "Image cannot be null.");
    imageNames.put(name, image);
  }

  /**
   * Ensures given image exists before
   * removing it from this HashMap<\String,Image>.
   *
   * @param name name of image to be removed.
   */
  @Override
  public void removeImage(String name) {
    ensureImageExists(name);
    imageNames.remove(name);
  }

  /**
   * Retrieves the image of the given name from this
   * HashMap<\String,Image> and ensures it exists.
   *
   * @param name name of image to be retrieved.
   * @return Image using given name.
   */
  @Override
  public Image getImage(String name) {
    //TODO: write test to check model is saving clones
    ensureImageExists(name);
    return imageNames.get(name).clone();
  }

  /**
   * Produces the set of all the names of the images in this HashMap<\String,Image>.
   *
   * @return a set of all the keys in this HashMap<\String, Image>.
   */
  @Override
  public Set<String> getImageNames() {
    return imageNames.keySet();
  }

  private void ensureImageExists(String name) {
    if (!imageNames.containsKey(name)) {
      throw new IllegalArgumentException("Image not found.");
    }
  }
}
