package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
//	private final ProjectEditController myEditor;

	/**
	 * Allows for viewing the user's project contents.
	 */
//	private final ProjectViewController myViewer;

	/**
	 * Holds the rules of the schema database. Holds item-type hierarchy and what
	 * each item-type has as fields. Does NOT hold user entered information.
	 */
//	private final SchemaController myRules;
	
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
	public CreatePanel() {
		//myMainFrame = theMainFrame;
		state = 1;
		this.setLayout(new BorderLayout());
		// the info stuffs
		BasicInfoPanel basicInfoPanel = new BasicInfoPanel();


		// adding stuffs together
		this.add(basicInfoPanel, BorderLayout.CENTER);
		JButton nextButton = new JButton("Next");
		this.add(nextButton, BorderLayout.EAST);
		JButton backButton = new JButton("Back");
		if (state == 1) {
			backButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					String[] optionStrings = {"Yes" , "No"};
					int x = JOptionPane.showOptionDialog(null, "You will lose all progress if you back out. Proceed?",
												 "Warning", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null,
												 optionStrings, optionStrings[0]);
					
					
				}
			});
		}
		this.add(backButton, BorderLayout.WEST);
	}
}