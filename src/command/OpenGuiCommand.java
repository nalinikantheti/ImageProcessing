package command;

import javax.swing.JFileChooser;

import model.ImageProcessorModel;

/**
 * A command that creates an appropriate load command based on the
 * user's interaction with the gui.
 */
public class OpenGuiCommand implements Command {

  /**
   * Runs this command by opening a JFileChooser and creating an
   * appropriate command from the user's interaction with the file chooser.
   * @param model model used to retrieve image for command to then process.
   */
  @Override
  public void run(ImageProcessorModel model) {
    JFileChooser file = new JFileChooser();
    int returnVal = file.showOpenDialog(null);

    if (returnVal == JFileChooser.APPROVE_OPTION) {
      String filepath = file.getSelectedFile().getAbsolutePath();
      String filetype = filepath.substring(filepath.lastIndexOf(".") + 1);
      if (filetype.equalsIgnoreCase("ppm")) {
        new LoadPPMCommand(filepath, "working").run(model);
      } else {
        new ReadImageIOCommand(filepath, "working").run(model);
      }
    }
  }
}
