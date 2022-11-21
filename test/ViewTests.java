import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import view.ImageProcessorTextView;

import static org.junit.Assert.assertEquals;

/**
 * Tests for {@link ImageProcessorTextView}.
 */
public class ViewTests {
  private StringBuilder actualOutput;
  private ImageProcessorTextView view;

  @Before
  public void setup() {
    actualOutput = new StringBuilder();
    view = new ImageProcessorTextView(actualOutput);
  }

  @Test
  public void testRenderMessage() throws IOException {

    view.renderMessage("hgjhv gch");
    assertEquals("hgjhv gch", actualOutput.toString());

    setup();
    view.renderMessage("borignial");
    assertEquals("borignial", actualOutput.toString());
  }
}
