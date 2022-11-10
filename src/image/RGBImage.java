package image;

import java.util.ArrayList;

import static util.ImageProcessorUtils.ensureNotNull;

/**
 * An Image made up pixels with red, green, and blue values.
 */
public class RGBImage extends AbstractImage {
  private ArrayList<ArrayList<Pixel>> pixels;

  /**
   * A Constructor for an RGBImage that takes in an ArrayList<\ArrayList<\Pixel>>,
   * ensures it's not null, checks that it is a rectangular array, and then copies
   * each column from the given ArrayList<\ArrayList<\Pixel>> to this ArrayList<\ArrayList<\Pixel>>.
   *
   * @param image the ArrayList<\ArrayList<\Pixel>> to copy into our image.
   */
  public RGBImage(ArrayList<ArrayList<Pixel>> image) {
    ensureNotNull(image, "Image Array cannot be null.");
    this.pixels = new ArrayList<>();

    int colHeight;
    if (image.size() > 0) {
      colHeight = image.get(0).size();
    } else {
      colHeight = 0;
    }
    for (ArrayList<Pixel> column : image) {
      if (column.size() != colHeight) {
        throw new IllegalArgumentException("Given pixel array is not well formed.");
      }
      pixels.add(new ArrayList<>(column));
    }
  }

  /**
   * Constructor for an RGBImage that takes in a width and height, and then constructs
   * and ArrayList<\ArrayList<\Pixel>> of that width and height, while setting the RGB values
   * of each pixel to 0 0 0.
   *
   * @param width  the width of this RBGImage.
   * @param height the height of this RBGImage.
   */
  public RGBImage(int width, int height) {
    pixels = new ArrayList<>();
    for (int x = 0; x < width; x += 1) {
      ArrayList<Pixel> column = new ArrayList<>();
      pixels.add(column);
      for (int y = 0; y < height; y += 1) {
        column.add(new Pixel(0, 0, 0));
      }
    }
  }

  /**
   * Changes a pixel at the given position in this image to the given pixel,
   * ensures the given pixel is not null, and ensures the given x and y
   * values are in bounds of this image.
   *
   * @param pix the pixel to be added to this image.
   * @param x   the x position of the pixel to be changed.
   * @param y   the y position of the pixel to be changed.
   */
  @Override
  public void setPixel(Pixel pix, int x, int y) {
    ensureNotNull(pix, "Pixel cannot be null.");
    ensureInBounds(x, y);
    pixels.get(x).set(y, pix);
  }

  /**
   * ensures given position is in bounds of this image, and
   * retrieves the pixel at the given position in this image.
   *
   * @param x the x position of the pixel to be retrieved.
   * @param y the y position of the pixel to be retrieved.
   * @return the pixel at the given position.
   */
  @Override
  public Pixel getPixel(int x, int y) {
    ensureInBounds(x, y);
    return pixels.get(x).get(y);
  }

  /**
   * Retrieves the height of this image.
   *
   * @return the size of the first row of this image.
   */
  @Override
  public int getHeight() {
    return pixels.get(0).size();
  }

  /**
   * Retrieves the width of this image.
   *
   * @return the size of the entire image.
   */
  @Override
  public int getWidth() {
    return pixels.size();
  }

  /**
   * Produces an identical copy of this image, by creating
   * a new image with this ArrayList<\ArrayList<\Pixel>>.
   *
   * @return a copy of this image.
   */
  @Override
  public Image clone() {
    return new RGBImage(this.pixels);
  }
}
