package factory;

import controller.factory.terminal.CommandFactory;
import controller.factory.terminal.SharpenFactory;
import view.ImageProcessorView;

import java.util.Scanner;

public class SharpenFactoryTests extends AbstractFactoryTests{
    @Override
    protected CommandFactory makeFactory(ImageProcessorView view, Scanner scanner) {
        return new SharpenFactory(view, scanner);
    }
}
