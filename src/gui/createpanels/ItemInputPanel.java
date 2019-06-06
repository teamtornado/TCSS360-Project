package gui.createpanels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

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
import model.SchemaField;
import model.schemautil.SchemaTypes;
import net.miginfocom.swing.MigLayout;
import javax.swing.JScrollPane;
import javax.swing.BoxLayout;

public class ItemInputPanel extends JPanel implements ActionListener {

	/**
	 * Default serial version
	 */
	private static final long serialVersionUID = 1L;
	
	private SchemaController myRules;
	
	private JComboBox<String> myChildTypeDropDown;
	
	private JComboBox<String> myItemtypeDropDown;

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
		stupidMessage.setText("plz");
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
		myItemtypeDropDown = new JComboBox<String>(itemStrings);
		itemTypeChooserPanel.add(myItemtypeDropDown);
		myItemtypeDropDown.addActionListener(this);
		
		myChildTypeDropDown = new JComboBox<String>(itemStrings);
//		myChildTypeDropDown.setEnabled(false);
		myChildTypeDropDown.addActionListener(this);
		itemTypeChooserPanel.add(myChildTypeDropDown);
		

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
//		for (int i = 1; i <= 50; i++) {
//			fieldListContainer.add(new JButton("woof " + i));
			// Instead, you'd be adding all the available fields offered by each child and
			// all parents.
//		}

		// So basically, I think all you have to do is create a special panel type that
		// can show the info from the schema and allow input from the user. It should
		// also have getters so we can query each tile for their values and upload them
		// into items into the project with the edit controller.
	}

	@SuppressWarnings("unchecked")
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == myItemtypeDropDown) {
			System.out.println("parent");
			JComboBox<String> cb = (JComboBox<String>) e.getSource();
			String parentName = (String) cb.getSelectedItem();
			System.out.println(parentName);
			// Get all the child type
			List<String> childTypes = myRules.getChildTypes(parentName);
			System.out.println("Length of childTypes: " + childTypes.size());
			// Clear out the old type
			myChildTypeDropDown.removeAllItems();
			for (String child : childTypes) {
				myChildTypeDropDown.addItem(child);
			}
			myChildTypeDropDown.setEnabled(true);
		} else if (e.getSource() == myChildTypeDropDown) {
			System.out.println("child");
		}
		
		
//		if (e.getSource() == myItemtypeDropDown) {
			
//		} 
//		else if (e.getSource() == myChildTypeDropDown) {
//			JComboBox<String> cb = (JComboBox<String>) e.getSource();
//			String childType = (String) cb.getSelectedItem();
//			System.out.println(childType);
//			List<SchemaField> schemaTypes = myRules.getSchemaFieldsFromItem(childType);
//			for (SchemaField schemaField : schemaTypes) {
//				System.out.println(schemaField.getSchemaFieldName() + " " 
//								 + schemaField.getDescription() + " "
//								 + schemaField.getValueType() + " "
//								 + schemaField.isRequired() + "\n");
//			}
//		}
		
		
	}
}
