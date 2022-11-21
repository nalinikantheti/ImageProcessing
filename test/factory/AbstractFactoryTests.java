package factory;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;
import java.util.Optional;
import java.util.Scanner;

import command.Command;
import controller.factory.CommandFactory;
import view.ImageProcessorTextView;
import view.ImageProcessorView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

/**
 * An abstract class to test all factories.
 */
public abstract class AbstractFactoryTests {
  private StringBuilder expectedOutput;
  private StringBuilder actualOuptut;
  private StringBuilder inputBuilder;

  @Before
  public void setup() {
    expectedOutput = new StringBuilder();
    actualOuptut = new StringBuilder();
    inputBuilder = new StringBuilder();
  }

  @Test
  public void testSuccess() {
    input("original original-modified");
    assertTrue(runTest().isPresent());

    setup();
    input("clap your-hands");
    assertTrue(runTest().isPresent());
  }

  @Test
  public void testQuit() {
    input("quit");
    output("Quitting program...");

    assertFalse(runTest().isPresent());

    setup();
    input("qUiT broriginal");
    output("Quitting program...");

    assertFalse(runTest().isPresent());

    setup();
    input("original Q");
    output("Quitting program...");
    assertFalse(runTest().isPresent());
  }

  @Test
  public void testEndOfInput() {
    input("original");
    output("Reached end of input.");
    assertFalse(runTest().isPresent());

    setup();
    input("originaljfkdla");
    output("Reached end of input.");
    assertFalse(runTest().isPresent());
  }

  @Test
  public void testConstructorThrows() {
    assertThrows(IllegalArgumentException.class, () -> this.makeFactory(null,
            makeScanner()));
    assertThrows(IllegalArgumentException.class, () -> this.makeFactory(makeView(),
            null));
  }

  @Test
  public void testTransmitThrows() {
    Appendable badAppendable = new Appendable() {
      @Override
      public Appendable append(CharSequence csq) throws IOException {
        throw new IOException();
      }

      @Override
      public Appendable append(CharSequence csq, int start, int end) throws IOException {
        throw new IOException();
      }

      @Override
      public Appendable append(char c) throws IOException {
        throw new IOException();
      }
    };
    ImageProcessorView view = new ImageProcessorTextView(badAppendable);
    CommandFactory factory = this.makeFactory(view, makeScanner());


    input("original 50 boriginal");
    assertThrows(IllegalStateException.class, factory::make);

  }

  /**
   * Appends a line to the expected output.
   *
   * @param message the message to append
   */
  protected void output(String message) {
    expectedOutput.append(message + "\n");
  }

  /**
   * Appends a line to the given input.
   *
   * @param message the message to append
   */
  protected void input(String message) {
    inputBuilder.append(message + "\n");
  }

  /**
   * Gets a factory and makes a command, ensuring that the logs in the mock view are as expected.
   *
   * @return the Optional of the Command returned by the factory
   */
  protected Optional<Command> runTest() {
    CommandFactory factory = this.makeFactory(makeView(), makeScanner());
    Optional<Command> command = factory.make();
    assertEquals(expectedOutput.toString(), actualOuptut.toString());
    return command;
  }

  private Scanner makeScanner() {
    return new Scanner(new StringReader(inputBuilder.toString()));
  }

  private ImageProcessorView makeView() {
    return new ImageProcessorTextView(actualOuptut);
  }

  /**
   * Gets a factory to test.
   *
   * @param view    the view to pass to the factory
   * @param scanner the scanner to pass to the factory
   * @return a {@link CommandFactory}
   */

  protected abstract CommandFactory makeFactory(ImageProcessorView view, Scanner scanner);
}
