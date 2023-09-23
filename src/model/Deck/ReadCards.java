package model.Deck;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;


/** Se auti tin klasi uparxei ena apo ta kommatia etoimou kwdika pou mas
 * dwthike to opoio xrisimopoiw gia na diavazw tis kartes apo ta arxeia
 */
public class ReadCards {
	
	private static ClassLoader cldr = ReadCards.class.getClassLoader();
	static String[][] mailCards = new String[48][4];
	static String[][] dealCards = new String[20][8];
	
	public static String[][] readFile(String path, String type) {
		
		BufferedReader br = null;
		String sCurrentLine;
		try {
			
			String fullPath = cldr.getResource(path).getPath();
			br = new BufferedReader(new FileReader(fullPath));
		} catch (FileNotFoundException ex) {
			Logger.getLogger(ReadCards.class.getName()).log(Level.SEVERE, null, ex);
		}
		int count = 0;
	//	int splitCount = 0;
	//	HashMap<Integer, String> domainsMap = new HashMap<>();
		try {
			br.readLine();
			while ((sCurrentLine = br.readLine()) != null) {
				if (type.equals("Mail")) {
					mailCards[count++] = sCurrentLine.split(",");
				} else {
					dealCards[count++] = sCurrentLine.split(",");
				}
			}
			br.close();
		} catch (IOException ex) {
			Logger.getLogger(ReadCards.class.getName()).log(Level.SEVERE, null, ex);
		}
		if(type == "Deal")
			return dealCards;
		return mailCards;
	}
}
