package image;

/**
 * A rectangular Image represented by an ArrayList<ArrayList<Pixel>>.
 */
public interface Image {

    void setPixel(Pixel pix, int x, int y);

    Pixel getPixel(int x, int y);

    int getHeight();

    int getWidth();

    Image clone();
}
