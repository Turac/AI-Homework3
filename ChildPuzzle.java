import java.util.Arrays;

public class ChildPuzzle implements Comparable<ChildPuzzle> {
    private int[] puzzle;
    private int fn;
    private int hn;
    private int gn;

    ChildPuzzle(int[] p, int gn, int hn){
        puzzle = p;
        this.fn=gn+hn;
        this.gn=gn;
        this.hn=hn;
    }
    public int[] getPuzlle()
   	{
   		return puzzle;
   	}
    public void setPuzzle(int[]puzzle)
   	{
   		this.puzzle =puzzle;
   	}
    public int getGN()
	{
		return gn;
	}
    public int getHN()
	{
		return hn;
	}
    public int getFN()
   	{
   		return hn+gn;
   	}

    @Override
    public String toString() {
        String str = "{";

        for (int i=0; i<puzzle.length; i++){
            if (i!=8){
                str += String.valueOf(puzzle[i]) + ", ";
            } else { //Last element
                str += String.valueOf(puzzle[i]) + "}";
            }
        }

        str += " F(n) = " + hn +"+"+ gn +" = "+ fn;

        return str;
    }

    @Override
    public int compareTo(ChildPuzzle puzzle) { //If u directly Collections.sort(arraylistnamehere), it will sort in ascending order.
        //return Integer.compare(manhattanDistance, puzzle.manhattanDistance);
    	return 0;
    }

}