package Assignment_3;

public class PositionState {
    private final int positionPawnOne;
    private final int positionPawnTwo;
    private final PositionState priorState;

    public PositionState(int positionPawnOne, int positionPawnTwo, PositionState priorState) {
        this.positionPawnOne = positionPawnOne;
        this.positionPawnTwo = positionPawnTwo;
        this.priorState = priorState;
    }

    public int getPositionPawnOne() {
        return positionPawnOne;
    }

    public int getPositionPawnTwo() {
        return positionPawnTwo;
    }

    public PositionState getPriorState() {
        return priorState;
    }
}
