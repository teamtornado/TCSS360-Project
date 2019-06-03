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
	 * The internal schema database! Represents the data parsed from a
	 * SchemaData.txt file
	 */
	private final List<SchemaItem> mySchemaItems;

	/**
	 * A list of all possible fields within the schema database.
	 */
	private final List<SchemaField> myAllFields;

	/**
	 * Takes the location of a schema database and constructs a Schema object to
	 * describe it.
	 * 
	 * @param theSchemaDatabaseLocation
	 *            the location of the schema database.
	 * @author Eric
	 */
	public Schema(final String theSchemaDatabaseLocation) {
		mySchemaItems = SchemaDataParser
				.parseSchemaDatabase(theSchemaDatabaseLocation);

		// Get the SchemaScanner
		final File schemaDatabaseFile = new File(
				theSchemaDatabaseLocation);
		Scanner schemaScan = null;
		try {
			schemaScan = new Scanner(schemaDatabaseFile);
		} catch (final FileNotFoundException theException) {
			System.out.println(
					"Error: could not find the SchemaData text file.");
			theException.printStackTrace();
		}

		myAllFields = SchemaDataParser.parseFields(schemaScan);
	}

	/**
	 * Testing method! It is likely you do not need to use this and shouldn't.
	 * Creates a copy of the list of SchemaItems within the internal Schema database
	 * and returns the copy.
	 * 
	 * @return a copy of the internal Schema database.
	 * @author Eric
	 */
	public List<SchemaItem> getSchemaList() {
		final List<SchemaItem> newSchemaList = new LinkedList<>();

		for (SchemaItem item : mySchemaItems) {
			newSchemaList.add(item);
		}
		return newSchemaList;
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
	 * Returns a list of all item-types within the Schema database, except for the
	 * general parent type that defines stuff like user notes.
	 * 
	 * @return A list of all item-types within the Schema database.
	 * @author Eric
	 */
	public List<String> getAllTypes() {
		final List<String> allItemTypes = new LinkedList<>();

		for (SchemaItem item : mySchemaItems) {
			allItemTypes.add(item.getItemType());
		}

		return allItemTypes;
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
		throw new IllegalArgumentException(
				"Itemtype not found inside Schema database.");
	}

	/**
	 * Returns a list of fields offered by the given item.
	 * 
	 * @param theItemType
	 *            the item type to draw fields from.
	 * @return a list of SchemaFields from the given item-type.
	 * @throws IllegalArgumentException
	 *             if there was no match with a SchemaItem
	 * @author Eric
	 */
	public List<SchemaField> getSchemaFieldsFromItem(
			final String theItemType) {
		final List<SchemaField> fieldsFromItemType = new LinkedList<>();

		// Get all the fields from the matching item-type
		for (SchemaItem item : mySchemaItems) {
			if (item.getItemType().equals(theItemType)) {
				// Found a match!
				for (SchemaField field : item.getFields()) {
					fieldsFromItemType.add(field);
				}

				// Should only ever be one match, so stop searching
				break;
			}
		}

		// Error checking. Has to at least have something in the list.
		if (fieldsFromItemType.isEmpty()) {
			throw new IllegalArgumentException(
					"Error: there was no match with itemType within the"
							+ " list of SchemaItems");
		}

		return fieldsFromItemType;
	}

	/**
	 * Returns the matching SchemaField with the schemaFieldName.
	 * 
	 * @param theSchemaFieldName
	 *            the name of the matching SchemaField.
	 * @throws IllegalArgumentException
	 *             if there was no match with a SchemaItem
	 * @return the SchemaField that had a matching schemaFieldName.
	 * @author Eric
	 */
	public SchemaField getSchemaField(final String theSchemaFieldName) {
		for (SchemaField field : myAllFields) {
			if (field.getSchemaFieldName().equals(theSchemaFieldName)) {
				return field;
			}
		}

		// Ah! Schema field not found within internal database!
		throw new IllegalArgumentException(
				"Error: given schemaFieldName does not have a match with any"
						+ "known SchemaField within Schema");
	}

	/**
	 * Gets a Schema Item that matches with the given SchemaItemType.
	 * 
	 * @param theSchemaItemType
	 *            an item-type that has a match within the schema database.
	 * @return the matching SchemaItem with the same SchemItemType given.
	 * @author Eric
	 */
	public SchemaItem getSchemaItem(final String theSchemaItemType) {
		for (SchemaItem item : mySchemaItems) {
			if (item.getItemType().equals(theSchemaItemType)) {
				// SchemaItems are immutable, so okay to return since this is a copy of its own
				// internal list.
				return item;
			}
		}

		// Oh no! No matching SchemaItem!
		throw new IllegalArgumentException(
				"Error: there was no match with itemType within the"
						+ " list of SchemaItems");
	}
}