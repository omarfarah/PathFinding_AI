import java.io.*;
import java.util.*;

public class PathFinding{
	
	public static Maze maze;
	public static ArrayList<Robot> robots=new ArrayList<Robot>();
	public static LinkedList<Node> frontier = new LinkedList<Node>();
	public static ArrayList<Node> visited = new ArrayList<Node>();
	
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
			int currentStep = 0;
			int initRow=robots.get(i).getRow();
			int initCol=robots.get(i).getCol();
			Node current = maze.getNode(initRow, initCol);
			

			frontier.add(current);

			while (frontier.size() != 0){
				currentStep++;
				
				current = getNext(); // get value with lowest value
				visited.add(current);
				if (maze.isGoal(current)){ //check if current is goal state
					break;
				}else{
					CheckNodes(current, currentStep, visited);

					
				}
			}

			if(maze.isGoal(current)){
				traceBackPath(current,robots.get(i));
				robots.get(i).addToPath(maze.getNode(initRow, initCol));
				robots.get(i).printRobotPath();
			}
			else{
				System.out.println("No Solution.");
			}
			
		}
	}

	public static void traceBackPath(Node currentNode, Robot robot){
		while(currentNode.getParent()!=null){
			robot.addToPath(currentNode);
			currentNode=currentNode.getParent();
		}
	}
	
	public static Node getNext(){
			Node min1 = frontier.poll();

			while(!frontier.isEmpty()){
				Node min2=frontier.poll();
				if ((min1.getDistance()) > (min2.getDistance())){
					min1 = min2;

				}
			}

			//System.out.println("popping min:("+min.getRow()+","+min.getRow()+")"+ ", F(x)="+min.getDistance());
			//frontier.remove(min);
			
			return min1;
	}
	
	/*===============================================================
	CheckNodes: Check the neighbouring nodes and add them to the frontier
	================================================================*/
	public static void CheckNodes(Node current, int currentStep, ArrayList<Node> visited){
		int col = current.getCol();
		int row = current.getRow();
		
		//check north node
		if (row-1 >= 0){
			Node north = maze.getNode(row-1, col);
			if (!north.isBool() && !visited.contains(north)){
				north.setG(currentStep);
				if(!frontier.contains(north)){
					//System.out.println("Added: ("+north.getCol()+","+north.getRow()+")");
					frontier.add(north);
					north.setParent(current);
					
				}
				
				//System.out.println("NORTH: ("+north.getRow()+","+north.getCol()+")");
			}
		}
		
		//check south node
		if (row+1 <= maze.height - 1){
			Node south = maze.getNode(row+1, col);
			if (!south.isBool() && !visited.contains(south)){
				//System.out.println("F("+col+","+(row+1)+")="+south.isBool());
				south.setG(currentStep);
				if(!frontier.contains(south)){
					//System.out.println("Added: ("+south.getCol()+","+south.getRow()+")");
					frontier.add(south);
					south.setParent(current);
				}
				//System.out.println("SOUTH: ("+south.getRow()+","+south.getCol()+")");

			}
		}
		
		//check west node
		if (col-1 >= 0){
			Node west = maze.getNode(row, col-1);
			if (!west.isBool() && !visited.contains(west)){
				//System.out.println("F("+(col-1)+","+row+")="+west.isBool());
				west.setG(currentStep);
				if(!frontier.contains(west)){
					//System.out.println("Added: ("+west.getCol()+","+west.getRow()+")");
					frontier.add(west);
					west.setParent(current);
				}
				//System.out.println("WEST: ("+west.getRow()+","+west.getCol()+")");
			}
		}
		
		//check east node
		if (col+ 1 <= maze.width-1){
			Node east = maze.getNode(row, col+1);
			if (!east.isBool() && !visited.contains(east)){
				//System.out.println("F("+(col+1)+","+row+")="+east.isBool());
				east.setG(currentStep);
				if(!frontier.contains(east)){
					//System.out.println("Added: ("+east.getCol()+","+east.getRow()+")");
					frontier.add(east);
					east.setParent(current);
				}
				//System.out.println("EAST: ("+east.getRow()+","+east.getCol()+")");
			}
		}

		for (int j = 0; j < frontier.size(); j++){
					System.out.println("frontier("+j+"):("+frontier.get(j).getCol()+","+frontier.get(j).getRow()+")");
				}
				System.out.println("========================================");
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