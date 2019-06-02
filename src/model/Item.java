package model;

import java.util.LinkedList;
import java.util.List;

/**
 * An instance of this class holds a part of information of a project.
 * 
 * For example, an item instance could be of type 'Stove' and hold a gas inlet
 * size and burner count field.
 * 
 * Inside the fields are the actual values of gas inlet size and the number of
 * burners.
 * 
 * @author Eric
 *
 */
public class Item {

	/**
	 * The kind of item. Could be a 'Stove', 'Appliance', or 'Insulation'.
	 */
	private String myItemType;

	/**
	 * The parts of data held within this item.
	 */
	private List<ItemField> myFields;

	/**
	 * Constructs this kind of item.
	 * 
	 * @param myItemType
	 *            The kind of item. Could be 'Stove', 'Appliance', or 'Insulation'
	 * @param myFields
	 *            The different parts of information of this item.
	 * @author Eric
	 */
	public Item(String myItemType, List<ItemField> myFields) {
		super();
		this.myItemType = myItemType;
		this.myFields = myFields;
	}

	/**
	 * The kind of item.
	 * 
	 * @return the kind of item.
	 * @author Eric
	 */
	public String getMyItemType() {
		return myItemType;
	}

	/**
	 * ** Returns a NEW list of ItemFields. This class is Immutable.
	 * 
	 * @return a new list of this items parts of data (ItemField)s.
	 * @author Eric
	 */
	public List<ItemField> getMyFields() {
		final List<ItemField> newListOfItemFields = new LinkedList<>();
		for (ItemField field : this.myFields) {
			newListOfItemFields.add(field);
		}
		return newListOfItemFields;
	}
}