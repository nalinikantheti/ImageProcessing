package util;

import java.awt.image.BufferedImage;

import image.BufferWrapper;
import image.Image;
import image.Pixel;

/**
 * A utilities class for methods that might be used throughout the program.
 */
public class ImageProcessorUtils {
  /**
   * Ensures that the given object is not null. If it is null,
   * throws a new IllegalArgumentException with the given
   * message.
   *
   * @param obj     the object to null check
   * @param message the message of the thrown IllegalArgumentException, if any
   * @param <T>     the type of object to null check
   * @throws IllegalArgumentException if the object is null
   */
  public static <T> void ensureNotNull(T obj, String message) {
    if (obj == null) {
      throw new IllegalArgumentException(message);
    }
  }
  //TODO test imageToBuffer
  public static BufferedImage imageToBuffer(Image image){
    BufferedImage buffer = new BufferedImage(image.getWidth(), image.getHeight(),
            BufferedImage.TYPE_INT_RGB);
    BufferWrapper wrapper = new BufferWrapper(buffer);
    for (int x = 0; x < image.getWidth(); x += 1) {
      for (int y = 0; y < image.getHeight(); y += 1) {
        wrapper.setPixel(image.getPixel(x, y), x, y);
      }
    }
    return buffer;
  }



  /**
   * Takes in red, green, and blue values and clamps them in the range [0,255]
   * so that each value is valid for a pixel.
   *
   * @param red   the red value
   * @param green the green value
   * @param blue  the blue value
   * @return a valid pixel with clamped values
   */
  public static Pixel createValidPixel(int red, int green, int blue) {
    red = clamp(red, 0, 255);
    green = clamp(green, 0, 255);
    blue = clamp(blue, 0, 255);

    return new Pixel(red, green, blue);

  }

  private static int clamp(int value, int min, int max) {
    if (value < min) {
      return min;
    }

    if (value > max) {
      return max;
    }

    return value;
  }
}
