package gui.createpanels;

import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class BasicInfoPanel extends JPanel {
	
	private JTextField myNameField;

	private JTextField myLocationField;
	
	private JTextField mybudgetField;
	
	private JTextField myDescriptionField;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BasicInfoPanel() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		JPanel name = new JPanel(new FlowLayout());
		name.add(new JLabel("Project name"));
		myNameField = new JTextField(20);
		name.add(myNameField);
		JPanel location = new JPanel(new FlowLayout());
		location.add(new JLabel("Location"));
		myLocationField = new JTextField(5);
		location.add(myLocationField);
		JPanel budget = new JPanel(new FlowLayout());
		budget.add(new JLabel("Budget"));
		mybudgetField = new JTextField(5);
		budget.add(mybudgetField);
		JPanel description = new JPanel();
		description.setLayout(new BoxLayout(description, BoxLayout.Y_AXIS));
		description.add(new JLabel("Description"));
		myDescriptionField = new JTextField(40);
		description.add(myDescriptionField);
		this.add(name);
		this.add(location);
		this.add(budget);
		this.add(description);
	}
	
	public String getProjectName() {
		return myNameField.getText();
	}
	
	public String getProjectLocation() {
		return myLocationField.getText();
	}
	
	public double getProjectBudget() {
		return Double.parseDouble(mybudgetField.getText());
	}
	
	public String getProjectDescription() {
		return myDescriptionField.getText();
	}
}
