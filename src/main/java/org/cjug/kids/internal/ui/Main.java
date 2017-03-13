package org.cjug.kids.internal.ui;

import java.awt.GridBagConstraints;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.SwingUtilities;

import org.cjug.kids.internal.model.Player;

public class Main extends JFrame {

	private MainGridPanel mainGridPanel;
	
	private GameGridPanel gameGridPanel;
	
	private JMenu menu, submenu;
	
	private Map<String, PlayerPanel> playerPanelMap;
	
	private GridBagConstraints c = new GridBagConstraints();
	
	private String gameName;

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
    	
    	
    	menu.addSeparator();
    	ButtonGroup group = new ButtonGroup();
    	
    	group.getSelection();
    	JRadioButtonMenuItem gameMenuItem = new JRadioButtonMenuItem("Hide and Seek");
    	gameMenuItem.setActionCommand("hide-and-seek");
    	gameMenuItem.setSelected(true);
    	gameMenuItem.addActionListener(new GameActionListener(this));
    	
    	
    	
    	group.add(gameMenuItem);
    	menu.add(gameMenuItem);
    	
    	menu.addSeparator();
    	
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
    	
    	this.gameName = group.getSelection().getActionCommand();

	}
    
    public void start()
    {
    	SwingUtilities.invokeLater(()->{
    		setVisible(true);
    	});
    	
    	
    }
    
    public boolean isPlayerActive(String id)
    {
    	for(PlayerPanel currentPlayerPanel : this.playerPanelMap.values())
    	{
    		if(currentPlayerPanel.isPlayerActive(id))
    		{
    			return true;
    		}
    	}
    	return false;
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
	
	public String getGameName() {
		return gameName;
	}
	
	public void setGameName(String gameName)
	{
		this.gameName = gameName;
	}

}
