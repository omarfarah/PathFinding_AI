import java.io.*;
import java.util.*;

public class Maze{
	Node[][] board;
	int height, width, endX, endY;
	
	public Maze(int endX, int endY, Node[][] board){
		this.board=board;
		this.endX=endX;
		this.endY=endY;
	}
}