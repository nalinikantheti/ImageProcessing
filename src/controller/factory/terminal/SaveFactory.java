package controller.factory.terminal;

import java.util.Optional;
import java.util.Scanner;

import command.Command;
import command.SaveImageIOCommand;
import command.SavePPMCommand;
import view.ImageProcessorView;

/**
 * A factory for creating {@link SavePPMCommand}s.
 */
public class SaveFactory extends AbstractTwoStringFactory {
  /**
   * Creates a new SaveFactory with the given view and scanner.
   * Throws an {@code IllegalArgumentException}
   * if either parameter is null.
   *
   * @param view the view which this factory will transmit messages to
   * @param s    the scanner which this factory will read input from
   * @throws IllegalArgumentException if either parameter is null
   */
  public SaveFactory(ImageProcessorView view, Scanner s) {
    super(view, s);
  }

  /**
   * Creates a new {@link SavePPMCommand} that takes in a filepath and an image
   * name and saves the image to that filepath. If the file extension of the first
   * argument is a ppm, it will create a SavePPMCommand, and a SaveImageIOCommand
   * otherwise.
   *
   * @param s1 the filepath
   * @param s2 the name of the image to save to the filesystem
   * @return an Optional of a new Save Command containing a valid command.
   */
  @Override
  protected Optional<Command> makeCommand(Optional<String> s1, Optional<String> s2) {
    String filepath = s1.get();
    String imageName = s2.get();
    String filetype = filepath.substring(filepath.lastIndexOf(".") + 1);
    if (filetype.equalsIgnoreCase("ppm")) {
      return Optional.of(new SavePPMCommand(filepath, imageName));
    } else {
      return Optional.of(new SaveImageIOCommand(filepath, imageName, filetype));
    }
  }
}
