package model;

import Image.Image;

import Image.ImageUtil;

import java.io.FileNotFoundException;
import Image.Pixel;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class ImageProcessorModelImpl implements ImageProcessorModel{
    private HashMap<String, Image> imageNames;

    public ImageProcessorModelImpl(HashMap<String, Image> imageNames){
        this.imageNames = imageNames;
    }

    @Override
    public void saveImageToFileSystem(String name, String filepath) throws IOException {
        Image image = this.imageNames.get(name);
        StringBuilder ppm = new StringBuilder();
        ppm.append("P3\n");
        ppm.append(image.getWidth() + " " + image.getHeight() + "\n");
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
        imageNames.put(name, image);
    }

    @Override
    public void loadImage(String filepath, String name) throws FileNotFoundException {
        Image loadImage = ImageUtil.readPPM(filepath);
        this.saveImageToModel(loadImage, name);
    }

    @Override
    public void removeImage(String name) {
        imageNames.remove(name);
    }

    @Override
    public Image getImage(String name) {
        return imageNames.get(name);
    }

    @Override
    public Set<String> getImageNames() {
        return imageNames.keySet();
    }
}
