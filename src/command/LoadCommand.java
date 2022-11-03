package command;

import model.ImageProcessorModel;

import java.io.FileNotFoundException;

/**
 * A command that loads an image from a given filepath and saves it to a given string.
 */
public class LoadCommand implements Command {
    private String filepath;
    private String newName;

    /**
     * A Constructor for a LoadCommand with two arguments.
     *
     * @param filepath the filepath the image will be loaded from.
     * @param newName  the name by which the loaded image will be saved as.
     */
    public LoadCommand(String filepath, String newName) {
        this.filepath = filepath;
        this.newName = newName;
    }

    /**
     * Runs this LoadCommand by calling loadImage in the given model.
     *
     * @param model model used to retrieve image from filepath.
     */
    @Override
    public void go(ImageProcessorModel model) {
        try {
            model.loadImage(filepath, newName);
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("file not found");
        }
    }
}
