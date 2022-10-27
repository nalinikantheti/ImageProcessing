package command;

import model.ImageProcessorModel;

public interface Command {
    public void go(ImageProcessorModel model);
}
