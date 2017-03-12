package org.cjug.kids.internal.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

public class GameGridPanel extends JPanel {
	
	String[] columnLabels = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
	
	Map<String, JPanel> jpanelMap;
	
	public GameGridPanel() {
		super(new GridLayout(11,11));
		this.jpanelMap = new HashMap<>();
		add(createLabelGridbox(""));
		for(String label : columnLabels)
		{
			add(createLabelGridbox(label));
		}
		for(int row = 1; row < 11; row ++)
		{
			add(createLabelGridbox(Integer.toString(row)));
			for(int col = 0; col < 10; col++)
			{
				JPanel gridComp = createGridBox();
				this.jpanelMap.put("box"+this.columnLabels[col] + "" + (row) , gridComp);
				add(gridComp);
			}
		}
		
	}
	private JPanel createLabelGridbox(String columnLabel)
	{
		JPanel box = createGridBox();
		JLabel label = new JLabel(columnLabel);
		label.setFont(new Font("Serif", Font.BOLD, 40));
		box.add(label);
		return box;
	}
	private JPanel createGridBox() {
		JPanel box = new JPanel();
		box.setSize(10, 10);
		box.setBackground(Color.LIGHT_GRAY);
		Border border = BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.BLACK, Color.BLACK);
		box.setBorder(border);
		return box;
	}
	
	public void triggerGrid(String col, int row)
	{
		setGridColor(col, row, Color.RED);
	}
	
	public void setGridColor(String col, int row, Color color)
	{
		JPanel triggeredPanel = this.jpanelMap.get("box" + col + "" + row);
		triggeredPanel.setBackground(color);
	}
	
	public void reset()
	{
		for(Entry<String, JPanel> currentEntry : this.jpanelMap.entrySet())
		{
			currentEntry.getValue().setBackground(Color.LIGHT_GRAY);
		}
	}
	
}
