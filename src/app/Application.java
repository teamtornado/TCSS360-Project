package app;

/**
 * @author Eric, Minh, Sharajit, Curran
 * 
 * Main class for this application. Currently does some simple setup
 * for the various GUI elements current operable.
 */
import java.io.FileNotFoundException;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.metal.MetalLookAndFeel;

import controller.GUIController;
/**
 * Entry point for the UI. Sets up the controllers and starts the application.
 * 
 */
public class Application {
	/**
	 * Runs the application - currently does mostly simple setup.
	 * 
	 * @param theArgs
	 *            The console arguments. These are not used within the code.
	 * @throws FileNotFoundException
	 *             if the schema database is not found.
	 * @author Also everyone
	 */
	public static void main(String[] theArgs) throws FileNotFoundException {
		try {
			UIManager.setLookAndFeel(new MetalLookAndFeel());
		} catch (final UnsupportedLookAndFeelException theException) {
			theException.printStackTrace();
		}

		final GUIController applicationGuiController = new GUIController();
		applicationGuiController.start();
	}

}