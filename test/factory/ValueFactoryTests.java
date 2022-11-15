package factory;

import java.util.Scanner;

import controller.factory.CommandFactory;
import controller.factory.terminal.ValueFactory;
import view.ImageProcessorView;

/**
 * Tests for {@link ValueFactory}.
 */
public class ValueFactoryTests extends AbstractFactoryTests {
  /**
   * Returns a {@link ValueFactory} that uses the given view and scanner.
   *
   * @param view    the view to pass to the factory
   * @param scanner the scanner to pass to the factory
   * @return a {@link ValueFactory}
   */
  @Override
  protected CommandFactory makeFactory(ImageProcessorView view, Scanner scanner) {
    return new ValueFactory(view, scanner);
  }
}
