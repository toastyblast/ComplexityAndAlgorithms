package Assignment_3;

/**
 * Class that represents an arrow on the PawnMaze. Can only go one direction and is of a certain colour.
 */
public class DirectionalEdge {
    private final Vertex fromVertex;
    private final Vertex toVertex;
    private final Colours colour;

    public DirectionalEdge(Vertex fromVertex, Vertex toVertex, Colours colour) {
        this.fromVertex = fromVertex;
        this.toVertex = toVertex;
        this.colour = colour;
    }

    public Vertex getToVertex() {
        return toVertex;
    }

    public Vertex getFromVertex() {
        return fromVertex;
    }

    public Colours getColour() {
        return colour;
    }

    @Override
    public String toString() {
        return "\n\t(-) From: " + fromVertex.getCoordinate() + " - To: " + toVertex.getCoordinate() + " - Colour: " + colour;
    }
}
