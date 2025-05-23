public class Configurations {
	private int boardSize;
	private int lengthToWin;
	private int maxLevels;
    private char[][] board;
    
    //Constructor for boardSize, lengthToWin, and maxLevels
    public Configurations(int size, int length, int max) {
        this.board = new char[size][size];
        this.boardSize = size;
        this.lengthToWin = length;
        this.maxLevels = max;
       
        //build an empty board
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                this.board[i][j] = ' ';
            }
        }
    }

    //returns an empty HashDictionary
    public HashDictionary createDictionary() {
        return new HashDictionary(7001); // 7001 is a prime number
    }

    //stores the characters of board in a String, checks whether the String representing the board is in hashTable
    public int repeatedConfiguration(HashDictionary hashTable) {
        String config = getStringRepresentation();
        int score = hashTable.get(config);
        if (score != -1) {
			return score;
		} else {
			return -1;
		}
    }

    //represents the content of board,  then it inserts the String and score in hashDictionary
    public void addConfiguration(HashDictionary hashDictionary, int score) {
        String config = getStringRepresentation();
        try {
            hashDictionary.put(new Data(config, score));
        } catch (DictionaryException e) {
        	throw e;
        }
    }

    //Stores char symbol in board of specific row and col
    public void savePlay(int row, int col, char symbol) {
        this.board[row][col] = symbol;
    }

    //See if board is empty at specific row and col
    public boolean squareIsEmpty(int row, int col) {
        return this.board[row][col] == ' ';
    }

    //Check for an X-shape or a +shape of k length
    public boolean wins(char symbol) {
    	for (int row=0; row< boardSize; row++) {
    		for (int col=0; col< boardSize; col++) {
    			if (this.board[row][col] == symbol) {
    				int cross = winCross(row, col, symbol);
    				int plus = winPlus(row, col, symbol);
    				
    				if (cross >= lengthToWin || plus >= lengthToWin) {
    					return true;
    				}
    			}
    		}
    	}

        
        return false; // No winning shape found
    }
    
    //checks for X shape and returns length
    private int winCross(int row, int col, char symbol) {
    	int topRight=0;
    	int topLeft=0;
    	int botRight=0;
    	int botLeft=0;
    	
    	//top right
    	int numRow = row +1;
    	int numCol = col +1;
    	while (checkBoundary(numRow, numCol, symbol)) {
    		numRow++;
    		numCol++;
    		topRight++;
    	}
    	
    	//top left
    	numRow = row+1;
    	numCol = col-1;
    	while (checkBoundary(numRow, numCol, symbol)) {
    		numRow++;
    		numCol--;
    		topLeft++;
    	}
    	
    	//bottom right
    	numRow = row-1;
    	numCol = col+1;
    	while (checkBoundary(numRow, numCol, symbol)) {
    		numRow--;
    		numCol++;
    		botRight++;
    	}
    	
    	//bottom left
    	numRow = row-1;
    	numCol = col-1;
    	while (checkBoundary(numRow, numCol, symbol)) {
    		numRow--;
    		numCol--;
    		botLeft++;
    	}
    	
    	if (topRight ==0 || topLeft ==0 || botRight ==0 || botLeft ==0) {
    		return 0;
    	}
    	return topRight+topLeft+botRight+botLeft;
    	
    }
    
    //checks for Plus shape and return length
    private int winPlus(int row, int col, char symbol) {
    	int right=0;
    	int left=0;
    	int bot=0;
    	int top=0;
    	
    	// top
    	int numRow = row+1;
    	int numCol =col;
    	while(checkBoundary(numRow, numCol, symbol)) {
    		numRow++;
    		top++;
    	}
    	
    	// bottom
    	numRow = row-1;
    	numCol = col;
    	while(checkBoundary(numRow, numCol, symbol)) {
    		numRow--;
    		bot++;
    	}
    	
    	//right
    	numRow =row;
    	numCol = col +1;
    	while(checkBoundary(numRow, numCol, symbol)) {
    		numCol++;
    		right++;
    	}
    	
    	numRow = row;
    	numCol = col-1;
    	while(checkBoundary(numRow, numCol, symbol)) {
    		numCol--;
    		left++;
    	}
    	if (top ==0 || bot ==0 || right ==0 || left ==0) {
    		return 0;
    	}
    	return top+bot+right+left;
    	
    }
    
    //Checks the boundary of the row and col that the symbol will be placed.
    private boolean checkBoundary(int row, int col, char symbol ){
    	if (row >= 0 && col >= 0 && row < boardSize  && col < boardSize  && this.board[row][col] == symbol) {
    		return true;
    	}
    	return false;
    }

    //Checks if the game is a draw
    public boolean isDraw() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        return !wins('X') && !wins('O');
    }

    //Returns different int values depending the outcome of the game.
    public int evalBoard() {
        if (wins('O')) {
            return 3;
        }
        if (wins('X')) {
            return 0;
        }
        if (isDraw()) {
            return 2;
        }
        return 1;
    }

    //converts the 2D character board into its string representation
    private String getStringRepresentation() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                builder.append(board[i][j]);
            }
        }
        return builder.toString();
    }
}
