package factory;

import controller.factory.terminal.CommandFactory;
import controller.factory.terminal.FlipHorizontalFactory;
import factory.AbstractFactoryTests;
import view.ImageProcessorView;

import java.util.Scanner;

public class FlipHorizontalFactoryTests extends AbstractFactoryTests {
    @Override
    protected CommandFactory makeFactory(ImageProcessorView view, Scanner scanner) {
        return new FlipHorizontalFactory(view, scanner);
    }
}
