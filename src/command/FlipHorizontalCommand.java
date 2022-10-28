package command;

import image.Image;
import model.ImageProcessorModel;

public class FlipHorizontalCommand implements Command{

    String imageName;
    String newName;

    public FlipHorizontalCommand(String imageName, String newName) {
        this.imageName = imageName;
        this.newName = newName;
    }

    @Override
    public void go(ImageProcessorModel model) {
        Image oldImage = model.getImage(imageName);
        Image newImage = model.getImage(imageName).clone();

        for (int y = 0; y < oldImage.getHeight(); y++) {
            for (int x = 0; x < oldImage.getWidth(); x++) {
                newImage.setPixel(oldImage.getPixel(oldImage.getWidth() - x - 1, y), x, y);
            }
        }

        model.saveImageToModel(newImage, newName);
    }
}
