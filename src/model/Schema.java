package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import model.schemautil.SchemaDataParser;

/**
 * A Schema class instance will hold the contents of one schema database as a
 * list of SchemaItems. These items are to be used to describe the contents of a
 * Project class instance.
 * 
 * @author Eric
 *
 */
public class Schema {

	/**
	 * The ID for a SchemaItem that identifies the item as a global parent.
	 * 
	 * Global parent simply means that the item does not have a parent itself.
	 */
	private static final int GLOBAL_PARENT_ID = 0;

	/**
	 * The internal schema database! Represents the data parsed from a
	 * SchemaData.txt file
	 */
	private final List<SchemaItem> mySchemaItems;

	/**
	 * Takes the location of a schema database and constructs a Schema object to
	 * describe it.
	 * 
	 * @param theSchemaDatabaseLocation
	 *            the location of the schema database.
	 * @author Eric
	 */
	public Schema(final String theSchemaDatabaseLocation) {
		mySchemaItems = SchemaDataParser.parseSchemaDatabase(theSchemaDatabaseLocation);

		// Get the SchemaScanner
		final File schemaDatabaseFile = new File(theSchemaDatabaseLocation);
		Scanner schemaScan = null;
		try {
			schemaScan = new Scanner(schemaDatabaseFile);
		} catch (final FileNotFoundException theException) {
			System.out.println("Error: could not find the SchemaData text file.");
			theException.printStackTrace();
		}
	}

	/**
	 * Returns the list of item types that have NO parents, meaning they are at the
	 * TOP of the item-type hierarchy.
	 * 
	 * @return the item types at the top of the item-type hierarchy.
	 * @author Eric
	 */
	public List<String> getAllParentTypes() {
		final List<String> parentItemTypes = new LinkedList<>();

		for (SchemaItem item : mySchemaItems) {

			if (item.getIsA() == 0) {
				parentItemTypes.add(item.getItemType());
			}
		}
		return parentItemTypes;
	}

	/**
	 * Takes an item-type and returns all the item-types that have the given as a
	 * parent. For example, in a hierarchy of Appliance -> Stove -> Wok, Calling
	 * getChildTypes(Stove) will return Appliance.
	 * 
	 * @param theParentItemType
	 *            the parent item-type.
	 * @return a list of children who have the given item-type as a parent.
	 * @author Eric
	 */
	public List<String> getChildTypes(final String theParentItemType) {
		final List<String> childItemTypes = new LinkedList<>();

		// Have to find the parent
		final int parentID = getID(theParentItemType);

		// Now find the children of the parent
		for (SchemaItem item : mySchemaItems) {
			if (item.getIsA() == parentID) {
				childItemTypes.add(item.getItemType());
			}
		}
		return childItemTypes;
	}

	/**
	 * Gets the ID of the SchemaItem which matched the given item-type.
	 * 
	 * @param theItemType
	 *            the SchemaItem's item-type.
	 * @return the SchemaItem's ID which matched the given theItemType.
	 * @author Eric
	 */
	private int getID(final String theItemType) {
		for (SchemaItem item : mySchemaItems) {
			if (item.getItemType().equals(theItemType)) {
				return item.getID();
			}
		}

		// Oh no! The item type wasn't in the database!
		throw new IllegalArgumentException("Itemtype not found inside Schema database.");
	}

	/**
	 * Gets a Schema Item that matches with the given SchemaItemType.
	 * 
	 * @param theSchemaItemType
	 *            an item-type that has a match within the schema database.
	 * @return the matching SchemaItem with the same SchemItemType given.
	 * @author Eric
	 */
	private SchemaItem getSchemaItem(final String theSchemaItemType) {
		for (SchemaItem item : mySchemaItems) {
			if (item.getItemType().equals(theSchemaItemType)) {
				// SchemaItems are immutable, so okay to return since this is a copy of its own
				// internal list.
				return item;
			}
		}

		// Oh no! No matching SchemaItem!
		throw new IllegalArgumentException(
				"Error: there was no match with itemType within the list of SchemaItems "
						+ theSchemaItemType);
	}

	/**
	 * Returns the item-type of the child's parent. For instance, if "Stove" is
	 * given, "Appliance" will be returned.
	 * 
	 * @param theChildItemType
	 *            the child who's parent item-type will be returned.
	 * @throws IllegalArgumentException
	 *             if the given child item-type was not found in the Schema.
	 * @return A String representing the parent's item-type, or null if its a global
	 *         parent. Global parent means that the given item-type has NO parent.
	 * @author Eric
	 */
	public String getParentOfChild(final String theChildItemType) {
		// Find the parent ID through child's isA field.
		for (SchemaItem item : mySchemaItems) {
			if (item.getItemType().equals(theChildItemType)) {
				final int parentID = item.getIsA();
				// If this is actually a global parent, then return null.
				if (parentID == 0) {
					return null; // Agh, a global parent!!
				}
				// Get parent item-type with ID
				for (SchemaItem possibleParent : mySchemaItems) {
					if (possibleParent.getID() == parentID) {
						// Found the parent, return its Item-type
						return possibleParent.getItemType();
					}
				}
			}
		}
		// Oh no! No item-type found!
		throw new IllegalArgumentException("Error: Schema item-type was not found.");
	}

	/**
	 * Returns the list of fields that are inherited by the give item. Includes the
	 * fields at the give item-types level.
	 * 
	 * @param theItemType
	 *            The item type to find inherited field for.
	 * @return the list of fields inherited by the given item-type
	 * @throws IllegalArgumentException
	 *             if the item-type has no match in the Schema.
	 * @author Eric
	 */
	public List<SchemaField> getInheritedFields(final String theItemType) {
		final List<SchemaField> listOfInheritedFields = new LinkedList<>();

		// find the itemType
		for (SchemaItem item : mySchemaItems) {
			if (item.getItemType().equals(theItemType)) {
				// Grab this item's fields.
				for (SchemaField field : item.getFields()) {
					listOfInheritedFields.add(field);
				}

				if (item.getIsA() == Schema.GLOBAL_PARENT_ID) {
					return item.getFields(); // This is already a parent global, so stop.
				}
				// Grab the parent of the item
				SchemaItem currentItem = getSchemaItem(getParentOfChild(item.getItemType()));
				// Found, now grab all the inherited stuff
				while (currentItem.getIsA() != Schema.GLOBAL_PARENT_ID) {
					for (SchemaField field : currentItem.getFields()) {
						listOfInheritedFields.add(field);
					}
					// Find the next parent
					currentItem = getSchemaItem(getParentOfChild(currentItem.getItemType()));
				}

				// Should be at the global parent at this point. So add those.
				for (SchemaField field : currentItem.getFields()) {
					listOfInheritedFields.add(field);
				}
				return listOfInheritedFields;
			}
		}

		// Oh no! Couldn't find the schema item~
		throw new IllegalArgumentException("Error: Schema item-type was not found: " + theItemType);
	}
}