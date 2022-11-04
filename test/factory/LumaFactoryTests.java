package factory;

import controller.factory.terminal.BrightenFactory;
import controller.factory.terminal.CommandFactory;
import controller.factory.terminal.LumaFactory;
import view.ImageProcessorView;

import java.util.Scanner;

/**
 * Tests for {@link LumaFactory}.
 */
public class LumaFactoryTests extends AbstractFactoryTests{
    /**
     * Returns a {@link LumaFactory} that uses the given view and scanner.
     * @param view the view to pass to the factory
     * @param scanner the scanner to pass to the factory
     * @return a {@link LumaFactory}
     */
    @Override
    protected CommandFactory makeFactory(ImageProcessorView view, Scanner scanner) {
        return new LumaFactory(view, scanner);
    }
}
