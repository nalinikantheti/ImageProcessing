import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;
import java.util.Optional;
import java.util.Scanner;

import controller.ImageProcessorTerminalController;
import mock.MockFactory;
import mock.MockModel;
import mock.MockView;
import view.ImageProcessorView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

/**
 * Tests all the functionality of {@link ImageProcessorTerminalController}.
 */
public class ControllerTests {

  StringBuilder expectedModelOutput;
  StringBuilder expectedViewOutput;
  StringBuilder actualModelOutput;
  StringBuilder actualViewOutput;
  StringBuilder inputBuilder;
  ImageProcessorTerminalController controller;
  MockFactory mockFactory;
  MockView view;
  MockModel model;
  Scanner scan;

  @Before
  public void setup() {

    expectedModelOutput = new StringBuilder();
    expectedViewOutput = new StringBuilder();
    actualModelOutput = new StringBuilder();
    actualViewOutput = new StringBuilder();
    inputBuilder = new StringBuilder();

  }

  private void makeMVC() {
    model = new MockModel(actualModelOutput);
    view = new MockView(actualViewOutput);
    scan = new Scanner(new StringReader(inputBuilder.toString()));
    controller = new ImageProcessorTerminalController(model, view, scan);
    mockFactory = new MockFactory(actualModelOutput);
  }

  protected void runTest() {
    controller.runProgram();
    assertEquals(expectedModelOutput.toString(), actualModelOutput.toString());
    assertEquals(expectedViewOutput.toString(), actualViewOutput.toString());

  }

  @Test
  public void testViewTransmitFail() {
    ImageProcessorTerminalController c = new ImageProcessorTerminalController(
            new MockModel(new StringBuilder()), new ImageProcessorView() {

              @Override
              public void renderMessage(String message) throws IOException {
                throw new IOException();
              }
            }, new Scanner(new StringReader("hello")));

    assertThrows(IllegalStateException.class, () -> c.runProgram());
  }


  @Test
  public void testRegisterCommandThrows() {

    makeMVC();
    assertThrows(IllegalArgumentException.class, () -> controller.registerCommand(null,
            mockFactory));
    assertThrows(IllegalArgumentException.class, () -> controller.registerCommand("br",
            null));
    assertThrows(IllegalArgumentException.class, () -> controller.registerCommand("q",
            mockFactory));
    assertThrows(IllegalArgumentException.class, () -> controller.registerCommand("Q",
            mockFactory));
    assertThrows(IllegalArgumentException.class, () -> controller.registerCommand("quit",
            mockFactory));
    assertThrows(IllegalArgumentException.class, () -> controller.registerCommand("qUIt",
            mockFactory));

  }

  @Test
  public void testConstructorThrows() {
    assertThrows(IllegalArgumentException.class, () -> new ImageProcessorTerminalController(null,
            null, null));
    assertThrows(IllegalArgumentException.class, () -> new ImageProcessorTerminalController(null,
            view, scan));
    assertThrows(IllegalArgumentException.class, () -> new ImageProcessorTerminalController(model,
            null, scan));
    assertThrows(IllegalArgumentException.class, () -> new ImageProcessorTerminalController(model,
            view, null));
  }

  @Test
  public void testQuitRunProgram() {
    assertEquals(1, Integer.parseInt("1"));

    input("q");
    viewOutput("Quitting program... \n");
    makeMVC();
    runTest();

    setup();
    input("quIt");
    viewOutput("Quitting program... \n");
    makeMVC();
    runTest();
  }

  @Test
  public void testRunProgram() {
    assertEquals(1, Integer.parseInt("1"));

    input("mock");
    viewOutput("unknown command\n");
    makeMVC();
    runTest();

    setup();
    input("mock");
    viewOutput("Successfully ran command!\n");
    modelOutput("made some changes to an image.");

    makeMVC();
    controller.registerCommand("mock", mockFactory);
    runTest();

    setup();
    input("mock q");
    viewOutput("Quitting program... \n");

    makeMVC();
    controller.registerCommand("mock", () -> Optional.empty());
    runTest();
  }

  private void modelOutput(String message) {
    expectedModelOutput.append(message);
  }

  private void viewOutput(String message) {
    expectedViewOutput.append(message);
  }

  private void input(String message) {
    inputBuilder.append(message + "\n");
  }

}
