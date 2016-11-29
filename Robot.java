import java.io.*;
import java.util.*;

public class Robot{
	int row;
	int col;
	private ArrayList<Node> path= new ArrayList<Node>();
	public Robot(int row, int col){
		this.row=row;
		this.col=col;
	}

	public void addToPath(Node newPathNode){
		this.path.add(newPathNode);
	}

	public ArrayList<Node> getRobotPath(){
		return this.path;
	}

	/*===============================================================
	getRow: sets the H value of the node
	================================================================*/
	public int getRow(){
		return this.row;
	}
	
	/*===============================================================
	getRow: sets the H value of the node
	================================================================*/
	public int getCol(){
		return this.col;
	}

	// public void printRobotPath(){
	// 	if(!this.path.isEmpty()){
	// 		System.out.println("Path:");
	// 		int pathSize=this.path.size();
	// 		String robPath="["+this.path.get(pathSize-1).getCol()+","+this.path.get(pathSize-1).getRow()+"]";
	// 		for(int i=pathSize-2; i>= 0;i--){
	// 			robPath=robPath+" => ["+this.path.get(i).getCol()+","+this.path.get(i).getRow()+"]";
	// 		}
	// 		System.out.println(robPath+"\n");
	// 	}
	// 	else{
	// 		System.out.println("No Solution.");
	// 	}
		
	// }
}
