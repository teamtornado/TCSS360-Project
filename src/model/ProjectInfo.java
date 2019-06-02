package model;

import java.util.Currency;
import java.util.List;

public class ProjectInfo {

	private String myName;
	private String myProjectDescription;
	private Currency myBudget; // Have look up how to use this library
	private String myLocation;
	private List<Item> myItems; // No getter and setter for this

	public String getMyName() {
		return myName;
	}

	public void setMyName(String myName) {
		this.myName = myName;
	}

	public String getMyProjectDescription() {
		return myProjectDescription;
	}

	public void setMyProjectDescription(String myProjectDescription) {
		this.myProjectDescription = myProjectDescription;
	}

	public Currency getMyBudget() {
		return myBudget;
	}

	public void setMyBudget(Currency myBudget) {
		this.myBudget = myBudget;
	}

	public String getMyLocation() {
		return myLocation;
	}

	public void setMyLocation(String myLocation) {
		this.myLocation = myLocation;
	}
}
