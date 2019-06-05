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
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

import gui.createpanels.BasicInfoPanel;
import utilities.About;
import utilities.FileParser;

/**
 * 
 * 
 * @author Minh Pham
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
	 * 
	 */
	private JPanel basicInfoPanel;
	
	/**
	 * 
	 */
	private JPanel itemPanel;
	
	/**
	 * 
	 */
	private JPanel mainPanel;
	
	/**
	 * 
	 */
	private JFrame myWindow;
	
	/**
	 * 
	 */
	private int state;
	
	public GUIController() {
		myWindow = new JFrame();
		myFileChooser = new JFileChooser();
		state = FIRST_PANEL;
		mainPanel = makeMainPanel();
		basicInfoPanel = makeCreatePanel();
		itemPanel = makeItemPanel();
		myWindow.setContentPane(mainPanel);
		setupFrameDimensions();
		setupJFrameIcon();
		setupMenuBar();
	}
	
	private JPanel makeMainPanel() {
		JPanel contentPanel = new JPanel();
		JButton openButton = new JButton("Open Project");
		contentPanel.add(openButton);
		JButton createButton = new JButton("Create Project");
		createButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				myWindow.setContentPane(basicInfoPanel);
				myWindow.pack();
			}
		});
		contentPanel.add(createButton);
		return contentPanel;
	}
	
	private JPanel makeItemPanel() {
		JPanel itemPanel = new JPanel();
		
		return itemPanel;
	}
	
	private JPanel makeCreatePanel() {
		JPanel createPanel = new JPanel();
		createPanel.setLayout(new BorderLayout());
		// the info stuffs
		
		BasicInfoPanel basicInfoPanel = new BasicInfoPanel();
		
		// adding stuffs together
		createPanel.add(basicInfoPanel, BorderLayout.CENTER);
		JButton nextButton = new JButton("Next");
		nextButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (state == FIRST_PANEL) {
					state = SECOND_PANEL;
					createPanel.remove(basicInfoPanel);
					createPanel.add(itemPanel, BorderLayout.CENTER);
					createPanel.revalidate();
					createPanel.repaint();
				}
				
			}
		});
		createPanel.add(nextButton, BorderLayout.EAST);
		
		JButton backButton = new JButton("Back");
		
		backButton.addActionListener(new ActionListener() {
				
			@Override
			public void actionPerformed(ActionEvent e) {
				if (state == FIRST_PANEL) {
					String[] optionStrings = {"Yes" , "No"};
					int x = JOptionPane.showOptionDialog(null, "You will lose all progress if you back out. Proceed?",
													 "Warning", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null,
													 optionStrings, optionStrings[0]);
					// If user say yes
					if (x == 0) {
						myWindow.setContentPane(mainPanel);
						myWindow.pack();
					}
				} else {
					state = FIRST_PANEL;
					createPanel.remove(itemPanel);
					createPanel.add(basicInfoPanel, BorderLayout.CENTER);
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
	 */
	public void start() {
		myWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		myWindow.setTitle(MAIN_TITLE);
		myWindow.pack();
		myWindow.setVisible(true);
	}
	
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
	 */
	private JMenuItem createChangeSizeItem() {
		final JMenuItem changeSize = new JMenuItem();
		changeSize.setText("Set Frame Size");
		changeSize.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent theException) {
				final String arg1 = JOptionPane.showInputDialog("Input new width");
				if (arg1 == null) {
					return;
				}
				final int width = Integer.parseInt(arg1);
				final String arg2 = JOptionPane.showInputDialog("Input new height");
				if (arg2 == null) {
					return;
				}
				final int height = Integer.parseInt(arg2);
				myWindow.setSize(new Dimension(width, height));
			}
		});

		return changeSize;
	}
	
	/**
	 * Creates the about page item to access about dialog
	 * 
	 * @return the about page item
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
	 * Sets the dialog panel up.
	 * 
	 * @param theAboutMessage
	 *            The dialog panel itself to be setup.
	 */
	private void setupDialogPanel(final JDialog theAboutMessage) {
//		theAboutMessage.setLocationRelativeTo(this);
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
	 * Creates the export menu bar item
	 * 
	 * @return the export menu bar item
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
	 */
	private void setupJFrameIcon() {
		final ImageIcon img = new ImageIcon("tornadoIcon.png");
		myWindow.setIconImage(img.getImage());
	}
}
