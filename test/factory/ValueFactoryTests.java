package factory;

import controller.factory.terminal.CommandFactory;
import controller.factory.terminal.ValueFactory;
import view.ImageProcessorView;

import java.util.Scanner;

public class ValueFactoryTests extends AbstractFactoryTests{
    @Override
    protected CommandFactory makeFactory(ImageProcessorView view, Scanner scanner) {
        return new ValueFactory(view,scanner);
    }
}
