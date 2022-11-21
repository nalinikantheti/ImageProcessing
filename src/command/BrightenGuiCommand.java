package command;

import java.awt.Rectangle;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JSlider;
import javax.swing.JButton;
import javax.swing.AbstractAction;
import model.ImageProcessorModel;
import view.ImageProcessorGUI;

/**
 * A command that creates a {@link BrightenCommand} using inputs from a gui.
 */
public class BrightenGuiCommand implements Command {
  private ImageProcessorGUI gui;

  /**
   * A constructor for a {@link BrightenGuiCommand}.
   * @param gui the GUI this command will take input from.
   */
  public BrightenGuiCommand(ImageProcessorGUI gui) {
    this.gui = gui;
  }

  /**
   * Runs this command by creating a slider popup that retrieves a value from the user,
   *    * and then using that value to create a {@link BrightenCommand}.
   * @param model model used to retrieve image for command to then process.
   */
  @Override
  public void run(ImageProcessorModel model) {
    JFrame popup = new JFrame();
    JSlider slider = new JSlider();
    slider.setMaximum(255);
    slider.setMinimum(0);
    popup.setLayout(new FlowLayout());
    JButton brighten = new JButton("Brighten by " + slider.getValue());
    slider.addChangeListener(e -> brighten.setText("Brighten by " + slider.getValue()));
    popup.add(slider);
    popup.add(brighten);
    Rectangle bounds = new Rectangle(400, 100);
    popup.setBounds(bounds);
    popup.setVisible(true);

    brighten.addActionListener(new AbstractAction() {
      @Override
      public void actionPerformed(ActionEvent e) {
        new BrightenCommand("working", slider.getValue(), "working").run(model);
        gui.display("working");
      }
    });
  }
}
