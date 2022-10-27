package Image;

import java.util.ArrayList;

public class RGBImage implements Image {
    ArrayList<ArrayList<Pixel>> pixels;


    RGBImage(ArrayList<ArrayList<Pixel>> image){
        this.pixels = image;
    }

    @Override
    public void setPixel(Pixel pix, int x, int y) {
        pixels.get(x).set(y, pix);
    }

    @Override
    public Pixel getPixel(int x, int y) {
        return pixels.get(x).get(y);
    }

    @Override
    public int getHeight() {
        return pixels.get(0).size();
    }

    @Override
    public int getWidth() {
        return pixels.size();
    }

    @Override
    public Image clone() {
        return new RGBImage(this.pixels);
    }
}
