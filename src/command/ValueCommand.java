package command;

import image.Pixel;

public class ValueCommand extends AbstractGreyScaleCommand{
    public ValueCommand(String imageName, String newName) {
        super(imageName, newName);
    }

    @Override
    protected int getValue(Pixel pixel) {
        int max = Math.max(pixel.getRed(), pixel.getGreen());
        return Math.max(max, pixel.getBlue());
    }
}
