package gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import utilities.About;
import utilities.FileParser;

public class MainFrame extends JFrame {

	/**
	 * Auto generated serial field
	 */
	private static final long serialVersionUID = -1683917555496614884L;

	/**
	 * Minimum width of the main window
	 */
	private static final int MIN_FRAME_WIDTH = 300;

	/**
	 * Minimum height of the main window
	 */
	private static final int MIN_FRAME_HEIGHT = 200;

	/**
	 * The fraction constant to provide relative calculations to the main
	 * window.
	 */
	private static final float FRACTION_OF_MAIN_WINDOW = 0.5f;

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
	 * Stupid message that will be deleted very soon.
	 */
	private static final String STUPID_CONTENT_PANEL_MESSAGE = "Nothing here yet except for that toolbar!";

	/**
	 * The text display the main title.
	 */
	private static final String MAIN_TITLE = "TCSS360 Project";

	/**
	 * The text name for the dialog panel.
	 */
	private static final String DIALOG_PANEL_NAME = "About";

	public MainFrame() throws FileNotFoundException {
		// Initialize fileParser for JFrame dimensions

		// Setup the main window
		this.setMinimumSize(new Dimension(MIN_FRAME_WIDTH, MIN_FRAME_HEIGHT));
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		final Dimension savedDimensions = FileParser
				.parse(new File("USER_SETTINGS.txt"));
		this.setPreferredSize(savedDimensions);

		// Setup content Panel
		final JPanel contentPanel = new JPanel();
		contentPanel.setSize(new Dimension(MIN_FRAME_WIDTH, MIN_FRAME_HEIGHT));
		this.setContentPane(contentPanel);
		final JTextArea stupidMessage = new JTextArea();
		stupidMessage.setText(STUPID_CONTENT_PANEL_MESSAGE);
		stupidMessage.setEditable(false);
		this.add(stupidMessage);
		this.setTitle(MAIN_TITLE);
		this.setupJFrameIcon();

		// Setup the JMenu
		final JMenuBar menuBar = new JMenuBar();
		final JMenu helpMenu = new JMenu();
		helpMenu.setText(HELP_MENU_TEXT);
		final JMenu settingMenu = new JMenu();
		settingMenu.setText(SETTING_MENU_TEXT);

		final MainFrame thisFrame = this;

		final JMenuItem aboutPage = new JMenuItem();
		aboutPage.setText(ABOUT_MENU_TEXT);
		aboutPage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				final JDialog aboutDialog = new JDialog(thisFrame, DIALOG_PANEL_NAME,
						true);
				thisFrame.setupDialogPanel(aboutDialog);
			}
		});

		final JMenuItem changeSize = new JMenuItem();
		changeSize.setText("Set frame size");
		changeSize.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent theException) {
				final String arg1 = JOptionPane.showInputDialog("input new width");
				if (arg1 == null) {
					return;
				}
				final int width = Integer.parseInt(arg1);
				final String arg2 = JOptionPane.showInputDialog("input new height");
				if (arg2 == null) {
					return;
				}
				final int height = Integer.parseInt(arg2);
				thisFrame.setSize(new Dimension(height, width));
			}
		});

		final JMenuItem export = new JMenuItem();
		export.setText("Export frame dimensions");
		final int width = this.getWidth();
		final int height = this.getHeight();
		export.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					PrintStream output = new PrintStream("USER_SETTINGS.txt");
					output.println("Width: " + width);
					output.println("Height: " + height);
					output.close();
					JOptionPane.showMessageDialog(thisFrame, "Setting exported.");
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
			}
		});

		// Hook the menus together
		helpMenu.add(aboutPage);
		settingMenu.add(changeSize);
		settingMenu.add(export);
		menuBar.add(helpMenu);
		menuBar.add(settingMenu);
		this.setJMenuBar(menuBar);

		// Final preparation
		this.pack();
		this.setVisible(true);

	}

	/**
	 * Sets the dialog panel up.
	 * 
	 * @param theAboutMessage
	 *            The dialog panel itself to be setup.
	 */
	private void setupDialogPanel(final JDialog theAboutMessage) {
		theAboutMessage.setLocationRelativeTo(this);
		final JTextArea aboutMessage = new JTextArea();
		String message = new About().getAbout();

		aboutMessage.setText(message);
		aboutMessage.setEditable(false); // Don't want anyone changing the text
											// field!
		aboutMessage.setAlignmentX(JTextArea.CENTER_ALIGNMENT);
		aboutMessage.setAlignmentY(JTextArea.CENTER_ALIGNMENT);
		theAboutMessage.add(aboutMessage);

		// Make the dialog a fraction of the size of the main window.
		theAboutMessage.setMinimumSize(
				new Dimension((int) (this.getWidth() * FRACTION_OF_MAIN_WINDOW),
						(int) (this.getHeight() * FRACTION_OF_MAIN_WINDOW)));

		theAboutMessage.setVisible(true);
	}

	/**
	 * Setup the icon for the JFrame.
	 */
	private void setupJFrameIcon() {
		final ImageIcon img = new ImageIcon("tornadoIcon.png");
		this.setIconImage(img.getImage());
	}

}
