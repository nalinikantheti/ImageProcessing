package command;

import model.ImageProcessorModel;


import Image.Image;
import Image.Pixel;

public abstract class AbstractGreyScaleCommand implements Command {
    protected String imageName;
    protected String newName;

    public AbstractGreyScaleCommand(String imageName, String newName) {
        this.imageName = imageName;
        this.newName = newName;
    }

    @Override
    public void go(ImageProcessorModel model) {
        Image image = model.getImage(imageName);
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                Pixel old = image.getPixel(x, y);
                Pixel newPix = new Pixel(getValue(old),
                        getValue(old),
                        getValue(old));
                image.setPixel(newPix, x, y);
            }
        }

        model.saveImageToModel(image, newName);
    }
    protected abstract int getValue(Pixel pixel);
}

