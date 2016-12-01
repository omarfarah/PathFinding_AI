import java.io.*;
import java.util.*;

/*===============================================================
Node Class: 
This class creates a instance for each node (position on the maze) which includes
the specific nodes function values (g,h and f) its row and column position (x,y),
if the node is a object, and each parent node it has.

		@param g;			g value
		@param h;			h value
		@param row;			row position of node
		@param col;			column position of node
		@param isObject;	object check
		@param parent;		The parents
================================================================*/

public class Node{
	private double g=0;
	private double h;
	private int row;
	private int col;
	private boolean isObject;
	private Node parent;
	private String symbol;

	public Node(int row, int col, boolean isObject){
		this.row=row;
		this.col=col;
		this.isObject=isObject;
	}

	/*===============================================================
	getRow: gets the row value of the node
	================================================================*/
	public int getRow(){
		return this.row;
	}
	
	/*===============================================================
	getCol: gets the column value of the node
	================================================================*/
	public int getCol(){
		return this.col;
	}
	
	/*===============================================================
	getDistance: gets the distance from current Node to Final
	================================================================*/
	public double getDistance(){
		return ((double)this.g + (double)this.h);
	}

	/*===============================================================
	isBool: checks if Node is an object
	================================================================*/
	public boolean isBool(){
		return this.isObject;
	}
	
	/*===============================================================
	setH: sets the H value of the node
	================================================================*/
	public void setH(double hValue){
		this.h=hValue;
	}

	/*===============================================================
	setH: gets the H value of the node
	================================================================*/
	public double getH(){
		return this.h;
	}

	/*===============================================================
	setG: sets the G value of the node
	================================================================*/
	public void setG(int gValue){
		this.g=gValue;
	}

	/*===============================================================
	getG: gets the G value of the node
	================================================================*/
	public double getG(){
		return this.g;
	}

	/*===============================================================
	setParent: sets the parent ndoes for current node
	================================================================*/
	public void setParent(Node parent){
		this.parent=parent;
	}
	/*===============================================================
	getParent: gets the parent ndoes for current node
	================================================================*/
	public Node getParent(){
		return this.parent;
	}
	/*===============================================================
	setSymbol: sets the symbol
	================================================================*/
	public void setSymbol(String symbol){
		this.symbol=symbol;
	}
	/*===============================================================
	getSymbol: gets the symbol
	================================================================*/
	public String getSymbol(){
		return this.symbol;
	}



}