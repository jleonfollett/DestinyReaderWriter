package john;

import java.io.IOException;
import java.text.ParseException;

public class Main {

	public static void main(String[] args)  {
		// TODO Auto-generated method stub
			try {
				try {
					new DestinyWriter(new DestinyReader().getFileContents());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

}
