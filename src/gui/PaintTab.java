package gui;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import chat.UpdateManager;

public class PaintTab extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTabbedPane tabbedPane;
	private PaintMessage messagePanel;
	private PaintConvo convoPanel;
	private UpdateManager update;
	
	public PaintTab(UpdateManager update) {
		super(new GridLayout(1, 1));
		this.update = update;
		tabbedPane = new JTabbedPane();
		JComponent chat1 = makeTextPanel();
        tabbedPane.addTab("Chat 1", chat1);
        add(tabbedPane);
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
	}
	
	protected JComponent makeTextPanel() {
        convoPanel = new PaintConvo();
        messagePanel = new PaintMessage(convoPanel, update);
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        mainPanel.add(convoPanel, c);
        c.gridx = 0;
        c.gridy = 1;
        mainPanel.add(messagePanel, c);
        return mainPanel;
    }
	
	public void addNewTab(){
		JComponent chat = makeTextPanel();
		tabbedPane.addTab("Chat " + (tabbedPane.getTabCount()+1), chat);
	}
	
	public void deleteTab(){
		int idx = tabbedPane.getSelectedIndex();
		tabbedPane.removeTabAt(idx);
	}
	
	public void changeName(String newName){
		int idx = tabbedPane.getSelectedIndex();
		Component ret = tabbedPane.getTabComponentAt(idx);
		tabbedPane.addTab(newName, ret);
		tabbedPane.setSelectedIndex(tabbedPane.getTabCount()-1);
//		tabbedPane.removeTabAt(idx);
	}
}