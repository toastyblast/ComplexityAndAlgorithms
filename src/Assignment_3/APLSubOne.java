package Assignment_3;

public class APLSubOne {
    public static void main(String[] args) {
        new APLSubOne().run();
    }

    private void run() {
        PawnMaze pawnMaze = new PawnMaze();
        pawnMaze.loadFile("pawnMaze.txt");

        System.out.println(pawnMaze.getPositions().toString());
    }
}
