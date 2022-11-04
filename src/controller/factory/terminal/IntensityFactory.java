package controller.factory.terminal;

import java.util.Optional;
import java.util.Scanner;

import command.Command;
import command.IntensityCommand;
import view.ImageProcessorView;

/**
 * A factory for {@link IntensityCommand}s.
 */
public class IntensityFactory extends AbstractTwoStringFactory {
  /**
   * Creates a new IntensityFactory with the given view and scanner.
   * Throws an {@code IllegalArgumentException}
   * if either parameter is null.
   *
   * @param view the view which this factory will transmit messages to
   * @param s    the scanner which this factory will read input from
   * @throws IllegalArgumentException if either parameter is null
   */
  public IntensityFactory(ImageProcessorView view, Scanner s) {
    super(view, s);
  }

  /**
   * Creates a {@link IntensityCommand} that uses an image with a name
   * matching the first argument and saves the
   * resulting image in the  model using the second argument as the name.
   *
   * @param s1 the name of the original image
   * @param s2 the name of the resulting image
   * @return an Optional of a new Intensity Command containing a valid command
   */
  @Override
  protected Optional<Command> makeCommand(Optional<String> s1, Optional<String> s2) {
    return Optional.of(new IntensityCommand(s1.get(), s2.get()));
  }
}
