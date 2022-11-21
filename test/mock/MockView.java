package mock;

import java.io.IOException;

import view.ImageProcessorView;

/**
 * A mock view which logs all the messages transmitted to this view.
 */
public class MockView implements ImageProcessorView {
  private StringBuilder output;

  /**
   * Creates a MockView that logs everything to the given StringBuilder.
   *
   * @param actualViewOutput the log
   */
  public MockView(StringBuilder actualViewOutput) {
    this.output = actualViewOutput;
  }

  /**
   * Logs the given message called to be rendered.
   *
   * @param message the message to log
   * @throws IOException never
   */
  @Override
  public void renderMessage(String message) throws IOException {
    output.append(message);
  }
}
