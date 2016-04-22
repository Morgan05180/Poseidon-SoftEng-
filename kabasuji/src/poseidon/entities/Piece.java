package poseidon.entities;
/**
 * Handles Pieces that are placed on the board/bullpen.
 * @author Natalia
 */
public class Piece {
	/** The 6 points that construct a piece*/
	Point [] piece = new Point[6];
	
	/** The container that handles the moving and positioning the piece*/
	PieceContainer container;
	
	Piece(Point[] piece) {
		this.piece = piece;
		this.container = new PieceContainer (this, null, false);
	}
	
	/**Constructor for random pieces*/
	Piece() {
		//TODO figure out how to generate a random pieces
	}

	/**
	 * Finds the Bottom left point of the piece (even if isn't a part of it) and returns it.
	 * 
	 * Note: Private function that helps with rotating the pieces.
	 * @return Point - The Bottom left point of the piece.
	 */
	private Point findBotLeft() {
		
		int botRow = piece[0].getRow();
		int temp;
		
		for (int i=1; i < piece.length; i++) {			//Finds the largest row value
			temp = piece[i].getRow();
			if (temp > botRow) {
				botRow = temp;
			}
		}
		Point botLeft = new Point(botRow,0);			//Returns the point in the 0 col with the highest row
		return botLeft;
	}
	
	/**
	 * Finds the top right point of the piece (even if isn't a part of it) and returns it.
	 * 
	 * Note: Private function that helps with rotating the pieces.
	 * @return Point - The top right point of the piece.
	 */
	private Point findTopRight() {
		
		int rightCol = piece[0].getCol();
		int temp;
		
		for (int i=1; i < piece.length; i++) {			//Finds the largest col value
			temp = piece[i].getCol();
			if (temp > rightCol) {
				rightCol = temp;
			}
		}
		Point topRight = new Point(0, rightCol);			//Returns the point in the 0 row with the highest col
		return topRight;
	}
	
	/**
	 * Rotates the piece Clockwise
	 */
	public void rotateCW() {
		Point newPivotPoint;
		int row, col;		
		newPivotPoint = findBotLeft();				//Finding the bottom left piece as the new pivot
		newPivotPoint.flip();						//Flips the axis on the pivot point
																														
		for (int i=0; i< piece.length; i++) {
			piece[i].flip();						//Flips the axis on the point			
			row = newPivotPoint.getRow() + piece[i].getRow();	//finding the piece's new row/col
			col = newPivotPoint.getCol() - piece[i].getCol();
			piece[i].setRow(row);
			piece[i].setCol(col);
		}
		
	}
	
	/**
	 * Rotates the piece counter Clockwise
	 */
	public void rotateCCW() {
		Point newPivotPoint;
		int row, col;		
		newPivotPoint = findTopRight();				//Finding the top right piece as the new pivot
		newPivotPoint.flip();						//Flips the axis on the pivot point
																														
		for (int i=0; i< piece.length; i++) {
			piece[i].flip();						//Flips the axis on the point			
			row = newPivotPoint.getRow() - piece[i].getRow();	//finding the piece's new row/col
			col = newPivotPoint.getCol() + piece[i].getCol();
			piece[i].setRow(row);
			piece[i].setCol(col);
		}
		
	}

	/**
	 * Horizontally flips the piece
	 */
	public void flipHorizontal() {
		Point newPivotPoint;
		int col;
		
		newPivotPoint = findTopRight();				//Finding the top right piece as the new pivot
		
		for(int i=0; i<piece.length; i++) {
			col = newPivotPoint.getCol() - piece[i].getCol();
			piece[i].setCol(col);
		}
	}
	
	/**
	 *  Vertically flips the piece
	 */
	public void flipVertical() {
		Point newPivotPoint;
		int row;
		
		newPivotPoint = findBotLeft();				//Finding the top right piece as the new pivot
		
		for(int i=0; i<piece.length; i++) {
			row = newPivotPoint.getRow() - piece[i].getRow();
			piece[i].setRow(row);
		}
	}
	
	
	@Override
	/**
	 * Overrides the standard equals() method for Pieces.
	 * 
	 * Note: Works on the premise that equal pieces have an equal order of points. 
	 * Also, disregards the container. Thus, comparing two pieces that are in different locations but "look" the same
	 * would produce true as the answer.
	 */
	public boolean equals(Object o) {
		if(!(o instanceof Piece)) { return false; }
		Piece newPiece = (Piece) o;
		Point[] newArray = newPiece.getPiece();
		for(int i=0; i<6; i++) {
			if (!this.piece[i].equals(newArray[i])){
				return false;
			}
		}
		return true;
	}

				/*********************
				 *  Getter & Setter  *
				 *********************/
	public Point[] getPiece()
	{
		return piece;
	}

	public void setPiece(Point[] piece)
	{
		this.piece = piece;
	}
	
	public PieceContainer getContainer() {
		return this.container;
	}
}
