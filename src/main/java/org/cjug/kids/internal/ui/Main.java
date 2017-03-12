package org.cjug.kids.internal.ui;

import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;

public class Main extends JFrame {

	private MainGridPanel mainGridPanel;
	
	private GameGridPanel gameGridPanel;
	
	private JMenu menu, submenu;
	
	private Map<String, PlayerPanel> playerPanelMap;
	
	private GridBagConstraints c = new GridBagConstraints();

    public Main() {
    	
    	setTitle ("First Swing Example");

    	setSize (1500, 1000);

    	setLocationRelativeTo (null);
    	
    	JMenuBar menuBar = new JMenuBar();
    	
    	menu = new JMenu("Main Menu");
    	menuBar.add(menu);
    	
    	JMenuItem resetMenuItem = new JMenuItem("Reset",
                KeyEvent.VK_R);
    	
    	resetMenuItem.addActionListener((event)->{
    		gameGridPanel.reset();
    	});
    	
    	menu.add(resetMenuItem);
    	
    	JMenuItem exitMenuItem = new JMenuItem("Exit", KeyEvent.VK_X);
    	
    	exitMenuItem.addActionListener((event)->{
    		System.exit(0);
    	});
    	
    	menu.add(exitMenuItem);
    	
    	setJMenuBar(menuBar);

    	this.playerPanelMap = new HashMap<>();
    	
    	PlayerPanel teamOne = new PlayerPanel();
    	
    	this.playerPanelMap.put("team1", teamOne);
    	
    	PlayerPanel teamTwo = new PlayerPanel();
    	
    	this.playerPanelMap.put("team2", teamTwo);
    	
    	this.mainGridPanel = new MainGridPanel();
    	this.gameGridPanel = new GameGridPanel();
    	c.fill = GridBagConstraints.HORIZONTAL;
    	c.weightx = 0.25;
    	c.gridx = 0;
    	c.gridy = 0;
    	mainGridPanel.add(teamOne, c);
    	c.fill = GridBagConstraints.HORIZONTAL;
    	c.weightx = 0.5;
    	c.gridx = 1;
    	c.gridy = 0;
    	mainGridPanel.add(this.gameGridPanel, c);
    	c.fill = GridBagConstraints.HORIZONTAL;
    	c.weightx = 0.25;
    	c.gridx = 2;
    	c.gridy = 0;
    	mainGridPanel.add(teamTwo,c);
    	
    	add(mainGridPanel);

    	setDefaultCloseOperation(EXIT_ON_CLOSE);

	}
    
    public void start()
    {
    	SwingUtilities.invokeLater(()->{
    		setVisible(true);
    	});
    	
    	
    }
    
    public void stop()
    {
    	setVisible(false);
    }
	
	public GameGridPanel getGameGridPanel() {
		return gameGridPanel;
	}
	
	public Map<String, PlayerPanel> getPlayerPanelMap() {
		return playerPanelMap;
	}

}
