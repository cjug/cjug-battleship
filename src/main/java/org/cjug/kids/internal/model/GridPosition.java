package org.cjug.kids.internal.model;

public class GridPosition {
	
	private String row;
	
	private int col;
	
	public GridPosition() {
		
	}
	
	public GridPosition(String row, int col) {
		this.row = row;
		this.col = col;
	}

	public String getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}
	
	@Override
	public String toString()
	{
		return this.row + this.col;
	}
	
	
}
