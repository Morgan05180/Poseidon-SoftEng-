package poseidon.builder.view;

import java.awt.EventQueue;

import poseidon.entities.LevelBuilderModel;

/**
 *  Starts the Kabasuji Level Builder application.
 *  
 *  @author Alex Titus
 */
public class Main
{
	/**
	 * Launch the application.
	 * Starts with the splash screen and then opens to the main menu.
	 * 
	 * @param args  the command line arguments
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Thread.sleep(1000);  // For splash screen
					
					// Create and show application
					LevelBuilderModel model = new LevelBuilderModel();
					LevelBuilderView application = new LevelBuilderView(model);
					application.getFrame().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
