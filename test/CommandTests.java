import command.BrightenCommand;
import command.Command;
import image.Image;
import image.RGBImage;
import model.ImageProcessorModel;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CommandTests {
    StringBuilder log;
    MockModel mock;
    Command brighten;
    @Before
    public void setup(){
        log = new StringBuilder();
        mock = new MockModel(log, new RGBImage(10,10));

        brighten = new BrightenCommand("original", 20, "original-bright");
    }

    @Test
    public void testLog(String message){
        assertTrue(log.toString().contains(message));
    }

    @Test
    public void testBrightenCommand(){
        brighten.go(mock);
        Image expectedImage = new RGBImage(10,10);
        assertEquals(expectedImage, mock.getLastSavedImage());

    }


}
