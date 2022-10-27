package command;

import Image.Pixel;

public class GreyScaleGreenCommand extends AbstractGreyScaleCommand{
    public GreyScaleGreenCommand(String imageName, String newName) {
        super(imageName, newName);
    }

    @Override
    protected int getValue(Pixel pixel) {
        return pixel.getGreen();
    }
}
