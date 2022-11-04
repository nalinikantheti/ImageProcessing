package mock;

import command.Command;
import model.ImageProcessorModel;

/**
 * A Command used solely for implementing a MockFactory.
 */
public class MockCommand implements Command {
  StringBuilder log;

  /**
   * Constructor for a Mock Command with a log to append to.
   *
   * @param log a stringbuilder that logs when this command is used.
   */
  public MockCommand(StringBuilder log) {
    this.log = log;
  }

  /**
   * Runs this mockCommand on a Provided model, which doesn't have to be a mock model.
   *
   * @param model model used to retrieve image for command to then process.
   */
  @Override
  public void run(ImageProcessorModel model) {
    log.append("made some changes to an image.");
  }
}
