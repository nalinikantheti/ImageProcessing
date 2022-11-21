package command;

import javax.swing.JFileChooser;

import model.ImageProcessorModel;

/**
 * A Command that creates the appropriate save command
 * based on the users interaction with the gui.
 */
public class SaveAsGuiCommand implements Command {

  /**
   * Runs this command by opening a JFileChooser, and then creating
   * a new SavePPMCommand or a SaveImageIOCommand based on the user
   * interaction with the file chooser.
   * @param model model used to retrieve image for command to then process.
   */
  @Override
  public void run(ImageProcessorModel model) {
    JFileChooser file = new JFileChooser();
    int returnVal = file.showSaveDialog(null);

    if (returnVal == JFileChooser.APPROVE_OPTION) {
      String filepath = file.getSelectedFile().getAbsolutePath();
      String filetype = filepath.substring(filepath.lastIndexOf(".") + 1);
      if (filetype.equalsIgnoreCase("ppm")) {
        new SavePPMCommand(filepath, "working").run(model);
      } else {
        new SaveImageIOCommand(filepath, "working", filetype).run(model);
      }
    }
  }
}
