package org.cjug.kids.internal.ui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.font.TextAttribute;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.text.Style;

import org.cjug.kids.internal.model.Player;

public class PlayerPanel extends JPanel {

	Map<String, JLabel> playerLabelMap;
	
	List<String> activePlayerList;
	
	GridBagConstraints c = new GridBagConstraints();
	
	public PlayerPanel() {
		super(new GridBagLayout());
		JLabel playerLabel = new JLabel("Players");
		add(playerLabel);
		playerLabelMap = new HashMap<>();
		activePlayerList = new ArrayList<>();
		
	}
	
	public void registerPlayer(Player player)
	{
		if(!this.playerLabelMap.containsKey(player.getId()))
		{
			JLabel playerLabel = new JLabel(player.getName());
			
			c.fill = GridBagConstraints.HORIZONTAL;
			c.weightx = 0.0;
			c.gridwidth = 3;
			c.gridx = 0;
			c.gridy = this.playerLabelMap.size()+1;
			add(playerLabel, c);
			this.playerLabelMap.put(player.getId(), playerLabel);
			this.activePlayerList.add(player.getId());
			revalidate();
		}
		
	}
	
	public void resetPlayers()
	{
		this.activePlayerList.addAll(this.playerLabelMap.keySet());
		for(JLabel currentPlayerLabel : this.playerLabelMap.values())
		{
			currentPlayerLabel.setForeground(Color.BLACK);
		}
	}
	
	public void eliminatePlayer(Player player)
	{
		JLabel playerLabel = this.playerLabelMap.get(player.getId());
		playerLabel.setForeground(Color.RED);
		this.activePlayerList.remove(player.getId());
		
	}
	
	public boolean isPlayerActive(String id)
	{
		return this.activePlayerList.contains(id);
	}
	
	public void unregisterPlayer(String id)
	{
		JLabel playerLabel = this.playerLabelMap.remove(id);
		remove(playerLabel);
		revalidate();
	}
}
