import image.Image;
import image.Pixel;
import image.RGBImage;
import model.ImageProcessorModel;
import model.ImageProcessorModelImpl;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;

import static org.junit.Assert.*;

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
        black = new Pixel(0,0,0);
        grey = new Pixel(127, 127, 127);
        white = new Pixel(255, 255,255);
        red = new Pixel(255,0,0);
        green = new Pixel(0, 255, 0);
        blue = new Pixel(0,0,255);
    }
    @Test
    public void testMain() {
        Image image = new RGBImage(3,4);
        image.setPixel(new Pixel(255,0,0), 1,2);

        model.saveImageToModel(image, "red");

        assertEquals(image, model.getImage("red"));
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
        assertThrows(IllegalArgumentException.class,
                () -> model.saveImageToModel(null, "red"));
        assertThrows(IllegalArgumentException.class,
                () -> model.getImage("red"));
        assertThrows(IllegalArgumentException.class,
                () -> model.removeImage("red"));

        assertThrows(IllegalArgumentException.class,
                () -> model.saveImageToFileSystem("red", "bread.ppm"));
        assertThrows(FileNotFoundException.class,
                () -> model.loadImage("nonexistent.ppm", "bad"));
    }

    @Test
    public void testLoad() throws FileNotFoundException {
        model.loadImage("test.ppm","test");
        Image image = model.getImage("test");

        assertEquals(black, image.getPixel(0,0));
        assertEquals(grey, image.getPixel(1,0));
        assertEquals(white, image.getPixel(2,0));
        assertEquals(red, image.getPixel(0,1));
        assertEquals(green, image.getPixel(1,1));
        assertEquals(blue, image.getPixel(2,1));
    }

    @Test
    public void testSaveToFileSystem() throws IOException {
        Image image = new RGBImage(2,2);
        image.setPixel(blue, 0,1);

        Path path = Paths.get("blue.ppm");

        assertTrue(Files.notExists(path));

        model.saveImageToModel(image, "blue");
        model.saveImageToFileSystem("blue", "blue.ppm");

        assertTrue(Files.exists(path));

        model.loadImage("blue.ppm", "blue2");
        Image newImage = model.getImage("blue2");

        assertEquals(black, newImage.getPixel(0,0));
        assertEquals(blue, newImage.getPixel(0,1));
        assertEquals(black, newImage.getPixel(1,0));
        assertEquals(black, newImage.getPixel(1,1));

        Files.delete(path);
    }
}
