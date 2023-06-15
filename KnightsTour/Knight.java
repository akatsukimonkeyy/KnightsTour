package arrays2d.KnightsTour;

import java.util.ArrayList;

public class Knight {
	private Square currentSquare;
	private Square startingSquare;
	private boolean[][] board;


	/**
	 * Creates a Knight with board of size rows x columns.
	 * Sets the value of board to true in the Square represented
	 * by s. Sets all other board values to false.
	 * Sets currentSquare and startingSquare to s.
	 * @param s the starting Square for this Knight
	 * @param rows the number of rows in this Knight's board
	 * @param cols the number of columns in this Knight's board
	 */
	public Knight(Square s, int rows, int cols) {
		startingSquare = s;
		currentSquare = s;
		board = new boolean[rows][cols];
		this.clearBoard();
		board[s.getRow()][s.getColumn()] = true;
	}
	
	/**
	 * Returns this Knight's current Square.
	 * @return this Knight's current Square.
	 */
	public Square getCurrentSquare() {
		return currentSquare;
	}

	/**
	 * Returns this Knight's starting Square.
	 * @return this Knight's starting Square.
	 */
	public Square getStartingSquare() {
		return startingSquare;
	}
	public void setStartingSquare(Square s){
		startingSquare = s;
	}
	public void setCurrentSquare(Square s){
		this.currentSquare = s;
	}


	/**
	 * Returns this Knight's board.
	 * @return this Knight's board.
	 */
	public boolean[][] getBoard() {
		return board;
	}

	/**
	 * Returns a list of Squares representing a Knights Tour for this Knight.
	 * @return a list of Squares representing a Knights Tour for this Knight
	 */
	public ArrayList<Square> solve() {
		ArrayList<Square> sequence = new ArrayList<Square>();
		
		int pos = 1;
		sequence.add(currentSquare);
		do {
			board[currentSquare.getRow()][currentSquare.getColumn()] = true;
			ArrayList<Square> possible = getPossibleSquares();
			if (possible.isEmpty()) {
				sequence.clear();
				sequence.add(startingSquare);
				pos = 1;
				currentSquare = startingSquare;
				clearBoard();
			}
			else {
				Square best = getBestSquare(possible);
				sequence.add(best);
				currentSquare = best;
				pos++;
			}			
		} while (pos < board.length*board[0].length);		
		System.out.println(currentSquare + "," + startingSquare);
		return sequence;
	}

	/**
	 * Determines if starting Square is reachable from current Square.
	 * @return true if starting Square is reachable from current Square, false otherwise
	 */
	public boolean startIsReachableFromCurrent() {
		ArrayList<Square> squarelist = new ArrayList<Square>();
		squarelist = this.getPossibleSquares();
		boolean result  = false;
		for(int i = 0; i < squarelist.size(); i++){
			if(squarelist.get(i).getRow() == startingSquare.getRow() && squarelist.get(i).getColumn() == startingSquare.getColumn())
				result = true;
		}
		return result;
	}
	
	/**
	 * Returns a Square with the smallest score in possible.
	 * If several Squares in possible have the same lowest score,
	 * one of these Squares is selected at random and returned.
	 * @param possible the list of Squares
	 * @return a Square with the smallest score in possible
	 */
	public Square getBestSquare(ArrayList<Square> possible) {
		//GETS THE LEAST SCORE AND ASSIGNS BESTSQUARE TO THAT

		Square bestSquare = possible.get(0);
		for(int i = 0; i < possible.size() - 1; i++) {
			//System.out.println("in get best square " + i);
			if (possible.get(i + 1).getScore() < bestSquare.getScore()) {
				bestSquare = possible.get(i + 1);
			}
		}
		return bestSquare;
	}

	/**
	 * Sets all values in this Knight's board to false.
	 */
	public void clearBoard() {
		for(int r = 0; r < board.length; r++){
			for(int c = 0; c < board[r].length; c++){
				board[r][c] = false;
			}
		}
	}

	/**
	 * Returns a list of all Squares that are within one knight move of
	 * this Knight's current Square.
	 * Each Square in the returned list has been given a score representing
	 * the number of unvisited Squares that can be reached (with a knight move) from that Square.
	 * @return a list of all Squares that are within one knight move of
	 * this Knight's current Square
	 */
	public ArrayList<Square> getPossibleSquares() {
		int x = currentSquare.getColumn();
		int y = currentSquare.getRow();
		ArrayList<Square> squareList = new ArrayList<Square>();

		//ALL POSSIBLE COMBINATIONS

		if(this.isValid((y - 2), (x + 1))){
			Square s = new Square((y - 2), (x + 1), this.getScoreOfSquare((y-2), (x + 1)));
			squareList.add(s);
		}
		if(this.isValid((y - 2), (x - 1))){
			Square s1 = new Square((y - 2), (x - 1), this.getScoreOfSquare((y-2), (x - 1)));
			squareList.add(s1);
		}
		if(this.isValid((y + 1), (x + 2))){
			Square s2 = new Square((y + 1), (x + 2), this.getScoreOfSquare((y+1), (x + 2)));
			squareList.add(s2);
		}
		if(this.isValid((y + 1), (x - 2))){
			Square s3 = new Square((y + 1), (x - 2), this.getScoreOfSquare((y + 1), (x - 2)));
			squareList.add(s3);
		}
		if(this.isValid((y + 2), (x + 1))){
			Square s4 = new Square((y + 2), (x + 1), this.getScoreOfSquare((y+2), (x + 1)));
			squareList.add(s4);
		}
		if(this.isValid((y + 2), (x - 1))){
			Square s5 = new Square((y + 2), (x - 1), this.getScoreOfSquare((y+2), (x - 1)));
			squareList.add(s5);
		}
		if(this.isValid((y - 1), (x + 2))){
			Square s6 = new Square((y - 1), (x + 2), this.getScoreOfSquare((y-1), (x + 2)));
			squareList.add(s6);
		}
		if(this.isValid((y - 1), (x - 2))){
			Square s7 = new Square((y - 1), (x - 2), this.getScoreOfSquare((y-1), (x - 2)));
			squareList.add(s7);
		}

		return squareList;
	}

	/**
	 * Returns the number of unvisited Squares that can be reached (with a knight move) from the Square at row, col.
	 * @param y the row
	 * @param x the column
	 * @return the number of unvisited Squares that can be reached (with a knight move) from the Square at row, col
	 */
	public int getScoreOfSquare(int y, int x){


		int count = 0;

		if(this.isValid((y - 2), (x + 1))){
			count++;
		}
		if(this.isValid((y - 2), (x - 1))){
			count++;
		}
		if(this.isValid((y + 1), (x + 2))){
			count++;
		}
		if(this.isValid((y + 1), (x - 2))){
			count++;
		}
		if(this.isValid((y + 2), (x + 1))){
			count++;
		}
		if(this.isValid((y + 2), (x - 1))){
			count++;
		}
		if(this.isValid((y - 1), (x + 2))){
			count++;
		}
		if(this.isValid((y - 1), (x - 2))){
			count++;
		}
		return count;
	}

	/**
	 * Returns true if the square at row r, column c is in this Knight's board; returns false otherwise.
	 * @param r the row
	 * @param c the column
	 * @return true if the square at row r, column c is in this Knight's board; false otherwise
	 */
	public boolean isValid(int r, int c) {
		//MUST BE IN RANGE
		if((r < 0 || r > 7 || c < 0 || c > 7) )
			return false;
		else if(board[r][c] == true)
			return false;
		return true;
	}	
}
