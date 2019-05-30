package model;

public class SchemaItemElement extends ItemElement {

	// Very important. Will use to make sure certain fields are taken by the
	// user.
	private boolean myIsRequired;

	public SchemaItemElement(final String theName, final String theItemDescription,
			final String theItemValue, final boolean isRequired) {
		super(theName, theItemDescription, theItemValue);
		this.myIsRequired = isRequired;
	}

	public boolean getIsRequired() {
		return this.myIsRequired;
	}
}
