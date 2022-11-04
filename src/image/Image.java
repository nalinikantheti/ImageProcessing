package image;

/**
 * A rectangular Image represented by an ArrayList<\ArrayList<\Pixel>>.
 */
public interface Image {
  /**
   * Sets pixel at the given x and y coordinate.
   *
   * @param pix the pixel
   * @param x   the x coordinate
   * @param y   the y coordinate
   */

  void setPixel(Pixel pix, int x, int y);

  /**
   * Gets a pixel at a given x and y coordinate.
   *
   * @param x the x coordinate
   * @param y the y coordinate
   * @return the pixel at those coordinates
   */

  Pixel getPixel(int x, int y);

  /**
   * Returns the height of the image.
   *
   * @return the height of the image
   */

  int getHeight();

  /**
   * Returns the width of the image.
   *
   * @return the width of the image
   */

  int getWidth();

  /**
   * Creates a deep copy of this image.
   *
   * @return a copy of this image
   */
  Image clone();
}
