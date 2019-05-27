package utilities;

import java.awt.Dimension;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Parses a file for window dimensions.
 * 
 * @author Curran, Eric, Sharanjit, Minh
 *
 */

public class FileParser {

	public static Dimension parse(final File file) throws FileNotFoundException {
		final Scanner input = new Scanner(file);
		int width = 0;
		int height = 0;
		while (input.hasNextLine()) {
			String[] line = input.nextLine().split(" ");

			if (line[0].equals("Height:")) {
				height = Integer.parseInt(line[1]);
			} else if (line[0].equals("Width:")) {
				width = Integer.parseInt(line[1]);
			} else {
				throw new IllegalArgumentException();
			}
		}
		input.close();

		return new Dimension(width, height);
	}
}
