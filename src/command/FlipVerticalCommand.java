package command;

import image.Image;
import model.ImageProcessorModel;

/**
 * A Command that flips an image on its horizontal axis and saves
 * the flipped image as a different file.
 */
public class FlipVerticalCommand implements Command {

    private String imageName;
    private String newName;

    /**
     * A constructor for a FlipVerticalCommand using two arguments.
     *
     * @param imageName the name of the image to be processed.
     * @param newName   the name the processed image is being saved as.
     */
    public FlipVerticalCommand(String imageName, String newName) {
        this.imageName = imageName;
        this.newName = newName;
    }

    /**
     * Runs this FlipVerticalCommand on an image.
     *
     * @param model model used to retrieve image for command to then process.
     */
    @Override
    public void go(ImageProcessorModel model) {
        Image oldImage = model.getImage(imageName);
        Image newImage = oldImage.clone();

        for (int y = 0; y < oldImage.getHeight(); y++) {
            for (int x = 0; x < oldImage.getWidth(); x++) {
                newImage.setPixel(oldImage.getPixel(x, oldImage.getHeight() - y - 1), x, y);
            }
        }

        model.saveImageToModel(newImage, newName);
    }
}
