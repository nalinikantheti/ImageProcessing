package view;

import java.io.IOException;

/**
 * Represents a text-only view for the image processor program.
 */
public class ImageProcessorTextView implements ImageProcessorView {
    private Appendable ap;

    /**
     * Creates an ImageProcessorTextView that routes all messages to the given appendable.
     *
     * @param ap the appendable to write to
     */

    public ImageProcessorTextView(Appendable ap) {
        this.ap = ap;
    }

    /**
     * Appends the given message to the appendable
     *
     * @param message the message to render
     * @throws IOException if the message cannot be appended
     */
    @Override
    public void renderMessage(String message) throws IOException {
        ap.append(message);
    }
}
