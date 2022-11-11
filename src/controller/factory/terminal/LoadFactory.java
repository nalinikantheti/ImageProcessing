package controller.factory.terminal;

import java.util.Optional;
import java.util.Scanner;

import command.Command;
import command.LoadPPMCommand;
import command.ReadImageIOCommand;
import view.ImageProcessorView;

/**
 * A factory for creating {@link LoadPPMCommand}s.
 */
public class LoadFactory extends AbstractTwoStringFactory {
  /**
   * Creates a new LoadFactory with the given view and scanner.
   * Throws an {@code IllegalArgumentException}
   * if either parameter is null.
   *
   * @param view the view which this factory will transmit messages to
   * @param s    the scanner which this factory will read input from
   * @throws IllegalArgumentException if either parameter is null
   */
  public LoadFactory(ImageProcessorView view, Scanner s) {
    super(view, s);
  }

  /**
   * Creates a new {@link LoadPPMCommand} that loads an image from a filepath
   * (indicated by the first argument) and saves it to the model using a name
   * provided by the second argument. If the file extension is a PPM, it will
   * create a LoadPPMCommand, and a ReadImageIOCommand otherwise.
   *
   * @param s1 the filepath of the image to load
   * @param s2 the name of the image in the model
   * @return an Optional of a new Load Command  containing a valid command
   */
  @Override
  protected Optional<Command> makeCommand(Optional<String> s1, Optional<String> s2) {
    String filepath = s1.get();
    String imageName = s2.get();
    String filetype = filepath.substring(filepath.lastIndexOf(".") + 1);
    if (filetype.equalsIgnoreCase("ppm")) {
      return Optional.of(new LoadPPMCommand(filepath, imageName));
    } else {
      return Optional.of(new ReadImageIOCommand(filepath, imageName));
    }
  }
}
