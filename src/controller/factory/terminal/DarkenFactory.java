package controller.factory.terminal;

import java.util.Optional;
import java.util.Scanner;

import command.Command;
import command.DarkenCommand;
import view.ImageProcessorView;

/**
 * A factory for {@link DarkenCommand}s.
 */
public class DarkenFactory extends AbstractShadeChangeFactory {
  /**
   * Creates a new DarkenFactory with the given view and scanner.
   * Throws an {@code IllegalArgumentException}
   * if either parameter is null.
   *
   * @param view the view which this factory will transmit messages to
   * @param s    the scanner which this factory will read input from
   * @throws IllegalArgumentException if either parameter is null
   */
  public DarkenFactory(ImageProcessorView view, Scanner s) {
    super(view, s);
  }


  /**
   * Creates a new {@link DarkenCommand} that uses the image with the given name,
   * darkens it by the given intensity,
   * and saves the resulting image to the model with the given name.
   *
   * @param s1        the name of the image to darken
   * @param intensity the intensity to darken the image by
   * @param s2        the name of the resulting image to save to the model
   * @return a new Optional of a new Darken Command containing a valid command.
   */
  @Override
  protected Optional<Command> makeCommand(Optional<String> s1,
                                          Optional<Integer> intensity,
                                          Optional<String> s2) {
    return Optional.of(new DarkenCommand(s1.get(), intensity.get(), s2.get()));
  }


}
