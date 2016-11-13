import java.io.*;
import java.util.*;

public class PathFinding{
	
	public static Node[][] board;
	public static ArrayList<ArrayList<Node>> board;
	public static ArrayList<Integer> robots;
	
	
	public static void readBoard(String filePath){
		String line = null;
		BufferedReader reader=null;

		//open file
		try{
			FileReader file = new FileReader(filePath);
			reader = new BufferedReader(file);
		}
		catch(FileNotFoundException f){
			System.out.println("Error: File not found");
		}

		//parse file
		try{
			int rowIndex=0;
			//get grid size
			
			//get number of robots
			
			//get robot coordinates
			
			//populate grid
			while((line = reader.readLine()) != null){
				String[] mazeRow=line.split(" ");

				System.out.println(mazeRow);

			}

		}
		catch (IOException e){
			System.out.println("Error: Cannot readfile");
		}
	}


public static void main(String[] args){
	//getMaze("/Users/Perlanie/Documents/Projects/PathFinding_Ai/maze.txt");
	// Maze m = new Maze(board,);	
	readBoard(args[0]);
}


}