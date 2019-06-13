package model;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * The main class for holding user entered information.
 * 
 * @author Eric
 *
 */
public class Project implements Serializable {

	/**
	 * Serial UID for outputing the Project.
	 */
	private static final long serialVersionUID = -5725244061748389461L;

	/**
	 * Default name of a project.
	 */
	public static final String DEFAULT_PROJECT_NAME = "...";

	/**
	 * Default description of a project.
	 */
	public static final String DEFAULT_PROJECT_DESCRIPTION = "...";

	/**
	 * Default budget of a project.
	 */
	public static final double DEFAULT_PROJECT_BUDGET = 0.00;

	/**
	 * Default location of a project.
	 */
	public static final String DEFAULT_PROJECT_LOCATION = "...";

	/**
	 * A new line escape character.
	 */
	public static final String NEWLINE = "\n";

	/**
	 * The user entered name of the project.
	 */
	private String myProjectName;

	/**
	 * A user entered description about the project as a whole.
	 */
	private String myProjectDescription;

	/**
	 * A user entered budget for the project.
	 */
	private double myProjectBudget;

	/**
	 * A user entered location for this project.
	 */
	private String myProjectLocation;

	/**
	 * A list of items holding fields which carry user entered data.
	 */
	public List<Item> myItems; // No getter and setter for this

	/**
	 * Default Constructor for a Project.
	 * 
	 * @author Curran, Minh, Sharanjit, Eric
	 */
	public Project() {
		this.myProjectName = "Dog Name";
		this.myProjectLocation = "Woof Location";
		this.myProjectBudget = 0.00;
		this.myProjectDescription = "This is a woof project";
		this.myItems = new LinkedList<Item>();
	}

	/**
	 * Returns the user entered name of this project.
	 * 
	 * @return the user entered name of this project.
	 * @author Eric
	 */
	public String getName() {
		return myProjectName;
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
		if (theName == null) {
			throw new IllegalArgumentException("Cannot set name to null");
		}
		this.myProjectName = theName;
	}

	/**
	 * Returns the user entered project description.
	 * 
	 * @return the user entered project description.
	 * @author Eric
	 */
	public String getProjectDescription() {
		return myProjectDescription;
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
		if (theProjectDescription == null) {
			throw new IllegalArgumentException("Cannot set projectDescription to null");
		}
		this.myProjectDescription = theProjectDescription;
	}

	/**
	 * Returns the budget of the project as a formatted double.
	 * 
	 * @return the formatted budget of the project.
	 * @author Eric
	 */
	public double getBudget() {
		// Just keeping only two decimal places.
		// Not worrying about rounding error.
		int budgetMult = (int) (myProjectBudget * 100);
		double budgetFormatted = ((double) budgetMult) / 100;
		return budgetFormatted;
	}

	/**
	 * Returns the budget as a String in the form x.xx
	 * 
	 * @return return a formatted budget String.
	 * @author Eric
	 */
	public String getFormattedBudgetAsString() {
		final double formattedBudget = getBudget();
		final String budgetStringRaw = "" + formattedBudget;
		final int indexOfDot = budgetStringRaw.indexOf('.');
		// Now everything after the dot.
		final String subBudgetString = budgetStringRaw.substring(indexOfDot + 1);
		if (subBudgetString.length() < 2) {
			return budgetStringRaw + "0";
		} else {
			return budgetStringRaw;
		}
	}

	/**
	 * Sets the budget of the project.
	 * 
	 * @param theBudget
	 *            the budget to set for this project.
	 * @author Eric
	 */
	public void setBudget(final double theBudget) {
		this.myProjectBudget = theBudget;
	}

	/**
	 * Returns the location of this project.
	 * 
	 * @return the location of this project.
	 * @author Eric
	 */
	public String getLocation() {
		return myProjectLocation;
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
		if (theLocation == null) {
			throw new IllegalArgumentException("Cannot set location to null");
		}
		this.myProjectLocation = theLocation;
	}

	// Field edit methods

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
		if (theItemType == null) {
			throw new IllegalArgumentException("Error: item-type cannot be null");
		}

		// Check to see if the itemType is unique
		for (Item item : myItems) {
			if (item.getItemType().equals(theItemType)) {
				throw new IllegalArgumentException("Error: item-type has to be unqiue");
			}
		}

		// Add the item
		final Item item = new Item(theItemType);
		this.myItems.add(item);
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
	public void removeItem(String theItemType){
		
	
			if (theItemType == null) {
			throw new IllegalArgumentException("Error: item-type cannot be null");
			
			} 
		
			final Iterator<Item> itemIter = myItems.iterator();
			while (itemIter.hasNext()) {
				final Item nextItem = itemIter.next();
				if (nextItem.getItemType().equals(theItemType)) {
					itemIter.remove();
					return; // Doesn't have to search since item-types are unique.
				}
			
		
		
			
			
			
			
			
		}
		
//		if (theItemType == null) {
//			throw new IllegalArgumentException("Error: item-type cannot be null");
//		}
//
//		// Find and remove the matching item.
//		final Iterator<Item> itemIter = myItems.iterator();
//		while (itemIter.hasNext()) {
//			final Item nextItem = itemIter.next();
//			if (nextItem.getItemType().equals(theItemType)) {
//				itemIter.remove();
//				return; // Doesn't have to search since item-types are unique.
//			}
//		}
//	

		// Oh no! There was no match!
		throw new IllegalArgumentException("Error: given item-type did not have a match");
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
		for (Item item : myItems) {
			if (item.getItemType().equals(theItemDestination)) {
				item.addField(theFieldName, theDescription, theValueType, theValue);
				return;
			}
		}

		// Oh no! there was no matching item-type
		throw new IllegalArgumentException("Error: the item-type destination could not be found.");
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
		for (Item item : myItems) {
			if (item.getItemType().equals(theItemType)) {
				item.removeField(theFieldName);
				return; // No duplicate fields so return.
			}
		}

		// No match found
		throw new IllegalArgumentException("Error: give item-type could not be found.");
	}

	/**
	 * For testing purposes. Print the contents of this project to the console.
	 * 
	 * @author Eric
	 */
	public void printToConsole() {
		System.out.println("Project Name: " + myProjectName);
		System.out.println("Project Description: " + myProjectDescription);
		System.out.println("Project Budget: $" + getFormattedBudgetAsString());
		System.out.println("Project Location: " + myProjectLocation);
		System.out.println("----------------------------");
		for (Item item : myItems) {
			System.out.println("ItemType: " + item.getItemType());
			for (ItemField field : item.getFields()) {
				System.out.println("\tField: " + field.toString());
			}
		}
	}

	/**
	 * Creates a String representation of the project and returns it.
	 * 
	 * All items currently within the project are included.
	 * 
	 * @author Curran, Eric
	 * @return a String representation of the project.
	 */
	public String getProjectString() {
		final StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("Project Name: ");
		stringBuilder.append(myProjectName);
		stringBuilder.append(NEWLINE);
		stringBuilder.append("Project Description: ");
		stringBuilder.append(myProjectDescription);
		stringBuilder.append(NEWLINE);
		stringBuilder.append("Project Budget: $");
		stringBuilder.append(getFormattedBudgetAsString());
		stringBuilder.append(NEWLINE);
		stringBuilder.append("Project Location: ");
		stringBuilder.append(myProjectLocation);
		stringBuilder.append(NEWLINE);
		stringBuilder.append("----------------------------");
		stringBuilder.append(NEWLINE);

		if (myItems != null && !myItems.isEmpty()) {
			// Add the contents
			for (Item item : myItems) {
				stringBuilder.append("ItemType: ");
				stringBuilder.append(item.getItemType());
				stringBuilder.append(NEWLINE);
				for (ItemField field : item.getFields()) {
					stringBuilder.append("         ");
					stringBuilder.append(field.getFieldName());
					stringBuilder.append(": ");
					stringBuilder.append(field.getValue());
					stringBuilder.append(NEWLINE);
				}
			}
		} else {
			stringBuilder.append("No items currently.");
		}
		return stringBuilder.toString();
	}

	
	/**
	 * Clears all items from list.
	 * 
	 * @author Eric
	 */
	public void clearAllItems() {
		this.myItems.clear();
	}

	/**
	 * Resets the basic information back to their default values.
	 * 
	 * @author Eric
	 */
	public void clearBasicInformation() {
		setName(DEFAULT_PROJECT_NAME);
		setProjectDescription(DEFAULT_PROJECT_DESCRIPTION);
		setBudget(DEFAULT_PROJECT_BUDGET);
		setLocation(DEFAULT_PROJECT_LOCATION);
	}
}
