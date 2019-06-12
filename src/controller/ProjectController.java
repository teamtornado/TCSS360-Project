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

import gui.createpanels.BasicInfoPanel;
import model.Project;

/**
 * An instance of this class will manage the import/export/load/save/new
 * functionality required by the application for each project.
 * 
 * @author Eric
 *
 */
public class ProjectController {

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
	public Project myProject;

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
	public ProjectController() {
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

	/**
	 * Sets the name of this project.
	 * 
	 * @param theName
	 *            the name to set the project name to.
	 * @throws IllegalArgumentException
	 *             if theName is null.
	 * @author Eric
	 */
	public void setName(final String theName) {
		myProject.setName(theName);
	}

	/**
	 * Sets the project description.
	 * 
	 * @param theProjectDescription
	 *            the description to set the project description to.
	 * @throws IllegalArgumentException
	 *             if theProjectDescription is null.
	 * @author Eric
	 */
	public void setDescription(String theProjectDescription) {
		myProject.setProjectDescription(theProjectDescription);
	}

	/**
	 * Sets the budget of the project.
	 * 
	 * @param theBudget
	 *            the budget to set for this project.
	 * @throws IllegalArgumentException
	 *             if theBudget is null.
	 * @author Eric
	 */
	public void setBudget(final double theBudget) {
		myProject.setBudget(theBudget);
	}

	/**
	 * Sets the location of this project.
	 * 
	 * @param theLocation
	 *            the location of this project.
	 * @throws IllegalArgumentException
	 *             if theLocation is null.
	 * @author Eric
	 */
	public void setLocation(final String theLocation) {
		myProject.setLocation(theLocation);
	}

	/**
	 * Adds an item with the given item-type to the project. The fields will be
	 * empty after this call.
	 * 
	 * @param theItemType
	 *            the type to set the new item to.
	 * @throws IllegalArgumentException
	 *             if the itemType is null.
	 * @throws IllegalArgumentException
	 *             if the itemType is a duplicate.
	 * @author Eric
	 */
	public void addItem(final String theItemType) {
		myProject.addItem(theItemType);
	}


	/**
	 * Finds the item that has a matching item-type and adds the following
	 * information to a new field within.
	 * 
	 * @param theItemDestination
	 *            The item to put the new field information within.
	 * @param theFieldName
	 *            the name of the new field.
	 * @param theDescription
	 *            the description of the new field.
	 * @param theValueType
	 *            the value type of the new field.
	 * @param theValue
	 *            the value of the new field.
	 * @throws IllegalArgumentException
	 *             if the given item-type did not have a match.
	 * @author Eric
	 */
	public void addFieldToItem(final String theItemDestination, final String theFieldName,
			final String theDescription, final String theValueType, final String theValue) {
		myProject.addFieldToItem(theItemDestination, theFieldName, theDescription, theValueType,
				theValue);
	}

	/**
	 * Given an item-type, this will remove the matching item-type from the project.
	 * 
	 * @param theItemType
	 *            the item-type to find a match for.
	 * @throws IllegalArgumentException
	 *             if item-type is null.
	 * @throws IllegalArgumentException
	 *             if item-type has no match within the project.
	 * @author Eric
	 */
	public void removeItem(final String theItemType) {
		myProject.removeItem(theItemType);
	}

	/**
	 * Removes the field from the given item-type.
	 * 
	 * @param theItemType
	 *            the item-type to remove from.
	 * @param theFieldName
	 *            the field to remove.
	 * @throws IllegalArgumentException
	 *             if the item-type could not be found.
	 * @author Eric
	 */
	public void removeFieldFromItem(final String theItemType, final String theFieldName) {
		myProject.removeFieldFromItem(theItemType, theFieldName);
	}

	/**
	 * Clears all items from list.
	 * 
	 * @author Eric
	 */
	public void clearAllItems() {
		myProject.clearAllItems();
	}

	/**
	 * Given a basicInfoPanel, will query for the given information, and will set
	 * them within the project
	 * 
	 * @param basicInfoPanel
	 *            the panel to query from.
	 */
	public void setBasicInformation(final BasicInfoPanel basicInfoPanel) {
		String projectName = basicInfoPanel.getProjectName();
		String projectLocation = basicInfoPanel.getProjectLocation();
		Double projectBudget = basicInfoPanel.getProjectBudget();
		String projectDescription = basicInfoPanel.getProjectDescription();
		myProject.setName(projectName);
		myProject.setLocation(projectLocation);
		myProject.setBudget(projectBudget);
		myProject.setProjectDescription(projectDescription);
	}
	


	/**
	 * Returns the user entered name of this project.
	 * 
	 * @return the user entered name of this project.
	 * @author Eric
	 */
	public String getName() {
		return myProject.getName();
	}

	/**
	 * Returns the user entered project description.
	 * 
	 * @return the user entered project description.
	 * @author Eric
	 */
	public String getProjectDescription() {
		return myProject.getProjectDescription();
	}

	/**
	 * Returns the budget as a String in the form x.xx
	 * 
	 * @return return a formatted budget String.
	 * @author Eric
	 */
	public String getFormattedBudgetAsString() {
		return myProject.getFormattedBudgetAsString();
	}
	

	/**
	 * Returns the location of this project.
	 * 
	 * @return the location of this project.
	 * @author Eric
	 */
	public String getLocation() {
		return myProject.getLocation();
	}

	/**
	 * For testing purposes. Print the contents of this project to the console.
	 * 
	 * @author Eric
	 */
	public void printToConsole() {
		myProject.printToConsole();
	}

	/**
	 * Returns a String representation of the Project.
	 * 
	 * @author Curran
	 */
	public String getProjectString() {
		return myProject.getProjectString();
	}
}
