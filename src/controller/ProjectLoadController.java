package controller;

import java.util.Currency;
import java.util.Locale;

import model.Project;

public class ProjectLoadController {

	private ProjectEditController myEditor;
	private ProjectViewController myViewer;

	public ProjectLoadController(final ProjectEditController theEditor,
			final ProjectViewController theViewer) {
		this.myEditor = theEditor;
		this.myViewer = theViewer;
	}

	public void loadProject(final String theFileLocation) {
		myEditor.myProject = null; // set it to something else!
		myViewer.myProject = null;
	}

	public void saveProjectToLocation(final String theSaveLocation) {
		@SuppressWarnings("unused")
		final Project project = myEditor.myProject;
	}

	public void createNewProject() {
		// At first, all the data is blank. Will be altered later by the edit
		// controller.
		final Project blankProject = new Project("...", "...", Currency.getInstance(Locale.US),
				"...");
		myEditor.myProject = blankProject;
		myViewer.myProject = blankProject;
	}
}
