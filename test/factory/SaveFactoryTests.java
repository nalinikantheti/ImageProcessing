package factory;

import controller.factory.terminal.CommandFactory;
import controller.factory.terminal.SaveFactory;
import view.ImageProcessorView;

import java.util.Scanner;

public class SaveFactoryTests extends AbstractFactoryTests{
    @Override
    protected CommandFactory makeFactory(ImageProcessorView view, Scanner scanner) {
        return new SaveFactory(view, scanner);
    }
}
