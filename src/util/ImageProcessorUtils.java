package util;

import image.Pixel;

public class ImageProcessorUtils {
    public static <T> void ensureNotNull(T obj, String message) {
        if(obj == null) {
            throw new IllegalArgumentException(message);
        }
    }

    public static Pixel createValidPixel(int red, int green, int blue){
        red = clamp(red, 0, 255);
        green = clamp(green, 0, 255);
        blue = clamp(blue, 0, 255);

        return new Pixel(red, green, blue);

    }

    private static int clamp(int value, int min, int max){
        if (value < min){
            return min;
        }

        if (value > max){
            return max;
        }

        return value;
    }
}
