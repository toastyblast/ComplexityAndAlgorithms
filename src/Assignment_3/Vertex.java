package Assignment_3;

/**
 * A class that represents one of the coloured positions a pawn can be on.
 */
public class Vertex {
    private final int coordinate;
    private final String colour;

    public Vertex(int coordinate, String colour) {
        this.coordinate = coordinate;
        this.colour = colour;
    }

    public int getCoordinate() {
        return coordinate;
    }

    public String getColour() {
        return colour;
    }
}
