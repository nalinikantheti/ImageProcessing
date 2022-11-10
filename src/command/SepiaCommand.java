package command;
import image.Pixel;


/**
 * A command that performs an operation on the RGB values of each pixel
 * in an image to produce a sepia effect.
 */
public class SepiaCommand extends ColorTransformationCommand {

    /**
     * A Constructor for a SepiaCommand with two arguments.
     * @param imageName the name of the image to be processed.
     * @param newName the name the processed image will be saved to.
     */
    public SepiaCommand(String imageName, String newName) {
        super(imageName, newName);

    }

    /**
     * @param pixel the pixel whose blue value will be transformed.
     * @return the new blue value for this pixel.
     */
    @Override
    protected int getBlue(Pixel pixel) {
        return (int)(0.272 * pixel.getRed() + 0.534 * pixel.getGreen() + 0.131 * pixel.getBlue());
    }

    /**
     * @param pixel the pixel whose green value will be transformed.
     * @return the new green value for this pixel.
     */
    @Override
    protected int getGreen(Pixel pixel) {
        return (int)(0.349 * pixel.getRed() + 0.686 * pixel.getGreen() + 0.168 * pixel.getBlue());
    }

    /**
     * @param pixel the pixel whose red value will be transformed.
     * @return the new red value for this pixel.
     */
    @Override
    protected int getRed(Pixel pixel) {
        return (int)(0.393 * pixel.getRed() + 0.769 * pixel.getGreen() + 0.189 * pixel.getBlue());
    }
}
