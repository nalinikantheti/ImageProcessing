import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;

import image.Image;
import image.Pixel;
import image.RGBImage;
import model.ImageProcessorModel;
import model.ImageProcessorModelImpl;

import static org.junit.Assert.*;

/**
 * Tests for {@link ImageProcessorModelImpl}.
 */
public class ModelTests {
  private ImageProcessorModel model;
  private Pixel black;

  private Pixel grey;

  private Pixel white;
  private Pixel red;
  private Pixel green;
  private Pixel blue;

  @Before
  public void setup() {
    model = new ImageProcessorModelImpl();
    black = new Pixel(0, 0, 0);
    grey = new Pixel(127, 127, 127);
    white = new Pixel(255, 255, 255);
    red = new Pixel(255, 0, 0);
    green = new Pixel(0, 255, 0);
    blue = new Pixel(0, 0, 255);
  }

  @Test
  public void testMain() {
    Image image = new RGBImage(3, 4);
    image.setPixel(new Pixel(255, 0, 0), 1, 2);

    model.saveImageToModel(image, "red");

    assertNotEquals(image, model.getImage("red")); //TESTS IT CLONES HAHAHAHAAHAHA
    TestUtils.assertImageEquals(image, model.getImage("red"));
    Set<String> names = model.getImageNames();
    assertEquals(1, names.size());
    assertTrue(names.contains("red"));

    model.removeImage("red");

    names = model.getImageNames();
    assertEquals(0, names.size());
    assertFalse(names.contains("red"));
  }

  @Test
  public void testThrows() {
    assertThrows(IllegalArgumentException.class, () -> model.saveImageToModel(null,
            "red"));
    assertThrows(IllegalArgumentException.class, () -> model.getImage("red"));
    assertThrows(IllegalArgumentException.class, () -> model.removeImage("red"));

//    assertThrows(IllegalArgumentException.class, () -> model.saveImageToFileSystem("red",
//            "bread.ppm"));

  }




}
