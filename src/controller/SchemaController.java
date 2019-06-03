package controller;

import java.util.List;

import model.Schema;
import model.SchemaField;
import model.SchemaItem;

/**
 * Allows the front-end to interact with the Schema.
 * 
 * @author Eric
 *
 */
public class SchemaController {

	/**
	 * The Schema object will describe the data that can be placed into a user's
	 * project. Does not actually hold any user information itself.
	 */
	private final Schema mySchema;

	/**
	 * Takes the location of a schema database and constructs a Schema object to
	 * describe it.
	 * 
	 * @param theSchemaDatabaseLocation
	 *            the location of the schema database.
	 * @author Eric
	 */
	public SchemaController(final String theSchemaDatabaseLocation) {
		mySchema = new Schema(theSchemaDatabaseLocation);
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
		return mySchema.getSchemaList();
	}

	/**
	 * Returns the list of item types that have NO parents, meaning they are at the
	 * TOP of the item-type hierarchy.
	 * 
	 * @return the item types at the top of the item-type hierarchy.
	 * @author Eric
	 */
	public List<String> getAllParentTypes() {
		return mySchema.getAllParentTypes();
	}

	/**
	 * Returns a list of all item-types within the Schema database, except for the
	 * general parent type that defines stuff like user notes.
	 * 
	 * @return A list of all item-types within the Schema database.
	 * @author Eric
	 */
	public List<String> getAllTypes() {
		return mySchema.getAllTypes();
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
		return mySchema.getChildTypes(theParentItemType);
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
		return mySchema.getSchemaFieldsFromItem(theItemType);
	}

	/**
	 * Returns a deep copy of the matching SchemaItem.
	 * 
	 * @param theSchemaItemType
	 *            the item-type to match by.
	 * @return a deep copy of the matching SchemaItem.
	 * @author Eric
	 */
	public SchemaItem getSchemaItem(final String theSchemaItemType) {
		return mySchema.getSchemaItem(theSchemaItemType);
	}
}