package player;

import java.util.Set;

public class ReleaseLevel extends Level{
	int allottedPieces, piecesUsed;
	Set redNumbers, greenNumbers, yellownumbers;
	ReleaseLevel(int allottedPieces, Board board, Bullpen bullpen, String name, Boolean isCustom){
		super(bullpen, board, RELEASE, name, isCustom);
		this.allottedPieces = allottedPieces;
	}
	
	void markNumber(int color, int number, Square square) {	 //Not sure
		//TODO: Change return value
	}
	
	void resetCollected () {
		//TODO: Change return value
	}
	
	void decrementPieces() {
		//TODO: Change return value
	}
	
	void initialize() {
		//TODO: Change return value
	}
	
	Boolean hasWon() {
		return false;							//TODO: Change return value
	}
	
	void reset() {
		//TODO: Change return value
	}
}
