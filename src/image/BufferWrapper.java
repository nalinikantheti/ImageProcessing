package image;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;

import static util.ImageProcessorUtils.ensureNotNull;

/**
 * A wrapper for a BufferedImage that is also an Image.
 */
public class BufferWrapper extends AbstractImage {
  private BufferedImage buffer;

  /**
   * A constructor for a BufferWrapper that takes in
   * a bufferedImage to be wrapped.
   *
   * @param buffer the bufferedImage this wrapper will hold.
   */
  public BufferWrapper(BufferedImage buffer) {
    ensureNotNull(buffer, "Buffer cannot be null.");
    this.buffer = buffer;
  }

  /**
   * Sets the RGB values of the specified pixel of the bufferedImage
   * to the given pixel's RGB values.
   *
   * @param pix the pixel with the RGB values to replace the corresponding pixel's RGB values.
   * @param x   the x coordinate of the pixel to be changed in the buffered image.
   * @param y   the y coordinate of the pixel to be changed in the buffered image.
   */
  @Override
  public void setPixel(Pixel pix, int x, int y) {
    ensureInBounds(x, y);
    ensureNotNull(pix, "Pixel cannot be null.");
    buffer.setRGB(x, y, this.pixToRGB(pix));
  }

  /**
   * Retrieves the pixel at the given positon in the buffered image.
   *
   * @param x the x coordinate of the pixel to be retrieved.
   * @param y the y coordinate of the pixel to be retrieved.
   * @return a Pixel with the same RGB values of the pixel at
   * the given position in the buffered image.
   */
  @Override
  public Pixel getPixel(int x, int y) {
    ensureInBounds(x, y);
    int rgb = buffer.getRGB(x, y);
    int red = (rgb >> 16) & 0xFF;
    int green = (rgb >> 8) & 0xFF;
    int blue = rgb & 0xFF;
    return new Pixel(red, green, blue);
  }

  /**
   * retrieves the height of the buffered Image.
   *
   * @return an integer representing height.
   */
  @Override
  public int getHeight() {
    return buffer.getHeight();
  }

  /**
   * retrieves the width of the buffered Image.
   *
   * @return an integer representing width.
   */
  @Override
  public int getWidth() {
    return buffer.getWidth();
  }

  /**
   * produces a copy of the buffered image.
   *
   * @return a new BufferWrapper containing a copy of the Buffered Image.
   */
  @Override
  public Image clone() {
    ColorModel cm = buffer.getColorModel();
    boolean isAlphaPremutiplied = cm.isAlphaPremultiplied();
    WritableRaster raster = buffer.copyData(buffer.getRaster().createCompatibleWritableRaster());
    return new BufferWrapper(new BufferedImage(cm, raster, isAlphaPremutiplied, null));
  }

  private int pixToRGB(Pixel pix) {
    return pix.getRed() << 16 | pix.getGreen() << 8 | pix.getBlue();
  }
}
