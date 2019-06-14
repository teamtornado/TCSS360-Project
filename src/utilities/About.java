package utilities;

/**
 * Holds information for the about dialog menu.
 * 
 * @author Minh Pham
 * @since 5/15/19
 */
public class About {
	private String teamName;
	private String version;
	private String members;

	/**
	 * Creates the about dialog menu information.
	 * 
	 * @author Minh
	 * @since 5/15/19
	 */
	public About() {
		teamName = "Team Tornado";
		version = "0.0.0.1";
		members = "\nEric Hoover\nMinh Pham\nSharanjit Singh\nCurran Seam";
	}

	/**
	 * Gets the printout for the about dialog menu.
	 * 
	 * @return the printout for the dialog menu.
	 * @author Minh
	 * @since 5/15/19
	 */
	public String getAbout() {
		StringBuilder result = new StringBuilder();
		result.append("Team Name: " + teamName + "\n");
		result.append("Version: " + version + "\n");
		result.append(members);
		return result.toString();
	}
}