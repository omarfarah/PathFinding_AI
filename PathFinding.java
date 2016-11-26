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
				height=Integer.parseInt(tokens[0]);
				width=Integer.parseInt(tokens[1]);
				board = new Node[height][width];
				
				//get number of robots
				line = reader.readLine();
				tokens = line.split(" ");
				int numRobots =  Integer.parseInt(tokens[0]);
				
				//get robot coordinates
				for (int i = 0; i < numRobots; i++){
					line = reader.readLine();
					tokens = line.split(" ");
					robots.add(new Robot(Integer.parseInt(tokens[1]), Integer.parseInt(tokens[0])));
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
	public static void findPathAStar(){
		for (int i = 0; i < robots.size(); i ++){
			LinkedList<Node> frontier = new LinkedList<Node>();
			ArrayList<Node> visited = new ArrayList<Node>();
			int currentStep = 0;
			Node current = maze.getNode(robots.get(i).getRow(), robots.get(i).getCol());
			frontier.add(current);
			while (frontier.size() != 0){
				currentStep++;
				current = getNext(frontier); // get value with lowest value
				visited.add(current);
				if (maze.isGoal(current)){ //check if current is goal state
					break;
				}else{
					CheckNodes(current, currentStep, frontier, visited);
				}
			}
			
		}
	}
	
	public static Node getNext(LinkedList<Node> frontier){
			Node min = frontier.get(0);
			for (int i = 1; i < frontier.size(); i++){
				if (min.getDistance() > frontier.get(i).getDistance()){
					min = frontier.get(i);
				}
			}
			frontier.remove(min);
			System.out.println("popping:("+min.getRow()+","+min.getRow()+")");
			return min;
	}
	
	/*===============================================================
	CheckNodes: Check the neighbouring nodes and add them to the frontier
	================================================================*/
	public static void CheckNodes(Node current, int currentStep, LinkedList<Node> frontier, ArrayList<Node> visited){
		int col = current.getCol();
		int row = current.getRow();
		
		//check north node
		if (row-1 >= 0){
			Node north = maze.getNode(row-1, col);
			if (!north.isBool() && !visited.contains(north)){
				north.setG(currentStep);
				frontier.add(north);
				System.out.println("NORTH: ("+north.getRow()+","+north.getCol()+")");
			}
		}
		
		//check south node
		if (row+1 <= maze.height - 1){
			Node south = maze.getNode(row+1, col);
			if (!south.isBool() && !visited.contains(south)){
				south.setG(currentStep);
				frontier.add(south);
				System.out.println("SOUTH: ("+south.getRow()+","+south.getCol()+")");

			}
		}
		
		//check west node
		if (col-1 >= 0){
			Node west = maze.getNode(row, col-1);
			if (!west.isBool() && !visited.contains(west)){
				west.setG(currentStep);
				frontier.add(west);
				System.out.println("WEST: ("+west.getRow()+","+west.getCol()+")");
			}
		}
		
		//System.out.println("EAST: " + (col+1) + ", maze: "+( maze.width-1));
		//check east node
		if (col+ 1 <= maze.width-1){
			Node east = maze.getNode(row, col+1);
			if (!east.isBool() && !visited.contains(east)){
				east.setG(currentStep);
				frontier.add(east);
				System.out.println("EAST: ("+east.getRow()+","+east.getCol()+")");
			}
		}
	}
	
	/*===================================
					Main
	====================================*/
	public static void main(String[] args) throws IOException{
		//getMaze("/Users/Perlanie/Documents/Projects/PathFinding_Ai/maze.txt");
		readMazeFile(new File(args[0]));
		maze.printBoard();
		maze.calcHValues();
		
		findPathAStar();
	}


}