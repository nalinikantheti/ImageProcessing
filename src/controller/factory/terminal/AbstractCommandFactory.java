package controller.factory.terminal;

import java.io.IOException;
import java.util.Optional;
import java.util.Scanner;

import controller.factory.CommandFactory;
import view.ImageProcessorView;

import static util.ImageProcessorUtils.ensureNotNull;

/**
 * A helper class containing methods that almost all {@code CommandFactory}s will use.
 * Provide helpers that wait for and convert text input,
 * as well as a constructor which ensures non-null values.
 */
public abstract class AbstractCommandFactory implements CommandFactory {
  private ImageProcessorView view;
  private Scanner s;

  /**
   * Creates a new AbstractCommandFactory with the given view and scanner.
   * Throws an {@code IllegalArgumentException}
   * if either parameter is null.
   *
   * @param view the view which this factory will transmit messages to
   * @param s    the scanner which this factory will read input from
   * @throws IllegalArgumentException if either parameter is null
   */
  public AbstractCommandFactory(ImageProcessorView view, Scanner s) {
    ensureNotNull(view, "View cannot be null.");
    ensureNotNull(s, "Scanner cannot be null.");
    this.view = view;
    this.s = s;
  }

  /**
   * If a {@code hadPreviousValue} is true, this method waits for input from the user.
   * If there is no remaining input, then a message is sent to the view that says the
   * program reached the end of input. If {@code hadPreviousValue} is false, then no
   * message is sent to the view. If the method begins waiting for user input and the
   * user enters a quit keyword ("q" or "quit", case insensitive) then a message is
   * sent to the view that the user has quit the program. In all these situations,
   * an empty {@code Optional<String>} is returned. Otherwise, an {@code Optional<String>}
   * is returned that contains the user's string input.
   *
   * @param hadPreviousValue whether previous {@code waitFor} methods
   *                         returns non-empty {@code Optional}s.
   * @return an {@code Optional<String>}. See the above description for more details.
   */
  protected Optional<String> waitForString(boolean hadPreviousValue) {
    if (s.hasNext() && hadPreviousValue) {
      String next = s.next();
      if (isQuit(next)) {
        return quitSequence();
      }
      return Optional.of(next);
    }
    return endOfInputSequence(hadPreviousValue);
  }

  /**
   * Waits for integer input very similarly to {@link #waitForString(boolean)},
   * except that if the user enters non integer input, this method asks the user
   * to re-enter a valid integer value. Otherwise, returns empty {@code Optional}s
   * in the same situations.
   *
   * @param hadPreviousValue whether previous {@code waitFor}
   *                         methods returns non-empty {@code Optional}s.
   * @param message          the error message to output if the user
   *                         enters invalid input, in order to notify the user what
   *                         argument was expected.
   * @return an {@code Optional<Integer>}.
   * See {@link #waitForString(boolean)} for more details.
   */
  protected Optional<Integer> waitForInteger(boolean hadPreviousValue, String message) {
    while (hadPreviousValue && s.hasNext()) {
      if (s.hasNextInt()) {
        return Optional.of(s.nextInt());
      } else {
        String next = s.next();
        if (isQuit(next)) {
          return quitSequence();
        }
        transmit(message + ", got: " + next);
        transmit("Re-enter value.");
      }
    }
    return endOfInputSequence(hadPreviousValue);
  }

  private void transmit(String message) {
    try {
      view.renderMessage(message + "\n");
    } catch (IOException e) {
      throw new IllegalStateException("Could not transmit to view.");
    }
  }

  private boolean isQuit(String message) {
    return message.equalsIgnoreCase("q")
            || message.equalsIgnoreCase("quit");
  }

  private <T> Optional<T> quitSequence() {
    transmit("Quitting program...");
    return Optional.empty();
  }

  private <T> Optional<T> endOfInputSequence(boolean hadPreviousValue) {
    if (hadPreviousValue) {
      transmit("Reached end of input.");
    }
    return Optional.empty();
  }

}
