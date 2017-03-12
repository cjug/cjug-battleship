package org.cjug.kids.internal.model;

public class Player {
	private String name;
	
	private GridPosition pos;
	
	public Player(String name, GridPosition pos)
	{
		this.name = name;
		this.pos = pos;
	}

	public String getName() {
		return name;
	}

	public GridPosition getPos() {
		return pos;
	}
	
	
}
