import java.io.*;
import java.util.*;

public class Node{
	private int g;
	private double h;
	private int row;
	private int col;
	private boolean isObject;

	public Node(int row, int col, boolean isObject){
		this.row=row;
		this.col=col;
		this.isObject=isObject;
	}

	/*===============================================================
	getRow: sets the H value of the node
	================================================================*/
	public void getRow(){
		return this.row;
	}
	
	/*===============================================================
	getRow: sets the H value of the node
	================================================================*/
	public void getCol(){
		return this.col;
	}
	public double getDistance(){
		return ((double)this.g + (double)this.h);
	}
	/*===============================================================
	getRow: sets the H value of the node
	================================================================*/
	public void getBool(){
		return this.isObject;
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