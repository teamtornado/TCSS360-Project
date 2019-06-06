package gui.createpanels;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * @author not Eric
 */
public class FieldInputPanel extends JPanel {

	private final String myFieldName;

	private final String myFieldDescription;

	private final String myFieldValueType;

	private final JTextField myInputField;

	private static final Random myRandom = new Random();

	public FieldInputPanel(final String theFieldName, final String theFieldDescription,
			final String theFieldValueType) {
		this.myFieldDescription = theFieldDescription;
		this.myFieldValueType = theFieldValueType;
		this.myFieldName = theFieldName;
		this.setLayout(new BorderLayout());
		this.setBorder(BorderFactory.createBevelBorder(3));
		this.setPreferredSize(new Dimension(150, 60));
		this.setMaximumSize(new Dimension(160, 60));
		final JLabel fieldNameLabel = new JLabel(theFieldName);
		this.add(fieldNameLabel, BorderLayout.NORTH);
		myInputField = new JTextField();
		myInputField.setMinimumSize(new Dimension(100, 50));
		myInputField.setEditable(true); // this is where they put the value.
		this.add(myInputField, BorderLayout.CENTER);
	}

	public String getFieldName() {
		// Grab the field name showed on the label.
		return myFieldName;
	}

	public String getValue() {
		// Grab the value stored in the input field.
		return myInputField.getText();
	}

	public String getDescription() {
		return myFieldDescription;
	}

	public String getValueType() {
		return myFieldValueType;
	}
}
