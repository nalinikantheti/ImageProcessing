package view;

import java.io.IOException;

public interface ImageProcessorView {
    public void show() throws IOException;
    public void renderMessage(String message) throws IOException;
}
