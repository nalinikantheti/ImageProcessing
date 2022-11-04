package factory;

import controller.factory.terminal.BrightenFactory;
import controller.factory.terminal.CommandFactory;
import controller.factory.terminal.SaveFactory;
import view.ImageProcessorView;

import java.util.Scanner;

/**
 * Tests for {@link SaveFactory}.
 */
public class SaveFactoryTests extends AbstractFactoryTests{
    /**
     * Returns a {@link SaveFactory} that uses the given view and scanner.
     * @param view the view to pass to the factory
     * @param scanner the scanner to pass to the factory
     * @return a {@link SaveFactory}
     */
    @Override
    protected CommandFactory makeFactory(ImageProcessorView view, Scanner scanner) {
        return new SaveFactory(view, scanner);
    }
}
