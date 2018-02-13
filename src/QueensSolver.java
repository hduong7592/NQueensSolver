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
        if(placeQueen())
        {
            //Found first solution
        }

        //Find the rest of the solutions
        do{
            backtrack = true;
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
                //System.out.println("Push to stack");
                listOfMoves.push(0);

                if (placeQueen()) {
                    //System.out.println("Found ***********");
                    //System.out.println(listOfMoves);
                    //solutionCount+=1;
                    return true;
                } else {

                    if (listOfMoves.getStackSize() < chessBoard.length) {
                        //System.out.println("Need back track");

                        int currentRow = 0;
                        try {
                            currentRow = (int) listOfMoves.pop();
                        }
                        catch (Exception ex){
                            return false;
                        }
                        chessBoard[currentRow][colToCheck] = ' ';
                        //System.out.println("Remove queen from the board: " + currentRow + ", " + colToCheck);
                        int nextRow = currentRow + 1;
                        //System.out.println("Next row"+nextRow);

                        if (nextRow < chessBoard.length) {
                            listOfMoves.push(nextRow);
                            placeQueen();
                        }
                    }
                    else return true;
                }
                //placeQueen();

            } else {
                //System.out.println("Not Safe " + rowToCheck + ", " + colToCheck);

                if(backtrack) {
                    removeQueens();
                }
                else {
                    int nextRow = (int) listOfMoves.pop() + 1;

                    if (nextRow < chessBoard.length) {
                        listOfMoves.push(nextRow);
                        //chessBoard[nextRow][colToCheck] = 'Q';
                        placeQueen();
                    } else {

                    }
                }
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
                if(chessBoard[row][col]!='Q') chessBoard[row][col] = ' ';
                System.out.print(chessBoard[row][col] +" | ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
