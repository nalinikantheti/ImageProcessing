package factory;

import java.util.Scanner;

import controller.factory.CommandFactory;
import controller.factory.terminal.IntensityFactory;
import view.ImageProcessorView;

/**
 * Tests for {@link IntensityFactory}.
 */
public class IntensityFactoryTests extends AbstractFactoryTests {
  /**
   * Returns a {@link IntensityFactory} that uses the given view and scanner.
   *
   * @param view    the view to pass to the factory
   * @param scanner the scanner to pass to the factory
   * @return a {@link IntensityFactory}
   */
  @Override
  protected CommandFactory makeFactory(ImageProcessorView view, Scanner scanner) {
    return new IntensityFactory(view, scanner);
  }
}
