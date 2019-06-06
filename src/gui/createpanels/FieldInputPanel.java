package gui.createpanels;

import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * @author not Eric
 */
public class FieldInputPanel extends JPanel {

	private final String myFieldName;
	
	private final JTextField myInputField;

	public FieldInputPanel(final String theFieldName) {
		this.myFieldName = theFieldName;
		this.setBorder(BorderFactory.createBevelBorder(3));
		this.setPreferredSize(new Dimension(150, 60));
		final JLabel fieldNameLabel = new JLabel(theFieldName);
		this.add(fieldNameLabel);
		myInputField = new JTextField();
		myInputField.setMinimumSize(new Dimension(100, 50));
		myInputField.setEditable(true); // this is where they put the value.
		this.add(myInputField);
	}

	public String getFieldName() {
		// Grab the field name showed on the label.
		return myFieldName;
	}

	public String getValue() {
		// Grab the value stored in the input field.
		return myInputField.getText();
	}
}
