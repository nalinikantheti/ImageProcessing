package command;

import image.Image;
import image.Pixel;
import model.ImageProcessorModel;

import java.util.function.Function;

import static util.ImageProcessorUtils.ensureNotNull;

public class BlurCommand extends FilterCommand {

    public BlurCommand(String imageName, String newName) {
        super(imageName, newName);

    }

    @Override
    protected double[][] makeFilter() {
        //THESE ARE COLUMNS!!!!!!
        double[][] blurFilter = new double[3][];
        blurFilter[0] = new double[3];
        blurFilter[0][0] = 1.0/16.0;
        blurFilter[0][1] = 1.0/8.0;
        blurFilter[0][2] = 1.0/16.0;

        blurFilter[1] = new double[3];
        blurFilter[1][0] = 1.0/8.0;
        blurFilter[1][1] = 1.0/4.0;
        blurFilter[1][2] = 1.0/8.0;

        blurFilter[2] = new double[3];
        blurFilter[2][0] = 1.0/16.0;
        blurFilter[2][1] = 1.0/8.0;
        blurFilter[2][2] = 1.0/16.0;

        return blurFilter;
    }

}
