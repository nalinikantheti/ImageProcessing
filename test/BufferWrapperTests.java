import image.BufferWrapper;
import image.Image;
import org.junit.Test;

import java.awt.image.BufferedImage;

import static org.junit.Assert.assertThrows;

/**
 * Tests for {@link BufferWrapper}.
 */
public class BufferWrapperTests extends ImageTests{

    /**
     * Produces a BufferWrapper containing a buffered image of the specified
     * width and height.
     * @param width width of buffered image in bufferwrapper.
     * @param height height of buffered image in bufferwrapper.
     * @return a new BufferWrapper.
     */
    @Override
    protected Image getImage(int width, int height) {
        BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB);
        return new BufferWrapper(image);
    }

    /**
     * Tests the BufferWrapper constructor throws an exception when given null arguments.
     */
    @Test
    public void testConstructorThrows() {
        assertThrows(IllegalArgumentException.class,
                () -> new BufferWrapper(null));
    }
}
