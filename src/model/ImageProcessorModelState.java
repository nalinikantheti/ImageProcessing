package model;

import Image.Image;

import java.util.ArrayList;
import java.util.Set;

public interface ImageProcessorModelState {
    public Image getImage(String name);

    public Set<String> getImageNames();



}
