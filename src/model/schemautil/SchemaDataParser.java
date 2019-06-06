package model.schemautil;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import model.SchemaField;
import model.SchemaItem;

/**
 * Parses a given schema database and returns a list of SchemaItem.
 * 
 * @author Eric
 *
 */
public class SchemaDataParser {

	/**
	 * Returns a list of SchemaItems describing a schema database.
	 * 
	 * @param theSchemaDataFileName
	 *            The name of the schema database file name.
	 * @return a list of SchemaItems from the schema database.
	 * @author Eric
	 */
	public static List<SchemaItem> parseSchemaDatabase(
			final String theSchemaDataFileName) {
		final File schemaDatabaseFile = new File(theSchemaDataFileName);
		Scanner schemaScan = null;
		try {
			schemaScan = new Scanner(schemaDatabaseFile);
		} catch (final FileNotFoundException theException) {
			System.out.println(
					"Error: could not find the SchemaData text file.");
			theException.printStackTrace();
		}
		// Have to get all fields first
		final List<SchemaField> allFields = parseFields(schemaScan);

		// Now you can compile the items from this list of fields.
		final List<SchemaItem> schemaItemList = parseItems(schemaScan,
				allFields);

		schemaScan.close();
		return schemaItemList;
	}

	/**
	 * Scans the beginning of a schema database and returns all the fields used
	 * within the items of the schema database.
	 * 
	 * ** Post: The scanner's next line will be just after the last field
	 * declaration.
	 * 
	 * @param theSchemaScan
	 *            the scanner of the schema database.
	 * @return all the fields used within the schema database.
	 * @author Eric
	 */
	public static List<SchemaField> parseFields(
			final Scanner theSchemaScan) {

		// Create the list
		final List<SchemaField> allSchemaFields = new LinkedList<>();

		// Skip over 'Field:' line
		theSchemaScan.nextLine();

		String line = theSchemaScan.nextLine();

		// Now read in the SchemaFields
		while (theSchemaScan.hasNextLine() && line.startsWith("Name:")) {
			// Divide into the three variables
			final String[] tokens = line.split(",");
			final String[] nameTokens = tokens[0].split(":");
			final String schemaName = nameTokens[1];

			final String[] descriptionTokens = tokens[1].split(":");
			final String description = descriptionTokens[1];

			final String[] valueTypeTokens = tokens[2].split(":");
			final String valueType = valueTypeTokens[1];

			// Create the SchemaField
			final SchemaField schemaField = new SchemaField(schemaName,
					description, valueType, false); // false as default value
			allSchemaFields.add(schemaField);

			line = theSchemaScan.nextLine();
		}
		return allSchemaFields;
	}

	/**
	 * Compiles the second part of a schema database. Takes a scanner on a schema
	 * database and a list of all the fields that show up within the items. Creates
	 * a list of items and their hierarchy from the database.
	 * 
	 * @param theSchemaScan
	 *            The scanner on a schema database.
	 * @param theSchemaFields
	 *            All the possible fields that may show up in the database.
	 * @return A list of all the items and their fields from the database.
	 * @author Eric
	 */
	private static List<SchemaItem> parseItems(final Scanner theSchemaScan,
			final List<SchemaField> theSchemaFields) {
		// Have to make sure the schema scanner is on the first item.
		String line = theSchemaScan.nextLine();
		while (!line.startsWith("ID:")) {
			line = theSchemaScan.nextLine();
		}

		final List<SchemaItem> schemaItemList = new LinkedList<>();

		// Parse away!
		boolean keepGoing = true;
		do {
			final String[] tokens = line.split(",");
			final String[] idTokens = tokens[0].split(":");
			final int id = Integer.parseInt(idTokens[1]);

			final String[] typeTokens = tokens[1].split(":");
			final String schemaType = typeTokens[1];

			final String[] isATokens = tokens[2].split(":");
			final int isA = Integer.parseInt(isATokens[1]); // an isA of '0' means parent

			final String[] fieldTokens = tokens[3].split(":");
			// Get the list without curly brackets.
			final String fieldNameListRaw = fieldTokens[1].substring(1,
					fieldTokens[1].length() - 1);
			final String[] fieldNames = fieldNameListRaw.split("-");

			// Add the fields to a new item
			final List<SchemaField> fieldsOfItem = new LinkedList<>();

			for (String fieldNameFromItem : fieldNames) {
				// Find the field and add
				for (SchemaField templateField : theSchemaFields) {
					String templateFieldName = templateField
							.getSchemaFieldName();

					// Check for required flag.
					boolean isRequired = templateFieldName.startsWith("*");
					if (isRequired) {
						templateFieldName.substring(1); // skip over "*"
					}

					if (templateFieldName.equals(fieldNameFromItem)) {
						final SchemaField newSchemaField = new SchemaField(
								templateField.getSchemaFieldName(),
								templateField.getDescription(),
								templateField.getValueType(), isRequired);

						// Add this new field to the item.
						fieldsOfItem.add(newSchemaField);
						break;
					}
				}
			}

			// Compile the new item
			final SchemaItem item = new SchemaItem(id, isA, schemaType,
					fieldsOfItem);

			// Add this new Item to the full list of items for the database.
			schemaItemList.add(item);

			if (theSchemaScan.hasNext()) {
				line = theSchemaScan.nextLine();
			} else {
				keepGoing = false; // Out of items to parse!
			}
		} while (keepGoing && line.startsWith("ID:"));

		return schemaItemList;
	}
}