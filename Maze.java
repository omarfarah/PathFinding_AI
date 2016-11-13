import java.io.*;
import java.util.*;

public class Maze{

	public Maze(){

	}

	public static void initMaze(String filePath){
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
				String 
				String[] mazeRow=line.split(" ");

				System.out.println(mazeRow);

			}

		}
		catch (IOException e){
			System.out.println("Error: Cannot readfile");
		}
}


}