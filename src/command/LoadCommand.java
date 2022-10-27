package command;

import model.ImageProcessorModel;

import java.io.FileNotFoundException;

public class LoadCommand implements Command{
    String filepath;
    String newName;

    public LoadCommand(String filepath, String newName) {
        this.filepath = filepath;
        this.newName = newName;
    }

    @Override
    public void go(ImageProcessorModel model) {
        try {
            model.loadImage(filepath, newName);
        } catch(FileNotFoundException e){
            throw new IllegalArgumentException("file not found");
        }
    }
}
