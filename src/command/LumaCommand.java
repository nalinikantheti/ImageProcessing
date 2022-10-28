package command;

import image.Pixel;

public class LumaCommand extends AbstractGreyScaleCommand{
    public LumaCommand(String imageName, String newName) {
        super(imageName, newName);
    }

    @Override
    protected int getValue(Pixel pixel) {
        return (int)(0.2126 * pixel.getRed() + 0.7152 * pixel.getGreen() + 0.0722 * pixel.getBlue());
    }
}
