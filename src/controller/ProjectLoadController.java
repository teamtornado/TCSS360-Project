package controller;

import java.util.Currency;
import java.util.Locale;

import model.Project;

/**
 * An instance of this class will manage the import/export/load/save/new
 * functionality required by the application for each project.
 * 
 * @author Eric
 *
 */
public class ProjectLoadController {

	/**
	 * Holds the user entered information. Basic and Item list info.
	 */
	protected Project myProject;

	/**
	 * Creates a project load controller to manage import/export/load/save/new
	 * functionality.
	 * 
	 * @param theProject
	 *            the project to manage.
	 * @author Eric
	 */
	public ProjectLoadController(final Project theProject) {
		this.myProject = theProject;
	}

	/**
	 * Loads an existing project into the application. Will prompt the user for a
	 * load path.
	 * 
	 * @author Eric
	 */
	public void loadProject() {
		// filechooser, find serialized instance, load.
	}

	/**
	 * Saves the existing project to a location specified by the user. Will prompt
	 * the user for a save path.
	 * 
	 * @author Eric
	 */
	public void saveProjectToLocation() {
		// export file as serialized to path
	}

	/**
	 * Creates a new project. WILL RESET THE CURRENT PROJECT WITHOUT ALERTING USER.
	 * 
	 * @author Eric
	 */
	public void createNewProject() {
		if (myProject != null) {
			// Okay to reset since if this is ever called, the project should have already
			// been saved or exported.
			myProject.clearAllItems();
			myProject.clearBasicInformation();
		}
	}
}
