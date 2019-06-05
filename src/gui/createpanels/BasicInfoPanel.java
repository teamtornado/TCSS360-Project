package gui.createpanels;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class BasicInfoPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BasicInfoPanel() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		JPanel name = new JPanel(new FlowLayout());
		name.add(new JLabel("Project name"));
		JTextField nameField = new JTextField(20);
		name.add(nameField);
		JPanel location = new JPanel(new FlowLayout());
		location.add(new JLabel("Location"));
		JTextField locationField = new JTextField(5);
		location.add(locationField);
		JPanel budget = new JPanel(new FlowLayout());
		budget.add(new JLabel("Budget"));
		budget.add(new JTextField(5));
		JPanel description = new JPanel();
		description.setLayout(new BoxLayout(description, BoxLayout.Y_AXIS));
		description.add(new JLabel("Description"));
		description.add(new JTextField(40));
		this.add(name);
		this.add(location);
		this.add(budget);
		this.add(description);
	}
}
