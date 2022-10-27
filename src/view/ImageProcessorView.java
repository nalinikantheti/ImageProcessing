package view;

import java.io.IOException;

public interface ImageProcessorView {
    public void show();
    public void renderMessage(String message) throws IOException;
}
