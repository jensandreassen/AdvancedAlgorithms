package Laboration1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
/**
 * Laboration for testing and learning about timecomplexity in algorithms
 * @author Jens Andreassen
 *
 */
public class Laboration1 {
	
	public static String filterOutChar(String in, char charToGetRidOf) {
	    String out = "";
	    for (int i = 0; i < in.length(); i++) {
	        char c = in.charAt(i);
	        if (c != charToGetRidOf) {
	            out += c;
	        }
	    }
	    return out;
	}
	
	public static void main(String[] args) throws IOException {
		
        //String s = "Testar. Ett, två, tre";
        
        byte[] encoded = Files.readAllBytes(Paths.get("C:\\Users\\andre\\Downloads\\GetFile.txt"));
		String s = new String(encoded, 0, 384000, "US-ASCII");
		
        long before = System.currentTimeMillis();
        String t = filterOutChar(s, 'e');
        long after = System.currentTimeMillis();
        //System.out.println("Efter: " + t);
        System.out.println("Tid: " + (after-before)/1000.000000);
    }
}
