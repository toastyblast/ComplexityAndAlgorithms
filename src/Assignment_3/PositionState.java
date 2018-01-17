package Assignment_3;

/**
 * Class that represents one step in the traversal of the Pawn Maze.
 */
public class PositionState {
    private final Vertex pawnOne, pawnTwo;
    private final PositionState priorState;

    /**
     * Constructor of a PositionState.
     *
     * @param pawnOne Vertex is the location of the first pawn at the time of this step.
     * @param pawnTwo Vertex is the location of the second pawn at the time of this step.
     * @param priorState PositionState is the state that came before this one, so the step performed before ending up at this one.
     */
    public PositionState(Vertex pawnOne, Vertex pawnTwo, PositionState priorState) {
        this.pawnOne = pawnOne;
        this.pawnTwo = pawnTwo;
        this.priorState = priorState;
    }

    public Vertex getPawnOne() {
        return pawnOne;
    }

    public Vertex getPawnTwo() {
        return pawnTwo;
    }

    /**
     * Method that can be used to check if the given Vertex appears in this PositionState, checked on matching coordinates.
     *
     * @param position Vertex is the Vertex of which we want to know if it's in this state.
     * @return boolean true if it does appear here, false if it does not.
     */
    public boolean presentHere(Vertex position) {
        //If the given position's coordinate equals to either the location of pawn 1 or pawn 2 in this state, it's here.
        return this.pawnOne.getCoordinate() == position.getCoordinate() || this.pawnTwo.getCoordinate() == position.getCoordinate();
    }

    /**
     * Method that can be used to check if this state is the same as the given PositionState, checked on coordinates.
     *
     * @param state PositionState is the state we want to check this one against.
     * @return boolean true if they're the same coordinate-wise, false if they are not.
     */
    public boolean equalsState(PositionState state) {
        //If the given state's pawn 1 & pawn 2 locations equal, then they're the same state.
        return state.pawnOne.getCoordinate() == this.pawnOne.getCoordinate() && state.pawnTwo.getCoordinate() == this.pawnTwo.getCoordinate();
    }

    /**
     * Method that forces this state to force it's last state to print its path, and then prints its own path. This
     * causes a chain reaction until the first starting state has been met again.
     */
    public void printPath() {
        //First let your parent state print itself (and/or its children).
        if (priorState != null) {
            priorState.printPath();
        }

        //Print yourself afterwards, so that you create a right order of printing (ascending).
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        return ">> Pawn 1: [" + pawnOne.getCoordinate() + "] (" + pawnOne.getColour() + ")   \t Pawn 2: [" + pawnTwo.getCoordinate() + "] (" + pawnTwo.getColour() + ")";
    }
}
