class Position implements Comparable<Position>{
	
	//    row0 col0  []         row0 row1 col0 col1 []           row0 col0  [][]  row0 col1
	//    row1 col0  []       
	
	//    row0<row1 col0=col1    (row0=row1)&(col0=col1)			row0=row1 col0<col1
	
	public int row0;
	public int row1;
	public int col0;
	public int col1;
	public Position previous;
	public String move;
	
	
	public Position (Position previous,String move, int row0, int col0, int row1, int col1) {	//constructor
		this.row0 = row0;
		this.row1 = row1;
		this.col0 = col0;
		this.col1 = col1;
		this.previous = previous;
		this.move = move;
		
		assert row0 <= row1;
		assert col0 <= col1;
		assert col1 - col0 + row1 - row0 <= 1;
	}
	
	
	public String toString() {
		return String.format("%d %d %d %d",row0, col0, row1, col1);
	}
	
	
	public int compareTo(Position other) {
		return toString().compareTo(other.toString());
	}
	@Override
	
	
	public boolean equals(Object obj) {
		if (this.getClass() != obj.getClass())
			return false;
		Position other = (Position)obj;
		return ((row0 == other.row0) 
			&& (row1 == other.row1) 
			&& (col0 == other.col0) 
			&& (col1 == other.col1));
	}
	
	
	public boolean stand() {
		return (col0 == col1) && (row0 == row1);
	}
	
	
	public boolean lieV() {
		return row0 < row1;
	}
	
	public boolean lieH() {
		return col0 < col1;
	}
	
	public Position up() {
		if (row0 == row1) 
		{
			if (col0 == col1) {
				
				return new Position(this,"U", row0-2, col0, row1-1, col1);
			} 
			else
			{
				return new Position(this,"U", row0-1, col0, row1-1, col1);
			}
		}
		else 
		{
			return new Position(this,"U", row0-1, col0, row1-2, col1);
		}
	}
	
	public Position down() {
		if (row0 == row1) 
		{
			if (col0 == col1) 
			{
				return new Position(this,"D", row0+1, col0, row1+2, col1);
			} 
			else 
			{
				return new Position(this,"D", row0+1, col0, row1+1, col1);
			}
		} 
		else {
			return new Position(this,"D", row0+2, col0, row1+1, col1);
		}
	}
	
	public Position left() {
		if (row0 == row1) 
		{
			if (col0 == col1) 
			{
				return new Position(this,"L", row0, col0-2, row1, col1-1);
			} 
			else 
			{
				return new Position(this,"L", row0, col0-1, row1, col1-2);
			}
		} 
		else 
		{
			return new Position(this,"L", row0, col0-1, row1, col1-1);
		}
	}

	public Position right() 
	{
		if (row0 == row1) 
		{
			if (col0 == col1) 
			{
				return new Position(this,"R", row0, col0+1, row1, col1+2);
			} 
			else 
			{
				return new Position(this,"R", row0, col0+2, row1, col1+1);
			}
		} 
		else 
		{
			return new Position(this,"R", row0, col0+1, row1, col1+1);
		}
	}
	
	public Position move(int direction) {
		switch (direction) {
			case 0: return up();
			case 1: return down();
			case 2: return left();
			case 3: return right();
		}
		assert false;
		return null;
	}

}