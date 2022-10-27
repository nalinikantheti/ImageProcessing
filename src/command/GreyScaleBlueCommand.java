package command;

import Image.Pixel;

public class GreyScaleBlueCommand extends AbstractGreyScaleCommand{
    public GreyScaleBlueCommand(String imageName, String newName) {
        super(imageName, newName);
    }

    @Override
    protected int getValue(Pixel pixel) {
        return pixel.getBlue();
    }
}
