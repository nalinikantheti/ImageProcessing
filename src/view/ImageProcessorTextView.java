package view;

import java.io.IOException;

public class ImageProcessorTextView implements ImageProcessorView{
    Appendable ap;

    public ImageProcessorTextView(Appendable ap){
        this.ap = ap;
    }

    @Override
    public void show() throws IOException {
        ap.append("Image!");
    }

    @Override
    public void renderMessage(String message) throws IOException {
        ap.append(message);
    }
}
