package view;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.*;

import controller.factory.terminal.CommandFactory;
import image.BufferWrapper;
import image.Image;
import image.Pixel;
import image.RGBImage;
import model.ImageProcessorModelState;

import static util.ImageProcessorUtils.imageToBuffer;

public class ImageProcessorGUIBasic extends JFrame implements ImageProcessorGUI{
  JLabel image;
  private ImageProcessorModelState model;

  public ImageProcessorGUIBasic(ImageProcessorModelState model) throws HeadlessException {
    this.model = model;
    this.image = new JLabel(new ImageIcon(getDefaultImage()));
    this.setLayout(new BorderLayout());


    this.addCommands();
    this.addFileButtons();
    this.add(image, BorderLayout.CENTER);
    this.pack();
    this.setVisible(true);
  }

  private void addFileButtons(){
    JButton open = new JButton("open");
    JButton save = new JButton("save");
    JButton saveAs = new JButton("save as");

    JPanel fileButtons = new JPanel();
    fileButtons.setLayout(new GridLayout(1, 3));
    fileButtons.add(open);
    fileButtons.add(save);
    fileButtons.add(saveAs);
    this.add(fileButtons, BorderLayout.NORTH);
  }

  private void addCommands() {
    JButton blur = new JButton("blur");
    JButton brighten = new JButton("brighten");
    JButton darken = new JButton("darken");
    JButton fliph = new JButton("flip: horizontal");
    JButton flipv = new JButton("flip: vertical");
    JButton greyr = new JButton("greyscale: Red");
    JButton greyb = new JButton("greyscale: Blue");
    JButton greyg = new JButton("greyscale: Green");
    JButton greyi = new JButton("greyscale: Intensity");
    JButton greyl = new JButton("greyscale: Luma");
    JButton greyv = new JButton("greyscale: Value");
    JButton sepia = new JButton("sepia");
    JButton sharp = new JButton("sharpen");
    JPanel commands = new JPanel();
    commands.setLayout(new GridLayout(20,1));
    commands.add(brighten);
    commands.add(blur);
    commands.add(darken);
    commands.add(fliph);
    commands.add(flipv);
    commands.add(sepia);
    commands.add(sharp);
    commands.add(greyr);
    commands.add(greyb);
    commands.add(greyg);
    commands.add(greyi);
    commands.add(greyl);
    commands.add(greyv);
    this.add(commands, BorderLayout.WEST);
  }

  private BufferedImage getDefaultImage() {
    Pixel grey = new Pixel(127,127,127);
    ArrayList<ArrayList<Pixel>> pix = new ArrayList<ArrayList<Pixel>>();
    for(int i = 0; i < 300; i ++){
      pix.add(new ArrayList<Pixel>());
      for(int j = 0; j< 300 ; j++){
        pix.get(i).add(grey);
      }
    }
    Image image = new RGBImage(pix);
    return imageToBuffer(image);
  }

  @Override
  public void display(String imageName) {
    Image img = model.getImage(imageName);
    image.setIcon(new ImageIcon(imageToBuffer(img)));
  }

  @Override
  public void registerCommandFactory(CommandFactory cmdf) {

  }

  @Override
  public void makeErrorPopUp(String message) {

  }

}
