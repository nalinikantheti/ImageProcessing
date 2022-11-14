import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

import controller.ImageProcessorTerminalController;
import controller.factory.terminal.BlurFactory;
import controller.factory.terminal.BrightenFactory;
import controller.factory.terminal.DarkenFactory;
import controller.factory.terminal.FlipHorizontalFactory;
import controller.factory.terminal.FlipVerticalFactory;
import controller.factory.terminal.GreyScaleBlueFactory;
import controller.factory.terminal.GreyScaleGreenFactory;
import controller.factory.terminal.GreyScaleRedFactory;
import controller.factory.terminal.IntensityFactory;
import controller.factory.terminal.LoadFactory;
import controller.factory.terminal.LumaFactory;
import controller.factory.terminal.SaveFactory;
import controller.factory.terminal.SepiaFactory;
import controller.factory.terminal.SharpenFactory;
import controller.factory.terminal.ValueFactory;
import model.ImageProcessorModelImpl;
import view.ImageProcessorGUIBasic;
import view.ImageProcessorTextView;
import view.ImageProcessorView;

/**
 * Main class for running an Image Processor through a terminal.
 */
public class Main {

  /**
   * Runs the image processor program using terminal user input.
   *
   * @param args user input
   */
  public static void main(String[] args) {
    boolean isTerm = false;


    if(isTerm){
      startTerminal(args);
    } else {
      startGUI();
    }
  }

  private static void startGUI() {

    ImageProcessorGUIBasic gui = new ImageProcessorGUIBasic(new ImageProcessorModelImpl());


  }

  private static void startTerminal(String[] args){
    Scanner scan = new Scanner(System.in);
    if (args.length >= 2) {
      try {
        scan = new Scanner(new StringReader(Files.readString(Paths.get(args[1]))));
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }
    ImageProcessorView view = new ImageProcessorTextView(System.out);

    ImageProcessorTerminalController controller = new ImageProcessorTerminalController(
            new ImageProcessorModelImpl(),
            view,
            scan);

    addCommands(controller, scan, view);

    controller.runProgram();
  }

  private static void addCommands(ImageProcessorTerminalController controller,
                                  Scanner scan, ImageProcessorView view) {
    controller.registerCommand("brighten", new BrightenFactory(view, scan));
    controller.registerCommand("darken", new DarkenFactory(view, scan));
    controller.registerCommand("greyscaleRed", new GreyScaleRedFactory(view, scan));
    controller.registerCommand("greyscaleGreen", new GreyScaleGreenFactory(view, scan));
    controller.registerCommand("greyscaleBlue", new GreyScaleBlueFactory(view, scan));
    controller.registerCommand("value", new ValueFactory(view, scan));
    controller.registerCommand("intensity", new IntensityFactory(view, scan));
    controller.registerCommand("luma", new LumaFactory(view, scan));
    controller.registerCommand("flipHorizontal", new FlipHorizontalFactory(view, scan));
    controller.registerCommand("flipVertical", new FlipVerticalFactory(view, scan));
    controller.registerCommand("load", new LoadFactory(view, scan));
    controller.registerCommand("save", new SaveFactory(view, scan));
    controller.registerCommand("blur", new BlurFactory(view, scan));
    controller.registerCommand("sharpen", new SharpenFactory(view, scan));
    controller.registerCommand("sepia", new SepiaFactory(view, scan));

  }
}