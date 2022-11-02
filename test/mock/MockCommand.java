package mock;

import command.Command;
import model.ImageProcessorModel;

public class MockCommand implements Command {
    StringBuilder log;
    public MockCommand(StringBuilder log) {
        this.log = log;
    }

    @Override
    public void go(ImageProcessorModel model) {
        log.append("made some changes to an image.");
    }
}
