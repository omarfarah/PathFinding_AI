import java.io.*;
import java.util.*;
import java.lang.Math;

public class Maze{
	Node[][] board;
	int height, width, endX, endY;
	
	public Maze(int height, int width,int endX, int endY, Node[][] board){
		this.height=height;
		this.width=width;
		this.board=board;
		this.endX=endX;
		this.endY=endY;
	}

	/*===============================================================
	readMazeFile: reads in the maze file to get the information to 
	intialize the maze and robots
	================================================================*/
	public void printBoard(){
		
		for(int r=0;r<this.height;r++){
			String row="";
			for(int c=0;c<this.width;c++){
				Node node=this.getNode(r,c);
				if(node.isObject==true){
					row=row+"1";
				}
				else{
					row=row+"0";
				}
			}
			System.out.println(row);
		}
	}

	/*===============================================================
	getNode: gets the node that is specified by the row and column
	----------------------------------------------------------------
	row: x position of the node
	col: y position of the node
	================================================================*/
	public Node getNode(int row, int col){
		return this.board[row][col];
	}

	/*=================================================================
	calcHValues: calculates the H values of all the nodes in the board
	==================================================================*/
	public void calcHValues(){
		for(int r=0; r<this.height;r++){
			for(int c=0;c<this.width;c++){
				double lineDistance = Math.hypot((this.endX-r),(this.endY-c));
				this.board[r][c].setH(lineDistance);
			}
		}



	}

}