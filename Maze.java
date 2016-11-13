import java.io.*;
import java.util.*;

public class Maze{
	ArrayList<ArrayList<Node>> board;
	int height;
	int width;
	public class Node{
		int g;
		int h;
		int row;
		int col;
		boolean isObject;
		public Node(int g, int h, int row; int col; boolean isObject){
			this.g=g;
			this.h=h;
			this.row=row;
			this.col=col;
			this.isObject=isObject;

		}
	}
	public Maze(int height, int width ){
		this.board=new ArrayList<ArrayList<Node>>();
		this. height=height;
		this.width=width;
	}

	public static void initMaze(String filePath){
		String line = null;
		BufferedReader reader=null;

		try{
			FileReader file = new FileReader(filePath);
			reader = new BufferedReader(file);
		}
		catch(FileNotFoundException f){
			System.out.println("Error: File not found");
		}

		try{
			int rowIndex=0;
			while((line = reader.readLine()) != null){
				String[] mazeRow=line.split(" ");

				System.out.println(mazeRow);

			}

		}
		catch (IOException e){
			System.out.println("Error: Cannot readfile");
		}
}


}