package controller;

/**
 * A controller that handles interactions between the user and a given ImageProcessorModel.
 */
public interface ImageProcessorController {
  /**
   * Runs the program. Terminates if the user enters "q" or "quit" case-insensitive,
   * or if there is no more input to read.
   */
  void runProgram();
}
