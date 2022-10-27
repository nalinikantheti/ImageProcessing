package controller;

import command.*;
import model.ImageProcessorModel;
import view.ImageProcessorView;

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
        this.addCommands();
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

    private void addCommands(){
        commands = new HashMap<>();
        commands.put("brighten", s -> new BrightenCommand(s.next(), s.nextInt(), s.next()));
        commands.put("darken", s -> new DarkenCommand(s.next(), s.nextInt(), s.next()));
        commands.put("greyscaleRed", s -> new GreyScaleRedCommand(s.next(), s.next()));
        commands.put("greyscaleGreen", s -> new GreyScaleGreenCommand(s.next(), s.next()));
        commands.put("greyscaleBlue", s -> new GreyScaleBlueCommand(s.next(), s.next()));
        commands.put("value", s -> new ValueCommand(s.next(), s.next()));
        commands.put("intensity", s -> new IntensityCommand(s.next(), s.next()));
        commands.put("luma", s -> new LumaCommand(s.next(), s.next()));
        commands.put("flipHorizontal", s -> new FlipHorizontalCommand(s.next(), s.next()));
        commands.put("flipVertical", s -> new FlipVerticalCommand(s.next(), s.next()));
        commands.put("load", s -> new LoadCommand(s.next(), s.next()));
        commands.put("save", s -> new SaveCommand(s.next(), s.next()));

    }
}
