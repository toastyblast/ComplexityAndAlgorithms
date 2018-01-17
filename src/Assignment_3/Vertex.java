package Assignment_3;

import java.io.IOException;
import java.util.ArrayList;

/**
 * A class that represents one of the coloured positions a pawn can be on.
 */
public class Vertex {
    private final int coordinate;
    private final Colours colour;
    private final ArrayList<DirectionalEdge> outgoingArrows;
    private final ArrayList<PositionState> visited;

    /**
     * Constructor for a Vertex, which is one of the numbered, coloured positions on the Pawn Maze.
     *
     * @param coordinate int is the coordinate of this position.
     * @param colour Colours (enum) is the colour of this position.
     */
    public Vertex(int coordinate, Colours colour) {
        this.coordinate = coordinate;
        this.colour = colour;
        outgoingArrows = new ArrayList<>();
        visited = new ArrayList<>();
    }

    public int getCoordinate() {
        return coordinate;
    }

    public Colours getColour() {
        return colour;
    }

    /**
     * Method to check if the PositionState given has had this Vertex in it.
     *
     * @param state PositionState is the state of which we want to know if it's in here.
     * @return boolean true if this Vertex has been in that state, false if it has not.
     */
    public boolean isInVisited(PositionState state) {
        for (PositionState positionSate: visited) {
            if (positionSate.equalsState(state)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Method that can be used to add the given PositionState to the list of times this Vertex has been visited (by pawn one).
     *  Checks if the coordinate of this Vertex matches that of the first pawn's of the state.
     *
     * @param state PositionState is the state we want to add to this visited list.
     */
    public void addToVisited(PositionState state) {
        if (!isInVisited(state)) {
            if (state.getPawnOne().getCoordinate() == coordinate) {
                visited.add(state);
            } else {
                System.out.println("ERROR: Cannot add a state to visited that does not have a matching pawnOne coordinate.");
            }
        } else {
            System.out.println("ERROR: Cannot add a state to visited that is already in. This means you're looping!");
        }
    }

    /**
     * Method that can be used to retrieve all neighbouring Vertices that can be travelled to with the given Colour.
     *
     * @param colour Colours is an enum value of the given Vertex' colour, so we can find all nodes you can travel to from here.
     * @return ArrayList<Vertex> is the list of neighbouring nodes that can be travelled through arrows with with the given Colour.
     */
    public ArrayList<Vertex> getTravelableNeighbours(Colours colour) {
        ArrayList<Vertex> visitableNeighbours = new ArrayList<>();

        for (DirectionalEdge arrow: outgoingArrows) {
            if (arrow.getColour() == colour) {
                visitableNeighbours.add(arrow.getToVertex());
            }
        }

        return visitableNeighbours;
    }

    /**
     * Method that can be used to add a new outgoing arrow to this Vertex.
     *
     * @param tempTo is the Vertex the arrow will be going to.
     * @param tempColour is the colour the arrow (DirectionalEdge) will be.
     * @throws IOException when someone tries to add an arrow that already exists from this Vertex.
     */
    public void addArrow(Vertex tempTo, Colours tempColour) throws IOException {

        for (DirectionalEdge tempArrow: outgoingArrows) {
            Vertex tempArrowFrom = tempArrow.getFromVertex();
            Vertex tempArrowTo = tempArrow.getToVertex();

            if (tempArrowFrom.equals(this) && tempArrowTo.equals(tempTo)) {
                //We already have an arrow exactly like this one in the graph. We do not have to check for colour as
                // only one arrow can go from one node to another.
                throw new IOException("Tried to add an arrow that already exists between the given from & to positions.");
            }
            else if (tempArrowTo.equals(this) && tempArrowFrom.equals(tempTo)) {
                //There can only be one arrow between to vertices in the PawnMaze, so no back and forth.
                throw new IOException("Tried to add an arrow that goes from and to a node that already have an arrow going the other way.");
            }
        }

        outgoingArrows.add(new DirectionalEdge(this, tempTo, tempColour));
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("\n<=> Coordinate: " + coordinate + " - Colour: " + colour + " - Outgoing arrows: ");
        for (DirectionalEdge arrow: outgoingArrows) {
            stringBuilder.append(arrow.toString());
        }
        stringBuilder.append('\n');

        return stringBuilder.toString();
    }
}
