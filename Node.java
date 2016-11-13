import java.io.*;
import java.util.*;

public class Node{
	int g;
	int h;
	int row;
	int col;
	boolean isObject;
	public Node(int g, int h, int row, int col, boolean isObject){
		this.g=g;
		this.h=h;
		this.row=row;
		this.col=col;
		this.isObject=isObject;
	}
}