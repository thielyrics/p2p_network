package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class PaintConvo extends JPanel {
	private static final long serialVersionUID = 1L;
	private JLabel label;
	private JScrollPane scroller;
	private JTextArea convoBox;
	
	public PaintConvo(){
		super();
		label = new JLabel("Conversation");
		label.setHorizontalAlignment(JLabel.LEFT);
        label.setVerticalAlignment(JLabel.TOP);
		convoBox = new JTextArea();
		convoBox.setColumns(20);
		convoBox.setRows(22);
		convoBox.setEditable(false);
		convoBox.setBackground(Color.white);
		scroller = new JScrollPane(convoBox);
		
		setLayout(new BorderLayout());
		add(label, BorderLayout.PAGE_START);
        add(scroller, BorderLayout.CENTER);
	}
	
	public JTextArea getConvo(){
		return convoBox;
	}
}