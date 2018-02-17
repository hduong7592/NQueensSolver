/**
 * This is QueenSolver class
 *
 * This class will put the queens into 8 places in the chessboard and display the result
 *
 * Total solution should be 92
 *
 * @author Hieu Duong
 * @since 2018-02-15
 */


public class QueenSolver {

    public LinkStack listOfMoves;
    public char[][] chessBoard;
    public int solutionCount;

    public QueenSolver(int numberOfQueens, int startPosition){

        //Create the chessboard with numberOfQueens as row and col
        chessBoard = new char[numberOfQueens][numberOfQueens];

        //Create a solution list
        listOfMoves = new SolutionList();

        //Initialize number of solution = 0
        solutionCount = 0;

        //Push row 0 into list of move and check if that row in the first column is safe to place the queen
        listOfMoves.push(startPosition);

        //Find first solution
        if(placeQueen()){
            //displayChessBoard();
        }

        //When all the queen pieces have already put into the board, back track to find other solutions
        do{
            //displayChessBoard();
        }
        while(removeQueens());
    }

    /**
     * Method to return solution count
     * @return
     */
    public int getSolutionCount() {
        return solutionCount;
    }

    /**
     * removeQueens method
     * This is the backtrack method
     * This method will pop the last row coordinate in the LinkStack list, the add one to that row, then find out if the queen can be put into that row
     * The column of that row should be the stack size - 1 (Because column starts at 0)
     * If current row > than the board size and there is no solution for that row, back track to the previous column
     * Remove the queen from the previous column and row, then add one to the row and check again for that row, column
     * Keep going until all of the solutions are found
     *
     * @return
     */
    public boolean removeQueens() {
        int row = 0;
        if(listOfMoves.getStackSize()>0) {
            try {
                row = (int) listOfMoves.pop();
            }
            catch(Exception ex){
                return false;
            }
            int col = listOfMoves.getStackSize();
            //System.out.println("Remove " + row + ", col " + col);
            chessBoard[row][col] = ' ';
            int newRow = row + 1;
            if (newRow < chessBoard.length) {
                listOfMoves.push(newRow);
            }
            placeQueen();
            //System.out.println(listOfMoves);
        }
        else{
            return false;
        }
        return true;
    }

    /**
     * placeQueen method
     *
     * This method will place the queen into the possible row and column of the chessboard.
     *
     * @return
     */
    public boolean placeQueen(){

        //Get the column to check
        //Because we put a first row position in to the LinkStack, so the size of the LinkStack increases by 1
        //In order to get the column of that row, subtract 1 from the stacksize
        //This can only be done if there is at least 1 position in the LinkStack

        int colToCheck = listOfMoves.getStackSize() - 1;
        //System.out.println("Current Stack size: "+ listOfMoves.getStackSize());

        //If the LinkStack size is more than 8, that means we already put 8 queens into the board
        //Return true as we already found the solution
        if(listOfMoves.getStackSize() > chessBoard.length) {
            listOfMoves.pop();
            System.out.println(listOfMoves);
            solutionCount+=1;
            return true;
        }

        //Else keep checking for possible row and column to put the queen
        else{
            int rowToCheck = 0;
            try {
                rowToCheck = (int) listOfMoves.peak();
            }
            catch (Exception ex){
                return false;
            }
            //System.out.println("Col to check " + colToCheck);
            //System.out.println("Check " + rowToCheck+", "+colToCheck);

            //If it is safe to place the queen, place the queen
            if (isSafe(chessBoard, rowToCheck, colToCheck)) {
                //System.out.println("Safe " + rowToCheck + ", " + colToCheck);
                //System.out.println("Put queen to the board: "+rowToCheck+", "+colToCheck);
                chessBoard[rowToCheck][colToCheck] = 'Q';
                //System.out.println("Push solution to stack");

                //Add the next row into the LinkStack, check that row and column (Stacksize -1)
                listOfMoves.push(0);

                if (placeQueen()) {
                    //System.out.println("Found ***********");
                    //System.out.println(listOfMoves);
                    //solutionCount+=1;
                    return true;
                } else {
                    //No solution in this row, col
                    //Need back track
                    if (listOfMoves.getStackSize() < chessBoard.length) {

                        //No solution, run the backtrack method
                        removeQueens();
                    }
                }
            } else {
                //System.out.println("Not Safe " + rowToCheck + ", " + colToCheck);
                //No solution in this row, col
                //Back track
                removeQueens();
            }
        }
        return false;
    }


    /**
     * Method to check whether the queen is safe to be placed into the board
     * @param chessBoard
     * @param row
     * @param col
     * @return
     */
    public boolean isSafe(char[][] chessBoard, int row, int col){

        //Check row
        //If any of the cell in the row already has the queen in it, the it is not safe to put the queen
        for(int i = 0; i<chessBoard.length; i++){
            if(chessBoard[i][col]=='Q'){
                return false;
            }
        }

        //Check column
        //If any of the cell in the column already has the queen in it, the it is not safe to put the queen
        for(int j = 0; j<chessBoard[row].length; j++){
            if(chessBoard[row][j] == 'Q'){
                return false;
            }
        }

        //Check diagonal right to left bottom to top
        //Since we place the queen left to right, we only have to check the left side of every new queen
        for(int i = row, j = col; i>=0 && j>=0; i--,j-- ){
            if(chessBoard[i][j]=='Q'){
                return false;
            }
        }

        //Check diagonal right to left, top to bottom
        //Since we place the queen left to right, we only have to check the left side of every new queen
        for(int i = row, j = col; i < chessBoard.length && j >=0; i++, j-- ){
            if(chessBoard[i][j] == 'Q'){
                return false;
            }
        }
        return true;
    }

    /**
     * Method to display the chessboard
     */
    public void displayChessBoard(){
        if(listOfMoves.getStackSize() >2) {
            System.out.println();
            for (int row = 0; row < chessBoard.length; row++) {
                for (int col = 0; col < chessBoard[row].length; col++) {
                    if (chessBoard[row][col] != 'Q') chessBoard[row][col] = '-';
                    System.out.print(chessBoard[row][col] + " | ");
                }
                System.out.println();
            }
            System.out.println();
        }
    }

    /**
     * Main method
     * @param args
     */
    public static void main(String[] args){
        int numberOfQueens = 8;
        QueenSolver qs = new QueenSolver(numberOfQueens, 0);
        System.out.println("Total solution: "+qs.getSolutionCount());
    }
}