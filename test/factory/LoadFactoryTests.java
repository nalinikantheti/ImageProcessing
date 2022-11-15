package factory;

import java.util.Scanner;

import controller.factory.CommandFactory;
import controller.factory.terminal.LoadFactory;
import view.ImageProcessorView;

/**
 * Tests for {@link LoadFactory}.
 */
public class LoadFactoryTests extends AbstractFactoryTests {
  /**
   * Returns a {@link LoadFactory} that uses the given view and scanner.
   *
   * @param view    the view to pass to the factory
   * @param scanner the scanner to pass to the factory
   * @return a {@link LoadFactory}
   */
  @Override
  protected CommandFactory makeFactory(ImageProcessorView view, Scanner scanner) {
    return new LoadFactory(view, scanner);
  }
}
