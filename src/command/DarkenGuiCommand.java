package command;

import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JSlider;
import javax.swing.JButton;
import javax.swing.AbstractAction;
import java.awt.FlowLayout;
import java.awt.Rectangle;
import model.ImageProcessorModel;
import view.ImageProcessorGUI;

/**
 * A command that creates a {@link DarkenCommand} using inputs from a gui.
 */

public class DarkenGuiCommand implements Command {
  private ImageProcessorGUI gui;

  /**
   * A constructor for a {@link DarkenGuiCommand}.
   * @param gui the gui this command will retrieve input from.
   */
  public DarkenGuiCommand(ImageProcessorGUI gui) {
    this.gui = gui;
  }

  /**
   * Runs this command by creating a popup that retrieves a value from the user,
   * and then using that value to create a {@link DarkenCommand}.
   * @param model model used to retrieve image for command to then process.
   */
  @Override
  public void run(ImageProcessorModel model) {
    JFrame popup = new JFrame();
    JSlider slider = new JSlider();
    slider.setMaximum(255);
    slider.setMinimum(0);
    popup.setLayout(new FlowLayout());
    JButton darken = new JButton("Darken by " + slider.getValue());
    slider.addChangeListener(e -> darken.setText("Darken by " + slider.getValue()));
    popup.add(slider);
    popup.add(darken);
    Rectangle bounds = new Rectangle(400, 100);
    popup.setBounds(bounds);
    popup.setVisible(true);

    darken.addActionListener(new AbstractAction() {
      @Override
      public void actionPerformed(ActionEvent e) {
        new DarkenCommand("working", slider.getValue(), "working").run(model);
        gui.display("working");
      }
    });
  }
}
