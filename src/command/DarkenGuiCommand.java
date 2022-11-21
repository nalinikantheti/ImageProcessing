package command;

import java.awt.*;
import java.awt.event.ActionEvent;

import javax.swing.*;

import model.ImageProcessorModel;
import view.ImageProcessorGUI;

public class DarkenGuiCommand implements Command {
  ImageProcessorGUI gui;

  public DarkenGuiCommand(ImageProcessorGUI gui) {
    this.gui = gui;
  }

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
