package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import chat.UpdateManager;

public class BuildGUI {
	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private PaintTab tab;
	private Menu menu = new Menu();
	private PaintUser user;
	private UpdateManager updateManager;
	
	public BuildGUI(UpdateManager update){
		updateManager = update;
		tab = new PaintTab(updateManager);
		String username = (String)JOptionPane.showInputDialog("Enter user name: ");
		checkLen(username);
		frame = new JFrame("Chatroom");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		tab.setPreferredSize(new Dimension(300, 500));
		user = new PaintUser(username);
		frame.add(user, BorderLayout.WEST);
		frame.add(tab, BorderLayout.CENTER);
		frame.add(menu.makeMenu(tab), BorderLayout.PAGE_START);
		frame.pack();
		frame.setVisible(true);
	}
	public String checkLen(String s){
		if(s.trim().length() == 0){
			s = (String)JOptionPane.showInputDialog("Please enter a name (not just whitespace): ");
			return checkLen(s);
		} else{
			return s;
		}
	}
	
	public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
        UIManager.put("swing.boldMetal", Boolean.FALSE);
        
        UpdateManager u = new UpdateManager();
        new BuildGUI(u);
        u.start();
        
            }
        });
    }
}