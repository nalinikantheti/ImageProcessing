import org.junit.Assert;

import image.Image;

/**
 * Utils Class for tests.
 */
public class TestUtils {

  /**
   * Test to determine if two images are equal.
   *
   * @param expected expected image
   * @param actual   actual image
   */
  public static void assertImageEquals(Image expected, Image actual) {
    Assert.assertEquals(expected.getWidth(), actual.getWidth());
    Assert.assertEquals(expected.getHeight(), actual.getHeight());

    for (int x = 0; x < expected.getWidth(); x++) {
      for (int y = 0; y < expected.getHeight(); y++) {
        if (!expected.getPixel(x, y).equals(actual.getPixel(x, y))) {
          throw new AssertionError("[" + x + ", " + y + "]: "
                  + "expected: " + expected.getPixel(x, y).toString() + "; "
                  + "actual: " + actual.getPixel(x, y).toString());
        }
      }
    }
  }
}