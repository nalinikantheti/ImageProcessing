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
    StringBuilder log;
    MockModel mock;
    Command brighten;
    Command brightenMax;
    Command darken;
    Command darkenMax;
    Command flipHorizontal;
    Command flipVertical;
    Command luma;
    Command value;
    Command intensity;
    Command greyScaleRed;
    Command greyScaleGreen;
    Command greyScaleBlue;
    Command load;
    Command save;
    Image blerner;
    Image blernerBright;
    Image blernerBrightMax;
    Image blernerDark;
    Image blernerDarkMax;
    Image blernerFlipHorizontal;
    Image blernerFlipVertical;
    Image blernerLuma;

    Image blernerValue;
    Image blernerIntensity;
    Image blernerGreyScaleRed;
    Image blernerGreyScaleGreen;
    Image blernerGreyScaleBlue;
    Image koala;
    Image koalaBright;
    Image koalaFlipHorizontal;
    Image koalaFlipVertical;
    Image koalaLuma;
    Image koalaValue;
    Image koalaIntensity;
    Image koalaGreyScaleRed;
    Image koalaGreyScaleGreen;
    Image koalaGreyScaleBlue;


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
        brighten = new BrightenCommand("original", 50, "original-bright");
        brightenMax = new BrightenCommand("original", 1000, "original-bright-max");

        koalaBright = ImageUtil.readPPM("koala-brighter-by-50.ppm");

        blernerBright = ImageUtil.readPPM("blerner_young_brightened.ppm");
        blernerBrightMax = ImageUtil.readPPM("blerner_young_brightened_max.ppm");

        runTest(brighten, koalaBright);
      //  runTest(brightenMax, blernerBrightMax);
    }

    @Test
    public void testDarkenCommand() throws FileNotFoundException {
        darken = new DarkenCommand("original", 50, "original-dark");
        darkenMax = new DarkenCommand("original", 1000, "original-dark-max");

        blernerDark = ImageUtil.readPPM("blerner_young_darkened.ppm");
        blernerDarkMax = ImageUtil.readPPM("blerner_young_darkened_max.ppm");

      //  runTest(darken, blernerDark);
      //  runTest(darkenMax, blernerDarkMax);
    }


    @Test
    public void testFlipHorizontalCommand() throws FileNotFoundException {
        flipHorizontal = new FlipHorizontalCommand("original", "original-flip-horizontal");

        blernerFlipHorizontal = ImageUtil.readPPM("blerner_young_flipped_horizontal.ppm");

        koalaFlipHorizontal = ImageUtil.readPPM("koala-horizontal.ppm");

        runTest(flipHorizontal, koalaFlipHorizontal);
    }

    @Test
    public void testFlipVerticalCommand() throws FileNotFoundException {
        blernerFlipVertical = ImageUtil.readPPM("blerner_young_flipped_vertical.ppm");

        koalaFlipVertical = ImageUtil.readPPM("koala-vertical.ppm");

        flipVertical = new FlipVerticalCommand("original", "original-flip-vertical");

        runTest(flipVertical, koalaFlipVertical);
    }

    @Test
    public void testLumaCommand() throws FileNotFoundException {
        blernerLuma = ImageUtil.readPPM("blerner_young_greyscale_luma.ppm");
        koalaLuma = ImageUtil.readPPM("koala-luma-greyscale.ppm");

        luma = new LumaCommand("original", "original-luma");

        runTest(luma, koalaLuma);
    }

    @Test
    public void testValueCommand() throws FileNotFoundException {
        //  blernerValue = ImageUtil.readPPM("blerner_young_greyscale_value.ppm");

        koalaValue = ImageUtil.readPPM("koala-value-greyscale.ppm");

        value = new ValueCommand("original", "original-value");

        runTest(value, koalaValue);
    }

    @Test
    public void testIntensityCommand() throws FileNotFoundException {
        blernerIntensity = ImageUtil.readPPM("blerner_young_greyscale_intensity.ppm");

        koalaIntensity = ImageUtil.readPPM("koala-intensity-greyscale.ppm");

        intensity = new IntensityCommand("original", "original-intensity");

        runTest(intensity, koalaIntensity);
    }

    @Test
    public void testGreyScaleRedCommand() throws FileNotFoundException {
        blernerGreyScaleRed = ImageUtil.readPPM("blerner_young_greyscale_red.ppm");

        koalaGreyScaleRed = ImageUtil.readPPM("koala-red-greyscale.ppm");

        greyScaleRed = new GreyScaleRedCommand("original", "original-greyscale-red");

        koalaIntensity = ImageUtil.readPPM("koala-intensity-greyscale.ppm");
        runTest(greyScaleRed, koalaGreyScaleRed);
    }

    @Test
    public void testGreyScaleGreenCommand() throws FileNotFoundException {
        blernerGreyScaleGreen = ImageUtil.readPPM("blerner_young_greyscale_green.ppm");

        koalaGreyScaleGreen = ImageUtil.readPPM("koala-green-greyscale.ppm");

        greyScaleGreen = new GreyScaleGreenCommand("original", "original-greyscale-green");



        runTest(greyScaleGreen, koalaGreyScaleGreen);
    }

    @Test
    public void testGreyScaleBlueCommand() throws FileNotFoundException {
        blernerGreyScaleBlue = ImageUtil.readPPM("blerner_young_greyscale_blue.ppm");

        koalaGreyScaleBlue = ImageUtil.readPPM("koala-blue-greyscale.ppm");

        greyScaleBlue = new GreyScaleBlueCommand("original", "original-greyscale-blue");



        runTest(greyScaleBlue, koalaGreyScaleBlue);
    }

    @Test
    public void testLoad() {

        load = new LoadCommand("./blerner.ppm", "blerner-goat");

        load.go(mock);
        assertEquals(log.toString(), "loaded: blerner-goat from filepath: ./blerner.ppm\n");
    }

    @Test
    public void testSave() {
        save = new SaveCommand("./C:", "blerner.ppm");

        save.go(mock);
        assertEquals(log.toString(), "saved blerner.ppm to filepath: ./C:\n");
    }


}
