package gui.subpanels;

import javax.swing.JPanel;

/**
 * This will track the progress of the Project creation process by showing the
 * viewer a visual flag for each suceeding step.
 * 
 * @author Eric
 *
 */
public class ProgressPanel extends JPanel {

	/**
	 * Auto generated Serial ID.
	 */
	private static final long serialVersionUID = -3690794805692756025L;

	private int myCurrentState;

	public ProgressPanel() {
		this.myCurrentState = 0;
	}

	public void nextState() {
		if (this.myCurrentState < 3) {
			this.myCurrentState++;
		}
	}

	public void previousState() {
		if (this.myCurrentState > 0) {
			this.myCurrentState--;
		}
	}

}
