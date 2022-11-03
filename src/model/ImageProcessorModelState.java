package model;

import image.Image;

import java.util.Set;

/**
 * An ImageProcessorModelState that only allows users to make observations of Images.
 */
public interface ImageProcessorModelState {
    Image getImage(String name);

    Set<String> getImageNames();

}
