import command.*;
import image.Image;
import image.RGBImage;
import model.ImageProcessorModel;
import org.junit.Before;
import org.junit.Test;
import util.ImageUtil;

import java.io.FileNotFoundException;

import static org.junit.Assert.*;

public class CommandTests {
    private StringBuilder log;
    private MockModel mock;
    private Image blerner;
    private Image koala;


    @Before
    public void setup() throws FileNotFoundException {
        log = new StringBuilder();
        blerner = ImageUtil.readPPM("blerner_young.ppm");
        koala = ImageUtil.readPPM("Koala.ppm");
        mock = new MockModel(log, koala);
    }

    public void assertImageEquals(Image expected, Image actual) {
        assertEquals(expected.getWidth(), actual.getWidth());
        assertEquals(expected.getHeight(), actual.getHeight());

        for (int x = 0; x < expected.getWidth(); x++) {
            for (int y = 0; y < expected.getHeight(); y++) {
//                assertEquals(expected.getPixel(x, y), actual.getPixel(x, y));
                if (!expected.getPixel(x, y).equals(actual.getPixel(x, y))) {
                    throw new AssertionError("[" + x + ", " + y + "]: "
                            + "expected: " + expected.getPixel(x, y).toString() + "; "
                            + "actual: " + actual.getPixel(x, y).toString());
                }
            }
        }
    }

    @Test
    public void testAssertImageEquals() throws FileNotFoundException {
        assertThrows(AssertionError.class,
                () -> assertImageEquals(blerner, koala));
        assertImageEquals(blerner, ImageUtil.readPPM("blerner_young.ppm"));
    }


    public void runTest(Command command, Image expectedImage) {
        command.go(mock);
        assertImageEquals(expectedImage, mock.getLastSavedImage());
    }

    @Test
    public void testBrightenCommand() throws FileNotFoundException {
        Command brighten = new BrightenCommand("original", 50, "original-bright");
        Command brightenMax = new BrightenCommand("original", 1000, "original-bright-max");

        Image koalaBright = ImageUtil.readPPM("koala-brighter-by-50.ppm");
        Image blernerBright = ImageUtil.readPPM("blerner_young_brightened.ppm");
        Image blernerBrightMax = ImageUtil.readPPM("blerner_young_brightened_max.ppm");

        runTest(brighten, koalaBright);
      //  runTest(brightenMax, blernerBrightMax);
    }

    @Test
    public void testDarkenCommand() throws FileNotFoundException {

        Command darken = new DarkenCommand("original", 50, "original-dark");
        Command darkenMax = new DarkenCommand("original", 1000, "original-dark-max");

        Image blernerDark = ImageUtil.readPPM("blerner_young_darkened.ppm");
        Image blernerDarkMax = ImageUtil.readPPM("blerner_young_darkened_max.ppm");

      //  runTest(darken, blernerDark);
      //  runTest(darkenMax, blernerDarkMax);
    }


    @Test
    public void testFlipHorizontalCommand() throws FileNotFoundException {
        Image blernerFlipHorizontal = ImageUtil.readPPM("blerner_young_flipped_horizontal.ppm");
        Image koalaFlipHorizontal = ImageUtil.readPPM("koala-horizontal.ppm");

        Command flipHorizontal = new FlipHorizontalCommand("original", "original-flip-horizontal");

        runTest(flipHorizontal, koalaFlipHorizontal);
    }

    @Test
    public void testFlipVerticalCommand() throws FileNotFoundException {
        Image blernerFlipVertical = ImageUtil.readPPM("blerner_young_flipped_vertical.ppm");
        Image koalaFlipVertical = ImageUtil.readPPM("koala-vertical.ppm");

        Command flipVertical = new FlipVerticalCommand("original", "original-flip-vertical");

        runTest(flipVertical, koalaFlipVertical);
    }

    @Test
    public void testLumaCommand() throws FileNotFoundException {
        Image blernerLuma = ImageUtil.readPPM("blerner_young_greyscale_luma.ppm");
        Image koalaLuma = ImageUtil.readPPM("koala-luma-greyscale.ppm");

        Command luma = new LumaCommand("original", "original-luma");

        runTest(luma, koalaLuma);
    }

    @Test
    public void testValueCommand() throws FileNotFoundException {
        // Image blernerValue = ImageUtil.readPPM("blerner_young_greyscale_value.ppm");
        Image koalaValue = ImageUtil.readPPM("koala-value-greyscale.ppm");

        Command value = new ValueCommand("original", "original-value");

        runTest(value, koalaValue);
    }

    @Test
    public void testIntensityCommand() throws FileNotFoundException {
        Image blernerIntensity = ImageUtil.readPPM("blerner_young_greyscale_intensity.ppm");
        Image koalaIntensity = ImageUtil.readPPM("koala-intensity-greyscale.ppm");

        Command intensity = new IntensityCommand("original", "original-intensity");

        runTest(intensity, koalaIntensity);
    }

    @Test
    public void testGreyScaleRedCommand() throws FileNotFoundException {
        Image blernerGreyScaleRed = ImageUtil.readPPM("blerner_young_greyscale_red.ppm");
        Image koalaGreyScaleRed = ImageUtil.readPPM("koala-red-greyscale.ppm");

        Command greyScaleRed = new GreyScaleRedCommand("original", "original-greyscale-red");

        Image koalaIntensity = ImageUtil.readPPM("koala-intensity-greyscale.ppm");
        runTest(greyScaleRed, koalaGreyScaleRed);
    }

    @Test
    public void testGreyScaleGreenCommand() throws FileNotFoundException {
        Image blernerGreyScaleGreen = ImageUtil.readPPM("blerner_young_greyscale_green.ppm");
        Image koalaGreyScaleGreen = ImageUtil.readPPM("koala-green-greyscale.ppm");

        Command greyScaleGreen = new GreyScaleGreenCommand("original", "original-greyscale-green");


        runTest(greyScaleGreen, koalaGreyScaleGreen);
    }

    @Test
    public void testGreyScaleBlueCommand() throws FileNotFoundException {
        Image blernerGreyScaleBlue = ImageUtil.readPPM("blerner_young_greyscale_blue.ppm");
        Image koalaGreyScaleBlue = ImageUtil.readPPM("koala-blue-greyscale.ppm");

        Command greyScaleBlue = new GreyScaleBlueCommand("original", "original-greyscale-blue");



        runTest(greyScaleBlue, koalaGreyScaleBlue);
    }

    @Test
    public void testLoad() {

        Command load = new LoadCommand("./blerner.ppm", "blerner-goat");

        load.go(mock);
        assertEquals(log.toString(), "loaded: blerner-goat from filepath: ./blerner.ppm\n");
    }

    @Test
    public void testSave() {
        Command save = new SaveCommand("./C:", "blerner.ppm");

        save.go(mock);
        assertEquals(log.toString(), "saved blerner.ppm to filepath: ./C:\n");
    }


}
