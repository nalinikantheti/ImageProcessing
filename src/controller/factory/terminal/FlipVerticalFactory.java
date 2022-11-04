package controller.factory.terminal;

import java.util.Optional;
import java.util.Scanner;

import command.Command;
import command.FlipVerticalCommand;
import view.ImageProcessorView;

/**
 * A factory for {@link FlipVerticalCommand}s.
 */
public class FlipVerticalFactory extends AbstractTwoStringFactory {
  /**
   * Creates a new FlipVerticalFactory with the given view and scanner.
   * Throws an {@code IllegalArgumentException}
   * if either parameter is null.
   *
   * @param view the view which this factory will transmit messages to
   * @param s    the scanner which this factory will read input from
   * @throws IllegalArgumentException if either parameter is null
   */
  public FlipVerticalFactory(ImageProcessorView view, Scanner s) {
    super(view, s);
  }

  /**
   * Creates a new {@link FlipVerticalCommand} that uses flips the image
   * that has a name matching the first string
   * argument and saves the result with the name of the second string argument.
   *
   * @param s1 the name of the image to flip
   * @param s2 the name of the resulting image to save to the model
   * @return a new Optional of a new FlipVertical Command  that contains a valid command
   */
  @Override
  protected Optional<Command> makeCommand(Optional<String> s1, Optional<String> s2) {
    return Optional.of(new FlipVerticalCommand(s1.get(), s2.get()));
  }
}
