package Image;

public interface Image {

    public void setPixel(Pixel pix, int x , int y);

    public Pixel getPixel(int x, int y);

    public int getHeight();

    public int getWidth();

    Image clone();
}
