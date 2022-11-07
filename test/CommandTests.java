import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import command.BrightenCommand;
import command.Command;
import command.DarkenCommand;
import command.FlipHorizontalCommand;
import command.FlipVerticalCommand;
import command.GreyScaleBlueCommand;
import command.GreyScaleGreenCommand;
import command.GreyScaleRedCommand;
import command.IntensityCommand;
import command.LoadPPMCommand;
import command.LumaCommand;
import command.ReadImageIOCommand;
import command.SaveImageIOCommand;
import command.SavePPMCommand;
import command.ValueCommand;
import image.Image;
import image.Pixel;
import image.RGBImage;
import mock.MockModel;
import util.ImageUtil;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

/**
 * Tests all the functionalities of a commands.
 */
public class CommandTests {
  private final String sixbitRoot = "./res/sixbit/";
  private final String koalaRoot = "./res/koala/";
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
    sixbit = ImageUtil.readPPM(sixbitRoot + "test.ppm");
    koala = ImageUtil.readPPM(koalaRoot + "Koala.ppm");
    mock = new MockModel(log, koala);
    mock2 = new MockModel(log2, sixbit);
  }

  private void assertImageEquals(Image expected, Image actual) {
    assertEquals(expected.getWidth(), actual.getWidth());
    assertEquals(expected.getHeight(), actual.getHeight());

    for (int x = 0; x < expected.getWidth(); x++) {
      for (int y = 0; y < expected.getHeight(); y++) {
        //assertEquals(expected.getPixel(x, y), actual.getPixel(x, y));
        if (!expected.getPixel(x, y).equals(actual.getPixel(x, y))) {
          throw new AssertionError("[" + x + ", " + y + "]: "
                  + "expected: " + expected.getPixel(x, y).toString() + "; "
                  + "actual: " + actual.getPixel(x, y).toString());
        }
      }
    }
  }

  private void assertLog(StringBuilder log, String name, String newName) {
    assertEquals("retrieved: " + name + "\n" + "saved "
            + newName + " to model" + "\n", log.toString());
  }

  private void assertLog(StringBuilder log, String newName) {
    assertLog(log, "original", newName);
  }

  @Test
  public void testAssertImageEquals() throws FileNotFoundException {
    assertThrows(AssertionError.class, () -> assertImageEquals(sixbit, koala));
    assertImageEquals(sixbit, ImageUtil.readPPM(sixbitRoot + "test.ppm"));
  }


  private void runTest(Command command, Image expectedImage, MockModel mock) {
    command.run(mock);
    assertImageEquals(expectedImage, mock.getLastSavedImage());
  }

  @Test
  public void testBrightenCommand() throws FileNotFoundException {
    Command brighten = new BrightenCommand("original", 50, "original-bright");
    Command brightenMax = new BrightenCommand("original", 1000, "original-bright-max");

    Image koalaBright = ImageUtil.readPPM(koalaRoot + "koala-brighter-by-50.ppm");
    Image sixbitBright = ImageUtil.readPPM(sixbitRoot + "sixbit-brighten.ppm");
    Image sixbitBrightMax = ImageUtil.readPPM(sixbitRoot + "sixbit-brighten-max.ppm");

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

    Image sixbitDark = ImageUtil.readPPM(sixbitRoot + "sixbit-darken.ppm");
    Image sixbitDarkMax = ImageUtil.readPPM(sixbitRoot + "sixbit-darken-max.ppm");

    runTest(darken, sixbitDark, mock2);
    assertLog(log2, "original", "original-dark");
    log2.setLength(0);
    runTest(darkenMax, sixbitDarkMax, mock2);
    assertLog(log2, "original", "original-dark-max");
  }


  @Test
  public void testFlipHorizontalCommand() throws FileNotFoundException {
    Image sixbitFlipHorizontal = ImageUtil.readPPM(sixbitRoot + "sixbit-horizontal-flip.ppm");
    Image koalaFlipHorizontal = ImageUtil.readPPM(koalaRoot + "koala-horizontal.ppm");

    Command flipHorizontal = new FlipHorizontalCommand("original", "original-flip-horizontal");

    runTest(flipHorizontal, koalaFlipHorizontal, mock);
    runTest(flipHorizontal, sixbitFlipHorizontal, mock2);
    assertLog(log, "original", "original-flip-horizontal");
    assertLog(log2, "original-flip-horizontal");
  }

  @Test
  public void testFlipVerticalCommand() throws FileNotFoundException {
    Image sixbitFlipVertical = ImageUtil.readPPM(sixbitRoot + "sixbit-vertical-flip.ppm");
    Image koalaFlipVertical = ImageUtil.readPPM(koalaRoot + "koala-vertical.ppm");

    Command flipVertical = new FlipVerticalCommand("boriginal", "original-flip-vertical");

    runTest(flipVertical, koalaFlipVertical, mock);
    runTest(flipVertical, sixbitFlipVertical, mock2);
    assertLog(log, "boriginal", "original-flip-vertical");
    assertLog(log2, "boriginal", "original-flip-vertical");
  }

  @Test
  public void testLumaCommand() throws FileNotFoundException {
    Image sixbitLuma = ImageUtil.readPPM(sixbitRoot + "sixbit-luma-greyscale.ppm");
    Image koalaLuma = ImageUtil.readPPM(koalaRoot + "koala-luma-greyscale.ppm");

    Command luma = new LumaCommand("hi", "original-luma");

    runTest(luma, sixbitLuma, mock2);
    assertLog(log2, "hi", "original-luma");

    runTest(luma, koalaLuma, mock);
    assertLog(log, "hi", "original-luma");
  }

  @Test
  public void testValueCommand() throws FileNotFoundException {
    Image sixbitValue = ImageUtil.readPPM(sixbitRoot + "sixbit-value-greyscale.ppm");
    Image koalaValue = ImageUtil.readPPM(koalaRoot + "koala-value-greyscale.ppm");

    Command value = new ValueCommand("original", "original-value");

    runTest(value, sixbitValue, mock2);
    runTest(value, koalaValue, mock);
    assertLog(log, "original-value");
    assertLog(log2, "original-value");

  }

  @Test
  public void testIntensityCommand() throws FileNotFoundException {
    Image sixbitIntensity = ImageUtil.readPPM(sixbitRoot + "sixbit-intensity-greyscale.ppm");
    Image koalaIntensity = ImageUtil.readPPM(koalaRoot + "koala-intensity-greyscale.ppm");

    Command intensity = new IntensityCommand("original", "original-intensity");

    runTest(intensity, koalaIntensity, mock);
    runTest(intensity, sixbitIntensity, mock2);
    assertLog(log, "original-intensity");
    assertLog(log2, "original-intensity");
  }

  @Test
  public void testGreyScaleRedCommand() throws FileNotFoundException {
    Image sixbitGreyScaleRed = ImageUtil.readPPM(sixbitRoot + "sixbit-red-greyscale.ppm");
    Image koalaGreyScaleRed = ImageUtil.readPPM(koalaRoot + "koala-red-greyscale.ppm");

    Command greyScaleRed = new GreyScaleRedCommand("original", "original-red");

    runTest(greyScaleRed, koalaGreyScaleRed, mock);
    runTest(greyScaleRed, sixbitGreyScaleRed, mock2);
    assertLog(log, "original-red");
    assertLog(log2, "original-red");
  }

  @Test
  public void testGreyScaleGreenCommand() throws FileNotFoundException {
    Image sixbitGreyScaleGreen = ImageUtil.readPPM(sixbitRoot + "sixbit-green-greyscale.ppm");
    Image koalaGreyScaleGreen = ImageUtil.readPPM(koalaRoot + "koala-green-greyscale.ppm");

    Command greyScaleGreen = new GreyScaleGreenCommand("original", "original-greyscale-green");


    runTest(greyScaleGreen, koalaGreyScaleGreen, mock);
    runTest(greyScaleGreen, sixbitGreyScaleGreen, mock2);
    assertLog(log, "original-greyscale-green");
    assertLog(log2, "original-greyscale-green");
  }

  @Test
  public void testGreyScaleBlueCommand() throws FileNotFoundException {
    Image sixbitGreyScaleBlue = ImageUtil.readPPM(sixbitRoot + "sixbit-blue-greyscale.ppm");
    Image koalaGreyScaleBlue = ImageUtil.readPPM(koalaRoot + "koala-blue-greyscale.ppm");

    Command greyScaleBlue = new GreyScaleBlueCommand("original", "original-greyscale-blue");

    runTest(greyScaleBlue, koalaGreyScaleBlue, mock);
    runTest(greyScaleBlue, sixbitGreyScaleBlue, mock2);
    assertLog(log, "original-greyscale-blue");
    assertLog(log2, "original-greyscale-blue");
  }

  @Test
  public void testLoadPPMCommand() {

    Command load = new LoadPPMCommand(sixbitRoot + "test.ppm", "sixbit-goat");

    load.run(mock);
    assertEquals(log.toString(), "saved sixbit-goat to model\n");
    assertImageEquals(sixbit, mock.getLastSavedImage());
  }

  @Test
  public void testSavePPMCommand() {
    Command save = new SavePPMCommand("./C:", "sixbit");

    save.run(mock);
    assertEquals(log.toString(), "retrieved: sixbit\n");
  }

  @Test
  public void readImageIOCommand(){
    Command readJPG = new ReadImageIOCommand(sixbitRoot + "sixbit.jpg", "sixbit");
    readJPG.run(mock);
    assertEquals(log.toString(), "saved sixbit to model\n");
    assertImageEquals(sixbit, mock.getLastSavedImage());

    Command readPNG = new ReadImageIOCommand(sixbitRoot + "sixbit.png", "sixbitp");
    readPNG.run(mock);
    assertEquals(log.toString(), "saved sixbit to model\n");
    assertImageEquals(sixbit, mock.getLastSavedImage());

    Command readBMP = new ReadImageIOCommand(sixbitRoot + "sixbit.bmp", "sixbitb");
    readBMP.run(mock);
    assertEquals(log.toString(), "saved sixbit to model\n");
    assertImageEquals(sixbit, mock.getLastSavedImage());


  }

  @Test
  public void testSaveImageIOCommand() {
    Command readJPG = new ReadImageIOCommand("sixbit.jpg", "sixbit");
    Command readPNG = new ReadImageIOCommand("sixbit.jpg", "sixbit");
    Command readBMP = new ReadImageIOCommand("sixbit.jpg", "sixbit");

    Command saveJPG = new SaveImageIOCommand("sixbit.jpg", "sixbit", "jpg");
    Command savePNG = new SaveImageIOCommand("sixbit.png", "sixbit", "png");
    Command saveBMP = new SaveImageIOCommand("sixbit.bmp", "sixbit", "bmp");


    saveJPG.run(mock);
    readJPG.run(mock);
    assertImageEquals(sixbit, mock.getLastSavedImage());
    assertEquals(log.toString(), "retrieved: sixbit\n");
    savePNG.run(mock);
    readPNG.run(mock);
    assertImageEquals(sixbit, mock.getLastSavedImage());
    assertEquals(log.toString(), "retrieved: sixbit\n");
    saveBMP.run(mock);
    readBMP.run(mock);
    assertImageEquals(sixbit, mock.getLastSavedImage());
    assertEquals(log.toString(), "retrieved: sixbit\n");

  }

  @Test
  public void testSaveToFileSystem() throws IOException {
    StringBuilder log = new StringBuilder();
    SavePPMCommand save = new SavePPMCommand("blue.ppm", "blue");
    Pixel black = new Pixel(0,0,0);
    Pixel blue = new Pixel(0,0,255);
    Image image = new RGBImage(2, 2);
    image.setPixel(blue, 0, 1);
    MockModel model = new MockModel(log, image);

    Path path = Paths.get("blue.ppm");

    assertTrue(Files.notExists(path));

    save.run(model);

    assertTrue(Files.exists(path));

    Image blue2 = ImageUtil.readPPM("blue.ppm");

    assertEquals(black, blue2.getPixel(0, 0));
    assertEquals(blue, blue2.getPixel(0, 1));
    assertEquals(black, blue2.getPixel(1, 0));
    assertEquals(black, blue2.getPixel(1, 1));

    Files.delete(path);
  }

  @Test
  public void testLoadPPM() throws FileNotFoundException {
    StringBuilder log = new StringBuilder();
    LoadPPMCommand load = new LoadPPMCommand(sixbitRoot + "test.ppm", "test");
    Pixel black = new Pixel(0,0,0);
    Pixel grey = new Pixel(127,127,127);
    Pixel white = new Pixel(255, 255, 255);
    Pixel red = new Pixel(255, 0, 0);
    Pixel green = new Pixel(0,255,0);
    Pixel blue = new Pixel(0,0,255);
    MockModel model = new MockModel(log);
    load.run(model);
    Image test = model.getLastSavedImage();

    assertEquals(black, test.getPixel(0, 0));
    assertEquals(grey, test.getPixel(1, 0));
    assertEquals(white, test.getPixel(2, 0));
    assertEquals(red, test.getPixel(0, 1));
    assertEquals(green, test.getPixel(1, 1));
    assertEquals(blue, test.getPixel(2, 1));
  }

  @Test
  public void testThrows(){
    StringBuilder log = new StringBuilder();
    MockModel mock = new MockModel(log);
    LoadPPMCommand load = new LoadPPMCommand("nonexistent.ppm","bad");
    assertThrows(IllegalArgumentException.class, () -> load.run(mock));
  }



}
