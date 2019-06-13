package gui;

import java.awt.Dimension;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

/**
 * Scrollable pane that can scroll when the data extends past the height dimension.
 * Can set the text of the data.
 * 
 * @author Curran
 * DATE:06/4/2019
 */
public class ScrollablePane extends JScrollPane {
	
	/**
	 * Auto generated serial id.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Main data to be contained within a scroll pane.
	 */
	private JTextArea myData;

	/**
	 * Constructs a ScrollablePane with the given parameters.
	 * 
	 * @param dimension  dimension of the pane
	 * @param isEditable a boolean whether the text area can be edited.
	 * @param data a JTextArea the data to be put into the scroll pane.
	 * @param fontSize afloat for font size.
	 * 
	 * @author Curran
	 * DATE:06/4/2019
	 */
	public ScrollablePane(final Dimension dimension, final boolean isEditable, 
			final JTextArea data, final float fontSize) {	
		super(data);
		myData = data;
        this.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        this.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        myData.setEditable(isEditable);
        myData.setFont(myData.getFont().deriveFont(fontSize));
	}
	
	/**
	 * Sets the given String to the data.
	 * @param theString String to set the data to.
	 * 
	 * @author Curran
	 * DATE:06/4/2019
	 */
	public void setText(final String theString) {
		myData.setText(theString);
	}
}
