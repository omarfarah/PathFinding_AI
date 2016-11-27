import java.io.*;
import java.util.*;

public class Node{
	private double g=0;
	private double h;
	private double f;
	private int row;
	private int col;
	private boolean isObject;
	private Node parent;

	public Node(int row, int col, boolean isObject){
		this.row=row;
		this.col=col;
		this.isObject=isObject;
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
	public double getDistance(){
		//System.out.println("F("+this.col+","+this.row+")="+(double)this.g +"+"+this.h+"="+((double)this.g + (double)this.h));
		return ((double)this.g + (double)this.h);
	}

	public void setF(double f){
		this.f=f;
	}

	/*===============================================================
	getRow: sets the H value of the node
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

	public double getH(){
		return this.h;
	}

	/*===============================================================
	setG: sets the G value of the node
	================================================================*/
	public void setG(int gValue){
		this.g=gValue;
	}

	public double getG(){
		return this.g;
	}

	public void setParent(Node parent){
		this.parent=parent;
	}

	public Node getParent(){
		return this.parent;
	}



}