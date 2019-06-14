package utilities;

import java.awt.Dimension;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import model.UserSettings;

/**
 * Parses a file for window dimensions.
 * 
 * @author Curran, Eric, Sharanjit, Minh
 * @since 6/5/19
 */
public class FileParser {

	/**
	 * Parses the user settings file and imports the information into the project.
	 * 
	 * @param file
	 *            the file containing the user settings.
	 * @return a UserSettings object containing the information parsed from the user
	 *         settings file.
	 * @throws FileNotFoundException
	 *             If the file is not found, will throw this exception.
	 * @author Curran, Minh, Eric
	 * @since 6/5/19
	 */
	public static UserSettings parse(final File file) throws FileNotFoundException {
		final Scanner input = new Scanner(file);
		int width = 0;
		int height = 0;
		String email = "";
		while (input.hasNextLine()) {
			String[] line = input.nextLine().split(" ");

			if (line[0].equals("Width:")) {
				width = Integer.parseInt(line[1]);
			} else if (line[0].equals("Height:")) {
				height = Integer.parseInt(line[1]);
			} else if (line[0].equals("Email:")) {
				email = line[1];
			} else {
				input.close();
				throw new IllegalArgumentException();
			}
		}

		input.close();

		return new UserSettings(new Dimension(width, height), email);
	}
}
