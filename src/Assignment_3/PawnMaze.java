package Assignment_3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Class that represents the Pawn maze from Assignment 3 - Sub-assignment 1. Has positions and arrows pointing between them.
 * Can be created only from a .txt file that holds the same syntax as mentioned in the pawnMaze.txt
 */
public class PawnMaze {
    private ArrayList<Vertex> positions;
    private PositionState startState;
    private Vertex startOne;
    private Vertex startTwo;
    private Vertex endVertex;

    public PawnMaze() {
        positions = new ArrayList<>();
    }

    /**
     * Method that loads a Pawn Maze from a file by reading the entries. The entries in the .txt file have to be
     * exactly as described in the example "pawnMaze.txt" file. If called upon an already loaded Pawn Maze object, it
     * will clear said object and load in the new pawn maze from the second given file.
     *
     * @param fileName String is the name of the file to read.
     */
    public void loadFile(String fileName) {
        String tempLine;
        int counter = 0;

        try {
            //Clear the positions list, even if there was none before.
            positions = new ArrayList<>();

            FileReader fileReader = new FileReader(fileName);

            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while((tempLine = bufferedReader.readLine()) != null) {
                counter++;
                String[] stringArray = tempLine.split(" ");

                if (stringArray[0].equals("#")) {
                    //Ignore this case, as it's a comment and does not need to be parsed.
                }
                else if (stringArray.length == 2) {
                    //CASE: The line encountered is "<nodeCoordinate> <colour>"
                    handleNewVertexLine(stringArray);
                }
                else if (stringArray.length == 3) {
                    //CASE: The line encountered is "<nodeCoordinate> <colour> <pawnOne/pawnTwo/goal>"
                    handleStartEndLine(stringArray);
                }
                else if (stringArray.length == 4) {
                    //CASE: The found line is "<fromNodeCoord> -> <colour> <toNodeCoord>"
                    handleNewEdgeLine(stringArray);
                }
                else {
                    //CASE: Unknown line.
                    throw new IOException("Unknown line read from file: " + tempLine + ". Does not follow the supplied conventions.");
                }
            }

            bufferedReader.close();

            if (correctConstruction()) {
                //Get the starting state for the maze.
                startState = new PositionState(startOne, startTwo, null);
                System.out.println("SUCCESS: Pawn maze has successfully been constructed from file: " + fileName + ".");
            }
        } catch (FileNotFoundException e) {
            System.out.println("Could not find file with name: " + fileName + ". Message: " + e.toString());
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error encountered on file " + fileName + " line #" + counter + ". Message: " + e.toString());
            e.printStackTrace();
        }
    }

    /**
     *
     * Private helper method that is used to create a new Vertex from a read line that tells the Pawn Maze to do so.
     *
     * @param stringArray String[] is the sentence split up, including the needed tags.
     * @throws IOException is thrown when the Vertex already exists in the Pawn Maze.
     */
    private void handleNewVertexLine(String[] stringArray) throws IOException {
        int tempCoordinate = Integer.parseInt(stringArray[0]);

        for (Vertex position: positions) {
            if (position.getCoordinate() == tempCoordinate) {
                throw new IOException("Position with coordinate " + tempCoordinate + " already exists in the maze!");
            }
        }

        Colours tempColour = Colours.valueOf(stringArray[1].toUpperCase());

        //We should be able to assume it is correct, otherwise either method throws an exception and the program is halted.
        positions.add(new Vertex(tempCoordinate, tempColour));
    }

    /**
     * Private helper method that is used to create each of the three special Vertices: start pawn one, start pawn two
     * and goal. Throws an exception when one of these have already been defined before.
     *
     * @param stringArray String[] is the sentence split up, including the needed tags.
     * @throws IOException is thrown when one of these special vertices is tried to be created a second time.
     */
    private void handleStartEndLine (String[] stringArray) throws IOException {
        int tempCoordinate = Integer.parseInt(stringArray[0]);

        for (Vertex position: positions) {
            if (position.getCoordinate() == tempCoordinate) {
                throw new IOException("Position with coordinate " + tempCoordinate + " already exists in the maze!");
            }
        }

        Colours tempColour = Colours.valueOf(stringArray[1].toUpperCase());

        Vertex temp = new Vertex(tempCoordinate, tempColour);

        if (stringArray[2].equalsIgnoreCase("pawnOne")) {
            if (startOne != null) {
                throw new IOException("Start of pawn 1 has already been defined once before in the .txt file!");
            }

            startOne = temp;
        } else if (stringArray[2].equalsIgnoreCase("pawnTwo")) {
            if (startTwo != null) {
                throw new IOException("Start of pawn 2 has already been defined once before in the .txt file!");
            }

            startTwo = temp;
        } if (stringArray[2].equalsIgnoreCase("goal")) {
            if (endVertex != null) {
                throw new IOException("Start of the goal has already been defined once before in the .txt file!");
            }

            endVertex = temp;
        }

        //We should be able to assume it is correct, otherwise either method throws an exception and the program is halted.
        positions.add(temp);
    }

    /**
     * Private helper method used to create a new DirectionalEdge (position) from the read file. Checks if the given
     * to and from Vertices actually exists as well.
     *
     * @param stringArray String[] is the sentence split up, including the needed tags.
     * @throws IOException is for when a to and/or from Vertex has been mentioned that does not exist.
     */
    private void handleNewEdgeLine(String[] stringArray) throws IOException {
        int fromNodeCoord = Integer.parseInt(stringArray[0]);
        int toNodeCoord = Integer.parseInt(stringArray[3]);
        Vertex fromNode = null;
        Vertex toNode = null;

        for (Vertex position: positions) {
            if (position.getCoordinate() == fromNodeCoord) {
                //We have found a position with the same from node coordinate as we were given, so store it.
                fromNode = position;
            } else if (position.getCoordinate() == toNodeCoord) {
                //We have found a position with the same to node coordinate as we were given, so store it.
                toNode = position;
            }

            if (fromNode != null && toNode != null) {
                //We have found the to and from node to exist, we can stop looping!
                break;
            }
        }

        if (fromNode == null) {
            throw new IOException("The specified from [" + fromNodeCoord + "] coordinate do not exist.");
        } else if (toNode == null) {
            throw new IOException("The specified to [" + toNodeCoord + "] coordinate do not exist.");
        }

        Colours tempColour = Colours.valueOf(stringArray[2].toUpperCase());

        fromNode.addArrow(toNode, tempColour);
    }

    /**
     * Private helper method that checks if the user defined the two start locations and the goal in their file.
     *
     * @return
     */
    private boolean correctConstruction() {
        return startOne != null && startTwo != null && endVertex != null;
    }

    /**
     * Returns a copy of the starting state of the maze, which can be used to traverse it.
     *
     * @return PositionState the state where both pawns are defined as the two starting Vertices given.
     */
    public PositionState getStartState() {
        final PositionState copyOfStart = startState;
        return copyOfStart;
    }

    /**
     * Returns a copy of the goal Vertex of this pawn maze.
     *
     * @return Vertex copy of the goal of the maze.
     */
    public Vertex getEndVertex() {
        final Vertex copyOfEnd= endVertex;
        return copyOfEnd;
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();

        for (Vertex position: positions) {

            if (position.equals(startOne)) {
                string.append("\n<-=-> START PAWN #1 <-=->");
            }
            else if (position.equals(startTwo)) {
                string.append("\n<-=-> START PAWN #2 <-=->");
            }
            if (position.equals(endVertex)) {
                string.append("\n[=-=] GOAL PAWN MAZE [=-=]");
            }

            string.append(position.toString());
        }

        return string.toString();
    }
}
