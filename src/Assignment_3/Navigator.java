package Assignment_3;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * The class that moves the pawns around and searches for a solution in the pawn maze.
 */
public class Navigator {
    private final PawnMaze pawnMaze;
    private final Queue<PositionState> nodesToVisit;

    /**
     * Constructor of the Navigator.
     *
     * @param pawnMaze is the pawnMaze this navigator will be used on.
     */
    public Navigator(PawnMaze pawnMaze) {
        this.pawnMaze = pawnMaze;
        nodesToVisit = new LinkedList<>();
    }

    /**
     * Method that the user can use to find a path in the given pawn maze at navigator construction.
     * Returns the first path found, using a BFS approach. If no possible path can be found, an message is also returned.
     */
    public void bfsFindTheWay() {
        PositionState currentState = pawnMaze.getStartState();

        while(currentState != null) {
            //While we still have a state we can continue on in, continue this loop.
            // currentState will become null as soon as the nodesToVisit queue has run out of positionStates.

            if (currentState.presentHere(pawnMaze.getEndVertex())) {
                //BASE CASE: The current state is actually on the end vertex with either pawn, so we're done!
                // Now call the current state's print, which will chain all of the prior positions to it.
                System.out.println("<-=-> START OF THE FOUND PATH <-=->");
                currentState.printPath();
                System.out.println("[=-=] END OF THE FOUND PATH [=-=]");
                //Just be happy with the first result you find!
                return;
            }

            //Get the latest location of the pawns.
            Vertex pawnOne = currentState.getPawnOne();
            Vertex pawnTwo = currentState.getPawnTwo();

            //Get all the neighbours you can travel to as pawn one, keeping in mind that the rule is that you can only travel over arrows that have the same colour as the position (Vertex) the other pawn is on.
            ArrayList<Vertex> positionsToVisit = pawnOne.getTravelableNeighbours(pawnTwo.getColour());
            for (Vertex firstPawnNewPosition: positionsToVisit) {
                addToQueue(firstPawnNewPosition, pawnTwo, currentState);
            }

            //Now repeat the process again, only for the second pawn this time.
            positionsToVisit = pawnTwo.getTravelableNeighbours(pawnOne.getColour());
            for (Vertex secondPawnNewPosition: positionsToVisit) {
                addToQueue(pawnOne, secondPawnNewPosition, currentState);
            }

            //Now that we've gotten the latest updates on new available positions, grab the next one in the queue to go
            // through, aka going breadth first.
            currentState = nodesToVisit.poll();
        }

        System.out.println("No solution has been found. Maybe try different start positions or a different goal?");
    }

    /**
     * Private helper method for .bfsFindTheWay(). Adds a new state to the queue to visit and notes the vertex down as
     * visited for the location of pawn one.
     *
     * @param pawnOne Vertex is the first "pawn". It's just one of the vertices of the Pawn maze, that the first pawn is supposedly located on.
     * @param pawnTwo Vertex is the second "pawn". Once more, it's just a vertex of the pawn maze, that the second pawn is "on".
     * @param currentState PositionState is the state from which this new one was found. Will become the new state's "priorState".
     */
    private void addToQueue(Vertex pawnOne, Vertex pawnTwo, PositionState currentState) {
        PositionState state = new PositionState(pawnOne, pawnTwo, currentState);

        if (!pawnOne.isInVisited(state)) {
            //Make sure that we don't loop by checking if this location hasn't already been travelled to.
            pawnOne.addToVisited(state);
            nodesToVisit.add(state);
        }
    }
}
