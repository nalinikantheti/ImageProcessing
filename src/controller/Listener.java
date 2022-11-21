package controller;

/**
 * An implementation of a listener whose actionPerformed
 * method directly takes in a String instead of an actionEvent.
 */
public interface Listener {
  /**
   * Performs the action correlating to the given actionCommand.
   *
   * @param actionCommand an actionCommand to be performed.
   */
  void actionPerformed(String actionCommand);
}
