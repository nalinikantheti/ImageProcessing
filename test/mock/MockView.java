package mock;

import view.ImageProcessorView;

import java.io.IOException;

public class MockView implements ImageProcessorView {
    StringBuilder output;
    public MockView(StringBuilder actualViewOutput) {
        this.output = actualViewOutput;
    }

    @Override
    public void show() throws IOException {
        output.append("show");
    }

    @Override
    public void renderMessage(String message) throws IOException {
        output.append(message);
    }
}
