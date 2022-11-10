package factory;

import controller.factory.terminal.BlurFactory;
import controller.factory.terminal.CommandFactory;
import controller.factory.terminal.GreyScaleBlueFactory;
import view.ImageProcessorView;

import java.util.Scanner;

/**
 * Tests for {@link BlurFactory}.
 */
public class BlurFactoryTests extends AbstractFactoryTests{

    /**
     * Returns a {@link BlurFactory} that uses the given view and scanner.
     *
     * @param view    the view to pass to the factory
     * @param scanner the scanner to pass to the factory
     * @return a {@link BlurFactory}
     */
    @Override
    protected CommandFactory makeFactory(ImageProcessorView view, Scanner scanner) {
        return new BlurFactory(view, scanner);
    }
}
