import java.awt.*;
import java.awt.image.AreaAveragingScaleFilter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class main {

    public static void main(String [] args)
    {
        int[] puzzle = {8, 0, 5, 6, 2, 7, 3, 1, 4}; //Initially at goal state.
        findSolution(puzzle);

    }

    public static Solution findSolution(int[] puzzle){
    	
    		//Step 1: put the initial node on a list start
    		ChildPuzzle ch = new ChildPuzzle (puzzle, 0,0);
    		Queue<ChildPuzzle> q = new LinkedList<ChildPuzzle>();
    		q.add(ch);
    		ArrayList<int[]> visited = new ArrayList<int[]>();
    		visited.add(puzzle);

       
        //Step 2: if (start is empty) or (start = goal) terminate search.
        boolean solved = false;
        int numOfMoves=0;
        int countOfSearch=0;
        while  (!q.isEmpty()){
        	//Step 3: remove the first node from the start call this node a
        	ChildPuzzle child =q.poll();
        	int[] theChild =child.getPuzlle();
        	ArrayList<ChildPuzzle> intermediaryBeamQueue = new ArrayList<ChildPuzzle>(); 
			ArrayList<int[]> children = findChildren(theChild);
			ArrayList<ChildPuzzle> childPuzzles = new ArrayList<ChildPuzzle>();
        		while (!solved) {
        			
        			 for (int[] p : children){
        				 childPuzzles.add(new ChildPuzzle(p, 0,0));
        	            }
        			
        			ChildPuzzle childPuzzle=null;
        			for (int i = 0; i < childPuzzles.size(); i++) {
        				childPuzzle = new ChildPuzzle(childPuzzles.get(i).getPuzlle(),
        						childPuzzles.get(i).getGN()+1,childPuzzles.get(i).getHN());
        				System.out.println(childPuzzles.get(i).toString());
        				
        				
        			}
        			intermediaryBeamQueue.add(childPuzzle);
        			 //Increment numOfSteps.
        			 numOfMoves++;
        			 countOfSearch++;   
        	            
        		}
        		for (int[] p : children){
                    visited.add(p);
            }
        	
        }
        
        return new Solution(puzzle,countOfSearch);

    }
    
   
    public static ArrayList<int[]> findChildren(int[] puzzle) {
        ArrayList<int[]> children = new ArrayList<int[]>();

        //Locating the empty tile.
        int indexOfZero = -1;
        for (int j : puzzle) {
            if (puzzle[j] == 0) {
                indexOfZero = j;
            }
        }

        if (indexOfZero == 0) {
            children.add(switchTileReturnNewPuzzle(puzzle, 0, 1));
            children.add(switchTileReturnNewPuzzle(puzzle, 0, 3));
        } else if (indexOfZero == 1) {
            children.add(switchTileReturnNewPuzzle(puzzle, 1, 2));
            children.add(switchTileReturnNewPuzzle(puzzle, 1, 0));
            children.add(switchTileReturnNewPuzzle(puzzle, 1, 4));
        } else if (indexOfZero == 2) {
            children.add(switchTileReturnNewPuzzle(puzzle, 2, 5));
            children.add(switchTileReturnNewPuzzle(puzzle, 2, 1));
        } else if (indexOfZero == 3) {
            children.add(switchTileReturnNewPuzzle(puzzle, 3, 0));
            children.add(switchTileReturnNewPuzzle(puzzle, 3, 6));
            children.add(switchTileReturnNewPuzzle(puzzle, 3, 4));
        } else if (indexOfZero == 4) {
            children.add(switchTileReturnNewPuzzle(puzzle, 4, 1));
            children.add(switchTileReturnNewPuzzle(puzzle, 4, 3));
            children.add(switchTileReturnNewPuzzle(puzzle, 4, 5));
            children.add(switchTileReturnNewPuzzle(puzzle, 4, 7));
        } else if (indexOfZero == 5) {
            children.add(switchTileReturnNewPuzzle(puzzle, 5, 8));
            children.add(switchTileReturnNewPuzzle(puzzle, 5, 2));
            children.add(switchTileReturnNewPuzzle(puzzle, 5, 4));
        } else if (indexOfZero == 6) {
            children.add(switchTileReturnNewPuzzle(puzzle, 6, 3));
            children.add(switchTileReturnNewPuzzle(puzzle, 6, 7));
        } else if (indexOfZero == 7) {
            children.add(switchTileReturnNewPuzzle(puzzle, 7, 6));
            children.add(switchTileReturnNewPuzzle(puzzle, 7, 8));
            children.add(switchTileReturnNewPuzzle(puzzle, 7, 4));
        } else if (indexOfZero == 8) {
            children.add(switchTileReturnNewPuzzle(puzzle, 8, 7));
            children.add(switchTileReturnNewPuzzle(puzzle, 8, 5));
        }

        return children;
    }

    public static int findManhattanDistance(int[] puzzle){
        int distance = 0;

        for (int i=0; i<puzzle.length; i++){ //.length is supposed to be 9.
            if (puzzle[i] != 0){ //Can not count the empty tile in. So 0 is left out. 0 represents empty tile.
                int goalIndex = puzzle[i] - 1; //Goal index is the tile number minus one.
                int tileDistance = Math.abs((goalIndex % 3) - (i % 3)) + Math.abs((goalIndex / 3) - (i / 3));
                distance += tileDistance;
            }
        }

        return distance;
    }
   


    public static String puzzleToString(int[] puzzle){
        String str = "{ ";

        for (int number : puzzle){
            str += String.valueOf(number) + ", ";
        }
        str += "}";
        return str;
    }

    public static int[] switchTileReturnNewPuzzle(int[] puzzle, int tileIndex1, int tileIndex2){
        int[] puzzleCopy = Arrays.copyOf(puzzle, puzzle.length);
        int tile = puzzleCopy[tileIndex1];
        puzzleCopy[tileIndex1] = puzzleCopy[tileIndex2];
        puzzleCopy[tileIndex2] = tile;
        return puzzleCopy;
    }
    
    public static String ArrpuzzleToString(ArrayList puzzle){
            String str = "{ ";
    
            for (int number=0; number<puzzle.size(); number++){
                str += String.valueOf(number) + ", ";
            }
            str += "}";
            return str;
        }
        
    public static void switchTile(int[] puzzle, int tileIndex1, int tileIndex2){ //Array must be size of 9.
        int tile = puzzle[tileIndex1];
        puzzle[tileIndex1] = puzzle[tileIndex2];
        puzzle[tileIndex2] = tile;
    }

    
    public static ArrayList<int[]> generateNDistinctPuzzles(int n){
        ArrayList<int[]> puzzles = new ArrayList<int[]>();

        for (int i=0; i<n; i++){
            int[] candidatePuzzle = generatePuzzle();
            boolean contains = false;
            for (int[] p : puzzles){
                if (Arrays.equals(p, candidatePuzzle)){
                    contains = true;
                }
            }
            if (contains){
                i--; //Try again.
            } else {
                puzzles.add(candidatePuzzle);
            }
        }

        return puzzles;
    }

    public static int[] generatePuzzle(){
        int[] puzzle = {1, 2, 3, 4, 5, 6, 7, 8, 0}; //Initially at goal state.
        Random rand = new Random();
        int  numberOfSteps = rand.nextInt(50) + 50; //Number of steps its going to take in order to generate a puzzle.

        for(int i=0; i<numberOfSteps; i++){
            int randomMove = rand.nextInt(12) + 1; //Gives a number between 1 and 12, inclusive.

            int indexOfZero = -1;
            for (int j : puzzle) { //Locating the empty tile.
                if (puzzle[j] == 0) {
                    indexOfZero = j;
                }
            }

            if (indexOfZero == 4) { //Empty tile is in the middle
                if (randomMove < 4) { //Case 1, 2, 3: UP
                    switchTile(puzzle, 4, 1);
                } else if (randomMove < 7) { //Case 4, 5, 6: LEFT
                    switchTile(puzzle, 4, 3);
                } else if (randomMove < 10) { //Case 7, 8, 9: RIGHT
                    switchTile(puzzle, 4, 5);
                } else { //Case 10, 11, 12: DOWN
                    switchTile(puzzle, 4, 7);
                }
            } else if ((indexOfZero == 1) || (indexOfZero == 3) || (indexOfZero == 5) || (indexOfZero == 7)) { //Empty tile is at some edge edge
                if (randomMove < 5) { //Case 1, 2, 3, 4: CLOCKWISE
                    if (indexOfZero == 1) {
                        switchTile(puzzle, 1, 2);
                    } else if (indexOfZero == 3) {
                        switchTile(puzzle, 3, 0);
                    } else if (indexOfZero == 5) {
                        switchTile(puzzle, 5, 8);
                    } else { //Its 7.
                        switchTile(puzzle, 7, 6);
                    }
                } else if (randomMove < 9) { //Case 5, 6, 7, 8: COUNTER CLOCKWISE
                    if (indexOfZero == 1) {
                        switchTile(puzzle, 1, 0);
                    } else if (indexOfZero == 3) {
                        switchTile(puzzle, 3, 6);
                    } else if (indexOfZero == 5) {
                        switchTile(puzzle, 5, 2);
                    } else { //Its 7.
                        switchTile(puzzle, 7, 8);
                    }
                } else { //Case 9, 10, 11, 12: MIDDLE
                    if (indexOfZero == 1) {
                        switchTile(puzzle, 1, 4);
                    } else if (indexOfZero == 3) {
                        switchTile(puzzle, 3, 4);
                    } else if (indexOfZero == 5) {
                        switchTile(puzzle, 5, 4);
                    } else { //Its 7.
                        switchTile(puzzle, 7, 4);
                    }
                }
            } else { //Empty tile is at some corner
                if (randomMove < 7) { //Case 1 to 6: CLOCKWISE
                    if (indexOfZero == 0) {
                        switchTile(puzzle, 0, 1);
                    } else if (indexOfZero == 2) {
                        switchTile(puzzle, 2, 5);
                    } else if (indexOfZero == 6) {
                        switchTile(puzzle, 6, 3);
                    } else { //Its 8.
                        switchTile(puzzle, 8, 7);
                    }
                } else { //Case 7 to 12: COUNTER CLOCKWISE
                    if (indexOfZero == 0) {
                        switchTile(puzzle, 0, 3);
                    } else if (indexOfZero == 2) {
                        switchTile(puzzle, 2, 1);
                    } else if (indexOfZero == 6) {
                        switchTile(puzzle, 6, 7);
                    } else { //Its 8.
                        switchTile(puzzle, 8, 5);
                    }
                }
            }
        }
        return puzzle;
    }
}









