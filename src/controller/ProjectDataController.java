package controller;

import model.ProjectInfo;

/**
 * This class will manage the data going in and out of the project info class.
 * 
 * Allows for altering the contents of the project and for 
 * @author Eric
 *
 */
public class ProjectDataController {
	
	private ProjectInfo myProject;
	
	public ProjectDataController() {
		this.myProject = new ProjectInfo();
	}

}
