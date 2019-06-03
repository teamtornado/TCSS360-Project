package tests;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.SchemaController;
import model.SchemaField;
import model.SchemaItem;
import model.SchemaTypes;

/**
 * Tests the Schema controller and database.
 * 
 * @author Eric
 *
 */
class SchemaTest {

	/**
	 * The file location of the schema test database.
	 */
	private static final String SCHEMA_FILE_TEST_LOCATION = "SchemaDataTest.txt";

	/**
	 * The controller for the schema test database.
	 */
	private SchemaController mySchemaController;

	/**
	 * Creates a new test schema database for each test run.
	 */
	@BeforeEach
	public void setup() {
		mySchemaController = new SchemaController(
				SCHEMA_FILE_TEST_LOCATION);
	}

	/**
	 * The first item in the list is correct. Does not check the fields.
	 */
	@Test
	void correctFirstItem() {
		// Grab the first schema item from the list.
		mySchemaController.toString();
		final SchemaItem item = mySchemaController.getSchemaList()
				.iterator().next();
		assertTrue(item.getID() == 1);
		assertTrue(item.getIsA() == 0);
		assertTrue(item.getItemType().equals(SchemaTypes.APPLIANCE));

	}

	/**
	 * Checks that the fields of the first item in the list are correct.
	 */
	@Test
	void correctFirstItemFields() {
		final SchemaItem item = mySchemaController.getSchemaList()
				.iterator().next();

		// There should only be one field in this
		// dataset.
		final List<SchemaField> fields = item.getFields();
		final SchemaField firstField = fields.iterator().next();
		assertTrue(fields.size() == 1);
		assertTrue(firstField.getSchemaFieldName().equals("User notes"));
		assertTrue(
				firstField.getDescription().equals("User written field"));
		assertTrue(firstField.getValueType().equals("String"));
	}

	/**
	 * Checks that the hierarchy of parent to child is working correctly.
	 */
	@Test
	void correctChildren() {
		final SchemaItem parentItem = mySchemaController.getSchemaList()
				.get(1);

		final List<String> childrenTypes = mySchemaController
				.getChildTypes(parentItem.getItemType());

		final String childItemType = childrenTypes.iterator().next();
		final SchemaItem childItem = mySchemaController
				.getSchemaItem(childItemType);
		assertTrue(childItem.getIsA() == parentItem.getID());
	}

	/**
	 * Checks that the correct global parents are given.
	 */
	@Test
	void correctGlobalParents() {
		final List<String> globalParentItemTypes = mySchemaController
				.getAllParentTypes();
		final String applianceType = globalParentItemTypes.get(0);
		final String heatingType = globalParentItemTypes.get(1);
		assertTrue(applianceType.equals(SchemaTypes.APPLIANCE));
		assertTrue(heatingType.equals(SchemaTypes.HEATING));
	}

}
