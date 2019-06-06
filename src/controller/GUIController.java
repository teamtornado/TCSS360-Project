package controller;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

import gui.ProjectViewer;
import gui.createpanels.BasicInfoPanel;
import gui.createpanels.ItemInputPanel;
import model.Project;
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
	 * 
	 */
	private static final int FIRST_PANEL = 1;

	/**
	 * 
	 */
	private static final int SECOND_PANEL = 2;

	/**
	 * 
	 */
	private JFileChooser myFileChooser;

	/**
	 * Allows the user to enter basic information when creating a new project.
	 */
	private JPanel myCreatePanel;

	/**
	 * A magical panel that does something.
	 */
	private JPanel myItemPanel;

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
	// private JPanel myCreatePanel;

	/**
	 * Allows the user to view projects that have already been created.
	 */
	private ProjectViewer myProjectViewer;

	private BasicInfoPanel myBasicInfoPanel;

	/**
	 * 
	 */
	private ProjectEditController myEditor;
	// private ProjectViewController myViewer;
	/**
	 * 
	 */
	private ProjectLoadController myLoader;
	// private SchemaController myRules;

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
	public GUIController() {
		myWindow = new JFrame();
		myFileChooser = new JFileChooser("./SavedProjects/");
		myLoader = new ProjectLoadController();
		myEditor = new ProjectEditController(myLoader);
		myState = FIRST_PANEL;
		mainPanel = makeMainPanel();
		myBasicInfoPanel = new BasicInfoPanel();
		myCreatePanel = makeCreatePanel();
		myItemPanel = new ItemInputPanel();
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
				final int returnValue = myLoader.loadProject(myWindow);
				if (returnValue == myLoader.SUCCESS) {
					// If the file loaded correctly, then switch the panels.
					JPanel tempPanel = new JPanel();
					JButton backButton = new JButton("Back");
					tempPanel.add(backButton);
					backButton.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent arg0) {
							myWindow.setContentPane(mainPanel);
							myWindow.pack();

						}
					});
					myWindow.setContentPane(tempPanel);
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
				myWindow.setContentPane(myCreatePanel);
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
		createPanel.add(myBasicInfoPanel, BorderLayout.CENTER);
		JButton nextButton = new JButton("Next");
		nextButton.addActionListener(new ActionListener() {

			/**
			 * Set how the next button function depending on the state of the program
			 * 
			 * @author Minh, Eric
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				if (myState == FIRST_PANEL) {

					// If the user tries to go to the next create panel without filling basic info
					// fields.
					
					if (!myBasicInfoPanel.checkAllField()) {
						JOptionPane.showMessageDialog(myWindow,
								"You need to filled all these fields before continue");
					} else {
						String projectName = myBasicInfoPanel.getProjectName();
						String projectLocation = myBasicInfoPanel.getProjectLocation();
						Double projectBudget = myBasicInfoPanel.getProjectBudget();
						String projectDescription = myBasicInfoPanel.getProjectDescription();
						
						if (containsIllegals(projectName)
								|| containsIllegals(Double.toString(projectBudget))
								|| containsIllegals(projectLocation)
								|| containsIllegals(projectDescription)) {
							JOptionPane.showMessageDialog(myWindow,
									"One of the field contains an illegal symbol\n "
											+ "Illegal symbols are ~ # @ * + % { } < > [ ] | \" \' \\ _ ^");
						} else {
							myState = SECOND_PANEL;
							// Set whatever the user entered into the project.
							myEditor.setBasicInformation(myBasicInfoPanel);
							createPanel.remove(myBasicInfoPanel);
							createPanel.add(myItemPanel, BorderLayout.CENTER);
							createPanel.revalidate();
							createPanel.repaint();
						}
					} 
					

				} else if (myState == SECOND_PANEL) {
					final int returnCondition = myLoader.saveProject(myWindow);
					if (returnCondition == ProjectLoadController.SUCCESS) {
						// If everything saved correctly!
						myState = FIRST_PANEL; // reset it for the next time around.
						myWindow.setContentPane(mainPanel); // go back to the main menu.
						createPanel.remove(myItemPanel);
						createPanel.add(myBasicInfoPanel, BorderLayout.CENTER);
						createPanel.revalidate();
						createPanel.repaint();
					}
					// If something when wrong or the user backed out of save.
					// want to stay on the same panel.
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
				if (myState == FIRST_PANEL) {
					String[] optionStrings = { "Yes", "No" };
					int x = JOptionPane.showOptionDialog(null,
							"You will lose all progress if you back out. Proceed?", "Warning",
							JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null,
							optionStrings, optionStrings[0]);
					// If user say yes
					if (x == 0) {
						myBasicInfoPanel.clearAllField();
						myWindow.setContentPane(mainPanel);
						myWindow.pack();
					}
				} else if (myState == SECOND_PANEL) {
					myState = FIRST_PANEL;
					createPanel.remove(myItemPanel);
					createPanel.add(myBasicInfoPanel, BorderLayout.CENTER);
					createPanel.revalidate();
					createPanel.repaint();
				}

			}

		});

		createPanel.add(backButton, BorderLayout.WEST);

		return createPanel;
	}

	/**
	 * Final setup including packing and setting the frame to visible.
	 * 
	 * @author Minh Pham
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

	private boolean containsIllegals(String toExamine) {
		Pattern pattern = Pattern.compile("[~#@*+%{}<>\\[\\]|\"\'\\_^]");
		Matcher matcher = pattern.matcher(toExamine);
		return matcher.find();
	}
}
