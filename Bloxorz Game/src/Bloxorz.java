import java.io.IOException;
import java.util.*;

class Bloxorz {
	
	static String mapFilename;
	
	public static void main(String[] args) throws IOException {	//main method
		Scanner input = new Scanner(System.in);
		
		System.out.println("Enter map name:"); 

		mapFilename = input.nextLine();

		Map map = new Map(mapFilename);
		
		map.bfs();
	}
}