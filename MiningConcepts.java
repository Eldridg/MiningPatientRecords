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
			
//record-13.con to con-13.txt
	        File cons = new File("C:/Users/Admin/VUwork/record-13.con");
	        Scanner scanner = new Scanner(cons);
	        ArrayList<String> listArray = new ArrayList<String>();
	        while(scanner.hasNextLine()){
	            String line = scanner.nextLine();
	            line=line.replaceFirst("c=\"", "");
	            line=line.replaceAll("\"[^\"]+\"", " ");
	            line=line.replaceAll("\"[^\"]+\"", " ");

	            line=line.replaceFirst("\"", "");
	            listArray.add(line);
	            String listString = "";
	            
	            for (String s : listArray){
	            	listString += s + "\n";
	            }
	        
	            String path = "C:/Users/Admin/VUwork/con-13.txt";
	            Files.write(Paths.get(path), listString.getBytes(), StandardOpenOption.CREATE);
		}
	        scanner.close();
			
//con-13.txt to conlist-13.txt
	        
	        File conlist = new File("C:/Users/Admin/VUwork/con-13.txt");
            Scanner lineScanner = new Scanner(conlist);
	        ArrayList<String> conArray = new ArrayList<String>();
	        while(lineScanner.hasNextLine()){
            	String line = lineScanner.nextLine();
            	String lastWord = line.substring(line.lastIndexOf(" ")+1);
            	Scanner wordScanner = new Scanner(line);
            	String word = wordScanner.next();
            	String wordLine = word + " B-" + lastWord;
        		//System.out.println(wordLine);

                conArray.add(wordLine);
            	word = wordScanner.next();
            	
            	while(wordScanner.hasNext()){
            		if(word.equals(lastWord)){
            			break;
            		} else{
            			wordLine = word + " I-" + lastWord;
            			word = wordScanner.next();
            			conArray.add(wordLine);
            			}   	
            	}
            	String textString = "";
            	for (String s : conArray){
            		textString += s + "\n";
            		}

            	String path = "C:/Users/Admin/VUwork/conlist-13.txt";
            	Files.write(Paths.get(path), textString.getBytes(), StandardOpenOption.CREATE);
            	wordScanner.close();
            	}
    	   lineScanner.close();
	        
//record-13.txt to text-13.txt    
		File text = new File("C:/Users/Admin/VUwork/record-13.txt");

		Scanner textscanner = new Scanner(text);
		ArrayList<String> textArray = new ArrayList<String>();
        while(textscanner.hasNext()){
        	String word = textscanner.next();
        	textArray.add(word);
        
        	String textString = "";
        	for (String s : textArray)
        		{
        			textString += s + "\n";
        		}
//      System.out.println(textString);
        
        	String path = "C:/Users/Admin/VUwork/text-13.txt";
        	Files.write(Paths.get(path), textString.getBytes(), StandardOpenOption.CREATE);
        	}
        
        textscanner.close();
			
//conlist-13.txt + text-13.txt ==> output-13.txt
		File text2 = new File("C:/Users/Admin/VUwork/text-13.txt");
		File con2 = new File("C:/Users/Admin/VUwork/conlist-13.txt");
		Scanner cScanner = new Scanner(con2);
		Scanner tScanner = new Scanner(text2);
	
		ArrayList<String> finalArray = new ArrayList<String>();
		String tWord = tScanner.next();
		String cWord = cScanner.next();
		String cLine = cScanner.nextLine();
		
		while(tScanner.hasNext()){
			String cTag = cLine.substring(cLine.lastIndexOf(" ")+1);
			char cTagLetter = cTag.charAt(0);
			System.out.println(cTag);
			if(tWord.equalsIgnoreCase(cWord) && cTagLetter == 'B'){
				String line = tWord + " " + cLine.substring(cLine.lastIndexOf(" ")+1);
				//System.out.println(line);
				finalArray.add(line);

				
				if(cScanner.hasNext()){
					
					tWord = tScanner.next();
					cWord = cScanner.next();
					cLine = cScanner.nextLine();
					
					cTag = cLine.substring(cLine.lastIndexOf(" ")+1);
					cTagLetter = cTag.charAt(0);
					//System.out.println(cTagLetter);
					while(cTagLetter == 'I'){
						line = tWord + " " + cLine.substring(cLine.lastIndexOf(" ")+1);
						System.out.println(line);
						finalArray.add(line);
						tWord = tScanner.next();
						cWord = cScanner.next();
						cLine = cScanner.nextLine();
						cTag = cLine.substring(cLine.lastIndexOf(" ")+1);
						cTagLetter = cTag.charAt(0);
					}
				}else{
					tWord = tScanner.next();
				}
			
			} else if(!cScanner.hasNext()){
				finalArray.add(tWord + " O");
				tWord = tScanner.next();
				cScanner = new Scanner(con2);
				
			} else{
				cWord = cScanner.next();
				cLine = cScanner.nextLine();
					} 
		}
		
		String textString = "";
	    for (String s : finalArray)
	    {
	       	textString += s + "\n";
	    }
//	    System.out.println(textString);
	    
	    String path = "C:/Users/Admin/VUwork/output-13.txt";
	    Files.write(Paths.get(path), textString.getBytes(), StandardOpenOption.CREATE);
	       
	    cScanner.close();
        tScanner.close();
	}		
}