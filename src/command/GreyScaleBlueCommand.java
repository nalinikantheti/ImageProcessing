package command;

import image.Pixel;

/**
 * A command that turns an image into greyscale by setting the
 * RGB value of every pixel to its blue value.
 */
public class GreyScaleBlueCommand extends AbstractGreyScaleCommand {
    /**
     * A constructor for a GreyScaleBlueCommand using two arguments.
     *
     * @param imageName the name of the image to be processed.
     * @param newName   the name the processed image will be saved as.
     */
    public GreyScaleBlueCommand(String imageName, String newName) {
        super(imageName, newName);
    }

    @Override
    protected int getValue(Pixel pixel) {
        return pixel.getBlue();
    }
}
