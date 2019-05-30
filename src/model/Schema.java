package model;

import java.util.List;

import model.schemautil.SchemaDataParser;

public class Schema {

	private List<SchemaItem> mySchemaDatabase;

	public Schema() {
		this.mySchemaDatabase = SchemaDataParser.parseSchemaDatabase();
	}

	/**
	 * Returns a list of all items associated to theItemType
	 * 
	 * @param theItemType
	 *            the item type associated to a list of schema items
	 * @return
	 */
	public List<Item> getSchemaListOfType(final String theItemType) {
		// loop through schema data base to find the right item type and return
		// the list of types, including subtypes using the 'isA' field.
		return null;
	}

}
