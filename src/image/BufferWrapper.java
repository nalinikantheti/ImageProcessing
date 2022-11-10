package image;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;

import static util.ImageProcessorUtils.ensureNotNull;

public class BufferWrapper extends AbstractImage{
  BufferedImage buffer;

  public BufferWrapper(BufferedImage buffer) {
    ensureNotNull(buffer, "Buffer cannot be null.");
    this.buffer = buffer;
  }

  @Override
  public void setPixel(Pixel pix, int x, int y) {
    ensureInBounds(x,y);
    ensureNotNull(pix, "Pixel cannot be null.");
    buffer.setRGB(x,y, this.pixToRGB(pix));
  }

  @Override
  public Pixel getPixel(int x, int y) {
    ensureInBounds(x,y);
    int rgb = buffer.getRGB(x,y);
    int red = (rgb >> 16) & 0xFF;
    int green = (rgb >> 8) & 0xFF;
    int blue = rgb & 0xFF;
    return new Pixel(red, green, blue);
  }

  @Override
  public int getHeight() {
    return buffer.getHeight();
  }

  @Override
  public int getWidth() {
    return buffer.getWidth();
  }

  @Override
  public Image clone() {
    ColorModel cm = buffer.getColorModel();
    boolean isAlphaPremutiplied = cm.isAlphaPremultiplied();
    WritableRaster raster = buffer.copyData(buffer.getRaster().createCompatibleWritableRaster());
    return new BufferWrapper(new BufferedImage(cm, raster, isAlphaPremutiplied, null));
  }



  private int pixToRGB(Pixel pix){
    return pix.getRed() << 16 | pix.getGreen() << 8 | pix.getBlue();
  }
}
