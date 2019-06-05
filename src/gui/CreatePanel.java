package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;

import controller.ProjectEditController;
import controller.ProjectViewController;
import controller.SchemaController;
import gui.createpanels.BasicInfoPanel;

/**
 * 
 * An instance of this panel will allow the user to create a project.
 * 
 * @author Minh Pham
 *
 */
public class CreatePanel extends JPanel {
	/**
	 * Auto-generated serial.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Allows for editing the user's project.
	 */
	final ProjectEditController myEditor;

	/**
	 * Allows for viewing the user's project contents.
	 */
	final ProjectViewController myViewer;

	/**
	 * Holds the rules of the schema database. Holds item-type hierarchy and what
	 * each item-type has as fields. Does NOT hold user entered information.
	 */
	final SchemaController myRules;
	
	private int state;

	/**
	 * Constructs a create panel which will allow the user to assemble a project.
	 * 
	 * @param theEditor
	 *            the editor of the project.
	 * @param theViewer
	 *            the viewer of the project.
	 * @param theRules
	 *            the schema rules of the schema database. Describes possible fields
	 *            and item-types.
	 */
	public CreatePanel(final ProjectEditController theEditor, final ProjectViewController theViewer,
			final SchemaController theRules) {
		myEditor = theEditor;
		myViewer = theViewer;
		myRules = theRules;
		state = 1;
		this.setLayout(new BorderLayout());
		// the info stuffs
		BasicInfoPanel basicInfoPanel = new BasicInfoPanel();


		// adding stuffs together
		this.add(basicInfoPanel, BorderLayout.CENTER);
		JButton nextButton = new JButton("Next");
		this.add(nextButton, BorderLayout.EAST);
		JButton backButton = new JButton("Back");
		this.add(backButton, BorderLayout.WEST);
	}
}