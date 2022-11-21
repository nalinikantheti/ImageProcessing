package view;
import java.awt.*;

import javax.swing.JPanel;
import image.Image;
import image.Pixel;

public class Histogram extends JPanel {
  boolean red = true;
  boolean green = true;
  boolean blue = true;
  boolean intensity = true;
  int width = 512;
  int height = 256;
  int[] redData = new int[256];
  int[] greenData = new int[256];
  int[] blueData = new int[256];
  int[] intensityData = new int[256];

  public void update(Image image){
    redData = new int[256];
    greenData = new int[256];
    blueData = new int[256];
    intensityData = new int[256];
    Dimension bounds = new Dimension(width,height);
    this.setPreferredSize(bounds);
    this.getValues(image);
    this.repaint();
  }

  @Override
  public void paintComponent(Graphics g){
    super.paintComponent(g);
    g.drawLine(0,0,0,width);
    g.drawLine(0,height,width,height);
    if(red){
      g.setColor(Color.red);
      this.makeDataPoint(redData, g);
    }

    if(green){
      g.setColor(new Color(34,139,34));
      this.makeDataPoint(greenData, g);
    }

    if(blue){
      g.setColor(Color.blue);
      this.makeDataPoint(blueData, g);
    }

    if(intensity){
      g.setColor(Color.black);
      this.makeDataPoint(intensityData,g);
    }
    this.addHashMarks(g);
    this.addTitles(g);
  }

  private void addTitles(Graphics g){
    g.setColor(Color.BLACK);
    g.drawString("Amount of Pixels", 0, height / 2);
    g.drawString("Pixel Value", width / 2, height + 15);
  }
  private void addHashMarks(Graphics g){

    for (int x = 0 ; x < 256; x +=30){

      g.setColor(Color.lightGray);
      g.drawLine(x * width / 256, height, x * width / 256, 0);
      g.setColor(Color.black);
      g.drawString(String.valueOf(x),x * width / 256, height);
    }

    for (int y = 0 ; y <= 100; y +=30){
      g.setColor(Color.lightGray);
      g.drawLine(0, y * height / 100, width, y * height / 100);
      g.setColor(Color.black);
      g.drawString(String.valueOf((int)((double)y * getPeak() / 100)),0, (100 - y) * height / 100);
    }
  }


  private void makeDataPoint(int[] data, Graphics g){
    int max = getPeak();
    for(int x = 1; x < 256 ; x += 1 ){
      g.drawLine((x - 1) * width / 256,height - normalize(data[x - 1], max),
              x * width / 256 ,height - normalize(data[x], max));
    }
  }

  private int getPeak(){
    return Math.max(getHighestValue(redData),
            Math.max(getHighestValue(greenData),
                    Math.max(getHighestValue(blueData),
                            getHighestValue(intensityData))));
  }

  private int getHighestValue(int[] data){
    int max = 0;
    for(int i: data){
      if(i > max){
        max = i;
      }
    }
    return max;
  }

  private int normalize(int value, int max){
    if (max == 0){
      return 0;
    }
    return (int)((double) value / max * height);
  }
  private void getValues(Image image) {
    for(int col = 0; col < image.getWidth(); col += 1){
      for(int row = 0; row < image.getHeight(); row += 1){
        Pixel pix = image.getPixel(col,row);
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

  public void toggleRed(){
    this.red = !red;
    this.repaint();
  }

  public void toggleGreen(){
    this.green = !green;
    this.repaint();
  }

  public void toggleBlue(){
    this.repaint();
    this.blue = !blue;
  }

  public void toggleIntensity(){
    this.repaint();
    this.intensity = !intensity;
  }
}
