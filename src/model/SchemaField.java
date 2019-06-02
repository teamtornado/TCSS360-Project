package model;

/**
 * Immutable. An instance of this class will describe an instance of an
 * ItemField.
 * 
 * @author Eric
 *
 */
public class SchemaField {
	/**
	 * The Name of the field.
	 */
	private String mySchemaFieldName;

	/**
	 * A description about the information that can be held in this kind of field.
	 */
	private String myDescription;

	/**
	 * The type of data that can be stored in this kind of field. Can be 'String' or
	 * 'Number'.
	 */
	private String myValueType;

	/**
	 * Whether the field is required on creation of an Item.
	 */
	private boolean myRequired;

	/**
	 * Constructs a SchemaField.
	 * 
	 * @param theSchemaFieldName
	 *            The name of the SchemaField.
	 * @param theDescription
	 *            A description about the information held within this kind of
	 *            field.
	 * @param theValueType
	 *            The type of data that can be stored in this kind of field. Can be
	 *            'String' or 'Number'.
	 * @author Eric
	 */
	public SchemaField(final String theSchemaFieldName,
			final String theDescription, final String theValueType,
			final boolean theRequired) {
		super();
		this.mySchemaFieldName = theSchemaFieldName;
		this.myDescription = theDescription;
		this.myValueType = theValueType;
		this.myRequired = theRequired;
	}

	/**
	 * The field name of this field.
	 * 
	 * @return the field name of this field.
	 * @author Eric
	 */
	public String getSchemaFieldName() {
		return mySchemaFieldName;
	}

	/**
	 * A description about the kind of information held in this field.
	 * 
	 * @return a description about the kind of information held in this field.
	 * @author Eric
	 */
	public String getDescription() {
		return myDescription;
	}

	/**
	 * The type of value that can be stored in this field kind of field. Can be
	 * 'String' or 'Number'.
	 * 
	 * @return the type of value that can be stored in this kind of field. Can be
	 *         'String' or 'Number'.
	 * @author Eric
	 */
	public String getValueType() {
		return myValueType;
	}

	/**
	 * Returns true if the data described in this field is required during the
	 * project creation process.
	 * 
	 * @return True if the field is required during the project creation process.
	 * @author Eric
	 */
	public boolean isRequired() {
		return this.myRequired;
	}
}