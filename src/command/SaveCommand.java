package command;

import model.ImageProcessorModel;
import java.io.IOException;

public class SaveCommand implements Command{
    String filepath;
    String newName;

    public SaveCommand(String filepath, String newName) {
        this.filepath = filepath;
        this.newName = newName;
    }

    @Override
    public void go(ImageProcessorModel model) {
        try {
            model.saveImageToFileSystem(newName, filepath);
        } catch(IOException e){
            throw new IllegalArgumentException("file not found");
        }
    }
}

