package factory;

import controller.factory.terminal.CommandFactory;
import controller.factory.terminal.GreyScaleBlueFactory;
import view.ImageProcessorView;

import java.util.Scanner;

/**
 * Tests for {@link GreyScaleBlueFactory}.
 */
public class GreyScaleBlueFactoryTests extends AbstractFactoryTests {
    /**
     * Returns a {@link GreyScaleBlueFactory} that uses the given view and scanner.
     *
     * @param view    the view to pass to the factory
     * @param scanner the scanner to pass to the factory
     * @return a {@link GreyScaleBlueFactory}
     */
    @Override
    protected CommandFactory makeFactory(ImageProcessorView view, Scanner scanner) {
        return new GreyScaleBlueFactory(view, scanner);
    }
}
