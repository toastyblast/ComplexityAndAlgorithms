package Assignment_3;

import java.io.IOException;
import java.util.ArrayList;

/**
 * A class that represents one of the coloured positions a pawn can be on.
 */
public class Vertex {
    private final int coordinate;
    private final Colours colour;
    private ArrayList<DirectionalEdge> outgoingArrows;

    public Vertex(int coordinate, Colours colour) {
        this.coordinate = coordinate;
        this.colour = colour;
        outgoingArrows = new ArrayList<>();
    }

    public int getCoordinate() {
        return coordinate;
    }

    public Colours getColour() {
        return colour;
    }

    public ArrayList<DirectionalEdge> getOutgoingArrows() {
        return outgoingArrows;
    }

    public void addArrow(Vertex tempFrom, Vertex tempTo, Colours tempColour) throws IOException {

        for (DirectionalEdge tempArrow: outgoingArrows) {
            Vertex tempArrowFrom = tempArrow.getFromVertex();
            Vertex tempArrowTo = tempArrow.getToVertex();

            if (tempArrowFrom.equals(tempFrom) && tempArrowTo.equals(tempTo)) {
                //We already have an arrow exactly like this one in the graph. We do not have to check for colour as
                // only one arrow can go from one node to another.
                throw new IOException("Tried to add an arrow that already exists between the given from & to positions.");
            }
            else if (tempArrowTo.equals(tempFrom) && tempArrowFrom.equals(tempTo)) {
                //There can only be one arrow between to vertices in the PawnMaze, so no back and forth.
                throw new IOException("Tried to add an arrow that goes from and to a node that already have an arrow going the other way.");
            }
        }

        outgoingArrows.add(new DirectionalEdge(tempFrom, tempTo, tempColour));
    }

    @Override
    public String toString() {
        return "\n<=> Coordinate: " + coordinate + " - Colour: " + colour + " - Outgoing arrows: " + outgoingArrows + "\n";
    }
}
