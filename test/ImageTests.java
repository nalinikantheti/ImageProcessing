import image.Image;
import image.Pixel;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Tests for {@link Image}.
 */
public abstract class ImageTests {
    protected Pixel black;
    protected Pixel grey;
    protected Pixel white;
    protected Pixel red;
    protected Pixel green;
    protected Pixel blue;

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
        Image image = getImage(2, 3);
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
    public void testSetPixel() {
        Image image = getImage(2,3);
        Pixel pixel = new Pixel(0, 255, 1);
        Pixel pixel2 = new Pixel(0, 255, 1);
        image.setPixel(pixel, 1,2);
        assertEquals(pixel2,image.getPixel(1,2));
    }

    @Test
    public void testClone() {
        Image oldImage = getImage(3, 2);
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
        Image image = getImage(2, 3);

        assertThrows(IllegalArgumentException.class, () -> image.getPixel(1, 3));
        assertThrows(IllegalArgumentException.class, () -> image.getPixel(5, 2));
        assertThrows(IllegalArgumentException.class, () -> image.getPixel(-3, 2));
        assertThrows(IllegalArgumentException.class, () -> image.getPixel(1, -300));
        assertThrows(IllegalArgumentException.class, () -> image.getPixel(2, 2));

        Pixel pix = new Pixel(255, 255, 255);
        assertThrows(IllegalArgumentException.class, () -> image.setPixel(pix, 1, 3));
        assertThrows(IllegalArgumentException.class, () -> image.setPixel(pix, 5, 2));
        assertThrows(IllegalArgumentException.class, () -> image.setPixel(pix, -3, 2));
        assertThrows(IllegalArgumentException.class, () -> image.setPixel(pix, 1, -300));
        assertThrows(IllegalArgumentException.class, () -> image.setPixel(pix, 2, 2));

        assertThrows(IllegalArgumentException.class, () -> image.setPixel(null, 1, 2));
    }

    /**
     * Gets an image to test on.
     * @param width width of image to be tested.
     * @param height height of image to be tested.
     * @return an Image.
     */
    protected abstract Image getImage(int width, int height);
}
