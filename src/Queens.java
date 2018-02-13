

public class Queens {

    public static void main(String[] args){
        int numberOfQueens = 8;
        //QueenSolver qs = new QueenSolver(numberOfQueens, );
        //qs.displayChessBoard();

        int solutionCount = 0;
        for(int i=0; i<numberOfQueens; i++){
            QueenSolver qs = new QueenSolver(numberOfQueens, i);
            solutionCount += qs.getSolutionCount();
        }

        System.out.println("Total: "+solutionCount);
    }
}
