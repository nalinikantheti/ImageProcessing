package command;

import image.Pixel;

public class IntensityCommand extends AbstractGreyScaleCommand{

    public IntensityCommand(String imageName, String newName) {
        super(imageName, newName);
    }

    @Override
    protected int getValue(Pixel pixel) {
        return (int)((pixel.getRed() + pixel.getGreen() + pixel.getBlue()) / 3.0);
    }
}
