package command;

/**
 * A Command that blurs a given image.
 */
public class BlurCommand extends FilterCommand {

  /**
   * A constructor for a blurCommand with two arguments.
   *
   * @param imageName the name of the image to be blurred.
   * @param newName   the name the blurred image will be saved as.
   */
  public BlurCommand(String imageName, String newName) {
    super(imageName, newName);

  }

  /**
   * provides a filter that will sharpen an image.
   *
   * @return the filter to sharpen an image.
   */
  @Override
  protected double[][] makeFilter() {
    //THESE ARE COLUMNS!!!!!!
    double[][] blurFilter = new double[3][];
    blurFilter[0] = new double[3];
    blurFilter[0][0] = 1.0 / 16.0;
    blurFilter[0][1] = 1.0 / 8.0;
    blurFilter[0][2] = 1.0 / 16.0;

    blurFilter[1] = new double[3];
    blurFilter[1][0] = 1.0 / 8.0;
    blurFilter[1][1] = 1.0 / 4.0;
    blurFilter[1][2] = 1.0 / 8.0;

    blurFilter[2] = new double[3];
    blurFilter[2][0] = 1.0 / 16.0;
    blurFilter[2][1] = 1.0 / 8.0;
    blurFilter[2][2] = 1.0 / 16.0;

    return blurFilter;
  }

}
