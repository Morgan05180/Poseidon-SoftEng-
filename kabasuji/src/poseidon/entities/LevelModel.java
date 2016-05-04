package poseidon.entities;

import poseidon.player.view.LevelView;

/**
 *  The base class of a Kabasuji level.
 *  
 *  Actual levels are implemented as subclasses of this class.
 *  
 * @author Natalia
 * @author Alex Titus
 */
public abstract class LevelModel {
	
	/** The number used to represent Puzzle levels. */
	public static final int PUZZLE = 1;
	/** The number used to represent Lightning levels. */
	public static final int LIGHTNING = 2;
	/** The number used to represent Release levels. */
	public static final int RELEASE = 3;
	
	/** The name of the level. */
	String levelName;
	/** The container for pieces used in the Kabasuji Level Builder application. */
	Bullpen infiniteBullpen;
	/** The container for pieces not on the board. */
	Bullpen playableBullpen;
	/** Indicates the type of game. */
	int gameMode;
	/** The player's current score on the level, from 0 to 3, represented as stars. */
	int score; 
	/** The Kabasuji game board. */
	Board board;
	/** Indicates if this level is built-in or user-created. */
	Boolean isCustom;
	/** Indicates if this level has been added to the player. */
	Boolean isAddedToPlayer;
	
	
	/**
	 *  Constructor.
	 *  
	 *  @param bullpen  the model of this level's bullpen
	 *  @param infinite  the container for pieces in the Level Builder
	 *  @param board  the model of this level's board
	 *  @param gameMode  the type of level this is
	 *  @param levelName  the name of this level
	 *  @param isCustom  true if the level is user-created
	 *  @param isAddedToPlayer  true if the level is in the LevelPlayer
	 */
	public LevelModel (Bullpen bullpen, Bullpen infinite, Board board, int gameMode,
			String levelName, Boolean isCustom, Boolean isAddedToPlayer) {
		this.levelName = levelName;
		this.playableBullpen = bullpen;
		this.infiniteBullpen = infinite;
		this.gameMode = gameMode;
		this.board = board;
		this.isCustom = isCustom;
		this.isAddedToPlayer = isAddedToPlayer;
	}
	
	/** 
	 *  Start the level in a game-type-specific way.
	 *  
	 *  Given the rendering object so that if the implementing level needs
	 *  to create game-type-specific controllers that can update the display
	 *  they (the controllers) can do so.
	 * 
	 *  @param view  the rendering object
	 */
	public abstract void initialize(LevelView view);
	
	/** 
	 *  Start the level in the builder.
	 *  
	 *  Should set the moves in such a way that moves can always be made.
	 * 
	 *  @param view  the rendering object
	 */
	public abstract void builderInitialize();
	
	/** @return  Whether the player reached 3 stars. */
	public abstract Boolean hasWon();
	
	/** @return  Whether the player can move to the next level. */
	abstract Boolean hasPassed();
	
	/** @return  The current score on this level. */
	abstract int calculateScore();

	/** 
	 *  Provides the game-type-specific limit for play.
	 *  
	 *  Override this in level subclasses to provide the current remaining move or time limit.
	 *  
	 *  @return  The current limit for the level subclass.
	 */
	public abstract int getLimit();
	
	
	/** 
	 *  Provides the game-type-specific allocated limit for play.
	 *  
	 *  Override this in level subclasses to provide the allotted move or time limit.
	 *  
	 *  @return  The allotted move or time limit for this level.
	 */
	public abstract int getMaxLimit();
	
	
	/**
	 *  Decreases the moves remaining by 1, if applicable.
	 *  
	 *  Override this in level subclasses to decrease the remaining move limit, if it exists.
	 */
	public abstract void decrementLimit();
	
	
	/**
	 *  Increase the moves remaining by 1, if applicable.
	 *  
	 *  Override this in level subclasses to increase the remaing move limit, if it exists.
	 */
	public abstract void incrementLimit();
	
	
	/**
	 *  Sets the game-type-specific allotted limit.
	 *  
	 *  Override this in level subclasses to set the maximum move or time limit.
	 *  @param newLimit  the new limit
	 */
	public abstract void setMaxLimit(int newLimit);
	
	
	/**
	 * Updates the score of the game
	 */
	void updateScore() {
		score = calculateScore();
	}
				/***********************
				 *  Getters & Setters  *
				 ***********************/
	/** @return  The name of the level. */
	public String getLevelName()
	{
		return levelName;
	}
	/** 
	 *  Set the level name.
	 *  
	 *  @param levelName  the new level name
	 */
	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}
	/** @return  The state of the level's infinite bullpen. */
	public Bullpen getInfiniteBullpen()
	{
		return infiniteBullpen;
	}
	/** @return  The state of the level's playable bullpen. */
	public Bullpen getPlayableBullpen()
	{
		return playableBullpen;
	}
	/** @return  The current score on this level (not all-time score). */
	public int getScore()
	{
		return score;
	}
	/** @return  Number indicating what kind of level this is. */
	public int getGameMode()
	{
		return gameMode;
	}
	/** @return  The state of the level's board. */
	public Board getBoard()
	{
		return board;
	}
	/** @return  Indicator of whether this level is custom-made. */
	public Boolean getIsCustom() {
		return isCustom;
	}
	/** 
	 *  Set the custom level flag.
	 *  
	 *  @param isCustom  the new status
	 */
	public void setIsCustom(Boolean isCustom) {
		this.isCustom = isCustom;
	}
	/** @return  Indicator of whether this level has been added to the player. */
	public Boolean getIsAddedToPlayer() {
		return isAddedToPlayer;
	}
	/**
	 *  Set the added level flag.
	 *  
	 *  @param isAddedToPlayer  the new status
	 */
	public void setIsAddedToPlayer(Boolean isAddedToPlayer) {
		this.isAddedToPlayer = isAddedToPlayer;
	}
	/**
	 *  Sets the infinite bullpen.
	 *  
	 *  @param bullpen  the new bullpen
	 */
	public void setInfiniteBullpen(Bullpen bullpen)
	{
		this.infiniteBullpen = bullpen;
	}
	
}
