package controller.factory.terminal;

import java.util.Optional;
import java.util.Scanner;

import command.Command;
import command.GreyScaleRedCommand;
import view.ImageProcessorView;

/**
 * A factory for {@link GreyScaleRedCommand}s.
 */
public class GreyScaleRedFactory extends AbstractTwoStringFactory {
  /**
   * Creates a new GreyScaleRedFactory with the given view and scanner.
   * Throws an {@code IllegalArgumentException}
   * if either parameter is null.
   *
   * @param view the view which this factory will transmit messages to
   * @param s    the scanner which this factory will read input from
   * @throws IllegalArgumentException if either parameter is null
   */
  public GreyScaleRedFactory(ImageProcessorView view, Scanner s) {
    super(view, s);
  }

  /**
   * Creates a new {@link GreyScaleRedCommand} that uses the image with the
   * name of the first argument and saves the
   * resulting image to the model with a name of the second argument.
   *
   * @param s1 the name of the image to perform the greyscale operation on
   * @param s2 the name of the resulting image to save to the model
   * @return an Optional of a new GreyScaleRedCommand containing a valid command
   */
  @Override
  protected Optional<Command> makeCommand(Optional<String> s1, Optional<String> s2) {
    return Optional.of(new GreyScaleRedCommand(s1.get(), s2.get()));
  }
}
