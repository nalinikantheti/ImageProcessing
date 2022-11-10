package factory;

import controller.factory.terminal.BlurFactory;
import controller.factory.terminal.CommandFactory;
import controller.factory.terminal.SepiaFactory;
import view.ImageProcessorView;

import java.util.Scanner;

/**
 * Tests for {@link SepiaFactory}.
 */
public class SepiaFactoryTests extends AbstractFactoryTests{
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
