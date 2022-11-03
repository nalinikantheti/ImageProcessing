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

/**
 * A model that allows users to make observations about images and
 * perform operations on them such as load and save. All other
 * operations are handled by the command function objects.
 */
public class ImageProcessorModelImpl implements ImageProcessorModel {
    private HashMap<String, Image> imageNames;


    /**
     * A constructor for an ImageProcessorModelImpl
     * that just initializes this Hashmap of ImageNames
     * to a new empty HashMap<>().
     */
    public ImageProcessorModelImpl() {
        imageNames = new HashMap<>();
    }

    /**
     * Retrieves an Image using a given name, constructs a portable pixel map
     * from it using a StringBuilder, and then saves it to a given filepath.
     *
     * @param name     name of image to be saved.
     * @param filepath location of image to be saved to.
     * @throws IOException if writing to file fails.
     */
    @Override
    public void saveImageToFileSystem(String name, String filepath) throws IOException {
        Image image = this.getImage(name);
        StringBuilder ppm = new StringBuilder();
        ppm.append("P3\n");
        ppm.append(image.getWidth() + " " + image.getHeight() + "\n");
        ppm.append("255\n"); //TODO fix it??
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                Pixel pix = image.getPixel(x, y);
                ppm.append(pix.getRed() + "\n");
                ppm.append(pix.getGreen() + "\n");
                ppm.append(pix.getBlue() + "\n");
            }
        }

        Path file = Paths.get(filepath);
        Files.writeString(file, ppm);


    }

    /**
     * Saves a given image to this model by adding it to this
     * Hashmap<String,Image>, and ensures given image is not null.
     *
     * @param image image to be saved.
     * @param name  name by which image will be saved.
     */
    @Override
    public void saveImageToModel(Image image, String name) {
        ensureNotNull(image, "Image cannot be null.");
        imageNames.put(name, image);
    }

    /**
     * Loads image of given name from given filepath.
     *
     * @param filepath location of image to be loaded.
     * @param name     name of image to be loaded.
     * @throws FileNotFoundException if given file cannot be found.
     */
    @Override
    public void loadImage(String filepath, String name) throws FileNotFoundException {
        Image loadImage = ImageUtil.readPPM(filepath);
        this.saveImageToModel(loadImage, name);
    }

    /**
     * Ensures given image exists before
     * removing it from this HashMap<String,Image>.
     *
     * @param name name of image to be removed.
     */
    @Override
    public void removeImage(String name) {
        ensureImageExists(name);
        imageNames.remove(name);
    }

    /**
     * Retrieves the image of the given name from this
     * HashMap<String,Image> and ensures it exists.
     *
     * @param name name of image to be retrieved.
     * @return Image of given name.
     */
    @Override
    public Image getImage(String name) {
        ensureImageExists(name);
        return imageNames.get(name);
    }

    /**
     * Produces the set of all the names of the images in this HashMap<String,Image>.
     *
     * @return a set of all the keys in this HashMap<String, Image>.
     */
    @Override
    public Set<String> getImageNames() {
        return imageNames.keySet();
    }

    private void ensureImageExists(String name) {
        if (!imageNames.containsKey(name)) {
            throw new IllegalArgumentException("Image not found.");
        }
    }
}
