package factory;

import controller.factory.terminal.CommandFactory;
import controller.factory.terminal.IntensityFactory;
import view.ImageProcessorView;

import java.util.Scanner;

public class IntensityFactoryTests extends AbstractFactoryTests{
    @Override
    protected CommandFactory makeFactory(ImageProcessorView view, Scanner scanner) {
        return new IntensityFactory(view, scanner);
    }
}
