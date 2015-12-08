package AppendFiles;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Scanner;

public class AppendFiles {

	public static void main(String args[]) throws IOException {

		// wekaInput.csv (input1) + txtprocessed.csv (input2) --> processedInput

		File input1 = new File("/Users/Eldridg/VUwork/wekaInput/");
		File[] input1List = input1.listFiles();
		File input2 = new File("/Users/Eldridg/VUwork/input/txtprocessed/");
		File[] input2List = input2.listFiles();
		for (int i = 0; i < input1List.length; i++) {
			for (int j = 0; j < input2List.length - 1; j++) {
				File file1 = input1List[i];
				File file2 = input2List[j];
				String file1name = file1.getName();
				String file2name = file2.getName();
				String fileName = file1.getName();

				if (file1name.substring(0, 10).equals(
						file2name.substring(0, 10))) {
					System.out.println(file1name);
					System.out.println(file2name);

					Scanner scanner1 = new Scanner(file1);
					Scanner scanner2 = new Scanner(file2);

					ArrayList<String> finalArray = new ArrayList<String>();
					String line1 = scanner1.nextLine();
					String word1 = scanner2.next();
					String word2 = scanner2.next();
					String word3 = scanner2.next();

					while (scanner1.hasNext() && scanner2.hasNext()) {

						if (word2.equals(",")) {
							word2 = "\",\"";
						}

						if (word1.substring(0, 1).equals(line1.substring(1, 2))) {

							String line = line1 + "," + word2 + "," + word3;

							System.out.println(line);
							finalArray.add(line);
							line1 = scanner1.nextLine();
							scanner2.nextLine();
							word1 = scanner2.next();
							word2 = scanner2.next();
							word3 = scanner2.next();
						}
					} 

					String textString = "";
					for (String s : finalArray) {
						textString += s + "\n";
					} //
					System.out.println(textString);

					String path = "/Users/Eldridg/VUwork/CompleteTextfiles/"
							+ fileName;

					Files.write(Paths.get(path), textString.getBytes(),
							StandardOpenOption.CREATE);

					scanner1.close();
					scanner2.close();

				}
			}
		}

	}
}
