package image;

import java.util.ArrayList;

import static util.ImageProcessorUtils.ensureNotNull;

public class RGBImage implements Image {
    private ArrayList<ArrayList<Pixel>> pixels;


    public RGBImage(ArrayList<ArrayList<Pixel>> image){
        ensureNotNull(image, "Image Array cannot be null.");
        this.pixels = new ArrayList<>();

        int colHeight;
        if(image.size() > 0) {
            colHeight = image.get(0).size();
        } else {
            colHeight = 0;
        }
        for(ArrayList<Pixel> column : image) {
            if(column.size() != colHeight){
                throw new IllegalArgumentException("Given pixel array is not well formed.");
            }
            pixels.add(new ArrayList<>(column));
        }
    }
    public RGBImage(int width, int height) {
        pixels = new ArrayList<>();
        for(int x = 0; x < width; x += 1) {
            ArrayList<Pixel> column = new ArrayList<>();
            pixels.add(column);
            for(int y = 0; y < height; y += 1) {
                column.add(new Pixel(0,0,0));
            }
        }

    }

    @Override
    public void setPixel(Pixel pix, int x, int y) {
        ensureNotNull(pix,"Pixel cannot be null.");
        ensureInBounds(x,y);
        pixels.get(x).set(y, pix);
    }

    @Override
    public Pixel getPixel(int x, int y) {
        ensureInBounds(x,y);
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

    private void ensureInBounds(int x, int y) {
        if(x < 0 || x >= this.getWidth() || y < 0 || y >= this.getHeight()) {
            throw new IllegalArgumentException("Coordinates out of bounds.");
        }
    }

}
