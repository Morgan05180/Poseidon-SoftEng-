package poseidon.entities;

/**
 * Manages squares in the puzzle level.
 * @author Natalia
 *
 */
public class PuzzleSquare extends Square {
	Boolean isHint;
	
	PuzzleSquare(Boolean isFilled) {
		super(isFilled);
		this.isHint = false;					//Set to false as default
	}
	
	void fillSquare() {
		this.isFilled = true;
	}
	
	void makeHint() {
		this.isHint = true;
	}

}
