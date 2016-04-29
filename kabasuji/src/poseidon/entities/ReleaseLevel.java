package poseidon.entities;

import java.util.Set;

import poseidon.entities.Board;
import poseidon.entities.Bullpen;
import poseidon.entities.LevelModel;
import poseidon.entities.Square;

/**
 *  Implementation of LevelModel for Release levels in Kabasuji.
 *  
 *  
 * @author Alex Titus
 * @author Natalia
 */
public class ReleaseLevel extends LevelModel{
	/** The number of moves to start the level with. */
	int allottedMoves;
	/** The number of moves remaining in this level. */
	int movesRemaining;
	/** Stores of numbers collected during gameplay. */
	Set<Integer> redNumbers, greenNumbers, yellownumbers;
	
	
	/**
	 *  Constructor.
	 *  
	 *  @param allottedMoves  the maximum number of moves allowed
	 *  @param levelName  the displayed name of this level
	 *  @param bullpen  the bullpen used in this level
	 *  @param infinite  the infinite bullpen
	 *  @param board  the board used in this level
	 *  @param isCustom  indicator whether level is custom-made by user
	 */
	public ReleaseLevel(int allottedMoves, String levelName, Bullpen bullpen, Bullpen infinite, Board board, Boolean isCustom){
		super(bullpen, infinite, board, RELEASE, levelName, isCustom);
		this.allottedMoves = allottedMoves;
		
		initialize();
	}
	
	void markNumber(int color, int number, Square square) {	 //Not sure
		//TODO: Change return value
	}
	
	void resetCollected () {
		//TODO: Change return value
	}
	
	
	/**
	 *  Decreases the number of moves remaining by 1.
	 */
	@Override
	public void decrementLimit() {
		movesRemaining--;
	}
	
	
	/**
	 *  Decreases the number of moves remaining by 1.
	 */
	@Override
	public void incrementLimit() {
		movesRemaining++;
	}
	
	
	/**
	 *  Sets the allotted move limit.
	 *  
	 *  @param newLimit  the new limit
	 */
	@Override
	public void setLimit(int newLimit)
	{
		allottedMoves = newLimit;
	}
	
	
	/**
	 *  TODO ReleaseLevel.initialize() finished?
	 */
	void initialize() {
		movesRemaining = allottedMoves;
	}
	
	/**
	 * Checks whether the player has achieved a perfect score.
	 * 
	 * @return  indicator whether game has been completed
	 */
	Boolean hasWon() {
		if (calculateScore() == 3) {return true;}
		return false;
	}

	
	/** @return  the number of moves remaining */
	@Override
	public int getLimit()
	{
		return movesRemaining;
	}
	

	/**
	 * Checks whether the player is eligible to move to the next level.
	 */
	Boolean hasPassed() {
		if(calculateScore() > 0) { return true; }
		return false;
	}

	/**
	 * Checks the amount of stars the player has reached in the game.
	 * 
	 */
	int calculateScore() {
		int stars = 0;
		boolean red, yellow, green;
		red = trackRelease(1);
		yellow = trackRelease(2);
		green = trackRelease(3);
		
		if(red) {stars+=1;}
		if(yellow) {stars+=1;}
		if(green) {stars+=1;}
		
		return stars;
	}
	
	
	/**
	 * Helper function that determines whether an entire set of a specific color was covered.
	 * @param color
	 * @return
	 */
	private boolean trackRelease (int color) {
		boolean [] checkSet = new boolean[6];
		boolean passed = true;
		Square [] [] playArea = new Square [board.getRows()] [board.getCols()];
		for (int i=0; i<board.getRows();i++) {
			for (int j=0; j<board.getCols();j++) {
				if (playArea[i][j].isFilled() && playArea[i][j] instanceof ReleaseSquare ) {
					if(playArea[i][j].getReleaseNumber().getColor() == color) {
						checkSet[playArea[i][j].getReleaseNumber().getNumber()] = true;
					}
					
				}
			}
		}
		for (int k=0;k<checkSet.length;k++) {
			if (checkSet[k] == false) { passed = false; }
		}
		
		return passed;
	}

	@Override
	void reset()
	{
		// TODO Auto-generated method stub
		
	}
}
