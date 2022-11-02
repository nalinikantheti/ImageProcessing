import controller.ImageProcessorControllerImpl;
import controller.factory.terminal.BrightenFactory;
import model.ImageProcessorModelImpl;
import view.ImageProcessorTextView;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {


        new ImageProcessorControllerImpl(
                new ImageProcessorModelImpl(),
                new ImageProcessorTextView(System.out),
        new Scanner(System.in)).runProgram();
    }
}