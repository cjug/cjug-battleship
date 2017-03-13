package org.cjug.kids.internal.model;

public class Player {
	
	private String id;
	
	private String name;
	
	private String team;
	
	private GridPosition pos;
	
	public Player()
	{
		
	}
	
	public Player(String id, String name, String team, GridPosition pos)
	{
		this.id = id;
		this.name = name;
		this.team = team;
		this.pos = pos;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	public void setPos(GridPosition pos) {
		this.pos = pos;
	}

	public String getName() {
		return name;
	}

	public GridPosition getPos() {
		return pos;
	}
	
	public String getTeam() {
		return team;
	}
	
}
