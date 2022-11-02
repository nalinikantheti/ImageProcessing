package controller;

import command.*;
import controller.factory.terminal.*;
import model.ImageProcessorModel;
import util.ImageProcessorUtils;
import view.ImageProcessorView;

import java.io.IOException;
import java.util.HashMap;
import java.util.Optional;
import java.util.Scanner;
import java.util.function.Function;

public class ImageProcessorTerminalController implements ImageProcessorController {
    private HashMap<String, CommandFactory> commandFactories;

    private ImageProcessorModel model;
    private ImageProcessorView view;
    private Scanner scan;

    public ImageProcessorTerminalController(ImageProcessorModel model, ImageProcessorView view, Scanner scan) {
        ImageProcessorUtils.ensureNotNull(model, "model cannot be null.");
        ImageProcessorUtils.ensureNotNull(view, "view cannot be null.");
        ImageProcessorUtils.ensureNotNull(scan, "scanner cannot be null.");
        this.model = model;
        this.view = view;
        this.scan = scan;
        commandFactories = new HashMap<>();
    }

    @Override
    public void runProgram(){
        while(scan.hasNext()){
            String input = scan.next();
            if(input.equalsIgnoreCase("q") || input.equalsIgnoreCase("quit")){
                transmit("Quitting program... ");
                return;
            }
            CommandFactory cmdfac = commandFactories.getOrDefault(input, null);
            if (cmdfac == null){
                transmit("unknown command");
            } else {

                Optional<Command> c = cmdfac.make();
                if(c.isPresent()) {
                    c.get().go(model);
                }
            }
        }
    }

    public void registerCommand(String name, CommandFactory cmd){
        ImageProcessorUtils.ensureNotNull(name, "name cannot be null.");
        ImageProcessorUtils.ensureNotNull(cmd, "Command Factory cannot be null.");
        if(name.equalsIgnoreCase("q") || name.equalsIgnoreCase("quit")){
            throw new IllegalArgumentException("Command name cannot be 'q' or 'quit'.");
        }
        this.commandFactories.put(name, cmd);
    }

    public void transmit(String message) throws IllegalStateException{
        try{
            this.view.renderMessage(message);
        } catch(IOException e){
            throw new IllegalStateException("unable to transmit to view.");
        }
    }


}
