import image.BufferWrapper;
import image.Image;
import org.junit.Test;

import java.awt.image.BufferedImage;

import static org.junit.Assert.assertThrows;

public class BufferWrapperTests extends ImageTests{
    @Override
    protected Image getImage(int width, int height) {
        BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB);
        return new BufferWrapper(image);
    }
    @Test
    public void testConstructorThrows() {
        assertThrows(IllegalArgumentException.class,
                () -> new BufferWrapper(null));
    }
}
