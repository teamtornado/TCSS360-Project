package controller;

import java.util.List;

import model.ItemField;

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
	private ProjectLoadController myLoader;

	/**
	 * Creates a controller for the given project.
	 * 
	 * @param theProject
	 *            the project to create a controller for.
	 * @author Eric
	 */
	public ProjectViewController(final ProjectLoadController theLoader) {
		this.myLoader = theLoader;
	}

	/**
	 * Returns the user entered name of this project.
	 * 
	 * @return the user entered name of this project.
	 * @author Eric
	 */
	public String getName() {
		return myLoader.myProject.getName();
	}

	/**
	 * Returns the user entered project description.
	 * 
	 * @return the user entered project description.
	 * @author Eric
	 */
	public String getProjectDescription() {
		return myLoader.myProject.getProjectDescription();
	}

	/**
	 * Returns the budget of the project.
	 * 
	 * @return the budget of the project.
	 * @author Eric
	 */
	public double getBudget() {
		return myLoader.myProject.getBudget();
	}

	/**
	 * Returns the budget as a String in the form x.xx
	 * 
	 * @return return a formatted budget String.
	 * @author Eric
	 */
	public String getFormattedBudgetAsString() {
		return myLoader.myProject.getFormattedBudgetAsString();
	}

	/**
	 * Returns the location of this project.
	 * 
	 * @return the location of this project.
	 * @author Eric
	 */
	public String getLocation() {
		return myLoader.myProject.getLocation();
	}

	/**
	 * Returns a list of all item-types held within this project.
	 * 
	 * @return a list of all item-types held within this project.
	 * @author Eric
	 */
	public List<String> getAllItemTypes() {
		return myLoader.myProject.getAllItemTypes();
	}

	/**
	 * Returns a COPY of the ItemFields within the matching Item to item-type.
	 * 
	 * @param theItemType
	 *            the item-type to get fields from.
	 * @return a COPY of the ItemFields within the matching Item to item-type.
	 * @author Eric
	 */
	public List<ItemField> getAllItemFieldsFromItem(final String theItemType) {
		return myLoader.myProject.getAllItemFieldsFromItem(theItemType);
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
	public ItemField getFieldFromItem(final String theItemType, final String theItemFieldName) {
		return myLoader.myProject.getFieldFromItem(theItemType, theItemFieldName);
	}

	/**
	 * For testing purposes. Print the contents of this project to the console.
	 * 
	 * @author Eric
	 */
	public void printToConsole() {
		myLoader.myProject.printToConsole();
	}
}