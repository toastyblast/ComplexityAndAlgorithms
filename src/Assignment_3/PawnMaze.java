package Assignment_3;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Class that represents the PawnMaze from Assignment 3 - Sub-assignment 1. Has positions and arrows pointing between them.
 */
public class PawnMaze {
    private Set<Vertex> positions;
    private Set<DirectionalEdge> arrows;
    private Map<Vertex, Set<DirectionalEdge>> outGoingArrowsFromPosition;

    public PawnMaze() {
        positions = new HashSet<>();
        arrows = new HashSet<>();
        outGoingArrowsFromPosition = new HashMap<>();
    }

    public Set<Vertex> getConnectedPositions(Vertex current) {
        Set<DirectionalEdge> outgoingArrows = outGoingArrowsFromPosition.get(current);
        Set<Vertex> result = new HashSet<>();

        for (DirectionalEdge arrow : outgoingArrows) {
            result.add(arrow.getToVertex());
        }

        return result;
    }

    public Set<DirectionalEdge> getConnectedArrows(Vertex current) {
        return outGoingArrowsFromPosition.get(current);
    }

    public boolean addPosition(Vertex newPosition) {
        if (positions.add(newPosition)) {
            //Now that we know we have added the position, also make an entry for it with all its connections.
            outGoingArrowsFromPosition.put(newPosition, new HashSet<>());

            //The new position could be added to the PawnMaze.
            return true;
        }

        //The new position could not be added to the PawnMaze as it most likely already exists in the set.
        return false;
    }

    public boolean addArrow(Vertex from, Vertex to, String colour) {
        DirectionalEdge arrow = new DirectionalEdge(from, to, colour);

//        for (DirectionalEdge tempArrow: arrows) {
//            if (tempArrow.getToVertex().equals(from) && tempArrow.getFromVertex().equals(to)) {
//                //There can only be one arrow between to vertices in the PawnMaze, so no back and forth.
//                return false;
//            }
//        }

        if (outGoingArrowsFromPosition.containsKey(to) && outGoingArrowsFromPosition.containsKey(from)) {
            if (!arrows.add(arrow)) {
                //The arrow could not be added, as there most likely is one that's exactly like it in the set already.
                return false;
            }

            //Now that we know we can add the arrow to the outgoing position's hashSet as well.
            outGoingArrowsFromPosition.get(from).add(arrow);

            //The new Directional edge has successfully been added to the PawnMaze.
            return true;
        }

        //The arrow could not be added as either the to or from positions given do not exist in the PawnMaze.
        return false;
    }
}
