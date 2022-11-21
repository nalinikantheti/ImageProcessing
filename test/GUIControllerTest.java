import org.junit.Before;
import org.junit.ComparisonFailure;
import org.junit.Test;

import java.util.Optional;

import controller.GUIController;
import mock.MockFactory;
import mock.MockGuiView;
import mock.MockModel;
import model.ImageProcessorModel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

public class GUIControllerTest {
  GUIController controller;
  MockModel mockModel;
  MockGuiView mockgui;
  StringBuilder log;
  MockFactory mockBrighten;
  MockFactory mockSepia;
  MockFactory mockBlur;
  String brighten;
  String sepia;
  String blur;

  @Before
  public void setup() {
    log = new StringBuilder();
    mockModel = new MockModel(log);
    mockgui = new MockGuiView(log);
    controller = new GUIController(mockModel, mockgui);
    mockBrighten = new MockFactory(log, "Brighten");
    mockSepia = new MockFactory(log, "Sepia");
    mockBlur = new MockFactory(log, "Blur");
    brighten = "Brighten";
    sepia = "Sepia";
    blur = "Blur";
  }

  private void assertLog(String message){
    try{
      assertTrue(log.toString().contains(message));
    } catch(AssertionError e) {
      throw new ComparisonFailure("AssertLog Failed", message, log.toString());
    }
  }

  @Test
  public void testController(){
    controller.registerFactory("Brighten", mockBrighten);
    controller.registerFactory("Sepia", mockSepia);
    controller.registerFactory("Blur", mockBlur);

    controller.actionPerformed("Brighten");
    assertLog("Brightenmade some changes to an image.");
    controller.actionPerformed("Sepia");
    assertLog("Sepiamade some changes to an image.");
    controller.actionPerformed("Blur");
    assertLog("Blurmade some changes to an image.");
    controller.actionPerformed("null");
    assertLog("Made error popup: Command: null not found.");
    controller.registerFactory("null", new MockFactory(log, "null"));
    controller.actionPerformed("null");
    assertLog("nullmade some changes to an image.");

    controller.registerFactory("failed-factory", () -> Optional.empty());
    controller.actionPerformed("failed-factory");
    assertLog("Made error popup: Command failed to run.");

    controller.registerFactory("failed-command",
            () -> Optional.of(
                    (ImageProcessorModel model) -> {
                      throw new IllegalArgumentException("Broken command");
                    }
    ));

    controller.actionPerformed("failed-command");
    assertLog("Made error popup: Broken command");
  }

  @Test
  public void testControllerThrows(){
    assertThrows(IllegalArgumentException.class, () -> controller.registerFactory("null", null));
    assertThrows(IllegalArgumentException.class, () -> new GUIController(null, mockgui));
    assertThrows(IllegalArgumentException.class, () -> new GUIController(mockModel, null));
    assertThrows(IllegalArgumentException.class, () -> new GUIController(null, null));
    controller.registerFactory("Brighten", mockBrighten);

  }



}
