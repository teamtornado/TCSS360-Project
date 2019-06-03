package gui;

import javax.swing.JPanel;

import controller.ProjectLoadController;
import controller.ProjectViewController;

/**
 * This class displays the contents of the project and allows the user to export
 * the project.
 * 
 * @author Sharanjit
 *
 */
public class ProjectViewer extends JPanel {

	/**
	 * Auto Generated Serial.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Allows for viewing the user's project contents.
	 */
	private ProjectViewController myViewer;

	/**
	 * Allows for loading, saving, and creating projects.
	 */
	private ProjectLoadController myLoader;

	public ProjectViewer(final ProjectViewController theViewer,
			final ProjectLoadController theLoader) {
		super();
		this.myViewer = theViewer;
		this.myLoader = theLoader;
	}

}
