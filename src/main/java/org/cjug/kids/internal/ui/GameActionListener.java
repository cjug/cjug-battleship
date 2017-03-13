package org.cjug.kids.internal.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameActionListener implements ActionListener {
	
	private Main main;

	public GameActionListener(Main main) {
		this.main = main;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		main.setGameName(e.getActionCommand());
		
		main.getGameGridPanel().reset();

	}

}
