package MiningConcepts;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Scanner;

public class MiningConcepts {
	public static void main(String args[]) throws IOException {

		// record-13.con to con-13.txt

		/*
		 * File files = new File("/Users/Eldridg/VUwork/input/con/"); File[]
		 * fileList = files.listFiles();
		 * 
		 * for (int i = 0; i < fileList.length; i++) { File file = fileList[i];
		 * 
		 * if (file.isFile() && file.getName().endsWith(".con")) { Scanner
		 * scanner = new Scanner(file); ArrayList<String> listArray = new
		 * ArrayList<String>(); while (scanner.hasNextLine()) { String line =
		 * scanner.nextLine(); line = line.replaceFirst("c=\"", ""); line =
		 * line.replaceAll("\"[^\"]+\"", " "); line =
		 * line.replaceAll("\"[^\"]+\"", " ");
		 * 
		 * line = line.replaceFirst("\"", ""); listArray.add(line); String
		 * listString = "";
		 * 
		 * for (String s : listArray) { listString += s + "\n"; }
		 * 
		 * String path = "/Users/Eldridg/VUwork/input/temp/" + file.getName() +
		 * "-con"; Files.write(Paths.get(path), listString.getBytes(),
		 * StandardOpenOption.CREATE); }
		 * 
		 * scanner.close();
		 * 
		 * }
		 * 
		 * // con-13.txt to conlist-13.txt (IOB tagging)
		 * 
		 * File files = new File("/Users/Eldridg/VUwork/input/temp/"); File[]
		 * conList = files.listFiles();
		 * 
		 * 
		 * for (int i = 0; i < conList.length; i++) { File file = conList[i];
		 * Scanner lineScanner = new Scanner(file); ArrayList<String> conArray =
		 * new ArrayList<String>(); while (lineScanner.hasNextLine()) { String
		 * line = lineScanner.nextLine(); String lastWord =
		 * line.substring(line.lastIndexOf(" ") + 1); Scanner wordScanner = new
		 * Scanner(line); String word = wordScanner.next(); String wordLine =
		 * word + " B-" + lastWord; // System.out.println(wordLine);
		 * 
		 * conArray.add(wordLine);
		 * 
		 * while (wordScanner.hasNext()) { word = wordScanner.next(); if
		 * (word.equals(lastWord)) { break; } else { wordLine = word + " I-" +
		 * lastWord; word = wordScanner.next(); conArray.add(wordLine); } }
		 * String textString = ""; for (String s : conArray) { textString += s +
		 * "\n"; }
		 * 
		 * String path = "/Users/Eldridg/VUwork/input/conlist/" +
		 * file.getName(); Files.write(Paths.get(path), textString.getBytes(),
		 * StandardOpenOption.CREATE); wordScanner.close(); }
		 * lineScanner.close();/*
		 * 
		 * // record-13.txt to text-13.txt
		 * 
		 * File text = new File("/Users/Eldridg/VUwork/input/text/"); File[]
		 * textList = text.listFiles();
		 * 
		 * for (int j = 0; j < textList.length; j++) { File file = textList[j];
		 * 
		 * Scanner textscanner = new Scanner(file); ArrayList<String> textArray
		 * = new ArrayList<String>(); while (textscanner.hasNext()) { String
		 * word = textscanner.next(); textArray.add(word);
		 * 
		 * String textString = ""; for (String s : textArray) { textString += s
		 * + "\n"; }
		 * 
		 * String path = "/Users/Eldridg/VUwork/input/texttab/" + file.getName()
		 * + ""; Files.write(Paths.get(path), textString.getBytes(),
		 * StandardOpenOption.CREATE); }
		 * 
		 * textscanner.close();
		 */
		
		

		// conlist-13.txt + text-13.txt ==> output-13.txt

		File cons = new File("/Users/Eldridg/VUwork/input/conlist/");
		File[] consList = cons.listFiles();
		File texttab = new File("/Users/Eldridg/VUwork/input/texttab/");
		File[] texttabList = texttab.listFiles();
		for (int i = 0; i < consList.length; i++) {
			for (int j = 0; j < texttabList.length-1; j++) {
				File conFile = consList[i];
				File texttabFile = texttabList[j];
				String conName = conFile.getName();
				String texttabName = texttabFile.getName();

				if (conName.substring(0,10).equals(
						texttabName.substring(0,10))) {
					System.out.println(conName);
					System.out.println(texttabName);

					Scanner cScanner = new Scanner(conFile);
					Scanner tScanner = new Scanner(texttabFile);

					ArrayList<String> finalArray = new ArrayList<String>();
					String tWord = tScanner.next();
					String cWord = cScanner.next();
					String cLine = cScanner.nextLine();

					while (tScanner.hasNext()) {
						String cTag = cLine
								.substring(cLine.lastIndexOf(" ") + 1);
						char cTagLetter = cTag.charAt(0);
						if (tWord.equalsIgnoreCase(cWord) && cTagLetter == 'B') {
							String line = tWord
									+ " "
									+ cLine.substring(cLine.lastIndexOf(" ") + 1);
							finalArray.add(line);

							if (cScanner.hasNext()) {

								tWord = tScanner.next();
								cWord = cScanner.next();
								cLine = cScanner.nextLine();

								cTag = cLine
										.substring(cLine.lastIndexOf(" ") + 1);
								cTagLetter = cTag.charAt(0);
								while (cTagLetter == 'I' && cScanner.hasNext()) {
									line = tWord
											+ " "
											+ cLine.substring(cLine
													.lastIndexOf(" ") + 1);
									finalArray.add(line);
									tWord = tScanner.next();
									cWord = cScanner.next();
									cLine = cScanner.nextLine();
									cTag = cLine.substring(cLine
											.lastIndexOf(" ") + 1);
									cTagLetter = cTag.charAt(0);
								}
							} else {
								tWord = tScanner.next();
							}

						} else if (!cScanner.hasNext()) {
							finalArray.add(tWord + " O");
							tWord = tScanner.next();
							cScanner = new Scanner(conFile);

						} else {
							cWord = cScanner.next();
							cLine = cScanner.nextLine();
						}
					}

					String textString = "";
					for (String s : finalArray) {
						textString += s + "\n";
					}
					// System.out.println(textString);

					String path = "/Users/Eldridg/VUwork/processed/"
							+ texttabName;

					Files.write(Paths.get(path), textString.getBytes(),
							StandardOpenOption.CREATE);

					cScanner.close();
					tScanner.close();

				}
			}
		}
	}
}
