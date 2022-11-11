package command;


/**
 * A Command that sharpens an image.
 */
public class SharpenCommand extends FilterCommand {
  /**
   * A Constructor for a SharpenCommand with two arguments.
   *
   * @param imageName the name of the image to be sharpened.
   * @param newName   the name the sharpened image will be saved to.
   */
  public SharpenCommand(String imageName, String newName) {
    super(imageName, newName);
  }

  /**
   * provides the filter that will blur an image.
   */
  @Override
  protected double[][] makeFilter() {
    //THESE ARE COLUMNS!!!!!
    double[][] filter = new double[5][];

    filter[0] = new double[5];
    filter[0][0] = -1.0 / 8.0;
    filter[0][1] = -1.0 / 8.0;
    filter[0][2] = -1.0 / 8.0;
    filter[0][3] = -1.0 / 8.0;
    filter[0][4] = -1.0 / 8.0;

    filter[1] = new double[5];
    filter[1][0] = -1.0 / 8.0;
    filter[1][1] = 1.0 / 4.0;
    filter[1][2] = 1.0 / 4.0;
    filter[1][3] = 1.0 / 4.0;
    filter[1][4] = -1.0 / 8.0;

    filter[2] = new double[5];
    filter[2][0] = -1.0 / 8.0;
    filter[2][1] = 1.0 / 4.0;
    filter[2][2] = 1.0;
    filter[2][3] = 1.0 / 4.0;
    filter[2][4] = -1.0 / 8.0;

    filter[3] = new double[5];
    filter[3][0] = -1.0 / 8.0;
    filter[3][1] = 1.0 / 4.0;
    filter[3][2] = 1.0 / 4.0;
    filter[3][3] = 1.0 / 4.0;
    filter[3][4] = -1.0 / 8.0;

    filter[4] = new double[5];
    filter[4][0] = -1.0 / 8.0;
    filter[4][1] = -1.0 / 8.0;
    filter[4][2] = -1.0 / 8.0;
    filter[4][3] = -1.0 / 8.0;
    filter[4][4] = -1.0 / 8.0;

    return filter;
  }
}
