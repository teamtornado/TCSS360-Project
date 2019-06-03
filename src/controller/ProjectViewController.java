package controller;

import java.util.Currency;
import java.util.List;

import model.ItemField;
import model.Project;

/**
 * This class allows the front end to query for data from the model to display
 * on the front end.
 * 
 * @author Eric
 *
 */
public class ProjectViewController {

	/**
	 * The project information. Includes the fields and the general info.
	 */
	protected Project myProject;

	/**
	 * Creates a controller for the given project.
	 * 
	 * @param theProject
	 *            the project to create a controller for.
	 */
	public ProjectViewController(final Project theProject) {
		this.myProject = theProject;
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
	 * Returns the budget of the project.
	 * 
	 * @return the budget of the project.
	 * @author Eric
	 */
	public Currency getBudget() {
		return myProject.getBudget();
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
	 * Returns a list of all item-types held within this project.
	 * 
	 * @return a list of all item-types held within this project.
	 * @author Eric
	 */
	public List<String> getAllItemTypes() {
		return myProject.getAllItemTypes();
	}

	/**
	 * Returns a COPY of the ItemFields within the matching Item to item-type.
	 * 
	 * @param theItemType
	 *            the item-type to get fields from.
	 * @return a COPY of the ItemFields within the matching Item to item-type.
	 * @author Eric
	 */
	public List<ItemField> getAllItemFieldsFromItem(
			final String theItemType) {
		return myProject.getAllItemFieldsFromItem(theItemType);
	}

	/**
	 * Returns the field from the matching item-type.
	 * 
	 * @param theItemType
	 *            the item-type to find.
	 * @param theItemFieldName
	 *            the field to find.
	 * @return the field from the given item-type.
	 * @throws IllegalArgumentException
	 *             if either the item-type or the fieldName had no match.
	 * @author Eric
	 */
	public ItemField getFieldFromItem(final String theItemType,
			final String theItemFieldName) {
		return myProject.getFieldFromItem(theItemType, theItemFieldName);
	}

	/**
	 * For testing purposes. Print the contents of this project to the console.
	 * 
	 * @author Eric
	 */
	public void printToConsole() {
		myProject.printToConsole();
	}
}