package gui.createpanels;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;

import controller.SchemaController;
import net.miginfocom.swing.MigLayout;
import javax.swing.JScrollPane;
import javax.swing.BoxLayout;

public class ItemInputPanel extends JPanel {

	/**
	 * Default serial version
	 */
	private static final long serialVersionUID = 1L;
	
	private SchemaController myRules;

	/**
	 * Create the panel.
	 */
	public ItemInputPanel(SchemaController theRules) {
		myRules = theRules;
		setLayout(new MigLayout("", "[300.00,grow,left][600.00px,grow,right][-634.00]", "[grow]"));

		JPanel currentItemsViewer = new JPanel();
		add(currentItemsViewer, "cell 0 0,grow");
		currentItemsViewer.setLayout(new BorderLayout(0, 0));

		JTextArea stupidMessage = new JTextArea();
		stupidMessage.setText(
				"This is where we will see all of the items currently loaded into the project.");
		currentItemsViewer.add(stupidMessage, BorderLayout.CENTER);

		JPanel itemAdder = new JPanel();
		itemAdder.setBorder(new EtchedBorder(EtchedBorder.RAISED, Color.LIGHT_GRAY, null));
		add(itemAdder, "cell 1 0,grow");
		itemAdder.setLayout(new BorderLayout());

		JPanel itemTypeChooserPanel = new JPanel();
		itemTypeChooserPanel.setBackground(Color.LIGHT_GRAY);
		itemAdder.add(itemTypeChooserPanel, BorderLayout.NORTH);

//		JButton upButton = new JButton("Up");
//		itemTypeChooserPanel.add(upButton);

		// Drop down menu for the item-types
		String[] itemStrings = theRules.getAllParentTypes().stream().toArray(String[] :: new);
		JComboBox<String> itemtypeDropDown = new JComboBox<String>(itemStrings);
		itemTypeChooserPanel.add(itemtypeDropDown);

//		JMenuItem firstItem = new JMenuItem("First Item");
//		itemtypeDropDown.add(firstItem);
//
//		JMenuItem secondItem = new JMenuItem("Second Item");
//		itemtypeDropDown.add(secondItem);

		// I don't know why the menu thing isn't working... -> but you get the idea.

		JButton addItemButton = new JButton("Add Item");
		itemTypeChooserPanel.add(addItemButton);

		JScrollPane scrollPane = new JScrollPane();
		final int lessShittyIncrement = 15;
		scrollPane.getVerticalScrollBar().setUnitIncrement(lessShittyIncrement);
		itemAdder.add(scrollPane, BorderLayout.CENTER);

		JPanel fieldListContainer = new JPanel();
		scrollPane.setViewportView(fieldListContainer);
		fieldListContainer.setLayout(new BoxLayout(fieldListContainer, BoxLayout.Y_AXIS));

		// Test! Check it out Minh!
		for (int i = 1; i <= 50; i++) {
			fieldListContainer.add(new JButton("woof " + i));
			// Instead, you'd be adding all the available fields offered by each child and
			// all parents.
		}

		// So basically, I think all you have to do is create a special panel type that
		// can show the info from the schema and allow input from the user. It should
		// also have getters so we can query each tile for their values and upload them
		// into items into the project with the edit controller.
	}
}
