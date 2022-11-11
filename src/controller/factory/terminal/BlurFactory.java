package controller.factory.terminal;

import java.util.Optional;
import java.util.Scanner;

import command.BlurCommand;
import command.Command;
import view.ImageProcessorView;

/**
 * A factory for creating {@link BlurCommand}.
 */
public class BlurFactory extends AbstractTwoStringFactory {

  /**
   * Creates a new BlurFactory with the given view and scanner.
   * Throws an {@code IllegalArgumentException}
   * if either parameter is null.
   *
   * @param view the view which this factory will transmit messages to
   * @param s    the scanner which this factory will read input from
   * @throws IllegalArgumentException if either parameter is null
   */
  public BlurFactory(ImageProcessorView view, Scanner s) {
    super(view, s);
  }

  /**
   * Creates a new {@link BlurCommand} that uses the image with the name
   * of the first argument and saves the resulting image to the name of the
   * second argument.
   *
   * @param s1 the name of the image to perform the blur operation
   * @param s2 the name of the blurred image
   * @return a new Optional of a new Blur Command containing a valid command
   */
  @Override
  protected Optional<Command> makeCommand(Optional<String> s1, Optional<String> s2) {
    return Optional.of(new BlurCommand(s1.get(), s2.get()));
  }
}
