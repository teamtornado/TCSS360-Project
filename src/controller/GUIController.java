package controller;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

import gui.CreatePanel;
import gui.ProjectViewer;
import gui.createpanels.BasicInfoPanel;
import gui.createpanels.ItemInputPanel;
import utilities.About;
import utilities.FileParser;

/**
 * Controls the flow of the application.
 * 
 * @author Minh Pham, Curran, Sharanjit, Eric
 */

public class GUIController {

	/**
	 * The text displayed on the 'help' JMenu button.
	 */
	private static final String HELP_MENU_TEXT = "Help";

	/**
	 * The text displayed on the setting JMenu button.
	 */
	private static final String SETTING_MENU_TEXT = "Setting";

	/**
	 * The text displayed on the 'about' JMenu button.
	 */
	private static final String ABOUT_MENU_TEXT = "About";

	/**
	 * The text display the main title.
	 */
	private static final String MAIN_TITLE = "TCSS360 Project";

	/**
	 * The text name for the dialog panel.
	 */
	private static final String DIALOG_PANEL_NAME = "About";

	/**
	 * Minimum width of the main window
	 */
	private static final int MIN_FRAME_WIDTH = 300;

	/**
	 * Minimum height of the main window
	 */
	private static final int MIN_FRAME_HEIGHT = 200;

	/**
	 * The fraction constant to provide relative calculations to the main window.
	 */
	private static final float FRACTION_OF_MAIN_WINDOW = 0.5f;

	/**
	 * Allows the user to enter basic information when creating a new project.
	 */
	private JPanel basicInfoPanel;

	/**
	 * A magical panel that does something.
	 */
	private JPanel itemPanel;

	/**
	 * The main menu panel for the application.
	 */
	private JPanel mainPanel;

	/**
	 * The Frame of the application.
	 */
	private JFrame myWindow;

	/**
	 * The project creation panel for adding items and features to projects.
	 */
	private CreatePanel myCreatePanel;

	/**
	 * Allows the user to view projects that have already been created.
	 */
	private ProjectViewer myProjectViewer;

	private ProjectEditController myEditor;
	private ProjectViewController myViewer;
	private ProjectLoadController myLoader;
	private SchemaController myRules;

	/**
	 * The state of the creation panel.
	 */
	private int myState;

	/**
	 * Constructs a controller that handles input from the user interface and
	 * maintains flow of UI.
	 * 
	 * @author Eric, Minh, Curran, Sharanjit
	 */
	public GUIController(final ProjectEditController theEditor,
			final ProjectViewController theViewer, final ProjectLoadController theLoader,
			final SchemaController theRules) {
		myEditor = theEditor;
		myViewer = theViewer;
		myLoader = theLoader;
		myRules = theRules;
		myCreatePanel = new CreatePanel(theEditor, theViewer, theRules);
		myProjectViewer = new ProjectViewer(theViewer, theLoader);
		myWindow = new JFrame();
		myState = 1;
		mainPanel = makeMainPanel();
		basicInfoPanel = makeCreatePanel();
		itemPanel = new ItemInputPanel();
		// makeItemPanel();
		myWindow.setContentPane(mainPanel);
		setupFrameDimensions();
		setupJFrameIcon();
		setupMenuBar();
	}

	/**
	 * Creates the main menu for the opening of the application.
	 * 
	 * @return the main menu panel for the application.
	 * @author Minh
	 */
	private JPanel makeMainPanel() {
		JPanel contentPanel = new JPanel();
		JButton openButton = new JButton("Open Project");
		openButton.addActionListener(new ActionListener() {

			/**
			 * Opens the ProjectViewer panel and loads an existent file.
			 * 
			 * @author Eric
			 */
			@Override
			public void actionPerformed(final ActionEvent theEvent) {
				int returnCondition = myLoader.loadProject(myWindow);
				if (returnCondition == ProjectLoadController.SUCCESS) {
					// If the file loaded correctly, then switch the panels.
					myWindow.setContentPane(myProjectViewer);
					myWindow.pack();
				}
				// Something went weird, so leave us on the main menu.
			}
		});
		contentPanel.add(openButton);
		JButton createButton = new JButton("Create Project");
		createButton.addActionListener(new ActionListener() {

			/**
			 * Opens the basic information panel and creates a new project.
			 * 
			 * @author Eric, Minh
			 */
			@Override
			public void actionPerformed(final ActionEvent theEvent) {
				myLoader.createNewProject();
				myWindow.setContentPane(basicInfoPanel);
				myWindow.pack();
			}
		});
		contentPanel.add(createButton);
		return contentPanel;
	}

	/**
	 * Magical code that does something great.
	 * 
	 * @return from the dead.
	 * @author Unknown
	 */
	private JPanel makeItemPanel() {
		JPanel itemPanel = new JPanel();
		return itemPanel;
	}

	/**
	 * Creates the project creation panel and returns it.
	 * 
	 * @return the project creation panel.
	 * @author Minh, Curran, Sharanjit
	 */
	private JPanel makeCreatePanel() {
		JPanel createPanel = new JPanel();
		createPanel.setLayout(new BorderLayout());
		// the info stuffs

		BasicInfoPanel basicInfoPanel = new BasicInfoPanel();

		// adding stuffs together
		createPanel.add(basicInfoPanel, BorderLayout.CENTER);
		JButton nextButton = new JButton("Next");
		nextButton.addActionListener(new ActionListener() {

			/**
			 * Does a bunch of stuff that Minh should have left comments for.
			 * 
			 * @author Minh, Eric
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				if (myState == 1) {
					myState = 2;
					createPanel.remove(basicInfoPanel);
					createPanel.add(itemPanel, BorderLayout.CENTER);
					createPanel.revalidate();
					createPanel.repaint();
					// myWindow.pack();
				} else if (myState == 2) {
					// I think this is resetting it... ?? Minh, stop making magic numbers!!
					// Constants would help - especially for stuff like states.
					myState = 1;
					myLoader.saveProject(myWindow);

					// Don't know if I need to reset all this stuff for the next time around.
					createPanel.remove(itemPanel);
					createPanel.add(basicInfoPanel, BorderLayout.CENTER);
					createPanel.revalidate();
					createPanel.repaint();

					// Back to the main menu!
					myWindow.setContentPane(mainPanel);
				}
			}
		});
		createPanel.add(nextButton, BorderLayout.EAST);

		JButton backButton = new JButton("Back");

		backButton.addActionListener(new ActionListener() {

			/**
			 * Back button to move to the previous state or panel of the application flow.
			 * 
			 * @author Minh
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				// Oh my goodness, Minh -> add some freaking comments!
				// Also, 'x' is a pretty shit variable name.

				// There's also a bug in here and its like 4 in the morning now.
				// Go to the last panel of the create Project thing, hit next one last
				// time, the click cancel when the filechooser comes up.

				// It should stay on that last create panel instead of going back to the
				// main menu.
				if (myState == 1) {
					String[] optionStrings = { "Yes", "No" };
					int x = JOptionPane.showOptionDialog(null,
							"You will lose all progress if you back out. Proceed?", "Warning",
							JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null,
							optionStrings, optionStrings[0]);

					if (x == 0) {
						myEditor.clearAllItems();
						myEditor.resetBasicInformation();
						myWindow.setContentPane(mainPanel);
						myWindow.pack();
					}
				} else {
					myState = 1;
					createPanel.remove(itemPanel);
					createPanel.add(basicInfoPanel, BorderLayout.CENTER);
					createPanel.revalidate();
					createPanel.repaint();
					// myWindow.pack();
				}

			}
		});

		createPanel.add(backButton, BorderLayout.WEST);

		return createPanel;
	}

	/**
	 * Final setup including packing and setting the frame to visible.
	 * 
	 * @author Minh
	 */
	public void start() {
		myWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		myWindow.setTitle(MAIN_TITLE);
		myWindow.pack();
		myWindow.setVisible(true);
	}

	/**
	 * Sets up the header menu bar of the application. Includes the about and
	 * settings options.
	 * 
	 * @author Minh, Curran, Sharanjit
	 */
	private void setupMenuBar() {
		// Setup the JMenu
		final JMenuBar menuBar = new JMenuBar();
		final JMenu helpMenu = new JMenu();
		helpMenu.setText(HELP_MENU_TEXT);
		final JMenu settingMenu = new JMenu();
		settingMenu.setText(SETTING_MENU_TEXT);

		final JMenuItem aboutPage = createAboutPageItem();
		final JMenuItem changeSize = createChangeSizeItem();
		final JMenuItem export = createExportItem();

		// Hook the menus together
		helpMenu.add(aboutPage);
		settingMenu.add(changeSize);
		settingMenu.add(export);
		menuBar.add(helpMenu);
		menuBar.add(settingMenu);
		myWindow.setJMenuBar(menuBar);
	}

	/**
	 * Creates the dimensions of the main window and sets minimum sizes. Will also
	 * take user preference for window size.
	 * 
	 * @author Minh, Curran
	 */
	private void setupFrameDimensions() {
		myWindow.setMinimumSize(new Dimension(MIN_FRAME_WIDTH, MIN_FRAME_HEIGHT));
		Dimension savedDimensions = null;
		try {
			savedDimensions = FileParser.parse(new File("USER_SETTINGS.txt"));
		} catch (final FileNotFoundException theException) {
			System.out.println("File Parser: could not find settings file");
			theException.printStackTrace();
		}
		myWindow.setPreferredSize(savedDimensions);
	}

	/**
	 * Creates the change size item for the menu bar
	 * 
	 * @return the change size item for the menu bar
	 * 
	 * @author Minh, Curran, Sharanjit
	 */
	private JMenuItem createChangeSizeItem() {
		final JMenuItem changeSize = new JMenuItem();
		changeSize.setText("Set Frame Size");
		changeSize.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent theException) {
				final String widthInput = JOptionPane.showInputDialog("Input new width");
				if (widthInput == null) {
					return;
				}
				final int width = Integer.parseInt(widthInput);
				final String heightInput = JOptionPane.showInputDialog("Input new height");
				if (heightInput == null) {
					return;
				}
				final int height = Integer.parseInt(heightInput);
				myWindow.setSize(new Dimension(width, height));
			}
		});

		return changeSize;
	}

	/**
	 * Creates the about page item to access about dialog
	 * 
	 * @return the about page item
	 * 
	 * @author Minh, Curran, Sharanjit
	 */
	private JMenuItem createAboutPageItem() {
		final JMenuItem aboutPage = new JMenuItem();

		aboutPage.setText(ABOUT_MENU_TEXT);
		aboutPage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				final JDialog aboutDialog = new JDialog(myWindow, DIALOG_PANEL_NAME, true);
				setupDialogPanel(aboutDialog);
			}
		});

		return aboutPage;
	}

	/**
	 * Sets the 'about project' dialogue panel up.
	 * 
	 * @param theAboutMessage
	 *            The dialog panel itself to be setup.
	 * @author Curran, Sharanjit
	 */
	private void setupDialogPanel(final JDialog theAboutMessage) {
		// theAboutMessage.setLocationRelativeTo(this);
		final JTextArea aboutMessage = new JTextArea();
		String message = new About().getAbout();

		aboutMessage.setText(message);
		aboutMessage.setEditable(false); // Don't want anyone changing the text
											// field!
		aboutMessage.setAlignmentX(JTextArea.CENTER_ALIGNMENT);
		aboutMessage.setAlignmentY(JTextArea.CENTER_ALIGNMENT);
		theAboutMessage.add(aboutMessage);

		// Make the dialog a fraction of the size of the main window.
		theAboutMessage
				.setMinimumSize(new Dimension((int) (myWindow.getWidth() * FRACTION_OF_MAIN_WINDOW),
						(int) (myWindow.getHeight() * FRACTION_OF_MAIN_WINDOW)));

		theAboutMessage.setVisible(true);
	}

	/**
	 * Creates the export menu bar item. Allows user to export the current frame
	 * size.
	 * 
	 * @return the export menu bar item
	 * 
	 * @author Curran, Sharanjit
	 */
	private JMenuItem createExportItem() {
		final JMenuItem export = new JMenuItem();
		export.setText("Export Frame Size");
		export.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent theEvent) {
				try {
					PrintStream output = new PrintStream("USER_SETTINGS.txt");
					output.println("Width: " + myWindow.getWidth());
					output.println("Height: " + myWindow.getHeight());
					output.close();
					JOptionPane.showMessageDialog(myWindow, "Settings exported");
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
			}
		});
		return export;
	}

	/**
	 * Setup the icon for the JFrame.
	 * 
	 * @author Eric
	 */
	private void setupJFrameIcon() {
		final ImageIcon img = new ImageIcon("tornadoIcon.png");
		myWindow.setIconImage(img.getImage());
	}
}