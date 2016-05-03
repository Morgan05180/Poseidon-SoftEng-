package poseidon.builder.view;

import javax.swing.JPanel;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;
import java.io.IOException;
import java.text.NumberFormat;

import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import poseidon.builder.controller.BackBuilderController;
import poseidon.builder.controller.ColSizeController;
import poseidon.builder.controller.HintSelectModeController;
import poseidon.builder.controller.ToEditPlayableBullpenController;
import poseidon.builder.controller.LimitController;
import poseidon.builder.controller.MakeReleaseSquareController;
import poseidon.builder.controller.RedoController;
import poseidon.builder.controller.ResetBuilderController;
import poseidon.builder.controller.RowSizeController;
import poseidon.builder.controller.SaveLevelController;
import poseidon.builder.controller.UndoController;
import poseidon.common.controller.BoardController;
import poseidon.common.controller.BullpenController;
import poseidon.common.controller.HorizontalFlipController;
import poseidon.common.controller.RotateCCWController;
import poseidon.common.controller.RotateCWController;
import poseidon.common.controller.VerticalFlipController;
import poseidon.common.view.BoardView;
import poseidon.common.view.BullpenView;
import poseidon.common.view.ILevelView;
import poseidon.entities.Board;
import poseidon.entities.LevelBuilderModel;
import poseidon.entities.LevelModel;
import poseidon.entities.RedoManager;
import poseidon.entities.UndoManager;

import javax.swing.ScrollPaneConstants;
import javax.swing.text.NumberFormatter;

/**
 *  Creates the GUI used to create Kabasuji levels.
 *  
 *  @author Alex Titus
 */
public class BuilderView extends JPanel implements IBuilderScreen, ILevelView
{
	/** The top-level GUI object. */
	LevelBuilderView application;
	/** The top-level entity object, representing the application's state. */
	LevelBuilderModel topmodel;
	/** The state of the level under construction. */
	LevelModel model;
	/** The GUI game board being built. */
	BoardView board;
	/** To hold GUI infinite bullpen. */
	JScrollPane bullpenContainer;
	/** The GUI infinite bullpen, to help build the board and for hints. */
	BullpenView bullpen;
	/** To save the level to a file. */
	JButton saveButton;
	/** To undo change. */
	JButton undoButton;
	/** To redo change. */
	JButton redoButton;
	/** To add hint to board. */
	JToggleButton addHintButton;
	/** To make changes to the playable bullpen. */
	JButton editPlayBullpenButton;
	/** To reset board. */
	JButton resetButton;
	/** To flip piece vertically. */
	JButton verFlipButton;
	/** To flip piece horizontally. */
	JButton horFlipButton;
	/** To rotate a piece clock-wise. */
	JButton rotateCWButton;
	/** To rotate a piece counter-clock-wise. */
	JButton rotateCCWButton;
	/** To return to the main menu (LevelBuilderView). */
	JButton quitButton;
	/** To change the board's width. */
	JFormattedTextField colSizeInput;
	/** To change the board's height. */
	JFormattedTextField rowSizeInput;
	/** To change the level's limit. */
	JFormattedTextField limitInput;
	/** The name of this level. */
	JLabel title;
	/** Label for the limit input field. */
	JLabel limitLabel;
	/** Icons for the rotate buttons*/
	Image rotateCW, rotateCCW, icon;
	/** To add a ReleaseSquare. */
	JToggleButton addReleaseSquare;
	/** To select the color of a ReleaseSquare. */
	JComboBox<String> colorSelector;
	/** To select the number of a ReleaseSquare. */
	JComboBox<Integer> numberSelector;
	/** The logo. */
	JLabel poseidon;


	/**
	 *  Constructor.
	 *  
	 *  @param model  the top-level model of the application
	 *  @param view  the top-level GUI of the application
	 */
	public BuilderView(LevelBuilderModel model, LevelBuilderView view)
	{
		this.topmodel = model;
		application = view;
		this.model = topmodel.getBuildingLevel();
		
		initialize();
	}
	
	
	/**
	 *  Creates and adds the view objects for each of the game components.
	 */
	void initialize()
	{
		setLayout(null);
		setBackground(new Color(0, 191, 255));
		
		board = new BoardView(model.getBoard());
		board.setBounds(160, 250, 361, 361);
		add(board);
		
		bullpen = new BullpenView(model.getInfiniteBullpen());
		bullpenContainer = new JScrollPane(bullpen);
		bullpenContainer.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		bullpenContainer.setBounds(160, 80, 360, 80);
		add(bullpenContainer);
		
		// Add board and bullpen controllers
		BoardController boardController = new BoardController(application, model, this);
		board.addMouseListener(boardController);
		board.addMouseMotionListener(boardController);
		bullpen.addMouseListener(new BullpenController(model.getInfiniteBullpen(), bullpen, board));
		
		rotateCCWButton = new JButton("");
		rotateCCWButton.setBounds(540, 160, 45, 45);
		rotateCCWButton.addActionListener(new RotateCCWController(bullpen));
		add(rotateCCWButton);
		
		horFlipButton = new JButton("<html><center>Horizontal<br>Flip</center></html>");
		horFlipButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		horFlipButton.setBounds(540, 100, 130, 50);
		horFlipButton.addActionListener(new HorizontalFlipController(bullpen));
		add(horFlipButton);
		
		verFlipButton = new JButton("<html><center>Vertical<br>Flip</center></html>");
		verFlipButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		verFlipButton.setBounds(540, 40, 130, 50);
		verFlipButton.addActionListener(new VerticalFlipController(bullpen));
		add(verFlipButton);
		
		rotateCWButton = new JButton("");
		rotateCWButton.setBounds(623, 160, 45, 45);
		rotateCWButton.addActionListener(new RotateCWController(bullpen));
		add(rotateCWButton);
		
		poseidon = new JLabel("");
		poseidon.setBounds(35, 40, 100, 100);
		add(poseidon);
		
		
		try {
			rotateCCW  = ImageIO.read(getClass().getClassLoader().getResource("images/rotateCCW.png"));
			rotateCW  = ImageIO.read(getClass().getClassLoader().getResource("images/rotateCW.png"));
			icon = ImageIO.read(getClass().getClassLoader().getResource("images/Logo.png"));
			rotateCCWButton.setIcon(new ImageIcon(rotateCCW));
			rotateCWButton.setIcon(new ImageIcon(rotateCW));
			poseidon.setIcon(new ImageIcon(icon));
		} catch (IOException e) {
		}
		
		title = new JLabel(model.getLevelName());
		title.setFont(new Font("Lucida Handwriting", Font.PLAIN, 35));
		title.setBounds(176, 22, 310, 45);
		add(title);
		
		// Default limit display for Puzzle and Release levels
		String limitDisplay = "Move Limit:";
		if(this.model.getGameMode() == LevelModel.LIGHTNING)  // If Lightning level
		{
			// Then label limit as time instead of moves
			limitDisplay = "Time Limit:";
		}
		limitLabel = new JLabel(limitDisplay);
		limitLabel.setBackground(Color.WHITE);
		limitLabel.setBounds(555, 220, 105, 35);
		limitLabel.setHorizontalAlignment(SwingConstants.LEFT);
		limitLabel.setFont(new Font("Dialog", Font.PLAIN, 20));
		add(limitLabel);
		
		NumberFormat limitFormat = NumberFormat.getIntegerInstance();
		limitFormat.setMinimumIntegerDigits(1);
		limitFormat.setMaximumIntegerDigits(5);
		NumberFormatter limitFormatter = new NumberFormatter(limitFormat);
		limitFormatter.setMinimum(new Integer(1));
		limitFormatter.setMaximum(new Integer(99999));
		
		limitInput = new JFormattedTextField(limitFormatter);
		limitInput.setValue(model.getMaxLimit());
		limitInput.setBounds(540, 255, 130, 30);
		limitInput.setColumns(10);
		limitInput.addPropertyChangeListener("value", new LimitController(this.getModel()));
		add(limitInput);
		
		addHintButton = new JToggleButton("Hint");
		addHintButton.setFont(new Font("Dialog", Font.PLAIN, 20));
		addHintButton.setBounds(540, 300, 130, 45);
		addHintButton.addItemListener(new HintSelectModeController(this));
		add(addHintButton);
		
		editPlayBullpenButton = new JButton("Choose Pieces");
		editPlayBullpenButton.setFont(new Font("Dialog", Font.PLAIN, 20));
		editPlayBullpenButton.setBounds(240, 180, 200, 50);
		editPlayBullpenButton.addActionListener(new ToEditPlayableBullpenController(application));
		add(editPlayBullpenButton);
		
		undoButton = new JButton("Undo");
		undoButton.setFont(new Font("Dialog", Font.PLAIN, 20));
		undoButton.setBounds(15, 160, 110, 45);
		undoButton.setEnabled(false);  // Initially nothing to undo
		undoButton.addActionListener(new UndoController(this));
		add(undoButton);
		
		redoButton = new JButton("Redo");
		redoButton.setFont(new Font("Dialog", Font.PLAIN, 20));
		redoButton.setBounds(15, 215, 110, 45);
		redoButton.setEnabled(false);  // Initially nothing to redo
		redoButton.addActionListener(new RedoController(this));
		add(redoButton);
		
		JLabel dimensionLabel = new JLabel("Size:");
		dimensionLabel.setFont(new Font("Dialog", Font.PLAIN, 20));
		dimensionLabel.setBounds(40, 280, 55, 30);
		add(dimensionLabel);
		
		NumberFormat boardSizeFormat = NumberFormat.getIntegerInstance();
		boardSizeFormat.setMinimumIntegerDigits(1);
		boardSizeFormat.setMaximumIntegerDigits(2);
		NumberFormatter boardSizeFormatter = new NumberFormatter(boardSizeFormat);
		boardSizeFormatter.setMinimum(new Integer(1));
		boardSizeFormatter.setMaximum(new Integer(Board.MAXCOLS));  // Board is square, so shouldn't matter
		
		rowSizeInput = new JFormattedTextField(boardSizeFormatter);
		rowSizeInput.setValue(Board.MAXROWS);
		rowSizeInput.setToolTipText("Enter new number of rows here.");
		rowSizeInput.setColumns(10);
		rowSizeInput.setBounds(15, 320, 45, 30);
		rowSizeInput.addPropertyChangeListener("value", new RowSizeController(this));
		add(rowSizeInput);
		
		colSizeInput = new JFormattedTextField(boardSizeFormatter);
		colSizeInput.setValue(Board.MAXCOLS);
		colSizeInput.setToolTipText("Enter new number of columns here.");
		colSizeInput.setColumns(10);
		colSizeInput.setBounds(80, 320, 45, 30);
		colSizeInput.addPropertyChangeListener("value", new ColSizeController(this));
		add(colSizeInput);
		
		JLabel xLabel = new JLabel("X");
		xLabel.setHorizontalAlignment(SwingConstants.CENTER);
		xLabel.setBounds(60, 327, 15, 16);
		add(xLabel);
		
		resetButton = new JButton("Reset");
		resetButton.setFont(new Font("Dialog", Font.PLAIN, 20));
		resetButton.setBounds(15, 370, 110, 55);
		resetButton.addActionListener(new ResetBuilderController(topmodel, application));
		add(resetButton);
		
		quitButton = new JButton("Quit");
		quitButton.setFont(new Font("Dialog", Font.PLAIN, 20));
		quitButton.setBounds(15, 440, 110, 55);
		quitButton.addActionListener(new BackBuilderController(topmodel, application));
		add(quitButton);
		
		saveButton = new JButton("Save");
		saveButton.setFont(new Font("Dialog", Font.PLAIN, 20));
		saveButton.setBounds(15, 510, 110, 55);
		saveButton.addActionListener(new SaveLevelController(topmodel, model, application));
		add(saveButton);
		
		if (model.getGameMode() == LevelModel.RELEASE){
			addReleaseSquare = new JToggleButton("<html><center>New Release<br>Square</center></html>");
			addReleaseSquare.setFont(new Font("Dialog", Font.PLAIN, 20));
			addReleaseSquare.setMargin(new Insets(2, 8, 2, 8));
			addReleaseSquare.setBounds(540, 385, 130, 80);
			addReleaseSquare.addItemListener(new MakeReleaseSquareController(this));
			add(addReleaseSquare);
			
			String[] colorModel = {"Red", "Green", "Yellow"};
			colorSelector = new JComboBox<String>(colorModel);
			colorSelector.setFont(new Font("Dialog", Font.PLAIN, 15));
			colorSelector.setBounds(540, 480, 130, 30);
			add(colorSelector);
			
			Integer[] numberModel = {1, 2, 3, 4, 5, 6};
			numberSelector = new JComboBox<Integer>(numberModel);
			numberSelector.setFont(new Font("Dialog", Font.PLAIN, 15));
			numberSelector.setBounds(540, 525, 130, 30);
			add(numberSelector);
		}
		
		

	}

	
	/**
	 *  Updates the display when the model is changed.
	 *  
	 *  @return  Indicator of whether operation completed successfully.
	 */
	@Override
	public Boolean modelUpdated()
	{
		bullpen.modelUpdated();
		board.modelUpdated();
		
		// If there are no moves to undo
		if(UndoManager.instance().isEmpty())
		{
			// Then disable the undo button
			undoButton.setEnabled(false);
		}
		else
		{
			undoButton.setEnabled(true);
		}
		
		// Same for redo button
		if(RedoManager.instance().isEmpty())
		{
			redoButton.setEnabled(false);
		}
		else
		{
			redoButton.setEnabled(true);
		}
		
		// Redraw everything that changed
		repaint();
		
		return true;
	}
	
	
	
				/***********************
				 *  Getters & Setters  *
				 ***********************/
	/** @return  The BoardView object associated with this BuilderView. */
	@Override
	public BoardView getBoard()
	{
		return board;
	}


	/** @return  The BullpenView object (the rendered infinite Bullpen) associated
	 * with this BuilderView. */
	@Override
	public BullpenView getBullpen()
	{
		return bullpen;
	}


	/** @return  The LevelModel object associated with this BuilderView. */
	@Override
	public LevelModel getModel()
	{
		return model;
	}
	
	
	/** @return  The JFormattedTextField responsible for the board's row size. */
	public JFormattedTextField getRowSizeInput()
	{
		return rowSizeInput;
	}
	
	
	/** @return  The JFormattedTextField responsible for the board's column size. */
	public JFormattedTextField getColSizeInput()
	{
		return colSizeInput;
	}
	
	/** @return  The button associated with saving the level. */
	public JButton getSave(){
		return saveButton;
	}
	
	/** @return  The button associated with the undo controller.  */
	public JButton getUndo(){
		return undoButton;
	}
	
	/** @return  The button associated with the redo controller. */
	public JButton getRedo(){
		return redoButton;
	}
	
	/** @return  The button associated with the edit playable bullpen controller. */
	public JButton getSetPlayBull(){
		return editPlayBullpenButton;
	}
	
	/** @return  The button associated with resetting the level. */
	public JButton getResetButton(){
		return resetButton;
	}
	
	/** @return  The combo box associated with the release number's color. */
	public JComboBox<String> getColorSelector(){
		return colorSelector;
	}
	
	/** @return  The combo box associated with the release number's number. */
	public JComboBox<Integer> getNumberSelector(){
		return numberSelector;
	}
	
	
}
