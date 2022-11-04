package mock;

import view.ImageProcessorView;

import java.io.IOException;

/**
 * A mock view which lLogs all the messages transmitted to this view.
 */
public class MockView implements ImageProcessorView {
    private StringBuilder output;

    /**
     * Creates a MockView that logs everything to the given StringBuilder.
     *
     * @param actualViewOutput the log
     */
    public MockView(StringBuilder actualViewOutput) {
        this.output = actualViewOutput;
    }

    /**
     * Logs the given message called to be rendered.
     *
     * @param message the message to log
     * @throws IOException never
     */
    @Override
    public void renderMessage(String message) throws IOException {
        output.append(message);
    }
}
