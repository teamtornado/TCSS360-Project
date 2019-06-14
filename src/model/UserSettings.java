package model;

import java.awt.Dimension;

/**
 * UserSetting object to hold the settings to be imported into the program.
 * 
 * @author Minh Pham
 * @since 06/13/2019
 */
public class UserSettings {
	/**
	 * User entered frame dimensions.
	 */
	private Dimension myDimension;

	/**
	 * User entered email addresses.
	 */
	private String myEmail;

	/**
	 * Creates a UserSettings object for storing user information.
	 * 
	 * @param newDimension
	 *            The new dimension of the frame.
	 * @param newEmail
	 *            The new email given by the user.
	 * @author Minh
	 * @since 6/13/19
	 */
	public UserSettings(Dimension newDimension, String newEmail) {
		myDimension = newDimension;
		myEmail = newEmail;
	}

	/**
	 * Returns the user entered dimensions for the frame.
	 * 
	 * @return the user entered dimensions for the frame.
	 * @author Minh
	 * @since 6/13/19
	 */
	public Dimension getDimension() {
		return myDimension;
	}

	/**
	 * Returns the user entered email.
	 * 
	 * @return the user entered email.
	 * @author Minh
	 * @since 6/13/19
	 */
	public String getEmail() {
		return myEmail;
	}
}
