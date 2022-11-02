package factory;

import controller.factory.terminal.CommandFactory;
import controller.factory.terminal.GreyScaleGreenFactory;
import factory.AbstractFactoryTests;
import view.ImageProcessorView;

import java.util.Scanner;

public class GreyScaleGreenFactoryTests extends AbstractFactoryTests {
    @Override
    protected CommandFactory makeFactory(ImageProcessorView view, Scanner scanner) {
        return new GreyScaleGreenFactory(view, scanner);
    }
}
