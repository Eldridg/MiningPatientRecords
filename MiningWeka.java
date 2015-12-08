package MiningWeka;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Scanner;

public class MiningWeka {

	public static void main(String args[]) throws IOException {

		// con-13.con to weka-13.txt
		File files = new File("/Users/Eldridg/VUwork/CompleteTextfiles/");
		File[] fileList = files.listFiles();
		File arff = new File("/Users/Eldridg/VUwork/ONEFile/allrecords.csv");

		FileWriter fileWriter = new FileWriter(arff, true);
		fileWriter.append("word,tag,POS-tag,chunk");
		for (int i = 0; i < fileList.length; i++) {
			File file = fileList[i];

			String weka = new Scanner(new File(
					"/Users/Eldridg/VUwork/CompleteTextfiles/"
							+ file.getName().substring(0,
									file.getName().length() - 4) + ".csv"))
					.useDelimiter("\\Z").next();

			// Create new .CSV file with prefix
			// File arff = new File("/Users/Eldridg/VUwork/NEWINPUT/" +
			// file.getName().substring(0, file.getName().length()-4) + ".csv");

			fileWriter.append("\n" + weka);
			

			/*
			 * Scanner scanner = new Scanner(file); ArrayList<String> listArray
			 * = new ArrayList<String>(); while (scanner.hasNextLine()) { String
			 * line = scanner.nextLine(); //String end = "\"," +
			 * line.substring(line.lastIndexOf(" ") + 1); //line =
			 * line.replaceAll(" [^ ]+$", ""); //String result = "\"" + line +
			 * end;
			 * 
			 * listArray.add(line); String listString = "";
			 * 
			 * for (String s : listArray) { listString += s + "\n"; }
			 * 
			 * //String path = "/Users/Eldridg/VUwork/NEWINPUT/" +
			 * file.getName().substring(0, file.getName().length()-4) + ".csv";
			 * //Files.write(Paths.get(path), listString.getBytes(), //
			 * StandardOpenOption.CREATE); }
			 * 
			 * String weka = new Scanner(new File(
			 * "/Users/Eldridg/VUwork/CompleteTextfiles/" +
			 * file.getName().substring(0, file.getName().length()-4) +
			 * ".csv")).useDelimiter("\\Z") .next(); fileWriter.append(weka);
			 * fileWriter.close();
			 */
			// scanner.close();
		} 
		fileWriter.close();


	}		
}
