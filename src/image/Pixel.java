package image;

import java.util.Objects;

public class Pixel {
    private int red;
    private int green;

    private int blue;

    public Pixel(int red, int green, int blue){
        this.red = requireValid(red);
        this.green = requireValid(green);
        this.blue = requireValid(blue);
    }

    private int requireValid(int value) {
        if(value < 0 || value > 255) {
            throw new IllegalArgumentException("Value: " + value + " must be in range [0, 255]");
        }
        return value;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pixel)) return false;
        Pixel pixel = (Pixel) o;
        return red == pixel.red && green == pixel.green && blue == pixel.blue;
    }

    @Override
    public int hashCode() {
        return Objects.hash(red, green, blue);
    }

    public String toString(){
        return "red:" + this.red + " green:" + this.green + " blue:" + this.blue;
    }
}
