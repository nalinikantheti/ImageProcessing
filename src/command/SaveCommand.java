package command;

import model.ImageProcessorModel;

import java.io.IOException;

/**
 * A command that saves a given image to a filesystem using a given filepath.
 */
public class SaveCommand implements Command {
    private String filepath;
    private String name;

    /**
     * A constructor for a SaveCommand that uses two arguments.
     *
     * @param filepath the filepath the image is being saved to.
     * @param name     the name of the image being saved.
     */
    public SaveCommand(String filepath, String name) {
        this.filepath = filepath;
        this.name = name;
    }

    /**
     * Runs this SaveCommand by calling the save method using a given model.
     *
     * @param model model used to save image to given filepath.
     */
    @Override
    public void go(ImageProcessorModel model) {
        try {
            model.saveImageToFileSystem(name, filepath);
        } catch (IOException e) {
            throw new IllegalArgumentException("file not found");
        }
    }
}

