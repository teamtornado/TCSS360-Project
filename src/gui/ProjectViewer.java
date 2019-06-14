package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

import controller.ProjectController;

/**
 * This class displays the contents of the project and allows the user to export
 * the project.
 * 
 * @author Curran, Sharanjit, Eric
 * @since 6/5/19
 */
public class ProjectViewer extends JPanel {

	/**
	 * Auto Generated Serial.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Amount of padding for the tabbed pane.
	 */
	private static final int TABPANE_PADDING = 10;

	/**
	 * Allows for viewing the user's project contents.
	 */
	private ProjectController myProject;

	/**
	 * The project summary
	 */
	private ScrollablePane myProjectSummary;

	/**
	 * Constructs a ProjectViewer, the GUI element that allows users to view
	 * projects that they've already created.
	 * 
	 * @param theProject
	 *            The project controller, allows for manipulation of the currently
	 *            loaded project data structure.
	 * @param theBackButton
	 *            Button with functionality to move the user back to the main menu
	 *            when clicked.
	 * @param theEditButton
	 *            Button that allows the user to jump to the project creation screen
	 *            with the currently loaded project so they can edit it.
	 * @author Sharanjit, Curran, Eric
	 * @since 6/5/19
	 */
	public ProjectViewer(final ProjectController theProject, final JButton theBackButton,
			final JButton theEditButton) {
		this.myProject = theProject;
		this.setLayout(new BorderLayout());

		final JPanel buttonPanel = new JPanel();

		final JTextArea myData = new JTextArea(5, 5);
		myProjectSummary = new ScrollablePane(new Dimension(10, 10), false, myData, 20);

		final JTabbedPane projectPane = new JTabbedPane();
		projectPane.setBorder(BorderFactory.createEmptyBorder(TABPANE_PADDING, TABPANE_PADDING,
				TABPANE_PADDING, TABPANE_PADDING));
		projectPane.addTab("Project Name", myProjectSummary);

		this.add(buttonPanel, BorderLayout.SOUTH);
		this.add(projectPane, BorderLayout.CENTER);

		JButton export = new JButton("Export");

		export.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				PrintStream output;
				try {
					output = new PrintStream(myProject.getName() + "_EXPORTED.txt");
					output.print(myProject.getProjectString());
					JOptionPane.showMessageDialog(myProjectSummary, "Project exported.");
				} catch (FileNotFoundException e1) {
					JOptionPane.showMessageDialog(myProjectSummary, "Error occured.");
				}
			}
		});

		buttonPanel.add(theBackButton);
		buttonPanel.add(theEditButton);
		buttonPanel.add(export);

	}

	/**
	 * Sets the given String to the project summary.
	 * 
	 * @param theString
	 *            String to be sent to the project summary.
	 * @author Curran
	 * @since 6/5/19
	 */
	public void addData(final String theString) {
		myProjectSummary.setText(theString);
	}
}
