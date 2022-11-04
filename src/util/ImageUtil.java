package util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import image.Image;
import image.Pixel;
import image.RGBImage;


/**
 * This class contains utility methods to read a PPM image from
 * file and simply print its contents. Feel free to change this method
 * as required.
 */
public class ImageUtil {


  /**
   * Reads a PPM at the given filepath and returns it as an image.
   *
   * @param filename the filepath of the PPM image
   * @return an {@link Image} representing the PPM
   * @throws FileNotFoundException if the file cannot be found
   */
  public static Image readPPM(String filename) throws FileNotFoundException {

    Scanner sc;

    try {
      sc = new Scanner(new FileInputStream(filename));
    } catch (FileNotFoundException e) {
      throw new FileNotFoundException("File " + filename + " not found!");
    }

    StringBuilder builder = new StringBuilder();

    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }

    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());

    String token;

    token = sc.next();
    if (!token.equals("P3")) {
      System.out.println("Invalid PPM file: plain RAW file should begin with P3");
    }
    int width = sc.nextInt();
    System.out.println("Width of image: " + width);
    int height = sc.nextInt();
    System.out.println("Height of image: " + height);
    int maxValue = sc.nextInt();
    System.out.println("Maximum value of a color in this file (usually 255): " + maxValue);

    Image image = new RGBImage(width, height);

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        Pixel pix = new Pixel(r, g, b);
        image.setPixel(pix, x, y);
      }
    }

    return image;
  }
}

