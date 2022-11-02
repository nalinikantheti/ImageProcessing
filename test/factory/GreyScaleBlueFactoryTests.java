package factory;

import controller.factory.terminal.CommandFactory;
import controller.factory.terminal.GreyScaleBlueFactory;
import factory.AbstractFactoryTests;
import view.ImageProcessorView;

import java.util.Scanner;

public class GreyScaleBlueFactoryTests extends AbstractFactoryTests {
    @Override
    protected CommandFactory makeFactory(ImageProcessorView view, Scanner scanner) {
        return new GreyScaleBlueFactory(view, scanner);
    }
}
