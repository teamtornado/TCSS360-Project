package controller;

import model.Project;

public class ProjectLoadAndSaveController {

	private ProjectDataEditController myEditor;
	private ProjectDataViewController myViewer;

	public ProjectLoadAndSaveController(
			final ProjectDataEditController theEditor,
			final ProjectDataViewController theViewer) {
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
}
