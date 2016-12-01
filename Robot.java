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


}
