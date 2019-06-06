package controller;

import java.awt.Component;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

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
	 * Message output to the user if the file fails to load.
	 */
	private static final String FILE_LOAD_ERROR_MESSAGE = "Failed to load project file.";

	/**
	 * Message output to the user if the file fails to save.
	 */
	private static final String FILE_SAVE_ERROR_MESSAGE = "Failed to save project file.";

	/**
	 * Message output to the user if the file saves successfully.
	 */
	private static final String FILE_SAVE_SUCCESS_MESSAGE = "File saved.";

	/**
	 * Text that appears on file chooser accept button when saving.
	 */
	private static final String SAVE_FILE_BUTTON_TEXT = "Save";

	/**
	 * Text that appears on file chooser accept button when loading.
	 */
	private static final String LOAD_FILE_BUTTON_TEXT = "Load";

	/**
	 * Return value condition if the operation was successful.
	 */
	public static final int SUCCESS = 1;

	/**
	 * Return value condition if the operation failed.
	 */
	public static final int ERROR = 0;

	/**
	 * Holds the user entered information. Basic and Item list info.
	 */
	protected Project myProject;

	/**
	 * Dialogue to prompt user to input a file location.
	 */
	private JFileChooser myChooser;

	/**
	 * Creates a project load controller to manage import/export/load/save/new
	 * functionality.
	 * 
	 * @param theProject
	 *            the project to manage.
	 * @author Eric
	 */
	public ProjectLoadController() {
		this.myProject = new Project();
		this.myChooser = new JFileChooser("./SavedProjects/");
	}

	/**
	 * Loads an existing project into the application. Will prompt the user for a
	 * load path.
	 * 
	 * @return ProjectLoadController.SUCCESS or ProjectLoadController.ERROR integer.
	 *         Will return SUCESS if file saved, ERROR otherwise.
	 * @author Eric
	 */
	public int loadProject(final Component theParentComponent) {
		// filechooser, find serialized instance, load.
		myChooser.setApproveButtonText(LOAD_FILE_BUTTON_TEXT);
		final int returnValue = myChooser.showOpenDialog(theParentComponent);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			try {
				final File saveFile = myChooser.getSelectedFile();
				final FileInputStream saveFileInput = new FileInputStream(saveFile);
				final ObjectInputStream saveFileInputObjStream = new ObjectInputStream(
						saveFileInput);
				myProject = (Project) saveFileInputObjStream.readObject();
				saveFileInputObjStream.close();
				saveFileInput.close();
				return SUCCESS;
			} catch (final IOException theException) {
				JOptionPane.showMessageDialog(theParentComponent, FILE_LOAD_ERROR_MESSAGE);
				return ERROR; // the file didn't load correct or wasn't found.
			} catch (final ClassNotFoundException theException) {
				JOptionPane.showMessageDialog(theParentComponent, FILE_LOAD_ERROR_MESSAGE);
				return ERROR; // something spooky happened...
			}
		} else {
			return ERROR; // user decided to cancel the window.
		}
	}

	/**
	 * Saves the existing project to a location specified by the user. Will prompt
	 * the user for a save path.
	 * 
	 * @return ProjectLoadController.SUCCESS or ProjectLoadController.ERROR integer.
	 *         Will return SUCCESS if file saved, ERROR otherwise.
	 * 
	 * @author Eric
	 */
	public int saveProject(final Component theParentComponent) {
		// export file as serialized to path
		myChooser.setApproveButtonText(SAVE_FILE_BUTTON_TEXT);
		final int returnValue = myChooser.showOpenDialog(theParentComponent);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			try {
				final File saveFile = myChooser.getSelectedFile();
				final FileOutputStream saveFileOutput = new FileOutputStream(saveFile);
				final ObjectOutputStream saveFileOutputObjStream = new ObjectOutputStream(
						saveFileOutput);
				saveFileOutputObjStream.writeObject(myProject);
				saveFileOutputObjStream.close();
				saveFileOutput.close();
				JOptionPane.showMessageDialog(theParentComponent, FILE_SAVE_SUCCESS_MESSAGE);
				return SUCCESS;
			} catch (final IOException theException) {
				JOptionPane.showMessageDialog(theParentComponent, FILE_SAVE_ERROR_MESSAGE);
				return ERROR; // the file didn't load correct or wasn't found.
			}
		} else {
			return ERROR; // user decided to cancel the window.
		}
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
