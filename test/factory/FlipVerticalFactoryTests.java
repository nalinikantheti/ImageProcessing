package factory;

import java.util.Scanner;

import controller.factory.CommandFactory;
import controller.factory.terminal.FlipVerticalFactory;
import view.ImageProcessorView;

/**
 * Test for {@link FlipVerticalFactory}.
 */
public class FlipVerticalFactoryTests extends AbstractFactoryTests {
  /**
   * Returns a {@link FlipVerticalFactory} that uses the given view and scanner.
   *
   * @param view    the view to pass to the factory
   * @param scanner the scanner to pass to the factory
   * @return a {@link FlipVerticalFactory}
   */
  @Override
  protected CommandFactory makeFactory(ImageProcessorView view, Scanner scanner) {
    return new FlipVerticalFactory(view, scanner);
  }
}
