package factory;

import controller.factory.terminal.CommandFactory;
import controller.factory.terminal.SepiaFactory;
import controller.factory.terminal.SharpenFactory;
import view.ImageProcessorView;

import java.util.Scanner;

/**
 * Tests for {@link SharpenFactory}.
 */
public class SharpenFactoryTests extends AbstractFactoryTests{
    /**
     * Returns a {@link SharpenFactory} that uses the given view and scanner.
     *
     * @param view    the view to pass to the factory
     * @param scanner the scanner to pass to the factory
     * @return a {@link SharpenFactory}
     */
    @Override
    protected CommandFactory makeFactory(ImageProcessorView view, Scanner scanner) {
        return new SharpenFactory(view, scanner);
    }
}
