package bruteForceString;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Random;

/**
 * The class is a test enviorment for a brute-force string algoritm. 
 * @author Jens Andreassen
 *
 */

public class Main {
	private static long compares;
	private static Random r = new Random();
		/**
		 * creates testdata in the form of a random string
		 * @param wanted lenght
		 * @return the string
		 */
		private static String createTestData(int lenght) {
			char[] data = new char[lenght];
			for(int i=0;i<data.length;i++) {
				data[i] = (char)(r.nextInt(26) + 'a');
			}
		return new String(data);
		}
		/**
		 * Saves text to file.
		 * @param text
		 */
		private static void saveToFile(String text) {
			try {
				Files.write(Paths.get("testdata.txt"), text.getBytes());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		/**
		 * Performs a brute-force/naive search of the provided text against the 
		 * provided pattern
		 * @param pat pattern 
		 * @param txt text to search
		 * @return the starting position of the occurence of the pattern in the text
		 *  or 0 if it doesent occur.
		 */
		private static int bruteForceSearch(String pat, String txt) {
			int m = pat.length();
			int n = txt.length();
			for(int i =0; i<=n-m;i++) {//i mindre än text - pattern
				int j;
				for(j=0; j<m;j++) {//patterns längd
					compares++;
					if(txt.charAt(i+j) != pat.charAt(j)) {//för att slippa räkna upp i osv
						break;//om den inte stämmer
					}
				}
				if(j==m) { // efter loopen, kolla om alla stämde i mönstret
					return i;//returnera vart man hittade det
				}
			}
			return 0; // returnera hela texten
		}
		/**
		 * creates a string wich is worst-case for a brute-force
		 * algoritm with the pattern: "aaaaaaab".
		 * @param length of the string wanted
		 * @return the string
		 */
		private static String createWorstcase(int length) {
			char[] data = new char[length];
			for(int i=0;i<data.length-1;i++) {
				data[i] = 'a';
			}
			data[data.length-1] = 'b';
			return new String(data);
		}
		/**
		 * reads a file
		 * @param lenght
		 * @return
		 * @throws IOException
		 */
		private static String readfile(int lenght) throws IOException {
			byte[] data = Files.readAllBytes(Paths.get("files\\GetFile.txt"));
			return new String(Arrays.copyOf(data, lenght));
		}
		
		private static String randomSubstring(String text, int size) {
			int start = r.nextInt(text.length()-size);
			return text.substring(start, start+size);
		}
		private static String lastIndexSubstring(String text, int size) {
			return text.substring(text.length()-size, text.length());
		}
		
		public static void main(String[] args) throws IOException {
//			
//			String pattern = createWorstcase(50000);
//			String data = createWorstcase(100000);
//			
//			String data = createTestData(100000);
//			String pattern = lastIndexSubstring(data, 60000);
			
			String data = readfile(100000);
			String pattern = lastIndexSubstring(data, 60000);
			
			//saveToFile(data);
			
			long before = System.currentTimeMillis();
			int res = bruteForceSearch(pattern, data);
			long after = System.currentTimeMillis();
			
			System.out.println("Antal jämförelser: " + compares);
			System.out.println("Tid: " + (after-before)/1000.000000);
			System.out.println("Hittade mönstret: " + pattern.length() + ": " + (res!=0) + ". På plats: " + res);
		
		}
}
