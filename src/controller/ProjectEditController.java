package controller;

import java.util.Currency;

import model.Project;

/**
 * This class allows the front end to alter the model.
 * 
 * @author Eric
 *
 */
public class ProjectEditController {
	/**
	 * The project information. Includes the fields and the general info.
	 */
	private ProjectLoadController myLoader;

	/**
	 * Creates a controller for the given project.
	 * 
	 * @param theProject
	 *            the project to create a controller for.
	 */
	public ProjectEditController(final ProjectLoadController theLoader) {
		this.myLoader = theLoader;
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
		myLoader.myProject.setName(theName);
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
	public void setProjectDescription(String theProjectDescription) {
		myLoader.myProject.setProjectDescription(theProjectDescription);
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
	public void setBudget(final Currency theBudget) {
		myLoader.myProject.setBudget(theBudget);
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
		myLoader.myProject.setLocation(theLocation);
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
		myLoader.myProject.addItem(theItemType);
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
		myLoader.myProject.addFieldToItem(theItemDestination, theFieldName, theDescription,
				theValueType, theValue);
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
		myLoader.myProject.removeItem(theItemType);
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
		this.myLoader.myProject.removeFieldFromItem(theItemType, theFieldName);
	}

	/**
	 * Clears all items from list.
	 * 
	 * @author Eric
	 */
	public void clearAllItems() {
		this.myLoader.myProject.clearAllItems();
	}
}