package view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Color;
import javax.swing.JPanel;

import image.Image;
import image.Pixel;

/**
 * A class representing an Image Histogram,
 * which graphs the amount of pixels that contain
 * each of the distinct 256 RGB values.
 */
public class Histogram extends JPanel {
  private boolean red = true;
  private boolean green = true;
  private boolean blue = true;
  private boolean intensity = true;
  private int width = 512;
  private int height = 256;
  private int[] redData = new int[256];
  private int[] greenData = new int[256];
  private int[] blueData = new int[256];
  private int[] intensityData = new int[256];

  /**
   * Updates the histogram using data from the provided image
   * by resetting the data for each RGB and intensity values
   * to all zeros, retrieving the new values from the image,
   * and then redrawing the graph using repaint().
   *
   * @param image the image the new data will be retrieved from.
   */
  public void update(Image image) {
    redData = new int[256];
    greenData = new int[256];
    blueData = new int[256];
    intensityData = new int[256];
    Dimension bounds = new Dimension(width, height);
    this.setPreferredSize(bounds);
    this.getValues(image);
    this.repaint();
  }

  /**
   * Overrides the paintComponent method so that it uses
   * this histogram's data to paint a graph.
   *
   * @param g the <code>Graphics</code> object to protect.
   */
  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.drawLine(0, 0, 0, width);
    g.drawLine(0, height, width, height);
    if (red) {
      g.setColor(Color.red);
      this.makeDataPoint(redData, g);
    }

    if (green) {
      g.setColor(new Color(34, 139, 34));
      this.makeDataPoint(greenData, g);
    }

    if (blue) {
      g.setColor(Color.blue);
      this.makeDataPoint(blueData, g);
    }

    if (intensity) {
      g.setColor(Color.black);
      this.makeDataPoint(intensityData, g);
    }
    this.addHashMarks(g);
    this.addTitles(g);
  }

  private void addTitles(Graphics g) {
    g.setColor(Color.BLACK);
    g.drawString("Amount of Pixels", 0, height / 2);
    g.drawString("Pixel Value", width / 2, height + 15);
  }

  private void addHashMarks(Graphics g) {

    for (int x = 0; x < 256; x += 30) {

      g.setColor(Color.lightGray);
      g.drawLine(x * width / 256, height, x * width / 256, 0);
      g.setColor(Color.black);
      g.drawString(String.valueOf(x), x * width / 256, height);
    }

    for (int y = 0; y <= 100; y += 30) {
      g.setColor(Color.lightGray);
      g.drawLine(0, y * height / 100, width, y * height / 100);
      g.setColor(Color.black);
      g.drawString(String.valueOf((int) ((double) y * getPeak() / 100)),
              0, (100 - y) * height / 100);
    }
  }


  private void makeDataPoint(int[] data, Graphics g) {
    int max = getPeak();
    for (int x = 1; x < 256; x += 1) {
      g.drawLine((x - 1) * width / 256, height - normalize(data[x - 1], max),
              x * width / 256, height - normalize(data[x], max));
    }
  }

  private int getPeak() {
    return Math.max(getHighestValue(redData),
            Math.max(getHighestValue(greenData),
                    Math.max(getHighestValue(blueData),
                            getHighestValue(intensityData))));
  }

  private int getHighestValue(int[] data) {
    int max = 0;
    for (int i : data) {
      if (i > max) {
        max = i;
      }
    }
    return max;
  }

  private int normalize(int value, int max) {
    if (max == 0) {
      return 0;
    }
    return (int) ((double) value / max * height);
  }

  private void getValues(Image image) {
    for (int col = 0; col < image.getWidth(); col += 1) {
      for (int row = 0; row < image.getHeight(); row += 1) {
        Pixel pix = image.getPixel(col, row);
        int redValue = pix.getRed();
        int greenValue = pix.getGreen();
        int blueValue = pix.getBlue();
        int intensity = (int) (greenValue + redValue + blueValue) / 3;
        redData[redValue] += 1;
        greenData[greenValue] += 1;
        blueData[blueValue] += 1;
        intensityData[intensity] += 1;
      }
    }
  }

  /**
   * toggles between the red data being shown and not being shown in the histogram.
   */
  public void toggleRed() {
    this.red = !red;
    this.repaint();
  }

  /**
   * toggles between the green data being shown and not being shown in the histogram.
   */
  public void toggleGreen() {
    this.green = !green;
    this.repaint();
  }

  /**
   * toggles between the blue data being shown and not being shown in the histogram.
   */
  public void toggleBlue() {
    this.repaint();
    this.blue = !blue;
  }

  /**
   * toggles between the intensity data being shown and not being shown in the histogram.
   */
  public void toggleIntensity() {
    this.repaint();
    this.intensity = !intensity;
  }
}
