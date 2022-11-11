package image;

/**
 * An Abstract class for representing an Image.
 */
public abstract class AbstractImage implements Image {
  /**
   * Ensures the given coordinates are inbounds of this image.
   *
   * @param x the x coordinate to be checked.
   * @param y the y coordinate to be checked.
   */
  protected void ensureInBounds(int x, int y) {
    if (x < 0 || x >= this.getWidth() || y < 0 || y >= this.getHeight()) {
      throw new IllegalArgumentException("Coordinates out of bounds.");
    }
  }

  /**
   * returns a copy of this image.
   *
   * @return a new Image identical to this one.
   */
  @Override
  public abstract Image clone();
}
