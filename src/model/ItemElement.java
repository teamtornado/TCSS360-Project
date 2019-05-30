package model;

public class ItemElement {
	private String myItemName;
	private String myItemDescription;
	private String myItemValue;

	public ItemElement(final String theName, final String theItemDescription,
			final String theItemValue) {
		this.myItemName = theName;
		this.myItemDescription = theItemDescription;
		this.myItemValue = theItemValue;
	}

	public String getItemName() {
		return myItemName;
	}

	public String getItemDescription() {
		return myItemDescription;
	}

	public String getItemValue() {
		return myItemValue;
	}
}
