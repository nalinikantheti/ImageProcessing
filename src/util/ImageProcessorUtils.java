package util;

public class ImageProcessorUtils {
    public static <T> void ensureNotNull(T obj, String message) {
        if(obj == null) {
            throw new IllegalArgumentException(message);
        }
    }
}
