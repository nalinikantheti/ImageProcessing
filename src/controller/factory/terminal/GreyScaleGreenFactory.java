package controller.factory.terminal;

import java.util.Optional;
import java.util.Scanner;

import command.Command;
import command.GreyScaleGreenCommand;
import view.ImageProcessorView;

/**
 * A factory for {@link GreyScaleGreenCommand}s.
 */
public class GreyScaleGreenFactory extends AbstractTwoStringFactory {
  /**
   * Creates a new GreyScaleGreenFactory with the given view and scanner.
   * Throws an {@code IllegalArgumentException}
   * if either parameter is null.
   *
   * @param view the view which this factory will transmit messages to
   * @param s    the scanner which this factory will read input from
   * @throws IllegalArgumentException if either parameter is null
   */
  public GreyScaleGreenFactory(ImageProcessorView view, Scanner s) {
    super(view, s);
  }

  /**
   * Creates a new {@link GreyScaleGreenCommand} that uses the image
   * with the name of the first argument and saves
   * the resulting image using the name of the second argument.
   *
   * @param s1 the name of the image to perform the greyscale operation
   * @param s2 the name of the resulting image
   * @return a new Optional of a new GreyScaleGreen Command containing a valid command
   */
  @Override
  protected Optional<Command> makeCommand(Optional<String> s1, Optional<String> s2) {
    return Optional.of(new GreyScaleGreenCommand(s1.get(), s2.get()));
  }
}
