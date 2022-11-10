package factory;

import controller.factory.terminal.CommandFactory;
import controller.factory.terminal.SepiaFactory;
import view.ImageProcessorView;

import java.util.Scanner;

public class SepiaFactoryTests extends AbstractFactoryTests{
    @Override
    protected CommandFactory makeFactory(ImageProcessorView view, Scanner scanner) {
        return new SepiaFactory(view, scanner);
    }
}
