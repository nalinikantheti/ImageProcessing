package view;

import java.io.IOException;

public class ImageProcessorTextView implements ImageProcessorView{
    Appendable ap;

    ImageProcessorTextView(Appendable ap){
        this.ap = ap;
    }

    @Override
    public void show() {
        System.out.println("Image!");
    }

    @Override
    public void renderMessage(String message) throws IOException {
        ap.append(message);
    }
}
