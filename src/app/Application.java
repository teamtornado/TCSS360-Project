package app;

/**
 * @author Eric, Minh, Sharajit, Curran
 * 
 * Main class for this application. Currently does some simple setup
 * for the various GUI elements current operable.
 */
import java.io.FileNotFoundException;
import java.util.Currency;
import java.util.Locale;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.metal.MetalLookAndFeel;

import controller.GUIController;
import controller.ProjectEditController;
import controller.ProjectLoadController;
import controller.ProjectViewController;
import controller.SchemaController;
import model.Project;

public class Application {

	public static final String SCHEMA_DATABASE_LOCATION = "SchemaData.txt";

	/**
	 * Runs the application - currently does mostly simple setup.
	 * 
	 * @param theArgs
	 *            The console arguments. These are not used within the code.
	 * @throws FileNotFoundException
	 * @author My dog
	 */
	public static void main(String[] theArgs) throws FileNotFoundException {
		try {
			UIManager.setLookAndFeel(new MetalLookAndFeel());
		} catch (final UnsupportedLookAndFeelException theException) {
			theException.printStackTrace();
		}

		// This dummy project will get overwritten either when the user chooses to view
		// a project, or when they choose to create a new project.
//		final Project dummyProject = new Project("Dummy", "Dummy Description",
//				Currency.getInstance(Locale.US), "Dummy Value");

		// Dole out the controllers to whoever needs them.
//		final ProjectEditController editor = new ProjectEditController(dummyProject);
//		final ProjectViewController viewer = new ProjectViewController(dummyProject);
//		final ProjectLoadController loader = new ProjectLoadController(editor, viewer);
//		final SchemaController rules = new SchemaController(SCHEMA_DATABASE_LOCATION);
		final GUIController applicationGuiController = new GUIController();
		applicationGuiController.start();
	}

}