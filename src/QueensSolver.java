public class QueensSolver {

    public LinkedList listOfMoves;
    public char[][] chessBoard;
    public boolean backtrack;
    public int solutionCount;

    public QueensSolver(int numberOfQueens, int startPosition){
        chessBoard = new char[numberOfQueens][numberOfQueens];
        listOfMoves = new SolutionList();
        solutionCount = 0;

        //Push row 0 into list of move and check if that row in the first column is safe to place the queen
        listOfMoves.push(startPosition);

        //Find first solution

        if(placeQueen()){
            displayChessBoard();
        }

        do{
            displayChessBoard();
        }
        while(removeQueens());
    }

    public int getSolutionCount() {
        return solutionCount;
    }

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

    public boolean placeQueen(){
        int colToCheck = listOfMoves.getStackSize() - 1;
        //System.out.println("Current Stack size: "+ listOfMoves.getStackSize());
        if(listOfMoves.getStackSize() > chessBoard.length) {
            listOfMoves.pop();
            System.out.println(listOfMoves);
            solutionCount+=1;
            return true;
        }
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
            if (isSafe(chessBoard, rowToCheck, colToCheck)) {
                //System.out.println("Safe " + rowToCheck + ", " + colToCheck);
                //System.out.println("Put queen to the board: "+rowToCheck+", "+colToCheck);
                chessBoard[rowToCheck][colToCheck] = 'Q';
                //System.out.println("Push solution to stack");

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


    public boolean isSafe(char[][] chessBoard, int row, int col){

        //System.out.println("Check for row: "+row+", and column: "+col);
        for(int i = 0; i<chessBoard.length; i++){
            if(chessBoard[i][col]=='Q'){
                return false;
            }
        }

        for(int j = 0; j<chessBoard[row].length; j++){
            if(chessBoard[row][j] == 'Q'){
                return false;
            }
        }

        for(int i = row, j = col; i>=0 && j>=0; i--,j-- ){
            if(chessBoard[i][j]=='Q'){
                return false;
            }
        }

        for(int i = row, j = col; i < chessBoard.length && j >=0; i++, j-- ){
            if(chessBoard[i][j] == 'Q'){
                return false;
            }
        }
        return true;
    }

    public void displayChessBoard(){
        System.out.println();
        for(int row = 0; row < chessBoard.length; row++){
            for(int col=0; col<chessBoard[row].length; col++){
                if(chessBoard[row][col]!='Q') chessBoard[row][col] = '-';
                System.out.print(chessBoard[row][col] +" | ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
