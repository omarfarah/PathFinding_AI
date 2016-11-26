import java.io.*;
import java.util.*;

public class Robot{
	int row;
	int col;

	public Robot(int row, int col){
		this.row=row;
		this.col=col;
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
