import java.util.*; 
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


class Map {
	
	private ArrayList _map = new ArrayList(); 		// The map which is to be read from the text file and stored in the form of 
													// ArrayList with each row of the text file as one element.
	
	private int rowNum; 							// Row Number in the map
	
	public Position startPos;						// The start position ('S') to be found from map with previous position as null
	
	public Position endPos;							// The end position in the map indicated by 'T'
	
	
	public Map(String filename) throws IOException  // Constructs the map of level given the file name 
	{
		
		BufferedReader br = new BufferedReader(new FileReader(filename));
		
		String sCurrentLine;
		rowNum = 0;
		
		int col;
		
		while ((sCurrentLine = br.readLine()) != null) {
			
			_map.add(sCurrentLine);
			
			if ((col = sCurrentLine.indexOf('S')) != -1) {
				startPos = new Position(null, null, rowNum, col, rowNum, col);
			}
			if ((col = sCurrentLine.indexOf('T')) != -1) {
				endPos = new Position(null, null,  rowNum, col, rowNum, col);
			}
			++rowNum;
		}
		assert this.inside(startPos);
		assert this.inside(endPos);
		br.close();
	}
	
	
	public void printPosition(Position p)			// Method for printing the position with the Position object provided 
	{
		
		if (!inside(p)) return;
		
		for (int i = 0 ; i < rowNum ; ++i) {
			
			char[] s = ((String)_map.get(i)).toCharArray();
			
			if (p.row0 == i)
				s[p.col0] = '*';
			
			if (p.row1 == i)
				s[p.col1] = '*';
			
			System.out.println(new String(s));
		}
		
		System.out.println("-----");
	}
	
	
	public boolean inside(Position p) 				// Method to check if the given position lies inside the map
	{
		
		char tile0, tile1;
		
		if ((p.row0<rowNum) && (p.row0>=0) && (p.col0>=0) 
				&& (p.col0<((String)_map.get(p.row0)).length()))
			
			tile0 = ((String)_map.get(p.row0)).charAt(p.col0);
		
		else 
			return false;
		
		if ((p.row1<rowNum) && (p.row1>=0) && (p.col1>=0) 
				&& (p.col1<((String)_map.get(p.row1)).length()))
			
			tile1 = ((String)_map.get(p.row1)).charAt(p.col1);
		else 
			return false;
		
		if (tile0 == '.' || tile1 == '.')					//For bonus part
			return !p.stand();
		
		return (tile0 != '-' && tile1 != '-');

	}
	
	
	public void bfs() 								// Breadth first search
	{
		Set visitedPosition = new TreeSet();		// Set storing the visited positions
		
		visitedPosition.add(startPos);
		
		assert visitedPosition.contains(startPos);
		assert visitedPosition.contains(new Position(startPos, "x",startPos.row0, startPos.col0, startPos.row1, startPos.col1));
		
		LinkedList<Position> queue = new LinkedList<Position>();	// queue 
		
		queue.add(startPos);						//Enqueue start position on queue
		
		Position curr,next;
		
		while (!queue.isEmpty() && !visitedPosition.contains(endPos)) 
		{
			curr = queue.remove();					//remove current position from queue
			
			// printPosition(curr);
			
			visitedPosition.add(curr);				//add the current position to visited
			
			for (int dir = 0; dir < 4; ++dir) {		//for all adjacent edges
				
				next = curr.move(dir);				// next position after moving in a direction
				
				if (inside(next) && !visitedPosition.contains(next))	// if next position is inside the map and visited position does not contain next
				{
					
					visitedPosition.add(next);		//visit next position
					queue.add(next);				// add to queue
					
					if (next.equals(endPos))		//if next is 'T' then assign next to end position
					{		
						endPos = next;
					}
				}
			}
		}
		
		if (visitedPosition.contains(endPos))		//if visited position contains end position then  
		{
			
			LinkedList<Position> stack = new LinkedList<Position>(); // create a stack of positions
			
			Position p = endPos;
			
			while (p.previous != null) {                            // keep adding the previous of end position while previous is not null
				stack.addFirst(p);
				p = p.previous;
			}
			
			while (!stack.isEmpty()) {			// while stack does not get empty keep removing positions from stack and printing the moves
				p = stack.removeFirst();
				System.out.print(p.move);
			}
			
			System.out.print("\n");
		} 
		else 									//Otherwise its impossible
		{
			System.out.println("Impossible!");
		}

	}
}