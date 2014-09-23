package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JColorChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class Menu implements ActionListener {
	private JMenuItem nTab;
	private PaintTab temp;
	private JMenuItem dTab;
	private JMenuItem reTab;
	private JMenuItem color;
	
	public JMenuBar makeMenu(PaintTab tab){
		JMenuBar menuBar = new JMenuBar();
		temp = tab;
		
		JMenu file = new JMenu("File");
		menuBar.add(file);
		nTab = new JMenuItem("New Tab");
		nTab.addActionListener(this);
		file.add(nTab);
		dTab = new JMenuItem("Delete Tab");
		dTab.addActionListener(this);
		file.add(dTab);
		
		JMenu edit = new JMenu("Edit");
		menuBar.add(edit);
		color = new JMenuItem("Custom Tab Color");
		color.addActionListener(this);
		edit.add(color);
		reTab = new JMenuItem("Rename Tab");
		reTab.addActionListener(this);
		edit.add(reTab);
		return menuBar;
	}

	@Override
	public void actionPerformed(ActionEvent evt){
		if(evt.getSource() == nTab){
			temp.addNewTab();
		}
		else if(evt.getSource() == dTab){
			temp.deleteTab();
		}
		else if(evt.getSource() == reTab){
			String newName = (String)JOptionPane.showInputDialog("Enter new name for tab: ");
			temp.changeName(newName);
		}
		else if(evt.getSource() == color){
//			JColorChooser tcc = new JColorChooser();
		}
	}
}
