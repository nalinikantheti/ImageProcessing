package controller;

import model.ImageProcessorModel;
import view.ImageProcessorView;
import command.Command;

import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.function.Function;

public class ImageProcessorControllerImpl implements ImageProcessorController {
    HashMap<String, Function<Scanner, Command>> commands;

    ImageProcessorModel model;
    ImageProcessorView view;
    Scanner scan;

    public ImageProcessorControllerImpl(ImageProcessorModel model, ImageProcessorView view, Scanner scan) {
        this.model = model;
        this.view = view;
        this.scan = scan;
    }

    @Override
    public void runProgram() throws IOException {
        while(scan.hasNext()){
            Command c;
            String input = scan.next();
            if(input.equalsIgnoreCase("q") || input.equalsIgnoreCase("quit")){
                return;
            }
            Function<Scanner, Command> cmd = commands.getOrDefault(input, null);
            if (cmd == null){
                view.renderMessage("unknown command");
            } else {
                c = cmd.apply(scan);
                c.go(model);
            }
        }
    }

    private void addCommand(){
        commands.put("brighten", s -> new BrightenCommand(s.next()));
        commands.put("darken", s -> new DarkenCommand(s.next()));
        commands.put("greyscaleRed", s -> new GreyScaleRedCommand(s.next()));
        commands.put("greyscaleGreen", s -> new GreyScaleGreenCommand(s.next()));
        commands.put("greyscaleBlue", s -> new GreyScaleBlueCommand(s.next()));
        commands.put("value", s -> new ValueCommand(s.next()));
        commands.put("intensity", s -> new IntensityCommand(s.next()));
        commands.put("luma", s -> new LumaCommand(s.next()));
        commands.put("flipHorizontal", s -> new FlipHorizontalCommand(s.next()));
        commands.put("flipVertical", s -> new FlipVerticalCommand(s.next()));
        commands.put("loadImage", s -> new LoadCommand(s.next()));
        commands.put("saveImage", s -> new SaveCommand(s.next()));

    }
}
