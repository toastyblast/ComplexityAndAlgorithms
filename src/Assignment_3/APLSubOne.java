package Assignment_3;

/**
 * APL for Complexity & Algorithms assignment 3.1 - Pawn maze.
 *
 * @author Yoran Kerbusch (430818) & Martin S. Slavov (435666).
 * Saxion Enschede HBO-IT International - Y2Q2 17/1/2018.
 */
public class APLSubOne {
    public static void main(String[] args) {
        new APLSubOne().run();
    }

    private void run() {
        //Create the pawn maze from the assignment! (Or load in your own!)
        PawnMaze pawnMaze = new PawnMaze();
        pawnMaze.loadFile("pawnMaze.txt");

        System.out.println(pawnMaze.toString());

        Navigator navigator = new Navigator(pawnMaze);
        navigator.bfsFindTheWay();

        //Create an non-solvable pawn maze!
//        PawnMaze unsolvablePawnMaze = new PawnMaze();
//        unsolvablePawnMaze.loadFile("unsolvablePawnMaze.txt");
//
//        System.out.println(unsolvablePawnMaze.toString());
//
//        Navigator unsolvableNavigator = new Navigator(pawnMaze);
//        unsolvableNavigator.bfsFindTheWay();
    }
}
