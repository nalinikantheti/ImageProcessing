import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;

import command.BlurCommand;
import command.SharpenCommand;
import image.Image;
import mock.MockModel;
import util.ImageUtil;

import static org.junit.Assert.assertEquals;

/**
 * Tests for {@link command.FilterCommand}.
 */
public class FilterTests {
  private MockModel mock;
  private StringBuilder log;
  private String root = "./res/25bit/";

  @Before
  public void setup() throws FileNotFoundException {
    Image twenBit;
    log = new StringBuilder();
    twenBit = ImageUtil.readPPM(root + "25bit.ppm");
    mock = new MockModel(log, twenBit);
  }

  @Test
  public void blurTest() throws FileNotFoundException {
    BlurCommand blur = new BlurCommand("original", "original-blur");
    Image expected = ImageUtil.readPPM(root + "25bit-blur.ppm");
    blur.run(mock);
    TestUtils.assertImageEquals(expected, mock.getLastSavedImage());
    assertEquals("retrieved: original\nsaved original-blur to model\n", log.toString());
  }

  @Test
  public void sharpenTest() throws FileNotFoundException {
    SharpenCommand sharp = new SharpenCommand("original", "original-sharpen");
    Image expected = ImageUtil.readPPM(root + "25bit-sharpen.ppm");
    sharp.run(mock);
    TestUtils.assertImageEquals(expected, mock.getLastSavedImage());
    assertEquals("retrieved: original\nsaved original-sharpen to model\n", log.toString());
  }
}
