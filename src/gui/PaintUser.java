package gui;


import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class PaintUser extends JPanel{
	private static final long serialVersionUID = 1L;
	private JLabel label;
	private JScrollPane scroller;
	private JTextArea userBox;
	
	public PaintUser(String login){
		super();
		label = new JLabel("Online Users");
		label.setHorizontalAlignment(JLabel.LEFT);
        label.setVerticalAlignment(JLabel.TOP);
        userBox = new JTextArea();
        userBox.setColumns(10);
		userBox.setRows(20);
		userBox.setEditable(false);
		userBox.setBackground(Color.getHSBColor((float)0.593, (float)0.278, (float)0.929));
		scroller = new JScrollPane(userBox);
		
		setLayout(new BorderLayout());
		add(label, BorderLayout.PAGE_START);
        add(scroller, BorderLayout.WEST);
        userBox.append(login + "\n");
	}
}
