package gui.createpanels;

import java.awt.Dimension;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * 
 * @author Curran
 */
public class ScrollablePane extends JScrollPane {
	
	private JTextArea myData;

	public ScrollablePane(final Dimension dimension, boolean isEditable, JTextArea data) {	
		super(data);
		myData = data;
        this.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        this.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.setPreferredSize(dimension);
        myData.setEditable(isEditable);
	}
	
	public void addText(final String theString) {
		myData.append(theString);
	}
}
