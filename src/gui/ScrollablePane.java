package gui;

import java.awt.Dimension;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * 
 * @author Curran
 */
public class ScrollablePane extends JScrollPane {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextArea myData;

	/**
	 * 
	 * @param dimension
	 * @param isEditable
	 * @param data
	 * @param fontSize Enter a float for font size.
	 */
	public ScrollablePane(final Dimension dimension, final boolean isEditable, 
			final JTextArea data, final float fontSize) {	
		super(data);
		myData = data;
        this.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        this.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        //this.setPreferredSize(dimension);
        myData.setEditable(isEditable);
        myData.setFont(myData.getFont().deriveFont(fontSize));
	}
	
	public void setText(final String theString) {
		myData.setText(theString);
	}
}
