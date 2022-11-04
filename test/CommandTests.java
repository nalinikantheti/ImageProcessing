import command.*;
import image.Image;
import mock.MockModel;
import org.junit.Before;
import org.junit.Test;
import util.ImageUtil;

import java.io.FileNotFoundException;
import static org.junit.Assert.*;

/**
 * Tests all the functionalities of a commands.
 */
public class CommandTests {
    private StringBuilder log;
    private StringBuilder log2;
    private MockModel mock;

    private MockModel mock2;
    private Image sixbit;
    private Image koala;


    @Before
    public void setup() throws FileNotFoundException {
        log = new StringBuilder();
        log2 = new StringBuilder();
        sixbit = ImageUtil.readPPM("test.ppm");
        koala = ImageUtil.readPPM("Koala.ppm");
        mock = new MockModel(log, koala);
        mock2 = new MockModel(log2, sixbit);
    }

    private void assertImageEquals(Image expected, Image actual) {
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

    private void assertLog(StringBuilder log, String name, String newName) {
        assertEquals("retrieved: " + name +"\n"+"saved " + newName + " to model"+"\n", log.toString());
    }
    private void assertLog(StringBuilder log, String newName) {
        assertLog(log, "original", newName);
    }

    @Test
    public void testAssertImageEquals() throws FileNotFoundException {
        assertThrows(AssertionError.class,
                () -> assertImageEquals(sixbit, koala));
        assertImageEquals(sixbit, ImageUtil.readPPM("test.ppm"));
    }


    private void runTest(Command command, Image expectedImage, MockModel mock) {
        command.go(mock);
        assertImageEquals(expectedImage, mock.getLastSavedImage());
    }

    @Test
    public void testBrightenCommand() throws FileNotFoundException {
        Command brighten = new BrightenCommand("original", 50, "original-bright");
        Command brightenMax = new BrightenCommand("original", 1000, "original-bright-max");

        Image koalaBright = ImageUtil.readPPM("koala-brighter-by-50.ppm");
        Image sixbitBright = ImageUtil.readPPM("sixbit-brighten.ppm");
        Image sixbitBrightMax = ImageUtil.readPPM("sixbit-brighten-max.ppm");

        runTest(brighten, koalaBright, mock);
        runTest(brighten, sixbitBright, mock2);

        assertLog(log, "original", "original-bright");
        assertLog(log2, "original", "original-bright");
        log2.setLength(0);
        runTest(brightenMax, sixbitBrightMax, mock2);
        assertLog(log2, "original", "original-bright-max");
    }

    @Test
    public void testDarkenCommand() throws FileNotFoundException {

        Command darken = new DarkenCommand("original", 50, "original-dark");
        Command darkenMax = new DarkenCommand("original", 1000, "original-dark-max");

        Image sixbitDark = ImageUtil.readPPM("sixbit-darken.ppm");
        Image sixbitDarkMax = ImageUtil.readPPM("sixbit-darken-max.ppm");

        runTest(darken, sixbitDark, mock2);
        assertLog(log2, "original", "original-dark");
        log2.setLength(0);
        runTest(darkenMax, sixbitDarkMax, mock2);
        assertLog(log2, "original", "original-dark-max");
    }


    @Test
    public void testFlipHorizontalCommand() throws FileNotFoundException {
        Image sixbitFlipHorizontal = ImageUtil.readPPM("sixbit-horizontal-flip.ppm");
        Image koalaFlipHorizontal = ImageUtil.readPPM("koala-horizontal.ppm");

        Command flipHorizontal = new FlipHorizontalCommand("original", "original-flip-horizontal");

        runTest(flipHorizontal, koalaFlipHorizontal, mock);
        runTest(flipHorizontal, sixbitFlipHorizontal, mock2);
        assertLog(log, "original", "original-flip-horizontal");
        assertLog(log2, "original-flip-horizontal");
    }

    @Test
    public void testFlipVerticalCommand() throws FileNotFoundException {
        Image sixbitFlipVertical = ImageUtil.readPPM("sixbit-vertical-flip.ppm");
        Image koalaFlipVertical = ImageUtil.readPPM("koala-vertical.ppm");

        Command flipVertical = new FlipVerticalCommand("boriginal", "original-flip-vertical");

        runTest(flipVertical, koalaFlipVertical, mock);
        runTest(flipVertical, sixbitFlipVertical, mock2);
        assertLog(log, "boriginal", "original-flip-vertical");
        assertLog(log2, "boriginal", "original-flip-vertical");
    }

    @Test
    public void testLumaCommand() throws FileNotFoundException {
        Image sixbitLuma = ImageUtil.readPPM("sixbit-luma-greyscale.ppm");
        Image koalaLuma = ImageUtil.readPPM("koala-luma-greyscale.ppm");

        Command luma = new LumaCommand("hi", "original-luma");

        runTest(luma, sixbitLuma, mock2);
        assertLog(log2, "hi", "original-luma");

        runTest(luma, koalaLuma, mock);
        assertLog(log, "hi", "original-luma");
    }

    @Test
    public void testValueCommand() throws FileNotFoundException {
        Image sixbitValue = ImageUtil.readPPM("sixbit-value-greyscale.ppm");
        Image koalaValue = ImageUtil.readPPM("koala-value-greyscale.ppm");

        Command value = new ValueCommand("original", "original-value");

        runTest(value, sixbitValue, mock2);
        runTest(value, koalaValue, mock);
        assertLog(log, "original-value");
        assertLog(log2, "original-value");

    }

    @Test
    public void testIntensityCommand() throws FileNotFoundException {
        Image sixbitIntensity = ImageUtil.readPPM("sixbit-intensity-greyscale.ppm");
        Image koalaIntensity = ImageUtil.readPPM("koala-intensity-greyscale.ppm");

        Command intensity = new IntensityCommand("original", "original-intensity");

        runTest(intensity, koalaIntensity, mock);
        runTest(intensity, sixbitIntensity, mock2);
        assertLog(log, "original-intensity");
        assertLog(log2, "original-intensity");
    }

    @Test
    public void testGreyScaleRedCommand() throws FileNotFoundException {
        Image sixbitGreyScaleRed = ImageUtil.readPPM("sixbit-red-greyscale.ppm");
        Image koalaGreyScaleRed = ImageUtil.readPPM("koala-red-greyscale.ppm");

        Command greyScaleRed = new GreyScaleRedCommand("original", "original-red");

        runTest(greyScaleRed, koalaGreyScaleRed, mock);
        runTest(greyScaleRed, sixbitGreyScaleRed, mock2);
        assertLog(log, "original-red");
        assertLog(log2, "original-red");
    }

    @Test
    public void testGreyScaleGreenCommand() throws FileNotFoundException {
        Image sixbitGreyScaleGreen = ImageUtil.readPPM("sixbit-green-greyscale.ppm");
        Image koalaGreyScaleGreen = ImageUtil.readPPM("koala-green-greyscale.ppm");

        Command greyScaleGreen = new GreyScaleGreenCommand("original", "original-greyscale-green");


        runTest(greyScaleGreen, koalaGreyScaleGreen, mock);
        runTest(greyScaleGreen, sixbitGreyScaleGreen, mock2);
        assertLog(log, "original-greyscale-green");
        assertLog(log2, "original-greyscale-green");
    }

    @Test
    public void testGreyScaleBlueCommand() throws FileNotFoundException {
        Image sixbitGreyScaleBlue = ImageUtil.readPPM("sixbit-blue-greyscale.ppm");
        Image koalaGreyScaleBlue = ImageUtil.readPPM("koala-blue-greyscale.ppm");

        Command greyScaleBlue = new GreyScaleBlueCommand("original", "original-greyscale-blue");

        runTest(greyScaleBlue, koalaGreyScaleBlue, mock);
        runTest(greyScaleBlue, sixbitGreyScaleBlue, mock2);
        assertLog(log, "original-greyscale-blue");
        assertLog(log2, "original-greyscale-blue");
    }

    @Test
    public void testLoad() {

        Command load = new LoadCommand("./sixbit.ppm", "sixbit-goat");

        load.go(mock);
        assertEquals(log.toString(), "loaded: sixbit-goat from filepath: ./sixbit.ppm\n");
    }

    @Test
    public void testSave() {
        Command save = new SaveCommand("./C:", "sixbit.ppm");

        save.go(mock);
        assertEquals(log.toString(), "saved sixbit.ppm to filepath: ./C:\n");
    }
    

}
