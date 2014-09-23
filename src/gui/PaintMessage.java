package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.net.InetAddress;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import chat.ChatThread;
import chat.UpdateManager;

public class PaintMessage extends JPanel implements KeyListener, ActionListener{

	private static final long serialVersionUID = 1L;
	private JLabel label;
	private JScrollPane scroller;
	private JTextArea messageBox;
	private JButton submit;
	private PaintConvo temp;
	private UpdateManager updateManager;
	
	public PaintMessage(PaintConvo convo, UpdateManager update){
		super();
		updateManager = update;
		temp = convo;
		label = new JLabel("Message");
		label.setHorizontalAlignment(JLabel.LEFT);
        label.setVerticalAlignment(JLabel.TOP);
        messageBox = new JTextArea();
		messageBox.setColumns(20);
		messageBox.setRows(2);
		messageBox.setEditable(true);
		messageBox.setBackground(Color.white);
		messageBox.addKeyListener(this);
		scroller = new JScrollPane(messageBox);
		submit = new JButton("Sumbit");
		
		setLayout(new BorderLayout());
		add(label, BorderLayout.PAGE_START);
        add(scroller, BorderLayout.EAST);
        add(submit, BorderLayout.PAGE_END);
        submit.addActionListener(this);
        
        updateManager.attach(this);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getSource() == messageBox){
			if((e.getKeyCode() == KeyEvent.VK_ALT) && (e.getKeyCode() == KeyEvent.VK_ENTER)){
				String more = messageBox.getText();
				messageBox.setText(more);
				messageBox.setText("\n");
			}
			if(e.getKeyCode() == KeyEvent.VK_ENTER){
				JTextArea convoBox = temp.getConvo();
				String text = messageBox.getText();
				
				updateManager.send(text);
				messageBox.selectAll();
				convoBox.setCaretPosition(convoBox.getDocument().getLength());
				messageBox.setText("");
				
				
				
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		JTextArea convoBox = temp.getConvo();
		String text = messageBox.getText();
		updateManager.send(text);
		messageBox.selectAll();
		convoBox.setCaretPosition(convoBox.getDocument().getLength());
		messageBox.setText("");
	}
	
	public void addText(String text){
		JTextArea convoBox = temp.getConvo();
		convoBox.append(text + '\n');
	}

}
