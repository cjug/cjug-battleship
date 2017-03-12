package org.cjug.kids.internal.ui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.cjug.kids.internal.model.Player;

public class PlayerPanel extends JPanel {

	Map<String, JLabel> playerLabelMap;
	
	GridBagConstraints c = new GridBagConstraints();
	
	public PlayerPanel() {
		super(new GridBagLayout());
		JLabel playerLabel = new JLabel("Players");
		add(playerLabel);
		playerLabelMap = new HashMap<>();
		
	}
	
	public void registerPlayer(Player player)
	{
		if(!this.playerLabelMap.containsKey(player.getName()))
		{
			JLabel playerLabel = new JLabel(player.getName());
			
			c.fill = GridBagConstraints.HORIZONTAL;
			c.weightx = 0.0;
			c.gridwidth = 3;
			c.gridx = 0;
			c.gridy = this.playerLabelMap.size()+1;
			add(playerLabel, c);
			this.playerLabelMap.put(player.getName(), playerLabel);
			revalidate();
		}
		
	}
	
	public void unregisterPlayer(Player player)
	{
		JLabel playerLabel = this.playerLabelMap.remove(player.getName());
		remove(playerLabel);
		revalidate();
	}
}
