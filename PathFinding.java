import java.io.*;
import java.util.*;

public class PathFinding{
	
	public static Maze maze;
	public static ArrayList<Robot> robots;
	
	public static void readBoard(File path)throws FileNotFoundException, IOException{
		try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
			Node[][] board = null;
			String line = null;
			String[] tokens = null;
			
			//parse file
			try{
				int current;
				//get grid size
				line = reader.readLine();
				tokens = line.split(" ");
				board = new Node[Integer.parseInt(tokens[0])][Integer.parseInt(tokens[1])];
				
				//get number of robots
				line = reader.readLine();
				tokens = line.split(" ");
				int numRobots =  Integer.parseInt(tokens[0]);
				
				//get robot coordinates
				for (int i = 0; i < numRobots; i++){
					line = reader.readLine();
					tokens = line.split(" ");
					robots.add(new Robot(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1])));
				}
				
				//get coordinates for end point
				line = reader.readLine();
				tokens = line.split(" ");
				
				//populate grid
				for (int row = 0; row < board.length; row++){
					for (int col = 0; col < board.length; col++){
						current = reader.read();
						if (current == 48){
							board[col][row] = new Node(0,0,row,col,false); // do we need to pass values for g and h?
						} else {
							board[col][row] = new Node(0,0,row,col,true);
						}
					}
					current = reader.read(); // read CR
					current = reader.read(); // read new line
				}
			
			}
			catch (IOException e){
				System.out.println("Error: Cannot readfile");
			}	
			maze = new Maze(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]), board);
		}			
		catch(FileNotFoundException f){
			System.out.println("Error: File not found");
		}
	}

	public static void main(String[] args) throws IOException{
		//getMaze("/Users/Perlanie/Documents/Projects/PathFinding_Ai/maze.txt");
		readBoard(new File(args[0]));	
		
	}


}