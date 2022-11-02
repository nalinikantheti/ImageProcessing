package factory;

import controller.factory.terminal.CommandFactory;
import controller.factory.terminal.FlipVerticalFactory;
import factory.AbstractFactoryTests;
import view.ImageProcessorView;

import java.util.Scanner;

public class FlipVerticalFactoryTests extends AbstractFactoryTests {
    @Override
    protected CommandFactory makeFactory(ImageProcessorView view, Scanner scanner) {
        return new FlipVerticalFactory(view, scanner);
    }
}
