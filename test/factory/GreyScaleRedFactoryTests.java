package factory;

import java.util.Scanner;

import controller.factory.CommandFactory;
import controller.factory.terminal.GreyScaleRedFactory;
import view.ImageProcessorView;

/**
 * Tests for {@link GreyScaleRedFactory}.
 */
public class GreyScaleRedFactoryTests extends AbstractFactoryTests {
  /**
   * Returns a {@link GreyScaleRedFactory} that uses the given view and scanner.
   *
   * @param view    the view to pass to the factory
   * @param scanner the scanner to pass to the factory
   * @return a {@link GreyScaleRedFactory}.
   */
  @Override
  protected CommandFactory makeFactory(ImageProcessorView view, Scanner scanner) {
    return new GreyScaleRedFactory(view, scanner);
  }
}
