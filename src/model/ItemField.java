package model;

import java.io.Serializable;

/**
 * Immutable. This class will hold a single piece of information within an Item.
 * 
 * @author Eric
 *
 */
public class ItemField implements Serializable {

	/**
	 * Serialize id
	 */
	private static final long serialVersionUID = -6409818913791891425L;

	/**
	 * The field name is the type of information in which a particular instance will
	 * store.
	 */
	private String myfieldName;

	/**
	 * Information or explanation about the value.
	 */
	private String myDescription;

	/**
	 * What type the value is. Could be 'String' or 'Number'.
	 */
	private String myValueType;

	/**
	 * The data itself.
	 */
	private String myValue;

	/**
	 * Constructs an ItemField.
	 * 
	 * @param theFieldName
	 *            The name or title of the information held.
	 * @param theDescription
	 *            Information or explanation about the value.
	 * @param theValueType
	 *            What type the information being held is. Could be 'String' or
	 *            'Number'.
	 * @param theValue
	 *            The data itself.
	 * @author Eric
	 */
	public ItemField(final String theFieldName, final String theDescription,
			final String theValueType, final String theValue) {
		this.myfieldName = theFieldName;
		this.myDescription = theDescription;
		this.myValue = theValue;
	}

	/**
	 * Returns the name of the information held.
	 * 
	 * @return the name of the information held.
	 * @author Eric
	 */
	public String getFieldName() {
		return myfieldName;
	}

	/**
	 * Returns a description about the information being held.
	 * 
	 * @return a description about the information being held.
	 * @author Eric
	 */
	public String getDescription() {
		return myDescription;
	}

	/**
	 * Returns the type of value that is stored. Could be 'String' or 'Number'.
	 * 
	 * @return the type of value that is stored.
	 * @author Eric
	 */
	public String getValueType() {
		return myValueType;
	}

	/**
	 * Returns the data.
	 * 
	 * @return the data.
	 * @author Eric
	 */
	public String getValue() {
		return myValue;
	}

	/**
	 * Returns a string representing the data held within this field.
	 * 
	 * @return a string representing the data held within this field.
	 * @author Eric
	 */
	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append(this.myfieldName);
		builder.append("\tDescription: ");
		builder.append(this.myDescription);
		builder.append("\tValueType: ");
		builder.append(this.myValueType);
		builder.append("\tValue: ");
		builder.append(this.myValue);
		return builder.toString();
	}
}
