package model;

import image.Image;

import java.util.Set;

public interface ImageProcessorModelState {
    public Image getImage(String name);

    public Set<String> getImageNames();



}
