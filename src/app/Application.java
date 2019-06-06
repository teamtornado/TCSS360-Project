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
import controller.ProjectEditController;
import controller.ProjectLoadController;
import controller.ProjectViewController;
import controller.SchemaController;
import model.Project;

/**
 * Entry point for the UI. Sets up the controllers and starts the app.
 * 
 * @author Everyone, including Unkown
 */
public class Application {

	/**
	 * The location for the rules of the database.
	 */
	public static final String SCHEMA_DATABASE_LOCATION = "SchemaData.txt";

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

		// Controllers need to be pre-loaded with a dummy project before they can run.
		final GUIController applicationGuiController = new GUIController();
		applicationGuiController.start();
	}

}