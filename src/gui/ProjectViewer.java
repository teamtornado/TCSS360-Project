package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import controller.ProjectLoadController;
import controller.ProjectViewController;
import gui.createpanels.BasicInfoPanel;

/**
 * This class displays the contents of the project and allows the user to export
 * the project.
 * 
 * @author Sharanjit
 *
 */
public class ProjectViewer extends JPanel {

	/**
	 * Auto Generated Serial.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Allows for viewing the user's project contents.
	 */
	private ProjectViewController myViewer;

	/**
	 * Allows for loading, saving, and creating projects.
	 */
	private ProjectLoadController myLoader;

	public ProjectViewer(final ProjectViewController theViewer,
			final ProjectLoadController theLoader) {
		super();
		this.myViewer = theViewer;
		this.myLoader = theLoader;
		this.setLayout(new BorderLayout());
		
		final JPanel projectSummary = new JPanel();
		final JPanel buttonPanel = new JPanel(new BorderLayout());
		final JTextArea myData = new JTextArea(5, 5);
		
		final JScrollPane dataScroll = new JScrollPane(myData);
        dataScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        dataScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        dataScroll.setPreferredSize(new Dimension(100, 100));
        myData.setEditable(false);
		this.add(buttonPanel, BorderLayout.SOUTH);
		this.add(dataScroll, BorderLayout.CENTER);
		
		
		JButton edit = new JButton("Edit");
		edit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String[] optionStrings = { "Yes", "No" };
				int x = JOptionPane.showOptionDialog(null,
						"You will lose all progress if you back out. Proceed?", "Warning",
						JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null,
						optionStrings, optionStrings[0]);

				System.out.println(x);
			}
		});
		
		JButton export = new JButton("Export");
		
		export.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String[] optionStrings = { "Yes", "No" };
				int x = JOptionPane.showOptionDialog(null,
						"You will lose all progress if you back out. Proceed?", "Warning",
						JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null,
						optionStrings, optionStrings[0]);

				System.out.println(x);
			}
		});
		
		
		buttonPanel.add(edit, BorderLayout.WEST);
		buttonPanel.add(export, BorderLayout.EAST);
		
	}

}
