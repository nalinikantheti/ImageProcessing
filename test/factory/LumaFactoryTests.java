package factory;

import controller.factory.terminal.CommandFactory;
import controller.factory.terminal.LumaFactory;
import view.ImageProcessorView;

import java.util.Scanner;

public class LumaFactoryTests extends AbstractFactoryTests{
    @Override
    protected CommandFactory makeFactory(ImageProcessorView view, Scanner scanner) {
        return new LumaFactory(view, scanner);
    }
}
