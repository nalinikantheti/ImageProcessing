package command;

import image.Image;
import image.Pixel;
import model.ImageProcessorModel;

import static util.ImageProcessorUtils.createValidPixel;

public class SepiaCommand extends ColorTransformationCommand {

    public SepiaCommand(String imageName, String newName) {
        super(imageName, newName);

    }

    @Override
    protected int getBlue(Pixel pixel) {
        return (int)(0.272 * pixel.getRed() + 0.534 * pixel.getGreen() + 0.131 * pixel.getBlue());
    }

    @Override
    protected int getGreen(Pixel pixel) {
        return (int)(0.349 * pixel.getRed() + 0.686 * pixel.getGreen() + 0.168 * pixel.getBlue());
    }

    @Override
    protected int getRed(Pixel pixel) {
        return (int)(0.393 * pixel.getRed() + 0.769 * pixel.getGreen() + 0.189 * pixel.getBlue());
    }
}
