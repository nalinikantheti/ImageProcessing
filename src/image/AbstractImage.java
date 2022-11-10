package image;

public abstract class AbstractImage implements Image {
    protected void ensureInBounds(int x, int y) {
        if (x < 0 || x >= this.getWidth() || y < 0 || y >= this.getHeight()) {
            throw new IllegalArgumentException("Coordinates out of bounds.");
        }
    }
    @Override
    public abstract Image clone();
}
