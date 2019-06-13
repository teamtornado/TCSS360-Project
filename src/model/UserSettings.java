package model;

import java.awt.Dimension;
/**
 * UserSetting object to hold the setting
 * that is going to be imported into the program.
 * 
 * @author Minh Pham
 * @since 06/13/2019
 */
public class UserSettings {
	private Dimension myDimension;
	
	private String myEmail;
	
	public UserSettings(Dimension newDimension, String newEmail) {
		myDimension = newDimension;
		myEmail = newEmail;
	}
	
	public Dimension getDimension() {
		return myDimension;
	}
	
	public String getEmail() {
		return myEmail;
	}
}
