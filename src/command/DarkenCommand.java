package command;

import image.Image;
import image.Pixel;
import model.ImageProcessorModel;

public class DarkenCommand implements Command {
    private String imageName;
    private int intensity;
    private String newName;

    public DarkenCommand(String imageName, int intensity, String newName) {
        this.imageName = imageName;
        this.intensity = intensity;
        this.newName = newName;
    }

    @Override
    public void go(ImageProcessorModel model) {
        Image image = model.getImage(imageName);
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                Pixel old = image.getPixel(x, y);
                Pixel newPix = new Pixel(old.getRed() - intensity,
                        old.getGreen() - intensity,
                        old.getBlue() - intensity);
                image.setPixel(newPix, x, y);
            }
        }

        model.saveImageToModel(image, newName);
    }
}
