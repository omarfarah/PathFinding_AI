import java.io.*;
import java.util.*;
import java.lang.Math;

public class Maze{
	Node[][] board;
	int height, width, endX, endY;
	int[] rowInverse;
	
	public Maze(int height, int width,int endX, int endY, Node[][] board){
		this.height=height;
		this.width=width;
		this.board=board;
		this.endX=endX;
		this.endY=endY;
		this.rowInverse= new int[this.height];
		int counter=0;
		for(int i=this.height-1;i>=0;i--){
			this.rowInverse[i]=counter;
			counter++;
		}	
	}

	public Maze copyMaze(){
		Node[][] newBoard = new Node[this.height][this.width];
		for(int r=0;r<this.height;r++){
			for(int c=0;c<this.width;c++){
				newBoard[r][c]=this.board[r][c];
			}
		}

		Maze newMaze=new Maze(this.height,this.width,this.endX,this.endY,newBoard);

		return newMaze;
		

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
				if(node.isBool()){
					row=row+"1";
				}
				else if (node.getRow()==this.endY&& node.getCol()==this.endX){
					row=row+"X";
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

	public void resetMaze(){
		for(int r=0; r<this.height;r++){
			for(int c=0;c<this.width;c++){
				this.board[r][c].setG(0);
				this.board[r][c].setParent(null);
			}
		}
	}

	/*=================================================================
	calcHValues: calculates the H values of all the nodes in the board
	==================================================================*/
	public void calcHValues(){
		for(int r=0; r<this.height;r++){
			for(int c=0;c<this.width;c++){
				//System.out.println("("+c+","++);
				double lineDistance = Math.sqrt(Math.pow(this.endY-r,2)+Math.pow(this.endX-c,2));
				this.board[r][c].setH(lineDistance);
			}
		}
	}
	
	public boolean isGoal(Node n){
		if (n.getRow() == endY && n.getCol() == endX){
			return true;
		}else{
			return false;
		}
	}
}