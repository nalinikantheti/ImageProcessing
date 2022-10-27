package command;

import model.ImageProcessorModel;


import Image.Image;
import Image.Pixel;

public class GreyScaleRedCommand extends AbstractGreyScaleCommand {


    public GreyScaleRedCommand(String imageName, String newName) {
        super(imageName, newName);
    }

    @Override
    protected int getValue(Pixel pixel) {
        return pixel.getRed();
    }
}

