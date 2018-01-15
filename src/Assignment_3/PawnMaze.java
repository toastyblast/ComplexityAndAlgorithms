package Assignment_3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Class that represents the PawnMaze from Assignment 3 - Sub-assignment 1. Has positions and arrows pointing between them.
 */
public class PawnMaze {
    private ArrayList<Vertex> positions;

    public PawnMaze() {
        positions = new ArrayList<>();
    }

    public void loadFile(String fileName) {
        String tempLine;
        int counter = 0;

        try {
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
                    handleNewNodeLine(stringArray);
                }
                else if (stringArray.length == 4) {
                    //CASE: The found line is "<fromNodeCoord> -> <colour> <toNodeCoord>"
                    handleNewVertexLine(stringArray);
                }
                else {
                    //CASE: Unknown line.
                    throw new IOException("Unknown line read from file: " + tempLine + ". Does not follow the supplied conventions.");
                }
            }

            bufferedReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Could not find file with name: " + fileName + ". Message: " + e.toString());
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error encountered on file " + fileName + " line #" + counter + ". Message: " + e.toString());
            e.printStackTrace();
        }
    }

    private void handleNewNodeLine (String[] stringArray) throws IOException {
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

    private void handleNewVertexLine (String[] stringArray) throws IOException {
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

        fromNode.addArrow(fromNode, toNode, tempColour);
    }

    public ArrayList<Vertex> getPositions() {
        return positions;
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder(super.toString());

        for (Vertex position: positions) {
            string.append(position.toString());
        }

        return string.toString();
    }
}
