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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;

import controller.SchemaController;
import model.SchemaField;
import model.schemautil.SchemaTypes;
import net.miginfocom.swing.MigLayout;
import javax.swing.JScrollPane;
import javax.swing.BoxLayout;

public class ItemInputPanel extends JPanel {

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

		// Drop down menu for the item-types
		List<String> parentList = theRules.getAllParentTypes();
		String[] parentString = parentList.stream().toArray(String[] :: new);
		myItemtypeDropDown = new JComboBox<String>(parentString);
		itemTypeChooserPanel.add(myItemtypeDropDown);
		ActionListener itemChangeListener = new ActionListener() {
			
			@SuppressWarnings("unchecked")
			@Override
			public void actionPerformed(ActionEvent e) {		
			
//				System.out.println("?");
				JComboBox<String> cb = (JComboBox<String>) e.getSource();
				String parentName = (String) cb.getSelectedItem();
				System.out.println(parentName);
				// Get all the child type
				List<String> childTypes = myRules.getChildTypes(parentName);
				if (childTypes.size() != 0) {
					// Clear out the old type
					myItemtypeDropDown.removeActionListener(itemChangeListener);
					myItemtypeDropDown.removeAllItems();
					for (String child : childTypes) {
						myItemtypeDropDown.addItem(child);
					}
					myItemtypeDropDown.addActionListener(itemChangeListener); 
				} else {
					JOptionPane.showMessageDialog(currentItemsViewer, "There are no more child type");
				}
				
			}
		};
		myItemtypeDropDown.addActionListener(itemChangeListener);
		
//		String[] childStrings = {"<Select a parent type>"};
//		myChildTypeDropDown = new JComboBox<String>(childStrings);
//		myChildTypeDropDown.setEnabled(false);
//		myChildTypeDropDown.addActionListener(this);
//		itemTypeChooserPanel.add(myChildTypeDropDown);

		JButton addItemButton = new JButton("Add Item");
		itemTypeChooserPanel.add(addItemButton);
		
		JButton resetButton = new JButton("Reset");
		resetButton.setToolTipText("When you want to change your mind");
		resetButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				myItemtypeDropDown.removeActionListener(itemChangeListener);
				myItemtypeDropDown.removeAllItems();
				for (String item : parentList) {
					myItemtypeDropDown.addItem(item);
				}
				myItemtypeDropDown = new JComboBox<String>(parentString);
				myItemtypeDropDown.addActionListener(itemChangeListener);
			}
		});
		itemTypeChooserPanel.add(resetButton);

		JScrollPane scrollPane = new JScrollPane();
		final int lessShittyIncrement = 15;
		scrollPane.getVerticalScrollBar().setUnitIncrement(lessShittyIncrement);
		itemAdder.add(scrollPane, BorderLayout.CENTER);

		JPanel fieldListContainer = new JPanel();
		scrollPane.setViewportView(fieldListContainer);
		fieldListContainer.setLayout(new BoxLayout(fieldListContainer, BoxLayout.Y_AXIS));

		

		// So basically, I think all you have to do is create a special panel type that
		// can show the info from the schema and allow input from the user. It should
		// also have getters so we can query each tile for their values and upload them
		// into items into the project with the edit controller.
	}
}
