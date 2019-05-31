package gui;

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
	 * The text display the main title.
	 */
	private static final String MAIN_TITLE = "TCSS360 Project";

	/**
	 * The text name for the dialog panel.
	 */
	private static final String DIALOG_PANEL_NAME = "About";

	private CreatePanel cp = new CreatePanel();

	public MainFrame() throws FileNotFoundException {
		// Initialize fileParser for JFrame dimensions

		// Setup the main window
		setupFrameDimensions();

		// Setup content Panel
		final JPanel contentPanel = new JPanel();
		contentPanel.setSize(new Dimension(MIN_FRAME_WIDTH, MIN_FRAME_HEIGHT));
		// this.setContentPane(contentPanel);

		this.setContentPane(contentPanel);

		// Setup the JMenu
		setupMenuBar();

		JButton openButton = new JButton("Open Project");
		contentPanel.add(openButton);

		final MainFrame thisFrame = this;
		JButton createButton = new JButton("Create Project");
		createButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				thisFrame.setContentPane(cp);
				thisFrame.pack();
			}
		});
		contentPanel.add(createButton);

		finalSetupAndVisible();

	}

	/**
	 * Final setup including packing and setting the frame to visible.
	 */
	private void finalSetupAndVisible() {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle(MAIN_TITLE);
		this.setupJFrameIcon();
		this.pack();
		this.setVisible(true);
	}

	/**
	 * Sets the min dimensions and current dimensions using the user preference
	 * file.
	 */
	private void setupFrameDimensions() {
		this.setMinimumSize(new Dimension(MIN_FRAME_WIDTH, MIN_FRAME_HEIGHT));
		Dimension savedDimensions = null;
		try {
			savedDimensions = FileParser.parse(new File("USER_SETTINGS.txt"));
		} catch (final FileNotFoundException theException) {
			System.out.println("File Parser: could not find settings file");
			theException.printStackTrace();
		}
		this.setPreferredSize(savedDimensions);
	}

	/**
	 * Sets the menu bar up with items.
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
		this.setJMenuBar(menuBar);
	}

	/**
	 * Creates the about page item to access about dialog
	 * 
	 * @return the about page item
	 */
	private JMenuItem createAboutPageItem() {
		final JMenuItem aboutPage = new JMenuItem();
		final MainFrame thisFrame = this;
		aboutPage.setText(ABOUT_MENU_TEXT);
		aboutPage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				final JDialog aboutDialog = new JDialog(thisFrame, DIALOG_PANEL_NAME,
						true);
				thisFrame.setupDialogPanel(aboutDialog);
			}
		});

		return aboutPage;
	}

	/**
	 * Creates the change size item for the menu bar
	 * 
	 * @return the change size item for the menu bar
	 */
	private JMenuItem createChangeSizeItem() {
		final JMenuItem changeSize = new JMenuItem();
		final MainFrame thisFrame = this;
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
				thisFrame.setSize(new Dimension(width, height));
			}
		});

		return changeSize;
	}

	/**
	 * Creates the export menu bar item
	 * 
	 * @return the export menu bar item
	 */
	private JMenuItem createExportItem() {
		final JMenuItem export = new JMenuItem();
		export.setText("Export Frame Size");
		final MainFrame thisFrame = this;
		export.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent theEvent) {
				try {
					PrintStream output = new PrintStream("USER_SETTINGS.txt");
					output.println("Width: " + thisFrame.getWidth());
					output.println("Height: " + thisFrame.getHeight());
					output.close();
					JOptionPane.showMessageDialog(thisFrame, "Settings exported");
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
			}
		});
		return export;

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
