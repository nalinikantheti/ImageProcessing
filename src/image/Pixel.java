package image;

import java.util.Objects;

/**
 * A Pixel in an Image with three values representing red, green, and blue.
 */
public class Pixel {
    private int red;
    private int green;

    private int blue;

    /**
     * A constructor for a Pixel with three arguments
     * that makes sure each value is in range of [0, 255].
     *
     * @param red   the red value of this pixel.
     * @param green the green value of this pixel.
     * @param blue  the blue value of this pixel.
     */
    public Pixel(int red, int green, int blue) {
        this.red = requireValid(red);
        this.green = requireValid(green);
        this.blue = requireValid(blue);
    }

    private int requireValid(int value) {
        if (value < 0 || value > 255) {
            throw new IllegalArgumentException("Value: " + value + " must be in range [0, 255]");
        }
        return value;
    }

    /**
     * returns the blue value of this pixel.
     *
     * @return blue
     */
    public int getBlue() {
        return blue;
    }

    /**
     * returns the red value of this pixel.
     *
     * @return red
     */
    public int getRed() {
        return red;
    }

    /**
     * returns the green value of this pixel.
     *
     * @return green
     */
    public int getGreen() {
        return green;
    }

    /**
     * Checks if this Pixel is equal to a given Object
     *
     * @param o object this pixel is being compared to.
     * @return true if these pixels are equal, false if not.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pixel)) return false;
        Pixel pixel = (Pixel) o;
        return red == pixel.red && green == pixel.green && blue == pixel.blue;
    }

    /**
     * Generates the hashcode for this pixel by using it's red, green, and blue values.
     *
     * @return the hashcode of this pixel.
     */
    @Override
    public int hashCode() {
        return Objects.hash(red, green, blue);
    }

    /**
     * Displays all the distinguishable characteristics of this pixel in a String.
     *
     * @return a String containing the red, green, and blue values of this pixel.
     */
    public String toString() {
        return "red:" + this.red + " green:" + this.green + " blue:" + this.blue;
    }
}
