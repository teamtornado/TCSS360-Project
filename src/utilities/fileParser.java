package utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Parses a file for window dimensions.
 * @author Curran, Eric, Sharanjit, Minh
 *
 */
public class fileParser {
	
	int myHeight;
	int myWidth;
	
	public fileParser(final File theFile) throws FileNotFoundException {
		parse(theFile);
	}
	
	public void parse(final File file) throws FileNotFoundException {
		final Scanner input = new Scanner(file);
		while(input.hasNextLine()) {
			String[] line = input.nextLine().split(" ");
			if (line[0].equals("Height:")) {
				myHeight = Integer.parseInt(line[1]);
			} else if (line[0].equals("Width:")) {
				myWidth = Integer.parseInt(line[1]);
			} else {
				throw new IllegalArgumentException();
			}
		}
		input.close();
	}
	
	public int getWidth() {
		return myWidth;
	}

	public int getHeight() {
		return myHeight;
	}
}
