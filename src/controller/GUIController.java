package controller;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
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
import javax.swing.JFrame;
import javax.swing.JLabel;
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
import model.UserSettings;
import utilities.About;
import utilities.FileParser;

/**
 * Controls the flow of the application.
 * 
 * @author Minh Pham, Curran Seam, Sharanjit Singh, Eric Hoover
 */

public class GUIController {

	/**
	 * The location for the rules of the database.
	 */
	public static final String SCHEMA_DATABASE_LOCATION = "SchemaData.txt";

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
	private static final int MIN_FRAME_WIDTH = 900;

	/**
	 * Minimum height of the main window
	 */
	private static final int MIN_FRAME_HEIGHT = 500;

	/**
	 * The fraction constant to provide relative calculations to the main window.
	 */
	private static final float FRACTION_OF_MAIN_WINDOW = 0.5f;

	/**
	 * The state of the program in the first panel
	 */
	private static final int FIRST_PANEL = 1;

	/**
	 * The state of the program in the second panel
	 */
	private static final int SECOND_PANEL = 2;

	/**
	 * Allows the user to enter basic information when creating a new project.
	 */
	private JPanel myCreatePanel;

	/**
	 * A panel for the user to add items into their project
	 */
	private ItemInputPanel myItemPanel;

	/**
	 * The main menu panel for the application.
	 */
	private JPanel mainPanel;
	
	/**
	 * String representing the user email.
	 */
	private String myEmail;
	
	/**
	 * String representing the sign in status
	 */
	private String mySignInStatus;

	/**
	 * The Frame of the application.
	 */
	private JFrame myWindow;

	/**
	 * Allows the user to view projects that have already been created.
	 */
	private ProjectViewer myProjectViewer;

	/**
	 * Panel that will get the basic information of the project
	 */
	private BasicInfoPanel myBasicInfoPanel;

	/**
	 * The current project of the application.
	 */
	private ProjectController myProject;

	/**
	 * Rules representing the hierarchy of item types.
	 */
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
	 * @since 06/03/19
	 */
	public GUIController() {
		myWindow = new JFrame();
		myProject = new ProjectController();
		myRules = new SchemaController(SCHEMA_DATABASE_LOCATION);
		myState = FIRST_PANEL;
		mainPanel = makeMainPanel();
		myProjectViewer = makeProjectViewer();
		myBasicInfoPanel = new BasicInfoPanel();
		myCreatePanel = makeCreatePanel();
		myItemPanel = new ItemInputPanel(myProject, myRules);
		mySignInStatus = "Sign In";
		myEmail = "";
		myWindow.setContentPane(mainPanel);
		setupFrameDimensions();
		setupJFrameIcon();
		setupMenuBar();
	}

	/**
	 * Creates the ProjectViewer panel and its components. Returns the resulting
	 * ProjectViewer.
	 * 
	 * @return the resulting ProjectViewer;
	 * @author Eric, Curran
	 * @since 06/03/19
	 */
	public ProjectViewer makeProjectViewer() {
		// Gotta make the back button to pass in so we can get out of the viewer.
		final JButton backButton = new JButton("Back");
		backButton.addActionListener(new ActionListener() {

			/**
			 * When clicked, takes the user to the main menu.
			 * 
			 * @author Eric
			 */
			@Override
			public void actionPerformed(final ActionEvent theEvent) {
				myWindow.setContentPane(mainPanel);
				myWindow.pack();
			}

		});

		// Now we need an edit button to take us into the createPanel
		final JButton editButton = new JButton("Edit");
		editButton.addActionListener(new ActionListener() {

			/**
			 * When clicked, takes the user to the editor panel.
			 * 
			 * @author Eric
			 */
			@Override
			public void actionPerformed(ActionEvent theEvent) {
				myWindow.setContentPane(myCreatePanel);

				// Pre-load the basicInfoPanel
				myBasicInfoPanel.setAllFields(myProject.getName(), myProject.getLocation(),
						myProject.getFormattedBudgetAsString(), myProject.getProjectDescription());
				myWindow.pack();
			}

		});

		final ProjectViewer projectViewer = new ProjectViewer(myProject, backButton, editButton);
		return projectViewer;
	}

	/**
	 * Creates the main menu for the opening of the application.
	 * 
	 * @return the main menu panel for the application.
	 * @author Minh Pham, Curran
	 * @since 06/04/19
	 */
	private JPanel makeMainPanel() {
		JPanel centerPanel = new JPanel(new BorderLayout());
		JPanel northPanel = new JPanel();
		JLabel emailLabel = new JLabel(myEmail);
		northPanel.add(emailLabel);
		JPanel contentPanel = new JPanel(new FlowLayout());
		JButton openButton = new JButton("Open Project");
		openButton.addActionListener(new ActionListener() {

			/**
			 * Opens the ProjectViewer panel and loads an existent file.
			 * 
			 * @author Eric, Curran
			 */
			@Override
			public void actionPerformed(final ActionEvent theEvent) {
				final int returnValue = myProject.loadProject(myWindow);
				if (returnValue == ProjectController.SUCCESS) {
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
					myProjectViewer.addData(myProject.getProjectString());
					myWindow.setContentPane(myProjectViewer);
					myWindow.pack();
				}
			}
		});
		contentPanel.add(openButton);
		JButton createButton = new JButton("Create Project");
		createButton.addActionListener(new ActionListener() {

			/**
			 * Opens the basic information panel and creates a new project.
			 * 
			 * @author Eric, Minh, Curran
			 */
			@Override
			public void actionPerformed(final ActionEvent theEvent) {
				myProject.createNewProject();
				myWindow.setContentPane(myCreatePanel);
				myWindow.pack();
			}
		});
		contentPanel.add(createButton);
		centerPanel.add(northPanel, BorderLayout.NORTH);
		centerPanel.add(contentPanel, BorderLayout.CENTER);
		return centerPanel;
	}

	/**
	 * Creates the project creation panel and returns it.
	 * 
	 * @return the project creation panel.
	 * @author Minh, Curran, Sharanjit
	 * @since 06/01/19
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
							// myEditor.setBasicInformation(myBasicInfoPanel);
							myProject.setName(projectName);
							myProject.setLocation(projectLocation);
							myProject.setBudget(projectBudget);
							myProject.setDescription(projectDescription);
							myItemPanel.updatePanel();
							createPanel.remove(myBasicInfoPanel);
							myItemPanel.updatePanel();
							createPanel.add(myItemPanel, BorderLayout.CENTER);
							createPanel.revalidate();
							createPanel.repaint();
						}
					}

				} else if (myState == SECOND_PANEL) {
					final int returnCondition = myProject.saveProject(myWindow);
					if (returnCondition == ProjectController.SUCCESS) {
						// If everything saved correctly!
						myState = FIRST_PANEL; // reset it for the next time around.
						myWindow.setContentPane(mainPanel); // go back to the main menu.
						myBasicInfoPanel.clearAllField();
						myItemPanel.clearAllFields();
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
			 * @author Minh Pham
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
	 * @author Minh Pham, Curran
	 * @since 05/28/19
	 */
	public void start() {
		myWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		myWindow.setTitle(MAIN_TITLE);
		myWindow.pack();
		myWindow.setLocationRelativeTo(null);
		myWindow.setVisible(true);
	}

	/**
	 * Sets up the header menu bar of the application. Includes the about and
	 * settings options.
	 * 
	 * @author Minh, Curran, Sharanjit
	 * @since 06/03/19
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
		final JMenuItem changeEmail = createEmailItem();

		// Hook the menus together
		helpMenu.add(aboutPage);
		settingMenu.add(changeSize);
		settingMenu.add(export);
		settingMenu.add(changeEmail);
		menuBar.add(helpMenu);
		menuBar.add(settingMenu);
		myWindow.setJMenuBar(menuBar);
	}

	/**
	 * Creates the dimensions of the main window and sets minimum sizes. Will also
	 * take user preference for window size.
	 * 
	 * @author Minh, Curran
	 * @since 06/03/19
	 */
	private void setupFrameDimensions() {
		myWindow.setMinimumSize(new Dimension(MIN_FRAME_WIDTH, MIN_FRAME_HEIGHT));
		UserSettings userSettings = null;
		try {
			userSettings = FileParser.parse(new File("USER_SETTINGS.txt"));
			myEmail = userSettings.getEmail();
			if (myEmail.equals("")) {
				mySignInStatus = "Sign In";
			} else {
				mySignInStatus = "Sign Out";
			}
		} catch (final FileNotFoundException theException) {
			System.out.println("File Parser: could not find settings file");
			theException.printStackTrace();
		}
		myWindow.setPreferredSize(userSettings.getDimension());
	}

	/**
	 * Creates the change size item for the menu bar
	 * 
	 * @return the change size item for the menu bar
	 * @author Minh, Curran, Sharanjit
	 * @since 06/04/19
	 */
	private JMenuItem createChangeSizeItem() {
		final JMenuItem changeSize = new JMenuItem();
		changeSize.setText("Set Frame Size");
		changeSize.addActionListener(new ActionListener() {
			@Override
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
	 * Creates the email item for the menu bar
	 * 
	 * @return the email item for the menu bar
	 * @author Curran
	 * @since 06/12/19
	 */
	private JMenuItem createEmailItem() {
		final JMenuItem emailItem = new JMenuItem();
		emailItem.setText("Sign In/Out");
		
		emailItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent theException) {
				if (mySignInStatus == "Sign In") { 
					myEmail = JOptionPane.showInputDialog("Enter Email");
					if (myEmail == null) {
						return;
					}
					emailItem.setText("Sign In/Out");
					mySignInStatus = "Sign Out";
				} else {
					JOptionPane.showMessageDialog(myWindow, "Successfully signed out of\n" + myEmail);
					mySignInStatus = "Sign In";
					myEmail = "";
					emailItem.setText("Sign In/Out");
				}
				
			}
		});

		return emailItem;
	}

	/**
	 * Creates the about page item to access about dialog
	 * 
	 * @return the about page item
	 * @author Minh, Curran, Sharanjit
	 * @since 05/28/19
	 */
	private JMenuItem createAboutPageItem() {
		final JMenuItem aboutPage = new JMenuItem();

		aboutPage.setText(ABOUT_MENU_TEXT);
		aboutPage.addActionListener(new ActionListener() {
			@Override
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
	 * @since 05/29/19
	 */
	private void setupDialogPanel(final JDialog theAboutMessage) {
		// theAboutMessage.setLocationRelativeTo(this);
		final JTextArea aboutMessage = new JTextArea();
		String message = new About().getAbout();

		aboutMessage.setText(message);
		aboutMessage.setEditable(false); // Don't want anyone changing the text field!
		aboutMessage.setAlignmentX(Component.CENTER_ALIGNMENT);
		aboutMessage.setAlignmentY(Component.CENTER_ALIGNMENT);
		theAboutMessage.add(aboutMessage);

		// Make the dialog a fraction of the size of the main window.
		theAboutMessage
				.setMinimumSize(new Dimension((int) (myWindow.getWidth() * FRACTION_OF_MAIN_WINDOW),
						(int) (myWindow.getHeight() * FRACTION_OF_MAIN_WINDOW)));

		theAboutMessage.setLocationRelativeTo(null);
		theAboutMessage.setVisible(true);
	}

	/**
	 * Creates the export menu bar item. Allows user to export the current frame
	 * size.
	 * 
	 * @return the export menu bar item
	 * @author Curran, Sharanjit
	 * @since 05/29/19
	 */
	private JMenuItem createExportItem() {
		final JMenuItem export = new JMenuItem();
		export.setText("Export User Settings");
		export.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent theEvent) {
				try {
					PrintStream output = new PrintStream("USER_SETTINGS.txt");
					output.println("Width: " + myWindow.getWidth());
					output.println("Height: " + myWindow.getHeight());
					output.println("User password: " + myEmail);
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
	 * @since 05/25/19
	 */
	private void setupJFrameIcon() {
		final ImageIcon img = new ImageIcon("tornadoIcon.png");
		myWindow.setIconImage(img.getImage());
	}

	/**
	 * Examines whether or not the given String contains illegal characters or not.
	 * Returns true if it does, false otherwise.
	 * 
	 * @param toExamine the String to check for illegal characters.
	 * @return boolean for if the string contains illegal characters or not.
	 * @author Curran, Eric, Sharanjit, Minh
	 * @since 06/05/19
	 */
	private boolean containsIllegals(String toExamine) {
		Pattern pattern = Pattern.compile("[~#@*+%{}<>\\[\\]|\"\'\\_^]");
		Matcher matcher = pattern.matcher(toExamine);
		return matcher.find();
	}
}
