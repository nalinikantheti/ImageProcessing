package command;

import java.awt.*;
import java.awt.event.ActionEvent;

import javax.swing.*;

import model.ImageProcessorModel;
import view.ImageProcessorGUI;

public class BrightenGuiCommand implements Command {
  ImageProcessorGUI gui;

  public BrightenGuiCommand(ImageProcessorGUI gui) {
    this.gui = gui;
  }

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
