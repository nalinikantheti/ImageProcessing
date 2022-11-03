package command;


import image.Pixel;

/**
 * A command that turns an image into greyscale by setting the
 * RGB value of every pixel to its red value.
 */
public class GreyScaleRedCommand extends AbstractGreyScaleCommand {

    /**
     * a constructor for a GreyScaleRedCommand using two arguments.
     *
     * @param imageName the name of the image to be processed.
     * @param newName   the name the processed image will be saved as.
     */
    public GreyScaleRedCommand(String imageName, String newName) {
        super(imageName, newName);
    }

    @Override
    protected int getValue(Pixel pixel) {
        return pixel.getRed();
    }
}

