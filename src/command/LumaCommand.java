package command;

import image.Pixel;

/**
 * A Command that greyscales an image by setting each rgb value
 * of each pixel in an image to a value that expresses the
 * relative luminance of each color (computed using a predetermined equation).
 */
public class LumaCommand extends AbstractGreyScaleCommand {

    /**
     * A constructor for a LumaCommand that uses two arguments.
     *
     * @param imageName the name of the image to be processed.
     * @param newName   the name the processed image will be saved as.
     */
    public LumaCommand(String imageName, String newName) {
        super(imageName, newName);
    }

    @Override
    protected int getValue(Pixel pixel) {
        return (int) (0.2126 * pixel.getRed() + 0.7152 * pixel.getGreen() + 0.0722 * pixel.getBlue());
    }

}
