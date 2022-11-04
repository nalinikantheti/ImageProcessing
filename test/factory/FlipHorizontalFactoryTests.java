package factory;

import controller.factory.terminal.BrightenFactory;
import controller.factory.terminal.CommandFactory;
import controller.factory.terminal.FlipHorizontalFactory;
import factory.AbstractFactoryTests;
import view.ImageProcessorView;

import java.util.Scanner;

/**
 * Tests for {@link FlipHorizontalFactory}.
 */
public class FlipHorizontalFactoryTests extends AbstractFactoryTests {
    /**
     * Returns a {@link FlipHorizontalFactory} that uses the given view and scanner.
     * @param view the view to pass to the factory
     * @param scanner the scanner to pass to the factory
     * @return a {@link FlipHorizontalFactory}
     */
    @Override
    protected CommandFactory makeFactory(ImageProcessorView view, Scanner scanner) {
        return new FlipHorizontalFactory(view, scanner);
    }
}
