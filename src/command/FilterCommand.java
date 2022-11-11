package command;

import java.util.function.Function;

import image.Image;
import image.Pixel;
import model.ImageProcessorModel;

import static util.ImageProcessorUtils.createValidPixel;
import static util.ImageProcessorUtils.ensureNotNull;

/**
 * A Command that applies a given filter, or a double array of doubles to each pixel of an image.
 */
public abstract class FilterCommand implements Command {
  protected final String imageName;
  protected final String newName;
  protected final double[][] filter;

  /**
   * A Constructor for a Filter Command with two arguments.
   *
   * @param imageName the name of the image the filter will be applied to.
   * @param newName   the name the processed image will be saved to.
   */
  public FilterCommand(String imageName, String newName) {
    this.imageName = imageName;
    this.newName = newName;
    this.filter = makeFilter();
    ensureNotNull(filter, "Filter cannot be null");
    ensureOddSquare(filter);
  }

  /**
   * Runs this FilterCommand by applying a given filter to each pixel of a given image.
   *
   * @param model model used to retrieve image for command to then process.
   */
  @Override
  public void run(ImageProcessorModel model) {
    Image image = model.getImage(imageName);
    Image newImage = image.clone();
    for (int x = 0; x < image.getWidth(); x += 1) {
      for (int y = 0; y < image.getHeight(); y += 1) {
        newImage.setPixel(applyFilter(image, x, y), x, y);
      }
    }
    model.saveImageToModel(newImage, newName);
  }

  /**
   * returns the filter that will be applied to this image.
   *
   * @return a double array of doubles for the filter.
   */
  protected abstract double[][] makeFilter();

  /**
   * ensures the given filter is of odd length and square shape.
   *
   * @param filter the filter that is checked to be well formed.
   */
  protected void ensureOddSquare(double[][] filter) {
    for (int x = 0; x < filter.length; x += 1) {
      if (filter.length != filter[x].length || filter.length % 2 == 0) {
        throw new IllegalArgumentException("Filter is not odd and square");
      }
    }
  }

  private Pixel applyFilter(Image image, int ox, int oy) {
    int[][] reds = getValues(image, ox, oy, p -> p.getRed());
    int[][] greens = getValues(image, ox, oy, p -> p.getGreen());
    int[][] blues = getValues(image, ox, oy, p -> p.getBlue());

    double red = multiply(reds);
    double green = multiply(greens);
    double blue = multiply(blues);

    return createValidPixel((int) red, (int) green, (int) blue);
  }

  private boolean inBounds(Image image, int ox, int oy) {
    return ox >= 0 && ox < image.getWidth() && oy >= 0 && oy < image.getHeight();
  }

  /**
   * produces a double array of integers of the specified size with all black pixels.
   *
   * @param width  the width of the array.
   * @param height the height of the array.
   * @return an array of black pixels.
   */
  protected int[][] make2dArray(int width, int height) {
    int[][] arr = new int[width][];
    for (int x = 0; x < width; x += 1) {
      arr[x] = new int[height];
    }
    return arr;
  }

  private int[][] getValues(Image image, int ox, int oy, Function<Pixel, Integer> selector) {
    int[][] color = make2dArray(filter.length, filter.length);
    int half = filter.length / 2;
    for (int x = ox - half, colorx = 0; x <= ox + half; x += 1, colorx += 1) {
      for (int y = oy - half, colory = 0; y <= oy + half; y += 1, colory += 1) {
        if (inBounds(image, x, y)) {
          color[colorx][colory] = selector.apply(image.getPixel(x, y));
        }
      }
    }
    return color;
  }

  private double multiply(int[][] values) {
    double result = 0;
    for (int x = 0; x < filter.length; x += 1) {
      for (int y = 0; y < filter.length; y += 1) {
        result += values[x][y] * filter[x][y];
      }
    }
    return result;
  }
}
