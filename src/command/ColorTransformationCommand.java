package command;

import image.Image;
import image.Pixel;
import model.ImageProcessorModel;

import static util.ImageProcessorUtils.createValidPixel;

public abstract class ColorTransformationCommand implements Command {
    protected String imageName;
    protected String newName;

    public ColorTransformationCommand(String imageName, String newName) {
        this.imageName = imageName;
        this.newName = newName;
    }

    @Override
    public void run(ImageProcessorModel model) {
        Image image = model.getImage(imageName);
        for (int x = 0; x < image.getWidth(); x += 1) {
            for (int y = 0; y < image.getHeight(); y += 1) {
                Pixel pixel = image.getPixel(x, y);
                image.setPixel(createValidPixel(getRed(pixel), getGreen(pixel), getBlue(pixel)), x
                        , y);
            }
        }
        model.saveImageToModel(image, newName);
    }

    protected abstract int getBlue(Pixel pixel);

    protected abstract int getGreen(Pixel pixel);

    protected abstract int getRed(Pixel pixel);
}
