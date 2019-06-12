/**
 * 
 * 
 * @author sharanjitsingh
 */

package tests;


import static org.junit.Assert.assertTrue;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

//import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import controller.ProjectController;
import gui.createpanels.BasicInfoPanel;
import model.schemautil.SchemaTypes;
//import org.junit.jupiter.api.Test;


//import org.junit.Test;
//import org.junit.jupiter.api.BeforeEach;
//
//import controller.ProjectController;
//import model.schemautil.SchemaTypes;
/**
 * @author sharanjitsingh
 *
 */
public class ProjectControllerTest {

	private ProjectController myProject;

	@BeforeEach
	public void setup() {
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
//	
//	@Test
//	void correctSerialSave() {
//		myProject.createNewProject();
//		myProject.setName("woof");
//		myProject.setDescription("woof");
//		myProject.setBudget(100);
//		myProject.setLocation("washington");
//		myProject.addItem(SchemaTypes.APPLIANCE);
//
//		myProject.saveProject(null);
//		myProject.loadProject(null);
//
//		// Agh! need a new test to make sure the serial save works!
//	}
	
	
//	@Test
//	public void testCreatNewProject() {
//		myProject  = new ProjectController();
//		myProject.addItem("");
//	
//		myProject.clearAllItems();
//		myProject.addItem(null);
//		
//		myProject.clearBasicInformation();
//		
//		
//		
//		
//		
//		
//
//	}
	
	@Test
	public void testsetBasicInfo() {
		
 		myProject  = new ProjectController();    
        myProject.setName("New Project");
        
		myProject.setLocation("WA");
		
		myProject.setBudget(200);
		myProject.setDescription("This is new pro");
		assertTrue(myProject.getName().equals("New Project"));
		
	
		}
	

	
	
	
	
	
	
	
	/**
	 * author Sharanjit Singh
	 * This method testing the setName Methd.
	 */
	
	@Test
	public void testsetName() {
	myProject  = new ProjectController();
		myProject.setName("NewProject");
		assertTrue(myProject.getName().equals("NewProject"));
		
		
	}
	
	/**
	 * author Sharanjit Singh
	 * This method testing the setDescription Method.
	 */
	
	@Test
	public void testProjectDescription() { 
		myProject  = new ProjectController();
		myProject.setDescription("This is new project");
		assertEquals("This is new project",myProject.getProjectDescription());
		
	}
	
	
	//author:
//	@Test
//	public void testgetFormattedBudget() {
//		myProject  = new ProjectController();
//		myProject.setBudget(100);
//		assertTrue(myProject.getFormattedBudgetAsString().equals(myProject));
//		
//	}
	
	/**
	 * author Sharanjit Singh
	 * This method testing the setLocation Method.
	 */
	@Test
	public void testgetLocation() throws ClassNotFoundException, SQLException{
		myProject  = new ProjectController();
		myProject.setLocation("W.A");
		assertEquals("W.A", myProject.getLocation());
		
	}
	
	
	/**
	 * author Sharanjit Singh
	 * This method testing the printToConsole Method.
	 */
	@Test
	public void PrintToConsole() {
		myProject  = new ProjectController();
		myProject.setDescription("This is fun");
		System.out.print(myProject.getProjectDescription());
		
		
		
		
//		myProject.addItem("This is fun");
//		assertEquals(myProject.printToConsole(),outContent.toString());
		
	}
	
	
	
	
//	@Test
//	
//	public void testsetBugdet() {
//		myProject  = new ProjectController();
//		myProject.setBudget(200);
//		assertTrue( myProject.setBudget().equals(200));
//	}
	
	
	/**
	 * author Sharanjit Singh
	 * This method testing the projectString Method.
	 */
//	@Test
//	public void testProjectString() {
//		myProject  = new ProjectController();
////		myProject.set("MyPro");
//		assertTrue(myProject.getProjectString().equals("MyPro"));
//		
//	}
//	
	
	/**
	 * author Sharanjit Singh
	 * This method testing the clearAll Method.
	 */
	
	@Test
	public void testclearAll() {
		myProject  = new ProjectController();
		myProject.addItem("");
		myProject.clearAllItems();
			
	}
	
//	@Test
//	public void testFormattedBudgetAsString() {
//		
//		myProject.setBudget(100);
//		equals(myProject.getFormattedBudgetAsString().equals(100));
//	}
	
	
	@Test
	public void testremoveItem() {
		
		ProjectController myPr1 = new ProjectController();
		myPr1.addItem("FURNANCE");
		myPr1.removeItem("FURNANCE");

		assertEquals(0,myPr1.myProject.myItems.size());	
		
	}
	
	
	@Test (expected = IllegalArgumentException.class)
	public void testremoveItemNull() {
		ProjectController myPr1 = new ProjectController();
		
		myPr1.removeItem(null);
		
	}
	
	
	

}

