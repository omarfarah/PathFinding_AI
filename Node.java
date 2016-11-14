import java.io.*;
import java.util.*;

public class Node{
	int g;
	double h;
	int row;
	int col;
	boolean isObject;

	public Node(int row, int col, boolean isObject){
		this.row=row;
		this.col=col;
		this.isObject=isObject;
	}

	/*===============================================================
	setH: sets the H value of the node
	================================================================*/
	public void setH(double hValue){
		this.h=hValue;
	}

	/*===============================================================
	setG: sets the G value of the node
	================================================================*/
	public void setG(int gValue){
		this.g=gValue;
	}

}