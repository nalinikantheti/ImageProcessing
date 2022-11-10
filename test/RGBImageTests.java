import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import image.Image;
import image.Pixel;
import image.RGBImage;
import util.ImageUtil;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

/**
 * Tests for {@link RGBImageTests}.
 */
public class RGBImageTests extends ImageTests {


  @Test
  public void testPixelEquals() {
    Pixel pix1 = new Pixel(0, 1, 2);
    Pixel pix2 = new Pixel(4, 5, 6);
    Pixel pix3 = new Pixel(0, 1, 2);

    assertTrue(pix1.equals(pix3));
    assertTrue(pix1.equals(pix1));
    assertFalse(pix1.equals(pix2));
  }

  @Test
  public void testPixelHashCode() {
    Pixel pix1 = new Pixel(0, 1, 2);
    Pixel pix2 = new Pixel(4, 5, 6);
    Pixel pix3 = new Pixel(0, 1, 2);

    assertTrue(pix3.hashCode() == pix1.hashCode());
    assertTrue(pix1.hashCode() == pix1.hashCode());
    assertFalse(pix1.hashCode() == pix2.hashCode());
  }

  @Test
  public void testArrayConstructor() {
    ArrayList<ArrayList<Pixel>> pixels = new ArrayList<ArrayList<Pixel>>();
    Pixel pixel = new Pixel(33, 33, 33);
    pixels.add(new ArrayList<>());
    pixels.get(0).add(pixel);
    pixels.get(0).add(pixel);
    pixels.get(0).add(pixel);
    pixels.add(new ArrayList<>());
    pixels.get(1).add(pixel);
    pixels.get(1).add(pixel);
    pixels.get(1).add(pixel);

    Pixel expected = new Pixel(33, 33, 33);
    Image image = new RGBImage(pixels);

    assertEquals(2, image.getWidth());
    assertEquals(3, image.getHeight());
    assertEquals(expected, image.getPixel(0, 0));
    assertEquals(expected, image.getPixel(0, 1));
    assertEquals(expected, image.getPixel(0, 2));
    assertEquals(expected, image.getPixel(1, 0));
    assertEquals(expected, image.getPixel(1, 1));
    assertEquals(expected, image.getPixel(1, 2));

    pixels.get(0).clear();
    pixels.clear();

    assertEquals(2, image.getWidth());
    assertEquals(3, image.getHeight());
    assertEquals(expected, image.getPixel(0, 0));
    assertEquals(expected, image.getPixel(0, 1));
    assertEquals(expected, image.getPixel(0, 2));
    assertEquals(expected, image.getPixel(1, 0));
    assertEquals(expected, image.getPixel(1, 1));
    assertEquals(expected, image.getPixel(1, 2));

    pixels.add(new ArrayList<>());

    ArrayList<ArrayList<Pixel>> pixels2 = new ArrayList<>();
    pixels2.add(new ArrayList<>());
    pixels2.add(new ArrayList<>());
    pixels2.get(1).add(new Pixel(0, 0, 0));
    pixels2.get(1).add(new Pixel(0, 0, 0));
    assertThrows(IllegalArgumentException.class, () -> new RGBImage(pixels2));
    assertThrows(IllegalArgumentException.class, () -> new RGBImage(null));

    new RGBImage(new ArrayList<>()); //makes sure that creation is successful
  }

  @Test
  public void testPixelThrows() {
    assertThrows(IllegalArgumentException.class, () -> new Pixel(-3, 128, 233));
    assertThrows(IllegalArgumentException.class, () -> new Pixel(0, -499, 233));
    assertThrows(IllegalArgumentException.class, () -> new Pixel(3, 128, -233));

    assertThrows(IllegalArgumentException.class, () -> new Pixel(333, 128, 233));
    assertThrows(IllegalArgumentException.class, () -> new Pixel(33, 1283, 233));
    assertThrows(IllegalArgumentException.class, () -> new Pixel(33, 128, 2334));
  }

  @Test
  public void testImageUtilReadPPM() throws FileNotFoundException {
    Image image = ImageUtil.readPPM("./res/sixbit/test.ppm");

    assertEquals(black, image.getPixel(0, 0));
    assertEquals(grey, image.getPixel(1, 0));
    assertEquals(white, image.getPixel(2, 0));
    assertEquals(red, image.getPixel(0, 1));
    assertEquals(green, image.getPixel(1, 1));
    assertEquals(blue, image.getPixel(2, 1));
  }


  @Override
  protected Image getImage(int width, int height) {
    return new RGBImage(width, height);
  }
}
