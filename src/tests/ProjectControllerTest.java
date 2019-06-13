/**
 * 	This class is used to test ProjectCintroller class which is our backed.
 * 
 * @author sharanjitsingh
 */

package tests;


import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
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
 * DATE:06/10/2019
 */
public class ProjectControllerTest {

	private ProjectController myProject;

	@BeforeEach
	public void setup() {
		myProject = new ProjectController();
	}


	/**
	 * author Sharanjit Singh
	 * This method testing the setBasicInfo method.
	 *  DATE:06/11/2019
	 * 
	 */
	@Test
	public void testsetBasicInfo() {
		ProjectController myPr1 = new ProjectController();
		
		myPr1.setName("New Project");
		
		myPr1.setLocation("WA");
		
		myPr1.setBudget(200);
		
		myPr1.setDescription("This is new pro");
   
		
		
		assertEquals("New Project",myPr1.getName());
		assertEquals("WA",myPr1.getLocation());
		assertEquals(200,200);
		assertEquals("This is new pro",myPr1.getProjectDescription());
		
	}
		
		
		
//		assertEquals("Project Name: Dog Name\n" + 
//				"Project Description: This is a woof project\n" + 
//				"Project Budget: $0.00\n" + 
//				"Project Location: Woof Location\n" + 
//				"----------------------------\n" + 
//				"No items currently.", myPr1.myProject);
//		
	
		
		
//	@Test
//	public void testsaveProject() {
//		ProjectController myPr1 = new ProjectController();
//		saveProject()
//		myPr1.saveProject("New Project");
//		
//		
//		
//	}

	
	
	
	
	
	
	
	/**
	 * author Sharanjit Singh
	 * This method testing the setName Method.
	 *  DATE:06/10/2019
	 */
	
	@Test
	public void testsetName() {
	myProject  = new ProjectController();
		myProject.setName("NewProject");
		assertTrue(myProject.getName().equals("NewProject"));
		
		
	}
	
	/**
	 * author Sharanjit Singh
	 * This method testing the setName method.
	 * Checking null point exception.
	 *  DATE:06/10/2019
	 */
	@Test (expected = IllegalArgumentException.class)
	public void testsetNamenull(){
		ProjectController myPr1 = new ProjectController();
		myPr1.setName(null);
	
	}
	
	
	
	/**
	 * author Sharanjit Singh
	 * This method testing the setDescription Method.
	 *  DATE:06/10/2019
	 */
	
	@Test
	public void testProjectDescription() { 
		myProject  = new ProjectController();
		myProject.setDescription("This is new project");
		assertEquals("This is new project",myProject.getProjectDescription());
		
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testProjectDescriptionnull() { 
		ProjectController myPr1 = new ProjectController();
		myPr1.setDescription(null);
		
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
	 *  DATE:06/10/2019
	 */
	@Test
	public void testgetLocation(){
		myProject  = new ProjectController();
		myProject.setLocation("W.A");
		assertEquals("W.A", myProject.getLocation());
		
	}
	
	/**
	 * author Sharanjit Singh
	 * This method testing the setLocation Method when there is nothing .
	 * Checking null point exception.
	 *  DATE:06/10/2019
	 */
	@Test (expected = IllegalArgumentException.class)
	public void testgetLocationnull(){
		ProjectController myPr1 = new ProjectController();
		myPr1.setLocation(null);
	
	}
	
	
	
	
	/**
	 * author Sharanjit Singh
	 * This method testing the printToConsole Method.
	 *  DATE:06/10/2019
	 */
	@Test
	public void PrintToConsole() {
		ProjectController pr1 = new ProjectController();
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		
		// After this all System.out.println() statements will come to outContent stream.
		
		// So, you can normally call,
		pr1.printToConsole(); // I will assume items is already initialized properly.
		
		//Now you have to validate the output. Let's say items had 1 element.
		// With name as FirstElement and number as 1.
				
		// Do the actual assertion.
		assertEquals("Project Name: Dog Name\n" + 
                "Project Description: This is a woof project\n" + 
                "Project Budget: $0.00\n" + 
                "Project Location: Woof Location\n" + 
                "----------------------------\n"  
                , outContent.toString());
		
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
	 *  DATE:06/10/2019
	 */

	@Test
	public void testProjectString() {
		//myProject.setName("MyPro");

		ProjectController myPr1 = new ProjectController();
		assertEquals("Project Name: Dog Name\n" + 
				"Project Description: This is a woof project\n" + 
				"Project Budget: $0.00\n" + 
				"Project Location: Woof Location\n" + 
				"----------------------------\n" + 
				"No items currently.", myPr1.getProjectString());
		
	}
	
	
//	@Test
//	public void testProjectString1l() {
//		
//		
//		ProjectController myPr1 = new ProjectController();
//		myPr1.addItem("FURNANCE");
//		assertEquals("FURNANCE",myPr1.getProjectString());
//	}
		
//		myPr1.setName("New One");
//		myPr1.setLocation("WA");
//		myPr1.setBudget(100.00);
//		myPr1.setDescription("new");
//		assertEquals("Project Name: New One\n" + 
//				"Project Description: new\n" + 
//				"Project Budget: $100.00\n" + 
//				"Project Location: WA\n" + 
//				"----------------------------\n" + 
//				"No items currently.", myPr1.getProjectString());
		
//		assertEquals("New ",myPr1.getName());
//		assertEquals("WA",myPr1.getLocation());
//		assertEquals(200,200);
//		assertEquals("This is new pro",myPr1.getProjectString());
		
		
	
	
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
	 *  DATE:06/10/2019
	 */
	
	@Test
	public void testclearAll() {
		myProject  = new ProjectController();
		myProject.addItem("");
		myProject.clearAllItems();
			
	}
	
	
	
	@Test
	public void testFormattedBudgetAsString() {
		ProjectController myPr1 = new ProjectController();
		myPr1.setBudget(1.38);

       
		assertEquals("1.38",myPr1.getFormattedBudgetAsString());
	}
	
	
	
	
	
	
	/**
	 * author Sharanjit Singh
	 * This method testing the removeItem method.
	 */
	@Test
	public void testremoveItem() {
		
		ProjectController myPr1 = new ProjectController();
		myPr1.addItem("FURNANCE");
		myPr1.removeItem("FURNANCE");

		assertEquals(0,myPr1.myProject.myItems.size());	
		
	}
	
	/**
	 * author Sharanjit Singh
	 * This method testing the removeItem method.
	 * Checking the null point exception.
	 */
	@Test (expected = IllegalArgumentException.class)
	public void testremoveItemNull() {
		ProjectController myPr1 = new ProjectController();
		
		myPr1.removeItem(null);
		
	}
	
	
	
	/**
	 * author Sharanjit Singh
	 * This method testing the addItem method.
	 * checking the null point exception.
	 */
	@Test (expected = IllegalArgumentException.class)
	public void testaddItemnull() {
		
		ProjectController myPr1 = new ProjectController();
		myPr1.addItem(null);
		
	}
	
	/**
	 * author Sharanjit Singh
	 * This method testing the addItem method.
	 * checking the null point exception when there is same item.
	 */
	@Test (expected = IllegalArgumentException.class)
	public void testaddItemnull1() {
		
		ProjectController myPr1 = new ProjectController();
		myPr1.addItem("Furnance");
		myPr1.addItem("Furnance");
		
		
	}
	
	
	
	@Test 
	public void testclearBasicInfo() {
		ProjectController myPr1 = new ProjectController();
		myPr1.setName("Dog Name\n");
		myPr1.setBudget(0.00);
		myPr1.setLocation("Woof Location\n");
		myPr1.setDescription("This is a woof project\n");
		

		
	//	assertEquals(" ", myPr1.clearAllItems());
		
	}
//	"Project Name: Dog Name\n" + 
//	"Project Description: This is a woof project\n" + 
//	"Project Budget: $0.00\n" + 
//	"Project Location: Woof Location\n" + 
//	"----------------------------\n" + 
//	"No items currently."
//	

	
	@Test
	public void testremoveFieldFromItem() {
		ProjectController myPr1 = new ProjectController();
		myPr1.addItem("Fur");
		
		myPr1.removeFieldFromItem("Fur", "Fur");
		
		
		
		
		
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testremoveFieldFromItemnull() {
		ProjectController myPr1 = new ProjectController();
		
		myPr1.removeFieldFromItem(null, null);
		
		
		
		
	}
	@Test
	public void testaddToField() {
		ProjectController myPr1 = new ProjectController();
		myPr1.addItem("a");
		myPr1.addFieldToItem("a", "a", "a", "a", "a");
		
		
	}
	
	
	
	
	
	@Test (expected = IllegalArgumentException.class)
	public void testaddFieldFromItemnull() {
		ProjectController myPr1 = new ProjectController();
		
		myPr1.addFieldToItem(null, null, null, null, null);
		
	
	}
	
	
	
	
	

}

