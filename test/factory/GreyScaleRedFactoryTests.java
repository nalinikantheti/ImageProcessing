package factory;

import controller.factory.terminal.BrightenFactory;
import controller.factory.terminal.CommandFactory;
import controller.factory.terminal.GreyScaleRedFactory;
import view.ImageProcessorView;

import java.util.Scanner;

/**
 * Tests for {@link GreyScaleRedFactory}.
 */
public class GreyScaleRedFactoryTests extends AbstractFactoryTests{
    /**
     * Returns a {@link GreyScaleRedFactory} that uses the given view and scanner.
     * @param view the view to pass to the factory
     * @param scanner the scanner to pass to the factory
     * @return a {@link GreyScaleRedFactory}.
     */
    @Override
    protected CommandFactory makeFactory(ImageProcessorView view, Scanner scanner) {
        return new GreyScaleRedFactory(view, scanner);
    }
}
