package view;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

import controller.factory.terminal.CommandFactory;
import image.BufferWrapper;
import image.Pixel;
import image.RGBImage;

public class ImageProcessorGUIBasic extends JFrame implements ImageProcessorGUI{

  public ImageProcessorGUIBasic() throws HeadlessException {

    JPanel defaultImg = new JPanel(new ImageIcon(def));



    this.pack();
    this.setVisible(true);
  }

  @Override
  public void display(String imageName) {

  }

  @Override
  public void registerCommandFactory(CommandFactory cmdf) {

  }

  @Override
  public void makeErrorPopUp(String message) {

  }

}
