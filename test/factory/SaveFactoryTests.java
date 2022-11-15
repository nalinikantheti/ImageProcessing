package factory;

import java.util.Scanner;

import controller.factory.CommandFactory;
import controller.factory.terminal.SaveFactory;
import view.ImageProcessorView;

/**
 * Tests for {@link SaveFactory}.
 */
public class SaveFactoryTests extends AbstractFactoryTests {
  /**
   * Returns a {@link SaveFactory} that uses the given view and scanner.
   *
   * @param view    the view to pass to the factory
   * @param scanner the scanner to pass to the factory
   * @return a {@link SaveFactory}
   */
  @Override
  protected CommandFactory makeFactory(ImageProcessorView view, Scanner scanner) {
    return new SaveFactory(view, scanner);
  }
}
