package tests;

import static org.junit.Assert.assertTrue;

import java.util.Currency;
import java.util.List;
import java.util.Locale;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import controller.ProjectDataEditController;
import controller.ProjectDataViewController;
import controller.ProjectLoadAndSaveController;
import model.ItemField;
import model.Project;
import model.SchemaTypes;

class ProjectTest {

	private ProjectDataEditController myEditor;
	private ProjectDataViewController myViewer;
	private ProjectLoadAndSaveController myImportExport;

	@BeforeEach
	void setup() {
		final Project project = new Project("WoofProject",
				"This Project is all about dogs",
				Currency.getInstance(Locale.US),
				"The United Empire of Doggos");

		myEditor = new ProjectDataEditController(project);
		myViewer = new ProjectDataViewController(project);
		myImportExport = new ProjectLoadAndSaveController(myEditor,
				myViewer);
	}

	@Test
	void createProjectTest() {
		// Add some items
		myEditor.addItem(SchemaTypes.APPLIANCE);
		myEditor.addItem(SchemaTypes.LIGHTING);

		// Add fields to the appliance item
		final String powerUsageFieldName = "Power usage";
		final String userNotesFieldName = "User notes";
		myEditor.addFieldToItem(SchemaTypes.APPLIANCE, powerUsageFieldName,
				"How much power the appliance uses", "Number", "1000");
		myEditor.addFieldToItem(SchemaTypes.APPLIANCE, userNotesFieldName,
				"User created notes", "String",
				"I think I might change my mind about this appliance");

		// Actually, I changed my mind. I won't want a lighting item anymore
		myEditor.removeItem(SchemaTypes.LIGHTING);

		// I also don't want user notes in my appliance item
		myEditor.removeFieldFromItem(SchemaTypes.APPLIANCE,
				userNotesFieldName);
	}

	@Test
	void viewProjectTest() {
		// Just setting this up from previous method.
		myEditor.addItem(SchemaTypes.APPLIANCE);
		myEditor.addItem(SchemaTypes.LIGHTING);

		final String powerUsageFieldName = "Power usage";
		final String userNotesFieldName = "User notes";
		myEditor.addFieldToItem(SchemaTypes.APPLIANCE, powerUsageFieldName,
				"How much power the appliance uses", "Number", "1000");
		myEditor.addFieldToItem(SchemaTypes.APPLIANCE, userNotesFieldName,
				"User created notes", "String",
				"I think I might change my mind about this appliance");

		myEditor.removeItem(SchemaTypes.LIGHTING);
		myEditor.removeFieldFromItem(SchemaTypes.APPLIANCE,
				userNotesFieldName);

		// Now onto some viewer stuff

		// I want to see all the different item-types in this
		final List<String> allItems = myViewer.getAllItemTypes();
		assertTrue(allItems.get(0).equals(SchemaTypes.APPLIANCE));

		// I want a specific field from a specific item now
		final ItemField field = myViewer.getFieldFromItem(
				SchemaTypes.APPLIANCE, powerUsageFieldName);
		final String fieldName = field.getFieldName();
		assertTrue(fieldName.equals(powerUsageFieldName));
		
		// What is the name of this project

		// Now I'm just debugging, show me the whole project in the console
		myViewer.printToConsole();
	}

}
