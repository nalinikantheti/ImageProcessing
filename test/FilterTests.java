import command.BlurCommand;
import command.SharpenCommand;
import image.Image;
import mock.MockModel;
import org.junit.Before;
import org.junit.Test;
import util.ImageUtil;

import java.io.FileNotFoundException;

import static org.junit.Assert.assertEquals;

public class FilterTests {
    MockModel mock;
    StringBuilder log;
    Image _25bit;
    String root = "./res/25bit/";
    @Before
    public void setup() throws FileNotFoundException {
        log = new StringBuilder();
        _25bit = ImageUtil.readPPM(root + "25bit.ppm");
        mock = new MockModel(log, _25bit);
    }
    @Test
    public void blurTest() throws FileNotFoundException {
        BlurCommand blur = new BlurCommand("original","original-blur");
        Image expected = ImageUtil.readPPM(root + "25bit-blur.ppm");
        blur.run(mock);
        TestUtils.assertImageEquals(expected, mock.getLastSavedImage());
        assertEquals("retrieved: original\nsaved original-blur to model\n", log.toString());
    }

    @Test
    public void sharpenTest() throws FileNotFoundException {
        SharpenCommand sharp = new SharpenCommand("original","original-sharpen");
        Image expected = ImageUtil.readPPM(root + "25bit-sharpen.ppm");
        sharp.run(mock);
        TestUtils.assertImageEquals(expected, mock.getLastSavedImage());
        assertEquals("retrieved: original\nsaved original-sharpen to model\n", log.toString());
    }
}
