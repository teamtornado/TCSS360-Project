package gui.createpanels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;

import controller.ProjectController;
import controller.SchemaController;
import model.SchemaField;
import net.miginfocom.swing.MigLayout;

/**
 * This class allows users to input items with their fields into the project.
 * 
 * @author Eric
 * @since 6/5/19
 */
public class ItemInputPanel extends JPanel {

	/**
	 * Default serial version
	 */
	private static final long serialVersionUID = 1L;

	private SchemaController myRules;

	private ProjectController myProject;

	private JComboBox<String> myItemtypeDropDown;

	/**
	 * The action to be performed with the drop down menu.
	 */
	private ActionListener myDropDownAction;

	private JPanel myItemFieldPane;

	private JTextArea myCurrentItemViewState;

	private List<FieldInputPanel> myCurrentFields;

	private String selectedItemType;

	/**
	 * Create the panel. I am so sorry, this is a monster of a method...
	 * 
	 * @author Mostly Minh, Eric wouldn't have written something as messy as this.
	 * @since 6/6/19
	 */
	public ItemInputPanel(final ProjectController theProject, SchemaController theRules) {
		myRules = theRules;
		myProject = theProject;
		myCurrentFields = new LinkedList<FieldInputPanel>();
		selectedItemType = null;
		setLayout(new MigLayout("", "[300.00,grow,left][600.00px,grow,right][-634.00]", "[grow]"));

		JPanel currentItemsViewer = new JPanel();

		// Can't let the viewer get to small, or its useless
		currentItemsViewer.setMinimumSize(new Dimension(350, 300));
		add(currentItemsViewer, "cell 0 0,grow");
		currentItemsViewer.setLayout(new BorderLayout(0, 0));

		myCurrentItemViewState = new JTextArea();
		myCurrentItemViewState.setEditable(false);
		myCurrentItemViewState.setText("");
		currentItemsViewer.add(myCurrentItemViewState, BorderLayout.CENTER);

		JPanel itemAdder = new JPanel();
		itemAdder.setBorder(new EtchedBorder(EtchedBorder.RAISED, Color.LIGHT_GRAY, null));
		add(itemAdder, "cell 1 0,grow");
		itemAdder.setLayout(new BorderLayout());

		JPanel itemTypeChooserPanel = new JPanel();
		itemTypeChooserPanel.setBackground(Color.LIGHT_GRAY);
		itemAdder.add(itemTypeChooserPanel, BorderLayout.NORTH);

		// Drop down menu for the item-types
		List<String> parentList = theRules.getAllParentTypes();
		String[] parentString = parentList.stream().toArray(String[]::new);
		myItemtypeDropDown = new JComboBox<String>(parentString);
		itemTypeChooserPanel.add(myItemtypeDropDown);

		// Need a pointer to the parent panel.
		final ItemInputPanel thisPanel = this;

		myDropDownAction = new ActionListener() {

			@SuppressWarnings("unchecked")
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e == null) {
					// this is the reset button here
					selectedItemType = null;
					resetClick();
					return;
				}
				JComboBox<String> cb = (JComboBox<String>) e.getSource();
				String parentName = (String) cb.getSelectedItem();
				selectedItemType = parentName;
				// Get all the child type
				List<String> childTypes = myRules.getChildTypes(parentName);
				if (childTypes.size() != 0) {
					// Clear out the old type
					removeDropDownAction();
					myItemtypeDropDown.removeAllItems();
					for (String child : childTypes) {
						myItemtypeDropDown.addItem(child);
					}
					// Update the field lists
					fillFieldPane(parentName);
					addActionToDropDown();
				} else {
					// Still need to update the field list though
					fillFieldPane(parentName);
					JOptionPane.showMessageDialog(thisPanel, "There are no more child type");
					thisPanel.revalidate();
					thisPanel.repaint();
				}
			}
		};
		addActionToDropDown();

		JButton addItemButton = new JButton("Add Item");
		itemTypeChooserPanel.add(addItemButton);
		final ActionListener addItemAction = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent theEvent) {

				if (selectedItemType == null) {
					return; // agh don't do anything
				}

				// Prevent users from throwing loads of duplicate item-type exceptions. Kind've
				// a band-aid sort of fix.
				if (myProject.isDuplicateItem(selectedItemType)) {
					JOptionPane.showMessageDialog(null, "Cannot add duplicate item-types.");
					return;
				}
				// Grab the data from the fields and throw it into the project.
				myProject.addItem(selectedItemType);
				// Now load in the stuff from the field panels
				for (FieldInputPanel input : myCurrentFields) {
					myProject.addFieldToItem(selectedItemType, input.getFieldName(),
							input.getDescription(), input.getValueType(), input.getValue());
				}
				myDropDownAction.actionPerformed(null);
				// Now do all the item-type and field grabbing
				// might need to save a pointer to the current item-type that was last clicked
				// on.

				// As last step, we need to update the current item text area
				myCurrentItemViewState.setText(myProject.getProjectString());
				myCurrentItemViewState.removeAll();
				myCurrentFields.clear();
			}

		};
		addItemButton.addActionListener(addItemAction);

		JButton resetButton = new JButton("Reset");
		resetButton.setToolTipText("When you want to change your mind");
		final ActionListener resetAction = new ActionListener() {

			/**
			 * When reset is clicked, all the global parents are loaded into the drop down.
			 */
			@Override
			public void actionPerformed(ActionEvent theEvent) {
				// null to tell the dropDownAction that this event is coming elsewhere.
				myDropDownAction.actionPerformed(null);
				myItemFieldPane.removeAll();
				myCurrentFields.clear();
			}

		};
		resetButton.addActionListener(resetAction);
		itemTypeChooserPanel.add(resetButton);

		final JButton deleteItemButton = new JButton("Delete Item");
		deleteItemButton.addActionListener(new ActionListener() {

			/**
			 * Prompts the user to delete items from the project.
			 * 
			 * @author Eric
			 * @since 6/13/19
			 */
			@Override
			public void actionPerformed(final ActionEvent theEvent) {
				final DeleteItemFrame deleteFrame = new DeleteItemFrame(myProject, thisPanel,
						myProject.getAllItemTypes());
			}
		});
		itemTypeChooserPanel.add(deleteItemButton);

		final JScrollPane fieldScrollPane = new JScrollPane();
		final int lessShittyIncrement = 15;
		fieldScrollPane.getVerticalScrollBar().setUnitIncrement(lessShittyIncrement);
		itemAdder.add(fieldScrollPane, BorderLayout.CENTER);

		myItemFieldPane = new JPanel();
		fieldScrollPane.setViewportView(myItemFieldPane);
		myItemFieldPane.setLayout(new BoxLayout(myItemFieldPane, BoxLayout.Y_AXIS));
	}

	private void removeDropDownAction() {
		myItemtypeDropDown.removeActionListener(myDropDownAction);
	}

	private void addActionToDropDown() {
		myItemtypeDropDown.addActionListener(myDropDownAction);
	}

	/**
	 * Removed the items in the current drop down and loads the global parents.
	 * 
	 * @author Eric
	 * @since 6/5/19
	 */
	private void resetClick() {
		removeDropDownAction();
		myItemtypeDropDown.removeAllItems();
		final List<String> globalParents = myRules.getAllParentTypes();
		for (String parentType : globalParents) {
			myItemtypeDropDown.addItem(parentType);
		}
		// clear out the old fields
		this.myCurrentFields.clear();
		this.myItemFieldPane.removeAll();
		addActionToDropDown();
	}

	/**
	 * Adds all the fields inherited to the input field pane for the user.
	 * 
	 * @param theItemType
	 *            the item-type from which the fields will be derived.
	 * @author Eric, Minh
	 * @since 6/6/19
	 */
	private void fillFieldPane(final String theItemType) {
		// Clear out the old stuff
		this.myCurrentFields.clear();
		myItemFieldPane.removeAll();

		// Add in the new field panels.
		final List<SchemaField> fieldsToAdd = myRules.getInheritedFields(theItemType);
		for (SchemaField field : fieldsToAdd) {
			final FieldInputPanel newField = new FieldInputPanel(field.getSchemaFieldName(),
					field.getDescription(), field.getValueType());
			myItemFieldPane.add(newField);
			this.myCurrentFields.add(newField);
		}
	}

	/**
	 * Clears all the fields from the item selection panel and removes all items
	 * from the list.
	 * 
	 * @author Eric
	 * @since 6/6/19
	 */
	public void clearAllFields() {
		myCurrentItemViewState.setText("");
		myProject.clearAllItems();
	}

	/**
	 * Updates the current Item viewer panel with the most recent information.
	 * 
	 * @author Eric, Minh
	 * @since 6/5/19
	 */
	public void updatePanel() {
		myCurrentItemViewState.setText(myProject.getProjectString());
	}
}