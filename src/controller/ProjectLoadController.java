package controller;

import java.util.Currency;
import java.util.Locale;

import model.Project;

public class ProjectLoadController {

	protected Project myProject;

	public ProjectLoadController(final Project theProject) {
		this.myProject = theProject;
	}

	public void loadProject(final String theFileLocation) {
		// filechooser, find serialized instance, load.
	}

	public void saveProjectToLocation(final String theSaveLocation) {
		// export file as serialized to path
	}

	public void createNewProject() {
		// At first, all the data is blank. Will be altered later by the edit
		// controller.
		final Project blankProject = new Project("...", "...", Currency.getInstance(Locale.US),
				"...");
		this.myProject = blankProject;
	}
}
