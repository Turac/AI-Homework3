import java.util.ArrayList;

public class Solution{
    public int[] puzzle;
    public int searchCount;
    public ArrayList<ArrayList> graphElements;

    Solution(int[] puzzle, int searchCount){
        this.puzzle = puzzle;
       this.searchCount=searchCount;
    }

    @Override
    public String toString() {
        String str = "Puzzle: " +  puzzleToString(puzzle);

        str += "SearchCount =  " +searchCount;

        return str;
    }
    public static String ArrpuzzleToString(ArrayList puzzle){
        String str = "{ ";

        for (int number=0; number<puzzle.size(); number++){
            str += String.valueOf(number) + ", ";
        }
        str += "}";
        return str;
    }
    

    public static String puzzleToString(int[] puzzle){
        String str = "{ ";

        for (int number : puzzle){
            str += String.valueOf(number) + ", ";
        }
        str += "}";
        return str;
    }

}