package john;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class DestinyWriter {
	Scanner scanner = new Scanner(System.in);
	BufferedWriter bw = null;
	FileWriter fw = null;
	
	DestinyWriter(ArrayList<String> Contents) throws IOException {
		System.out.println("Please enter location of output file");
		try {
			fw = new FileWriter(scanner.nextLine());
		bw = new BufferedWriter(fw);
		for(String content: Contents){
			bw.write(content);
			bw.newLine();
		}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Error" + e.getMessage());
		}
		bw.flush();
		fw.close();
	}
}
