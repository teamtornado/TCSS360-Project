package model;

import java.util.Currency;
import java.util.List;

public class Project {

	private String myName;
	private String myProjectDescription;
	private Currency myBudget; // Have look up how to use this library
	private String myLocation;
	private List<Item> myItems; // No getter and setter for this

	public String getName() {
		return myName;
	}

	public void setName(String myName) {
		this.myName = myName;
	}

	public String getProjectDescription() {
		return myProjectDescription;
	}

	public void setProjectDescription(String myProjectDescription) {
		this.myProjectDescription = myProjectDescription;
	}

	public Currency getBudget() {
		return myBudget;
	}

	public void setBudget(Currency myBudget) {
		this.myBudget = myBudget;
	}

	public String getLocation() {
		return myLocation;
	}

	public void setLocation(String myLocation) {
		this.myLocation = myLocation;
	}
}
