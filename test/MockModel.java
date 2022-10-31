import image.Image;
import image.RGBImage;
import model.ImageProcessorModel;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class MockModel implements ImageProcessorModel {
    private StringBuilder log;

    private Image lastSavedImage;
    private Image dummy;

    MockModel(StringBuilder ap){
      this.log = ap;
      lastSavedImage = new RGBImage(0,0);
      this.dummy = new RGBImage(0,0);
    }

    MockModel(StringBuilder ap, Image dummy){
        this.log = ap;
        lastSavedImage = new RGBImage(0,0);
        this.dummy = dummy;
    }
    @Override
    public void saveImageToFileSystem(String name, String filepath) throws IOException {
        log.append("saved " + name + " to filepath: " + filepath+"\n");
    }

    @Override
    public void saveImageToModel(Image image, String name) {
        log.append("saved " + name + " to model"+"\n");
        this.lastSavedImage = image;
    }

    @Override
    public void loadImage(String filepath, String name) throws FileNotFoundException {
        log.append("loaded: " + name + " from filepath: " + filepath+"\n");
    }

    @Override
    public void removeImage(String name) {
        log.append("removed: " + name+"\n");
    }

    @Override
    public Image getImage(String name) {
        log.append("retrieved: " + name +"\n");
        return this.dummy;
    }

    @Override
    public Set<String> getImageNames() {
        log.append("returned set of all image names\n");
        return new HashSet<String>();
    }

    public Image getLastSavedImage(){
        return this.lastSavedImage;
    }
}
