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

/**
 * 
 * An instance of this panel will allow the user to create a project.
 * 
 * @author Minh, Curran
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

		// the info stuffs
		this.setLayout(new BorderLayout());
		JPanel infoPanel = new JPanel();
		infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
		JPanel name = new JPanel(new FlowLayout());
		name.add(new JLabel("Project name"));
		name.add(new JTextField(20));
		JPanel budget = new JPanel(new FlowLayout());
		budget.add(new JLabel("Budget"));
		budget.add(new JTextField(5));
		infoPanel.add(name);
		infoPanel.add(budget);

		// progress panel stuffs
		JPanel progressPanel = new JPanel();
		progressPanel.setLayout(new BoxLayout(progressPanel, BoxLayout.Y_AXIS));
		JSlider progressBar = new JSlider(JSlider.HORIZONTAL, 1, 3, 1);
		JPanel sliderPanel = new JPanel(new BorderLayout());
		sliderPanel.add(progressBar, BorderLayout.CENTER);
		BorderLayout temp = new BorderLayout();
		JPanel labelPanel = new JPanel(temp);
		progressPanel.add(sliderPanel);
		// label for progress panel
		labelPanel.add(new JLabel("Basic info"), BorderLayout.WEST);
		JPanel panel = new JPanel();
		panel.add(new JLabel("Select a category"));
		labelPanel.add(panel, BorderLayout.CENTER);
		labelPanel.add(new JLabel("Project information"), BorderLayout.EAST);
		progressPanel.add(labelPanel);

		// adding stuffs together
		this.add(progressPanel, BorderLayout.NORTH);
		this.add(infoPanel, BorderLayout.CENTER);
		panel = new JPanel();
		panel.add(new JButton("Next"));
		this.add(panel, BorderLayout.EAST);
		panel = new JPanel();
		panel.add(new JButton("Back"));
		this.add(panel, BorderLayout.WEST);
	}
}