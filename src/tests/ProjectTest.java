package tests;

import static org.junit.Assert.assertEquals;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import controller.ProjectController;
import model.schemautil.SchemaTypes;

class ProjectTest {

	private ProjectController myProject;

	@BeforeEach
	void setup() {
		myProject = new ProjectController();
	}

//	@Test
//	void createProjectTest() {
//		// Add some items
//		myProject.addItem(SchemaTypes.APPLIANCE);
//		myProject.addItem(SchemaTypes.LIGHTING);
//
//		// Add fields to the appliance item
//		final String powerUsageFieldName = "Power usage";
//		final String userNotesFieldName = "User notes";
//		myProject.addFieldToItem(SchemaTypes.APPLIANCE, powerUsageFieldName,
//				"How much power the appliance uses", "Number", "1000");
//		myProject.addFieldToItem(SchemaTypes.APPLIANCE, userNotesFieldName, "User created notes",
//				"String", "I think I might change my mind about this appliance");
//
//		// Actually, I changed my mind. I won't want a lighting item anymore
//		myProject.removeItem(SchemaTypes.LIGHTING);
//
//		// I also don't want user notes in my appliance item
//		myProject.removeFieldFromItem(SchemaTypes.APPLIANCE, userNotesFieldName);
//	}
//
//	@Test
//	void viewProjectTest() {
//		// Just setting this up from previous method.
//		myProject.addItem(SchemaTypes.APPLIANCE);
//		myProject.addItem(SchemaTypes.LIGHTING);
//
//		final String powerUsageFieldName = "Power usage";
//		final String userNotesFieldName = "User notes";
//		myProject.addFieldToItem(SchemaTypes.APPLIANCE, powerUsageFieldName,
//				"How much power the appliance uses", "Number", "1000");
//		myProject.addFieldToItem(SchemaTypes.APPLIANCE, userNotesFieldName, "User created notes",
//				"String", "I think I might change my mind about this appliance");
//
//		myProject.removeItem(SchemaTypes.LIGHTING);
//		myProject.removeFieldFromItem(SchemaTypes.APPLIANCE, userNotesFieldName);
//
//		// Now onto some viewer stuff
//
//		// What is the name of this project?
//		final String projectName = myProject.getName();
//
//		// What is the location?
//		final String projectLocation = myProject.getLocation();
//
//		// Now I'm just debugging, show me the whole project in the console
//		myProject.printToConsole();
//	}
//
//	@Test
//	void correctSerialSave() {
//		myProject.createNewProject();
//		myProject.setName("woof");
//		myProject.setDescription("wof");
//		myProject.setBudget(100);
//		myProject.setLocation("washington");
//		myProject.addItem(SchemaTypes.APPLIANCE);
//
//		myProject.saveProject(null);
//		myProject.loadProject(null);
//
//		// Agh! need a new test to make sure the serial save works!
//	}
	
	//Sharnjit
	
	@Test 
	final void testsetName() {
		myProject.setName("NewProject");
		assertEquals("NewProject", myProject.getName());
		
		
	}
	
	@Test
	void testProjectDescription() { 
		
		myProject.setDescription("This is new project");
		assertEquals("This is new project",myProject.getProjectDescription());
		
	}
	
	
	@Test
	void testgetLocation() {
		
		myProject.setLocation("W.A");
		assertEquals("W.A", myProject.getLocation());
		
	}
	
	@Test
	void testclearAll() {
		assertEquals("",myProject.clearAllItems());
	
		
	}
	
	
	

}
