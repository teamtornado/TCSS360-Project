package tests;

import static org.junit.Assert.assertTrue;



import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.SchemaController;
import model.SchemaField;
import model.schemautil.SchemaTypes;

/**
 * Tests the Schema controller and database.
 * 
 * @author Eric Sharanjit 
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
		mySchemaController = new SchemaController(SCHEMA_FILE_TEST_LOCATION);
	}

	/**
	 * Checks that the hierarchy of parent to child is working correctly.
	 */
	@Test
	void correctChildren() {
		// Need a new test for this!
		
		
	}

	/**
	 * Checks that the correct global parents are given.
	 */
	@Test
	void correctGlobalParents() {
		final List<String> globalParentItemTypes = mySchemaController.getAllParentTypes();
		final String applianceType = globalParentItemTypes.get(0);
		final String heatingType = globalParentItemTypes.get(1);
		assertTrue(applianceType.equals(SchemaTypes.APPLIANCE));
		assertTrue(heatingType.equals(SchemaTypes.HEATING));
	}
	//author eric, Sharanjit
    //	This test method testing if the all parent types works fine.
	@Test
	void correctGetChildAndGetParent() {

	

		final String furnace = mySchemaController.getChildTypes(SchemaTypes.HEATING).get(0);
		assertTrue(furnace.equals(SchemaTypes.FURNACE));

		List<String> shouldBeEmptyList = mySchemaController.getChildTypes(SchemaTypes.WOK_STOVE);
		assertTrue(shouldBeEmptyList.isEmpty());
	}
 

	@Test
	void correctInheritedFields() {
		final List<SchemaField> inheritedFields = mySchemaController
				.getInheritedFields(SchemaTypes.WOK_STOVE);
	}
	
	
	//author Sharanjit
    //	This test method testing if the all parent types works fine.
	
	@Test
	void testAllParentType() {
		final String furnace = mySchemaController.getChildTypes(SchemaTypes.HEATING).get(0);
		assertTrue(furnace.equals(SchemaTypes.FURNACE));

//		List<String> shouldBeEmptyList = mySchemaController.getAllParentTypes();
//		assertTrue(shouldBeEmptyList.isEmpty());
	}
	
	
	//author Sharanjit
    //	This test method testing the child of parent.
	@Test
	void testParentOfChild() {
		final String heating= mySchemaController.getParentOfChild(SchemaTypes.FURNACE);
		
		assertTrue(heating.equals(SchemaTypes.HEATING));
		
	}
	
	
	
	
	
	
	
	
	
	

}
