import java.io.*;
import java.util.*;

public class PathFinding{



public static void getMaze(String filePath){
	String line = null;
		BufferedReader reader=null;

		try{
			FileReader file = new FileReader(filePath);
			reader = new BufferedReader(file);
		}
		catch(FileNotFoundException f){
			System.out.println("Error: File not found");
		}

		try{
			int rowIndex=0;
			while((line = reader.readLine()) != null){
				String[] mazeRow=line.split(" ");
				System.out.println(mazeRow);

			}

		}
		catch (IOException e){
			System.out.println("Error: Cannot readfile");
		}
}

public static void main(String[] args){
	getMaze("/Users/Perlanie/Documents/Projects/PathFinding_Ai/maze.txt");

}


}