package command;

import model.ImageProcessorModel;

public class DarkenCommand implements Command{
    String imageName;

    public DarkenCommand(String imageName) {
        this.imageName = imageName;
    }

    @Override
    public void go(ImageProcessorModel model) {
        model.getImage();
    }
}
