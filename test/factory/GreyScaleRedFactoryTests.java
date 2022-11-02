package factory;

import controller.factory.terminal.CommandFactory;
import controller.factory.terminal.GreyScaleRedFactory;
import view.ImageProcessorView;

import java.util.Scanner;

public class GreyScaleRedFactoryTests extends AbstractFactoryTests{
    @Override
    protected CommandFactory makeFactory(ImageProcessorView view, Scanner scanner) {
        return new GreyScaleRedFactory(view, scanner);
    }
}
