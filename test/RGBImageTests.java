import image.Image;
import image.Pixel;
import image.RGBImage;
import org.junit.Before;
import org.junit.Test;
import util.ImageUtil;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Tests for {@link RGBImageTests}.
 */
public class RGBImageTests {
    private Pixel black;
    private Pixel grey;
    private Pixel white;
    private Pixel red;
    private Pixel green;
    private Pixel blue;


    @Before
    public void setup() {
        black = new Pixel(0, 0, 0);
        grey = new Pixel(127, 127, 127);
        white = new Pixel(255, 255, 255);
        red = new Pixel(255, 0, 0);
        green = new Pixel(0, 255, 0);
        blue = new Pixel(0, 0, 255);
    }

    @Test
    public void testMain() {
        Image image = new RGBImage(2, 3);
        Pixel expected = new Pixel(0, 0, 0);
        assertEquals(2, image.getWidth());
        assertEquals(3, image.getHeight());
        assertEquals(expected, image.getPixel(0, 0));
        assertEquals(expected, image.getPixel(0, 1));
        assertEquals(expected, image.getPixel(0, 2));
        assertEquals(expected, image.getPixel(1, 0));
        assertEquals(expected, image.getPixel(1, 1));
        assertEquals(expected, image.getPixel(1, 2));

        image.setPixel(new Pixel(255, 127, 0), 1, 2);
        assertEquals(new Pixel(255, 127, 0), image.getPixel(1, 2));
    }

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
        assertThrows(IllegalArgumentException.class,
                () -> new RGBImage(pixels2));
        assertThrows(IllegalArgumentException.class,
                () -> new RGBImage(null));

        new RGBImage(new ArrayList<>()); //makes sure that creation is successful
    }

    @Test
    public void testClone() {
        Image oldImage = new RGBImage(3, 2);
        oldImage.setPixel(new Pixel(0, 0, 255), 2, 1);
        Image newImage = oldImage.clone();

        for (int x = 0; x < oldImage.getWidth(); x += 1) {
            for (int y = 0; y < oldImage.getHeight(); y += 1) {
                assertEquals(oldImage.getPixel(x, y), newImage.getPixel(x, y));
            }
        }

        assertEquals(3, newImage.getWidth());
        assertEquals(2, newImage.getHeight());

        oldImage.setPixel(new Pixel(255, 0, 0), 1, 1);

        assertFalse(oldImage.getPixel(1, 1).equals(newImage.getPixel(1, 1)));
    }

    @Test
    public void testImageThrows() {
        Image image = new RGBImage(2, 3);

        assertThrows(IllegalArgumentException.class,
                () -> image.getPixel(1, 3));
        assertThrows(IllegalArgumentException.class,
                () -> image.getPixel(5, 2));
        assertThrows(IllegalArgumentException.class,
                () -> image.getPixel(-3, 2));
        assertThrows(IllegalArgumentException.class,
                () -> image.getPixel(1, -300));
        assertThrows(IllegalArgumentException.class,
                () -> image.getPixel(2, 2));

        Pixel pix = new Pixel(255, 255, 255);
        assertThrows(IllegalArgumentException.class,
                () -> image.setPixel(pix, 1, 3));
        assertThrows(IllegalArgumentException.class,
                () -> image.setPixel(pix, 5, 2));
        assertThrows(IllegalArgumentException.class,
                () -> image.setPixel(pix, -3, 2));
        assertThrows(IllegalArgumentException.class,
                () -> image.setPixel(pix, 1, -300));
        assertThrows(IllegalArgumentException.class,
                () -> image.setPixel(pix, 2, 2));

        assertThrows(IllegalArgumentException.class,
                () -> image.setPixel(null, 1, 2));
    }

    @Test
    public void testPixelThrows() {
        assertThrows(IllegalArgumentException.class,
                () -> new Pixel(-3, 128, 233));
        assertThrows(IllegalArgumentException.class,
                () -> new Pixel(0, -499, 233));
        assertThrows(IllegalArgumentException.class,
                () -> new Pixel(3, 128, -233));

        assertThrows(IllegalArgumentException.class,
                () -> new Pixel(333, 128, 233));
        assertThrows(IllegalArgumentException.class,
                () -> new Pixel(33, 1283, 233));
        assertThrows(IllegalArgumentException.class,
                () -> new Pixel(33, 128, 2334));
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


}
