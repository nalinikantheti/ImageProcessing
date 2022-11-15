package factory;

import java.util.Scanner;

import controller.factory.CommandFactory;
import controller.factory.terminal.SepiaFactory;
import view.ImageProcessorView;

/**
 * Tests for {@link SepiaFactory}.
 */
public class SepiaFactoryTests extends AbstractFactoryTests {
  /**
   * Returns a {@link SepiaFactory} that uses the given view and scanner.
   *
   * @param view    the view to pass to the factory
   * @param scanner the scanner to pass to the factory
   * @return a {@link SepiaFactory}
   */
  @Override
  protected CommandFactory makeFactory(ImageProcessorView view, Scanner scanner) {
    return new SepiaFactory(view, scanner);
  }
}
