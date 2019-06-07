package controller;

import java.util.List;

import model.Schema;
import model.SchemaField;

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
		return mySchema.getParentOfChild(theChildItemType);
	}

	/**
	 * Returns the list of fields that are inherited by the give item. Includes
	 * the fields at the give item-types level.
	 * 
	 * @param theItemType
	 *            The item type to find inherited field for.
	 * @return the list of fields inherited by the given item-type
	 * @throws IllegalArgumentException
	 *             if the item-type has no match in the Schema.
	 * @author Eric
	 */
	public List<SchemaField> getInheritedFields(final String theItemType) {
		return mySchema.getInheritedFields(theItemType);
	}
}