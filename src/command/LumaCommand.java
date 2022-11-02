package command;

import image.Pixel;

public class LumaCommand extends AbstractGreyScaleCommand{
    public LumaCommand(String imageName, String newName) {
        super(imageName, newName);
    }

    @Override
    protected int getValue(Pixel pixel) {
        return (int) ((2126 * pixel.getRed() + 7152 * pixel.getGreen() + 722 * pixel.getBlue())/ 10000.0);
    }

}
