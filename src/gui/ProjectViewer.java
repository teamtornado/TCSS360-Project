package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

import controller.ProjectLoadController;
import controller.ProjectViewController;
import gui.createpanels.ScrollablePane;

/**
 * This class displays the contents of the project and allows the user to export
 * the project.
 * 
 * @author Curran, Sharanjit
 *
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
	private ProjectViewController myViewer;

	/**
	 * Allows for loading, saving, and creating projects.
	 */
	private ProjectLoadController myLoader;

	private ScrollablePane myProjectSummary;
	
	//private JPanel myCenterPanel;

	public ProjectViewer(final ProjectViewController theViewer, final ProjectLoadController theLoader, 
			final JButton theBackButton, final JButton theEditButton) {
		this.myViewer = theViewer;
		this.myLoader = theLoader;
		//myCenterPanel = new JPanel(new BorderLayout());
		//myCenterPanel.setPreferredSize(new Dimension(600, 400));
		this.setLayout(new BorderLayout());

		final JPanel buttonPanel = new JPanel();

		final JTextArea myData = new JTextArea(5, 5);
		myProjectSummary = new ScrollablePane(new Dimension(10, 10), false, myData, 20);

		final JTabbedPane projectPane = new JTabbedPane();
		projectPane.setBorder(BorderFactory.createEmptyBorder(TABPANE_PADDING, TABPANE_PADDING,
				TABPANE_PADDING, TABPANE_PADDING));
		projectPane.addTab(myViewer.getName(), myProjectSummary);

		this.add(buttonPanel, BorderLayout.SOUTH);
		this.add(projectPane, BorderLayout.CENTER);


		JButton export = new JButton("Export");

		export.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				PrintStream output;
				try {
					output = new PrintStream(myViewer.getName() + "_EXPORTED.txt");
					output.print(myViewer.getProjectString());
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
	 * @author Curran
	 * @param theString
	 */
	public void addData(final String theString) {
		myProjectSummary.addText(theString);
	}
	
	/**
	 * @author Curran
	 */
//	public void start() {
//        setTitle("Project Viewer");
//        add(myCenterPanel, BorderLayout.CENTER);
//        //add(myEastPanel, BorderLayout.CENTER);
//        //add(myStatusPanel, BorderLayout.SOUTH);
//        pack();
//        setLocationRelativeTo(null);
//        setResizable(false);
//        setVisible(true);
//    }
}
