package factory;

import controller.factory.terminal.CommandFactory;
import controller.factory.terminal.GreyScaleGreenFactory;
import view.ImageProcessorView;

import java.util.Scanner;

/**
 * Tests for {@link GreyScaleGreenFactory}
 */
public class GreyScaleGreenFactoryTests extends AbstractFactoryTests {
    /**
     * Returns a {@link GreyScaleGreenFactory} that uses the given view and scanner.
     *
     * @param view    the view to pass to the factory
     * @param scanner the scanner to pass to the factory
     * @return a {@link GreyScaleGreenFactory}
     */
    @Override
    protected CommandFactory makeFactory(ImageProcessorView view, Scanner scanner) {
        return new GreyScaleGreenFactory(view, scanner);
    }
}
