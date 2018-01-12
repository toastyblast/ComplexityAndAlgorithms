package Assignment_3;

/**
 * A pawn, of which two should be created; One placed on position (vertex) 1 at start, and one on position (vertex) 2
 */
public class Pawn {
    private Vertex currentLocation;

    public Pawn(Vertex currentLocation) {
        this.currentLocation = currentLocation;
    }

    public void setCurrentLocation(Vertex currentLocation) {
        this.currentLocation = currentLocation;
    }

    public Vertex getCurrentLocation() {
        return currentLocation;
    }
}
