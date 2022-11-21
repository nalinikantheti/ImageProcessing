package view;

import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.ImageIcon;
import java.awt.BorderLayout;
import java.awt.Rectangle;
import java.awt.GridLayout;
import java.awt.Dimension;

import controller.Listener;
import image.Image;
import image.Pixel;
import image.RGBImage;
import model.ImageProcessorModelState;

import static util.ImageProcessorUtils.imageToBuffer;

/**
 * Represents a basic Graphical User Interface for this Image Processor.
 */
public class ImageProcessorGUIBasic extends JFrame implements ImageProcessorGUI {
  private Listener listener;
  private ActionListener redirect;

  private JLabel image;
  private Histogram histogram;
  private ImageProcessorModelState model;


  /**
   * A constructor for a {@link ImageProcessorGUIBasic}. This gui uses
   * a border layout to display all of its components.
   *
   * @param model the model that this gui will use to manipulate images.
   */
  public ImageProcessorGUIBasic(ImageProcessorModelState model) {
    JScrollPane imageBorder;
    this.model = model;
    this.image = new JLabel(new ImageIcon(getDefaultImage()));
    imageBorder = new JScrollPane(image);
    this.redirect = e -> listener.actionPerformed(e.getActionCommand());
    this.histogram = new Histogram();
    this.setLayout(new BorderLayout());


    this.addCommands();
    this.addFileButtons();
    this.add(imageBorder, BorderLayout.CENTER);
    this.addHistogram();

    this.setPreferredSize(new Dimension(1280, 720));
    this.pack();
    this.setVisible(true);
  }

  private void addHistogram() {
    JPanel hist = new JPanel();
    Dimension size = new Dimension(500, 700);
    hist.setPreferredSize(size);
    JPanel histChecks = new JPanel();
    hist.setLayout(new GridLayout(2, 1));
    histChecks.setLayout(new GridLayout(4, 1));
    hist.add(this.histogram);

    JCheckBox red = new JCheckBox("Red");
    red.setSelected(true);
    red.addChangeListener(e -> histogram.toggleRed());
    histChecks.add(red);

    JCheckBox green = new JCheckBox("Green");
    green.setSelected(true);
    green.addChangeListener(e -> histogram.toggleGreen());
    histChecks.add(green);

    JCheckBox blue = new JCheckBox("Blue");
    blue.setSelected(true);
    blue.addChangeListener(e -> histogram.toggleBlue());
    histChecks.add(blue);

    JCheckBox intensity = new JCheckBox("Intensity");
    intensity.setSelected(true);
    intensity.addChangeListener(e -> histogram.toggleIntensity());
    histChecks.add(intensity);
    hist.add(histChecks);
    this.add(hist, BorderLayout.EAST);
  }

  private void addFileButtons() {
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
    commands.setLayout(new GridLayout(20, 1));
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
    Pixel grey = new Pixel(127, 127, 127);
    ArrayList<ArrayList<Pixel>> pix = new ArrayList<ArrayList<Pixel>>();
    for (int i = 0; i < 300; i++) {
      pix.add(new ArrayList<Pixel>());
      for (int j = 0; j < 300; j++) {
        pix.get(i).add(grey);
      }
    }
    Image image = new RGBImage(pix);
    return imageToBuffer(image);
  }

  /**
   * Updates the current image by using a model to retrieve
   * the image from the given name, making a buffered image
   * from it, and then setting the icon of a Jlabel to that
   * buffered image.
   *
   * @param imageName the name of the new image to be displayed.
   */
  @Override
  public void display(String imageName) {
    Image img = model.getImage(imageName);
    image.setIcon(new ImageIcon(imageToBuffer(img)));
    histogram.update(img);
  }

  /**
   * Sets this listener to the given listener.
   *
   * @param listener the listener that will listen
   *                 to this gui.
   */
  @Override
  public void setListener(Listener listener) {
    this.listener = listener;
  }

  /**
   * Creates a simple popup window displaying a given error message.
   *
   * @param message the error message to be displayed.
   */
  @Override
  public void makeErrorPopUp(String message) {
    JFrame errorPane = new JFrame();
    JLabel error = new JLabel();
    error.setText(message);
    Rectangle bounds = new Rectangle(300, 200);
    errorPane.setBounds(bounds);
    errorPane.setTitle("ERROR:");
    errorPane.add(error);
    errorPane.setVisible(true);
  }
}
