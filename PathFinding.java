import java.io.*;
import java.util.*;

public class PathFinding{
	
	public static Maze maze;
	public static String mazeFilePath;
	public static ArrayList<Robot> robots=new ArrayList<Robot>();
	public static Comparator<Node> comparator = new NodeComparator();
    public static PriorityQueue<Node> frontier = new PriorityQueue<Node>(comparator);
	//public static LinkedList<Node> frontier = new LinkedList<Node>();
	public static ArrayList<Node> visited = new ArrayList<Node>();
	public static int [] rowInverse;
	
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
				rowInverse= new int[height];
				int counter=0;
				for(int h=height-1;h>=0;h--){
					rowInverse[h]=counter;
					counter++;
				}	
				

				//get number of robots
				line = reader.readLine();
				tokens = line.split(" ");
				int numRobots =  Integer.parseInt(tokens[0]);
				
				//get robot coordinates
				for (int i = 0; i < numRobots; i++){
					line = reader.readLine();
					tokens = line.split(" ");
					robots.add(new Robot(rowInverse[Integer.parseInt(tokens[1])], Integer.parseInt(tokens[0])));
				}
				
				//get coordinates for end point
				line = reader.readLine();
				tokens = line.split(" ");
				endX=Integer.parseInt(tokens[0]);
				endY=rowInverse[Integer.parseInt(tokens[1])];
				
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
	public static void findPathAStar()throws FileNotFoundException, IOException{
		Maze copyMaze=maze.copyMaze();

		int numRobots=robots.size();
		for (int i = 0; i < numRobots; i ++){
			System.out.println("====================================================================");
			int currentStep = 0;
			int initRow=robots.get(i).getRow();
			int initCol=robots.get(i).getCol();
			Node current = maze.getNode(initRow, initCol);
			System.out.println("Robot Starting Position: ("+initCol+","+rowInverse[initRow]+")");
			
			if(!current.isBool()){
				frontier.add(current);

				while (frontier.size() != 0){
					currentStep++;
					
					current = frontier.poll();//getNext(); // get value with lowest value
					visited.add(current);
					if (maze.isGoal(current)){ //check if current is goal state
						break;
					}else{
						CheckNodes(current, currentStep, visited);
					}
				}
			}
			else{
				System.out.println("Robot Position is an obstacle.");
			}
			if(maze.isGoal(current)){
				System.out.println("Getting Path...");
				traceBackPath(current,robots.get(i));
				robots.get(i).addToPath(maze.getNode(initRow, initCol));
				printRobotPath(robots.get(i));
				printVisualRobotPath(robots.get(i));
			}
			else{
				System.out.println("No Solution.");
			}
		
			while(!frontier.isEmpty()){
				frontier.poll();
			}
	
			maze=copyMaze.copyMaze();
			
		}
	}

	public static void printVisualRobotPath(Robot robot){
		ArrayList<Node> path=robot.getRobotPath();
		if(!path.isEmpty()){
			System.out.println("Visual Path:");
			int pathSize=path.size();
			maze.getNode(path.get(pathSize-1).getRow(),path.get(pathSize-1).getCol()).setSymbol("S");
			for(int i=0; i<pathSize-1;i++){
				maze.getNode(path.get(i).getRow(),path.get(i).getCol()).setSymbol("*");
			}
			
			maze.printBoard();
		}
		else{
			System.out.println("No Solution.");
		}
			

	}

	public static void traceBackPath(Node currentNode, Robot robot){
		while(currentNode.getParent()!=null){
			robot.addToPath(currentNode);
			currentNode=currentNode.getParent();
		}
	}
	
	// public static Node getNext(){
	// 	Node min1 = frontier.get(0);

	// 	for(Node min2: frontier){
	// 		if(maze.isGoal(min2)){
	// 			return min2;
	// 		}
	// 		if ((min1.getDistance()) > (min2.getDistance())){
	// 			min1 = min2;

	// 		}
	// 	}

	// 	frontier.remove(min1);

	// 	return min1;
	// }

	public static void printRobotPath(Robot robot){
		ArrayList<Node> path=robot.getRobotPath();
		if(!path.isEmpty()){
			System.out.println("Path:");
			int pathSize=path.size();
			String robPath="["+path.get(pathSize-1).getCol()+","+rowInverse[path.get(pathSize-1).getRow()]+"]";
			for(int i=pathSize-2; i>= 0;i--){
				robPath=robPath+" => ["+path.get(i).getCol()+","+rowInverse[path.get(i).getRow()]+"]";
			}
			System.out.println(robPath+"\n");
		}
		else{
			System.out.println("No Solution.");
		}
		
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
				
				if(north.getG()==0.0){
					north.setG(currentStep);
				}
				if(!frontier.contains(north)){
					frontier.add(north);
					north.setParent(current);
					
				}
			}
		}
		
		//check south node
		if (row+1 <= maze.height - 1){
			Node south = maze.getNode(row+1, col);
			if (!south.isBool() && !visited.contains(south)){
				if(south.getG()==0.0){
					south.setG(currentStep);
				}
				if(!frontier.contains(south)){
					frontier.add(south);
					south.setParent(current);
				}
			}
		}
		
		//check west node
		if (col-1 >= 0){
			Node west = maze.getNode(row, col-1);
			if (!west.isBool() && !visited.contains(west)){
				if(west.getG()==0.0){
					west.setG(currentStep);
				}
				if(!frontier.contains(west)){
					frontier.add(west);
					west.setParent(current);
				}
			}
		}
		
		//check east node
		if (col+ 1 <= maze.width-1){
			Node east = maze.getNode(row, col+1);
			if (!east.isBool() && !visited.contains(east)){
				if(east.getG()==0.0){
					east.setG(currentStep);
				}
				if(!frontier.contains(east)){
					frontier.add(east);
					east.setParent(current);
				}
			}
		}

		//prints current values in the frontier
		// for (int j = 0; j < frontier.size(); j++){
		// 			Node node=maze.getNode(frontier.get(j).getRow(),frontier.get(j).getCol());
		// 			System.out.println("frontier("+j+"):("+node.getCol()+","+rowInverse[node.getRow()]+"), F(x)="+node.getG()+"+"+node.getH()+"="+node.getDistance());
		// 		}
		// 		System.out.println("========================================");
	}
	
	/*===================================
					Main
	====================================*/
	public static void main(String[] args) throws IOException{
		mazeFilePath=args[0];
		readMazeFile(new File(mazeFilePath));
		maze.printBoard();
		maze.calcHValues();
		int[] destination=maze.getDestination();
		System.out.println("====================================================================");
		System.out.println("Destination: ("+destination[0]+","+rowInverse[destination[1]]+")");
		findPathAStar();
		
	 }


}



