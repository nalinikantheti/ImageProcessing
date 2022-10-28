package model;

import image.Image;

import util.ImageUtil;

import java.io.FileNotFoundException;
import image.Pixel;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Set;

import static util.ImageProcessorUtils.ensureNotNull;

public class ImageProcessorModelImpl implements ImageProcessorModel{
    private HashMap<String, Image> imageNames;


    public ImageProcessorModelImpl() {
        imageNames = new HashMap<>();
    }

    @Override
    public void saveImageToFileSystem(String name, String filepath) throws IOException {
        Image image = this.getImage(name);
        StringBuilder ppm = new StringBuilder();
        ppm.append("P3\n");
        ppm.append(image.getWidth() + " " + image.getHeight() + "\n");
        ppm.append("255\n"); //TODO fix it??
        for(int y = 0; y < image.getHeight(); y++){
            for(int x = 0; x < image.getWidth(); x++){
                Pixel pix = image.getPixel(x,y);
                ppm.append(pix.getRed() + "\n");
                ppm.append(pix.getGreen() + "\n");
                ppm.append(pix.getBlue() + "\n");
            }
        }

        Path file = Paths.get(filepath);
        Files.writeString(file, ppm);


    }

    @Override
    public void saveImageToModel(Image image, String name) {
        ensureNotNull(image, "Image cannot be null.");
        imageNames.put(name, image);
    }

    @Override
    public void loadImage(String filepath, String name) throws FileNotFoundException {
        Image loadImage = ImageUtil.readPPM(filepath);
        this.saveImageToModel(loadImage, name);
    }

    @Override
    public void removeImage(String name) {
        ensureImageExists(name);
        imageNames.remove(name);
    }

    @Override
    public Image getImage(String name) {
        ensureImageExists(name);
        return imageNames.get(name);
    }

    @Override
    public Set<String> getImageNames() {
        return imageNames.keySet();
    }
    private void ensureImageExists(String name) {
        if(!imageNames.containsKey(name)) {
            throw new IllegalArgumentException("Image not found.");
        }
    }
}
