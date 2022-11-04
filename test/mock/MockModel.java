package mock;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import image.Image;
import image.RGBImage;
import model.ImageProcessorModel;

/**
 * A mock of an ImageProcessorModel that writes all actions to a log.
 */
public class MockModel implements ImageProcessorModel {
  private StringBuilder log;

  private Image lastSavedImage;
  private Image dummy;

  /**
   * Creates a MockModel that logs to the given StringBuilder.
   *
   * @param ap the StringBuilder to log to
   */

  public MockModel(StringBuilder ap) {
    this.log = ap;
    lastSavedImage = new RGBImage(0, 0);
    this.dummy = new RGBImage(0, 0);
  }

  /**
   * Creates a MockModel that logs to the given StringBuilder
   * and returns the given image whenever getImage is called.
   *
   * @param ap    the string builder to log to
   * @param dummy the image to return on getImage calls
   */
  public MockModel(StringBuilder ap, Image dummy) {
    this.log = ap;
    lastSavedImage = new RGBImage(0, 0);
    this.dummy = dummy;
  }

  /**
   * Logs that this method was called with the given arguments.
   *
   * @param name     the name of the image to save
   * @param filepath the filepath of the image to save
   * @throws IOException if writing to appendable fails.
   */
  @Override
  public void saveImageToFileSystem(String name, String filepath) throws IOException {
    log.append("saved " + name + " to filepath: " + filepath + "\n");
  }

  /**
   * Logs that this method was called with the given arguments.
   *
   * @param image the image to store
   * @param name  the name to give this image
   */
  @Override
  public void saveImageToModel(Image image, String name) {
    log.append("saved " + name + " to model" + "\n");
    this.lastSavedImage = image;
  }

  /**
   * Logs that this method was called with the given arguments.
   *
   * @param filepath the filepath to read the image from
   * @param name     the name to give the image
   * @throws FileNotFoundException never
   */
  @Override
  public void loadImage(String filepath, String name) throws FileNotFoundException {
    log.append("loaded: " + name + " from filepath: " + filepath + "\n");
  }

  /**
   * Logs that this method was called with the given arguments.
   *
   * @param name the name of the image to remove
   */
  @Override
  public void removeImage(String name) {
    log.append("removed: " + name + "\n");
  }

  /**
   * Logs that this method was called with the given arguments.
   *
   * @param name the name of the image
   * @return the given dummy image
   */
  @Override
  public Image getImage(String name) {
    log.append("retrieved: " + name + "\n");
    return this.dummy;
  }

  /**
   * Returns an empty hashset and logs that this method was called.
   *
   * @return an empty hashset
   */
  @Override
  public Set<String> getImageNames() {
    log.append("returned set of all image names\n");
    return new HashSet<String>();
  }

  /**
   * Gets the image last saved to this mock model.
   *
   * @return the last saved image
   */
  public Image getLastSavedImage() {
    return this.lastSavedImage;
  }
}
