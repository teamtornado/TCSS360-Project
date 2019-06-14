package gui.createpanels;

import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * This panel allows the user to enter basic information about the project.
 * 
 * @author Minh
 * @since 6/5/19
 */
public class BasicInfoPanel extends JPanel {

	private final ArrayList<JTextField> textFields = new ArrayList<>();

	private JTextField myNameField;

	private JTextField myLocationField;

	private JTextField mybudgetField;

	private JTextField myDescriptionField;

	/**
	 * Auto generated serial ID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Sets up the info panel for the user to input basic project information.
	 * 
	 * @author Minh
	 * @since 6/5/19
	 */
	public BasicInfoPanel() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		JPanel name = new JPanel(new FlowLayout());
		name.add(new JLabel("Project name"));
		myNameField = new JTextField(20);
		textFields.add(myNameField);
		name.add(myNameField);

		JPanel location = new JPanel(new FlowLayout());
		location.add(new JLabel("State"));
		myLocationField = new JTextField(5);
		textFields.add(myLocationField);
		location.add(myLocationField);

		JPanel budget = new JPanel(new FlowLayout());
		budget.add(new JLabel("Budget"));
		mybudgetField = new JTextField(5);
		textFields.add(mybudgetField);
		budget.add(mybudgetField);

		JPanel description = new JPanel();
		description.setLayout(new BoxLayout(description, BoxLayout.Y_AXIS));
		description.add(new JLabel("Description"));
		myDescriptionField = new JTextField(40);
		textFields.add(myDescriptionField);
		description.add(myDescriptionField);

		this.add(name);
		this.add(location);
		this.add(budget);
		this.add(description);
	}

	/**
	 * Returns the project name from the project name input field.
	 * 
	 * @return the project name.
	 * @author Minh
	 * @since 6/5/19
	 */
	public String getProjectName() {
		return myNameField.getText();
	}

	/**
	 * Returns the project location from the project location input field.
	 * 
	 * @return the project location.
	 * @author Minh
	 * @since 6/5/19
	 */
	public String getProjectLocation() {
		return myLocationField.getText();
	}

	/**
	 * Returns the project budget from the budget input field.
	 * 
	 * @return the project budget as a double.
	 * @author Minh
	 * @since 6/5/19
	 */
	public double getProjectBudget() {
		return Double.parseDouble(mybudgetField.getText());
	}

	/**
	 * Returns the project description from the project description input field.
	 * 
	 * @return the project description.
	 * @author Minh
	 * @since 6/5/19
	 */
	public String getProjectDescription() {
		return myDescriptionField.getText();
	}

	/**
	 * Checks each field for input. If there is none, then false is returned.
	 * 
	 * @return False if there is a field without input, true otherwise.
	 * @author Minh
	 * @since 6/5/19
	 */
	public boolean checkAllField() {
		for (JTextField textField : textFields) {
			if (textField.getText().trim().isEmpty()) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Clears all fields of information.
	 * 
	 * @author Eric
	 * @since 6/1/19
	 */
	public void clearAllField() {
		for (JTextField textField : textFields) {
			textField.setText("");
		}
	}

	/**
	 * Takes the basic project info and loads them into the fields.
	 * 
	 * @param theProjectName
	 *            Name of the project.
	 * @param theProjectLocation
	 *            Location of the Project.
	 * @param theProjectBudget
	 *            Budget of the project as a String.
	 * @param theProjectDescription
	 *            Description of the project.
	 * @author Eric
	 * @since 6/1/19
	 */
	public void setAllFields(final String theProjectName, final String theProjectLocation,
			final String theProjectBudget, final String theProjectDescription) {
		this.myNameField.setText(theProjectName);
		this.myLocationField.setText(theProjectLocation);
		this.mybudgetField.setText(theProjectBudget);
		this.myDescriptionField.setText(theProjectDescription);
	}
}
