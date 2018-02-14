

public class Queens {

    public static void main(String[] args){
        int numberOfQueens = 8;
        QueenSolver qs = new QueenSolver(numberOfQueens, 0);
        System.out.println("Total solution: "+qs.getSolutionCount());
    }
}
