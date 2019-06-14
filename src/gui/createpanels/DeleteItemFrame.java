package gui.createpanels;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import controller.ProjectController;

/**
 * Allows the user to delete items from their project.
 * 
 * @author Eric
 * @since 6/13/19
 */
public class DeleteItemFrame extends JFrame {

	private final ProjectController myProject;

	/**
	 * Creates a delete item frame to remove unwanted items from the project.
	 * 
	 * @param theProject
	 *            the project controller to delete the item through.
	 * @param theItemInputPanel
	 *            the input panel to update after delete
	 * @param theItemTypes
	 *            the item-type to delete from the project.
	 * @author Eric
	 * @since 6/13/19
	 */
	public DeleteItemFrame(final ProjectController theProject,
			final ItemInputPanel theItemInputPanel, final List<String> theItemTypes) {
		myProject = theProject;

		// Setup scrollpane
		final JScrollPane scrollPane = new JScrollPane();
		final JPanel scrollContent = new JPanel();
		scrollContent.setLayout(new BorderLayout());
		
		final JPanel directionsHolder = new JPanel();
		scrollContent.add(directionsHolder, BorderLayout.NORTH);
		final JLabel directions = new JLabel("Select the item-type to delete.");
		directionsHolder.add(directions);
		
		final JPanel buttonHolder = new JPanel();
		scrollContent.add(buttonHolder, BorderLayout.CENTER);

		// Set the viewport scrollPane.
		scrollPane.setViewportView(scrollContent);
		
		for (String itemType : theItemTypes) {
			// Create each delete button
			final JButton itemDeleteButton = new JButton(itemType);
			addDeleteAction(itemDeleteButton, theItemInputPanel, itemType);
			buttonHolder.add(itemDeleteButton);
		}
		
		this.setLocationRelativeTo(null);
		this.setMinimumSize(new Dimension(400, 200));
		this.add(scrollPane);
		this.pack();
		this.setVisible(true);
	}

	/**
	 * Deletes the given item-type when clicked and updates the UI.
	 * 
	 * @param theDeleteButton
	 *            the button to add an action to.
	 * @param theItemInputPanel
	 *            the panel to update.
	 * @param theItemType
	 *            the item-type to delete.
	 * @author Eric
	 * @since 6/13/19
	 */
	private void addDeleteAction(final JButton theDeleteButton,
			final ItemInputPanel theItemInputPanel, final String theItemType) {
		final DeleteItemFrame thisFrame = this;
		theDeleteButton.addActionListener(new ActionListener() {

			// Deletes the itemType from the project when pressed.
			@Override
			public void actionPerformed(final ActionEvent theEvent) {
				myProject.removeItem(theItemType);

				// Now that the project is missing a item, update the viewer.
				theItemInputPanel.updatePanel();

				// Close this frame now.
				thisFrame.setVisible(false);
				thisFrame.dispose(); // quit now that its job is done!
			}

		});
	}

}
