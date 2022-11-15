package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.*;

import controller.GUIController;
import image.Image;
import image.Pixel;
import image.RGBImage;
import model.ImageProcessorModelState;

import static util.ImageProcessorUtils.imageToBuffer;

public class ImageProcessorGUIBasic extends JFrame implements ImageProcessorGUI{
  ActionListener listener;
  ActionListener redirect;
  JScrollPane imageBorder;
  JLabel image;
  private ImageProcessorModelState model;


  public ImageProcessorGUIBasic(ImageProcessorModelState model) throws HeadlessException {
    this.model = model;
    this.image = new JLabel(new ImageIcon(getDefaultImage()));
    this.imageBorder = new JScrollPane(image);
    this.redirect = e -> listener.actionPerformed(e);
    this.setLayout(new BorderLayout());




    this.addCommands();
    this.addFileButtons();
    this.add(imageBorder, BorderLayout.CENTER);
    this.pack();
    this.setVisible(true);
  }

  private void addFileButtons(){
    JButton open = new JButton("Open");
    open.addActionListener(redirect);
    open.setActionCommand("open");
    JButton saveAs = new JButton("Save As");
    saveAs.addActionListener(redirect);
    saveAs.setActionCommand("saveAs");

    JPanel fileButtons = new JPanel();
    fileButtons.setLayout(new GridLayout(1, 3));
    fileButtons.add(open);
    fileButtons.add(saveAs);
    this.add(fileButtons, BorderLayout.NORTH);
  }

  private void addCommands() {
    JButton blur = new JButton("Blur");
    blur.addActionListener(redirect);
    blur.setActionCommand("blur");
    JButton brighten = new JButton("Brighten");
    brighten.addActionListener(redirect);
    brighten.setActionCommand("brighten");
    JButton darken = new JButton("Darken");
    darken.addActionListener(redirect);
    darken.setActionCommand("darken");
    JButton fliph = new JButton("Flip: Horizontal");
    fliph.addActionListener(redirect);
    fliph.setActionCommand("fliph");
    JButton flipv = new JButton("Flip: Vertical");
    flipv.addActionListener(redirect);
    flipv.setActionCommand("flipv");
    JButton greyr = new JButton("Greyscale: Red");
    greyr.addActionListener(redirect);
    greyr.setActionCommand("greyr");
    JButton greyb = new JButton("Greyscale: Blue");
    greyb.addActionListener(redirect);
    greyb.setActionCommand("greyb");
    JButton greyg = new JButton("Greyscale: Green");
    greyg.addActionListener(redirect);
    greyg.setActionCommand("greyg");
    JButton greyi = new JButton("Greyscale: Intensity");
    greyi.addActionListener(redirect);
    greyi.setActionCommand("greyi");
    JButton greyl = new JButton("Greyscale: Luma");
    greyl.addActionListener(redirect);
    greyl.setActionCommand("greyl");
    JButton greyv = new JButton("Greyscale: Value");
    greyv.addActionListener(redirect);
    greyv.setActionCommand("greyv");
    JButton sepia = new JButton("Sepia");
    sepia.setActionCommand("sepia");
    sepia.addActionListener(redirect);
    JButton sharp = new JButton("Sharpen");
    sharp.addActionListener(redirect);
    sharp.setActionCommand("sharp");
    JPanel commands = new JPanel();
    commands.setLayout(new GridLayout(20,1));
    commands.add(brighten);
    commands.add(blur);
    commands.add(darken);
    commands.add(sepia);
    commands.add(sharp);
    commands.add(fliph);
    commands.add(flipv);
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
  public void setListener(ActionListener listener) {
    this.listener = listener;
  }

  @Override
  public void makeErrorPopUp(String message) {
    JFrame errorPane = new JFrame();
    JLabel error = new JLabel();
    error.setText(message);
    Rectangle bounds = new Rectangle(300,200);
    errorPane.setBounds(bounds);
    errorPane.setTitle("ERROR:");
    errorPane.add(error);
    errorPane.setVisible(true);
  }
}
