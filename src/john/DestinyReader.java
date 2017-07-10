package john;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class DestinyReader {
	private BufferedReader br;
	private FileReader fr;
	private ArrayList<String> fileContents;
	private ArrayList<String> remove;
	private Scanner scanner = new Scanner(System.in);
	private enum Result{
		GOOD,
		BAD
	}


	public DestinyReader() throws IOException, ParseException{
		BuildList(); // Builds ArrayLists that are used

		// Opens File and BufferedReader
		System.out.println("Please enter file location");
		fr = new FileReader(scanner.nextLine());
		br = new BufferedReader(fr);
		AddRemoves();
	}
	private void BuildList(){
		fileContents = new ArrayList<String>();
		remove = new ArrayList<String>();
	}
	private void AddRemoves() throws IOException, ParseException{
		//  Allows users to input Strings they want removed from the file until "Done" is entered
		String input = "";
		while (true){
			System.out.println("Please enter a word you want removed.  Enter 'Done' when done.");
			input = scanner.nextLine(); 
			if(input.equals("Time")){
				CheckLinesTime(br);
				break;
			}
			if(input.equals("Done")){
				CheckLines(br);
				break;
			}
			remove.add(input);
		}
	}

	private void CheckLinesTime(BufferedReader br) throws IOException, ParseException{
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy-MM-dd hh:mm:ss");
		String line;
		System.out.println("Enter start time");
		String start = scanner.nextLine();
		Date parsedTimeStamp = dateFormat.parse(start);		
		Timestamp timestampStart = new Timestamp(parsedTimeStamp.getTime());
		System.out.println("Enter end time");
		String end = scanner.nextLine();
		parsedTimeStamp = dateFormat.parse(end);
		Timestamp timestampEnd = new Timestamp(parsedTimeStamp.getTime());	   
		Timestamp currentTime = null;
		while((line = br.readLine()) != null){
			if(Character.isDigit(line.charAt(0))){
				parsedTimeStamp = dateFormat.parse(line.substring(0,19));
				currentTime = new Timestamp(parsedTimeStamp.getTime());
			}
			if(currentTime.compareTo(timestampStart) >= 0){
				do{
					fileContents.add(line);
					line = br.readLine();
					if(Character.isDigit(line.charAt(0))){
						parsedTimeStamp = dateFormat.parse(line.substring(0,19));
						currentTime = new Timestamp(parsedTimeStamp.getTime());
					} 
				}
				while ((line != null && currentTime.compareTo(timestampEnd) < 0) || !Character.isDigit(line.charAt(0)));
			}
			if(!fileContents.isEmpty()){
				break;
			}
		}

		// For loop checks if line contains the strings that should be removed

		fr.close();
	}



	private void CheckLines(BufferedReader br) throws IOException{
		// Iterates through each line in the file
		String line;
		while((line = br.readLine()) != null){
			Result result = Result.GOOD;
			// For loop checks if line contains the strings that should be removed
			for(String removeStr: remove){
				if(line.contains(removeStr)){
					result = Result.BAD;
					break;
				}else{
					result = Result.GOOD;
				}
			}
			if(result == Result.GOOD){
				fileContents.add(line);
			}
		}
		fr.close();
	}

	public ArrayList<String> getFileContents(){
		return this.fileContents;
	}
}

