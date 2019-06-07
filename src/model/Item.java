package model;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * An instance of this class holds a part of information of a project.
 * 
 * For example, an item instance could be of type 'Stove' and hold a gas inlet
 * size and burner count field.
 * 
 * Inside the fields are the actual values of gas inlet size and the number of
 * burners.
 * 
 * @author Eric
 *
 */
public class Item implements Serializable {

	/**
	 * Serialized id.
	 */
	private static final long serialVersionUID = -1886109609838625431L;

	/**
	 * The kind of item. Could be a 'Stove', 'Appliance', or 'Insulation'.
	 */
	private String myItemType;

	/**
	 * The parts of data held within this item.
	 */
	private List<ItemField> myFields;

	/**
	 * Constructs this kind of item. The fields will be empty when finished with
	 * construction.
	 * 
	 * @param myItemType
	 *            The kind of item. Could be 'Stove', 'Appliance', or 'Insulation'
	 * @author Eric
	 */
	public Item(String myItemType) {
		this.myItemType = myItemType;
		this.myFields = new LinkedList<ItemField>();
	}

	/**
	 * The kind of item.
	 * 
	 * @return the kind of item.
	 * @author Eric
	 */
	public String getItemType() {
		return myItemType;
	}

	/**
	 * ** Returns a NEW list of ItemFields that copies the original of this field.
	 * 
	 * @return a new list of this items parts of data (ItemField)s.
	 * @author Eric
	 */
	public List<ItemField> getFields() {
		final List<ItemField> newListOfItemFields = new LinkedList<>();
		for (ItemField field : this.myFields) {
			newListOfItemFields.add(field);
		}
		return newListOfItemFields;
	}

	/**
	 * Removes the field of the given fieldName.
	 * 
	 * @param theFieldName
	 *            the name of the field to remove.
	 * @author Eric
	 */
	public void removeField(final String theFieldName) {
		final Iterator<ItemField> fieldsIter = myFields.iterator();
		while (fieldsIter.hasNext()) {
			final ItemField field = fieldsIter.next();
			if (field.getFieldName().equals(theFieldName)) {
				fieldsIter.remove();
				// All field names should be unique, therefore, no need to keep searching for
				// field name matches.
				break;
			}
		}
	}

	/**
	 * Adds a field with the given parameters to this item.
	 * 
	 * @param theFieldName
	 *            The name to set the new field to.
	 * @param theDescription
	 *            the description to set the new field to.
	 * @param theValueType
	 *            the value type to set the new field to.
	 * @param theValue
	 *            the value to set the new field to.
	 * @throws IllegalArgumentException
	 *             if the fieldName matches another field within the field list.
	 * @author Eric
	 */
	public void addField(final String theFieldName,
			final String theDescription, final String theValueType,
			final String theValue) {
		// Check that the field name is unqiue.
		for (ItemField field : myFields) {
			if (field.getFieldName().equals(theFieldName)) {
				throw new IllegalArgumentException(
						"Error: All field names within a single item must be UNIQUE");
			}
		}

		// Good to go, add to the field list.
		final ItemField newField = new ItemField(theFieldName,
				theDescription, theValueType, theValue);
		this.myFields.add(newField);
	}
}