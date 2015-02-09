package MiningWeka;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Scanner;

public class MiningWeka {

public static void main(String args[]) throws IOException {	  
			
//con-13.con to weka-13.txt
	        File cons = new File("C:/Users/Admin/VUwork/con-13.txt");
	        Scanner scanner = new Scanner(cons);
	        ArrayList<String> listArray = new ArrayList<String>();
	        while(scanner.hasNextLine()){
	            String line = scanner.nextLine();
	            String end = "'," + line.substring(line.lastIndexOf(" ")+1);
	            line = line.replaceAll(" [^ ]+$", "");
	            String result ="'" + line + end;
	            
	            listArray.add(result);
	            String listString = "";
	            
	            for (String s : listArray){
	            	listString += s + "\n";
	            }
	        
	            String path = "C:/Users/Admin/VUwork/weka-13.txt";
	            Files.write(Paths.get(path), listString.getBytes(), StandardOpenOption.CREATE);
		}
	        scanner.close();	
}
}

