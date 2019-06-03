package model;

import java.util.LinkedList;
import java.util.List;

/**
 * Immutable. This item holds all the different parts of information that
 * describe a particular item.
 * 
 * @author Eric
 *
 */
public class SchemaItem {
	/**
	 * The id of this item.
	 */
	private int myID;

	/**
	 * The parent id of this item.
	 */
	private int myIsA;

	/**
	 * The type of item this is.
	 */
	private String myItemType;

	/**
	 * The Schema field of this item.
	 */
	private List<SchemaField> myFields;

	/**
	 * Constructs a Schema Item.
	 * 
	 * @param theID
	 *            the id of this item.
	 * @param theIsA
	 *            the parent id of this item.
	 * @param theItemType
	 *            the type of item.
	 * @param theFields
	 *            the list of fields (data) this type of item can have.
	 * @author Eric
	 */
	public SchemaItem(int theID, int theIsA, String theItemType,
			List<SchemaField> theFields) {
		this.myID = theID;
		this.myIsA = theIsA;
		this.myItemType = theItemType;
		this.myFields = theFields;
	}

	/**
	 * The ID of this item.
	 * 
	 * @return the ID of this item
	 * @author Eric
	 */
	public int getID() {
		return myID;
	}

	/**
	 * The parent ID of this item.
	 * 
	 * @return the parent ID of this item.
	 * @author Eric
	 */
	public int getIsA() {
		return myIsA;
	}

	/**
	 * The type of item.
	 * 
	 * @return the type of item.
	 * @author Eric
	 */
	public String getItemType() {
		return myItemType;
	}

	/**
	 * ** Returns a NEW list of SchemaFields. This class is immutable.
	 * 
	 * @return a NEW list of SchemaFields. The SchemaFields describe the kind of
	 *         data that can be held in an ItemField.
	 * @author Eric
	 */
	public List<SchemaField> getFields() {
		final List<SchemaField> newListOfFields = new LinkedList<>();
		for (SchemaField field : this.myFields) {
			newListOfFields.add(field);
		}
		return newListOfFields;
	}

	/**
	 * Returns a string representation of this SchemaItem. Does not output the
	 * contents of the fields list, only the number of fields contained.
	 */
	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append(myID);
		builder.append(", IsA: ");
		builder.append(myIsA);
		builder.append(", Type: ");
		builder.append(myItemType);
		builder.append(", Num of fields: ");
		builder.append(myFields.size());
		return builder.toString();
	}

}
