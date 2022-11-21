package mock;

import command.Command;
import model.ImageProcessorModel;

/**
 * A Command used solely for implementing a MockFactory.
 */
public class MockCommand implements Command {

  private String name;
  private StringBuilder log;

  /**
   * Constructor for a Mock Command with a log to append to.
   *
   * @param log a stringbuilder that logs when this command is used.
   */
  public MockCommand(StringBuilder log) {
    this.log = log;
    this.name = "";
  }

  /**
   * Constructor for a Mock Command with a log to append to and command name.
   *
   * @param log a stringbuilder that logs when this command is used.
   * @param name a String that logs when this command is used.
   */
  public MockCommand(StringBuilder log, String name) {
    this.log = log;
    this.name = name;
  }

  /**
   * Runs this mockCommand on a Provided model, which doesn't have to be a mock model.
   *
   * @param model model used to retrieve image for command to then process.
   */
  @Override
  public void run(ImageProcessorModel model) {
    log.append(this.name + "made some changes to an image.");

  }
}
