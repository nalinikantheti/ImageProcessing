package factory;

import controller.factory.terminal.CommandFactory;
import controller.factory.terminal.LoadFactory;
import view.ImageProcessorView;

import java.util.Scanner;

public class LoadFactoryTests extends AbstractFactoryTests{
    @Override
    protected CommandFactory makeFactory(ImageProcessorView view, Scanner scanner) {
        return new LoadFactory(view, scanner);
    }
}
