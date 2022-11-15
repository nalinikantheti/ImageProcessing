package controller.factory.gui;

import java.awt.*;
import java.util.Optional;

import javax.swing.*;

import command.BrightenCommand;
import command.Command;
import controller.factory.CommandFactory;

public class BrightenFactory implements CommandFactory {




  @Override
  public Optional<Command> make() {
    JFrame popup = new JFrame();
    JSlider slider = new JSlider();
    slider.setMaximum(255);
    slider.setMinimum(0);
    popup.setLayout(new FlowLayout());
    JButton brighten = new JButton("Brighten");
    slider.addChangeListener( e -> brighten.setText("Brighten by " + slider.getValue()));
    popup.add(slider);
    popup.add(brighten);
    Rectangle bounds = new Rectangle(200, 40);
    popup.setBounds(bounds);
    popup.setVisible(true);

    return Optional.of(new BrightenCommand("working", slider.getValue(), "working"));
  }
}
