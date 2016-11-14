import java.io.*;
import java.util.*;

public class PathFinding{
	
	public static Maze maze;
	public static ArrayList<Robot> robots=new ArrayList<Robot>();
	
	/*===============================================================
	readMazeFile: reads in the maze file to get the information to 
	intialize the maze and robots
	================================================================*/
	public static void readMazeFile(File path)throws FileNotFoundException, IOException{
		try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
			Node[][] board = null;
			String line = null;
			String[] tokens = null;
			int width=0;
			int height=0;
			int endX=0;
			int endY=0;
			//parse file
			try{
				
				//get grid size
				line = reader.readLine();
				tokens = line.split(" ");
				width=Integer.parseInt(tokens[0]);
				height=Integer.parseInt(tokens[1]);
				board = new Node[height][width];
				
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
				endX=Integer.parseInt(tokens[0]);
				endY=Integer.parseInt(tokens[1]);

				//populate grid
				int current;
				for (int row = 0; row < height; row++){
					for (int col = 0; col < width; col++){
						current = reader.read();
						if (current == 48){
							board[row][col] = new Node(row,col,false); 
						} else {
							board[row][col] = new Node(row,col,true);
						}
					}
					current = reader.read(); // read CR
				}
			
			}
			catch (IOException e){
				System.out.println("Error: Cannot readfile");
			}	
			maze = new Maze(height,width,endX,endY,board);
		}			
		catch(FileNotFoundException f){
			System.out.println("Error: File not found");
		}
	}

	/*===============================================================
	findPathAStar: uses the A* algorithm to find a path for the robot
	if there is a solution
	================================================================*/
	public void findPathAStar(){

	}

	/*===================================
					Main
	====================================*/
	public static void main(String[] args) throws IOException{
		//getMaze("/Users/Perlanie/Documents/Projects/PathFinding_Ai/maze.txt");
		readMazeFile(new File(args[0]));
		maze.printBoard();	
		
	}


}