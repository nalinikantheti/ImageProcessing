import image.Image;
import org.junit.Assert;

public class TestUtils {

    public static void assertImageEquals(Image expected, Image actual) {
        Assert.assertEquals(expected.getWidth(), actual.getWidth());
        Assert.assertEquals(expected.getHeight(), actual.getHeight());

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
}