package Image;

public class Pixel {
    private int red;
    private int green;

    private int blue;

    Pixel(int red, int green, int blue){
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public int getBlue() {
        return blue;
    }

    public int getRed() {
        return red;
    }

    public int getGreen() {
        return green;
    }

}
