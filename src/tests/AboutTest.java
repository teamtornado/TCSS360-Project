package tests;

/**
 * @author Sharanjit, Curran
 * 
 * Tests the About class to verify the correct information
 * is sent to the dialog menu.
 */
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import utilities.About;

class AboutTest {

	/**
	 * The correct output for the About class method 'getAbout'.
	 */
	private static final String CORRECT_DIALOG_MESSAGE = "Team Name: Team Tornado\nVersion: 0.0.0.1\n"
			+ "\nEric Hoover\nMinh Pham\nSharanjit Singh\nCurran Seam";

	/**
	 * Hold the information required for the about dialog.
	 */
	private About aboutTest = new About();

	/**
	 * Sets this class up before each round of testing.
	 */
	@Before
	public void setup() {
		aboutTest = new About();
	}

	/**
	 * Test that the About class outputs the correct String message.
	 */
	@Test
	public void testGetAbout() {
		assertTrue(aboutTest.getAbout().equals(CORRECT_DIALOG_MESSAGE));
	}

}
