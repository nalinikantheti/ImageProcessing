package model;


import image.Image;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface ImageProcessorModel extends ImageProcessorModelState {
    public void saveImageToFileSystem(String name, String filepath) throws IOException;

    public void saveImageToModel(Image image, String name);

    public void loadImage(String filepath, String name) throws FileNotFoundException;

    public void removeImage(String name);
}
