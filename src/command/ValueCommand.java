package command;

import image.Pixel;

/**
 * A Command that greyscales an image by setting each RGB value in each pixel
 * to the maximum of the red, green, and blue values.
 */
public class ValueCommand extends AbstractGreyScaleCommand {
    /**
     * A constructor for a ValueCommand that uses two arguments.
     *
     * @param imageName the name of the image to be processed.
     * @param newName   the name the processed image will be saved as.
     */
    public ValueCommand(String imageName, String newName) {
        super(imageName, newName);
    }

    @Override
    protected int getValue(Pixel pixel) {
        int max = Math.max(pixel.getRed(), pixel.getGreen());
        return Math.max(max, pixel.getBlue());
    }
}
