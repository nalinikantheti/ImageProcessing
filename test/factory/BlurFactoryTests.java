package factory;

import controller.factory.terminal.BlurFactory;
import controller.factory.terminal.CommandFactory;
import view.ImageProcessorView;

import java.util.Scanner;

public class BlurFactoryTests extends AbstractFactoryTests{
    @Override
    protected CommandFactory makeFactory(ImageProcessorView view, Scanner scanner) {
        return new BlurFactory(view, scanner);
    }
}
