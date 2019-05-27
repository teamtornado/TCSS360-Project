package app;

/**
 * @author Eric, Minh, Sharajit, Curran
 * 
 * Main class for this application. Currently does some simple setup
 * for the various GUI elements current operable.
 */
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
import utilities.fileParser;

public class Application extends JFrame {
	/**
	 * Auto generated serial field
	 */
	private static final long serialVersionUID = -1683917555496614884L;

	/**
	 * Minimum width of the main window
	 */
	private static final int MIN_FRAME_WIDTH = 800;

	/**
	 * Minimum height of the main window
	 */
	private static final int MIN_FRAME_HEIGHT = 500;

	/**
	 * The fraction constant to provide relative calculations to the main window.
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
	
	private static int newHeight;
	
	private static int newWidth;

	/**
	 * Runs the application - currently does mostly simple setup.
	 * 
	 * @param theArgs
	 *            The console arguments. These are not used within the code.
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] theArgs) throws FileNotFoundException {
		// Initialize fileParser for Jframe dimensions
		final fileParser parser = new fileParser(new File("FRAME_SIZE.txt"));
		
		// Setup the main window
		final Application mainFrame = new Application();
		mainFrame.setMinimumSize(
				new Dimension(MIN_FRAME_WIDTH, MIN_FRAME_HEIGHT));
		mainFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		mainFrame.setPreferredSize(new Dimension(parser.getWidth(), parser.getHeight()));
		
		// Setup content Panel
		final JPanel contentPanel = new JPanel();
		contentPanel
				.setSize(new Dimension(MIN_FRAME_WIDTH, MIN_FRAME_HEIGHT));
		mainFrame.setContentPane(contentPanel);
		final JTextArea stupidMessage = new JTextArea();
		stupidMessage.setText(STUPID_CONTENT_PANEL_MESSAGE);
		stupidMessage.setEditable(false);
		mainFrame.add(stupidMessage);
		mainFrame.setTitle(MAIN_TITLE);
		mainFrame.setupJFrameIcon();

		// Setup the JMenu
		final JMenuBar menuBar = new JMenuBar();
		final JMenu helpMenu = new JMenu();
		helpMenu.setText(HELP_MENU_TEXT);
		final JMenu settingMenu = new JMenu();
		settingMenu.setText(SETTING_MENU_TEXT);
		
		final JMenuItem aboutPage = new JMenuItem();
		aboutPage.setText(ABOUT_MENU_TEXT);
		aboutPage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				final JDialog aboutDialog = new JDialog(mainFrame,
						DIALOG_PANEL_NAME, true);
				mainFrame.setupDialogPanel(aboutDialog);
			}
		});
		
		final JMenuItem changeSize = new JMenuItem();
		changeSize.setText("Set frame size");
		changeSize.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newHeight = Integer.parseInt(JOptionPane.showInputDialog("input new height"));
				newWidth = Integer.parseInt(JOptionPane.showInputDialog("input new width"));
			}
		});
		
		final JMenuItem export = new JMenuItem();
		export.setText("Export frame dimensions");
		export.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					PrintStream output = new PrintStream("FRAME_SIZE.txt");
					output.println("Height: " + newHeight);
					output.println("Width: " + newWidth);
					output.close();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
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
		mainFrame.setJMenuBar(menuBar);

		// Final preparation
		mainFrame.pack();
		mainFrame.setVisible(true);

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
		aboutMessage.setEditable(false); // Don't want anyone changing the text field!
		aboutMessage.setAlignmentX(JTextArea.CENTER_ALIGNMENT);
		aboutMessage.setAlignmentY(JTextArea.CENTER_ALIGNMENT);
		theAboutMessage.add(aboutMessage);

		// Make the dialog a fraction of the size of the main window.
		theAboutMessage.setMinimumSize(new Dimension(
				(int) (this.getWidth() * FRACTION_OF_MAIN_WINDOW),
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