package graphs;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
/**
 *
 * @author Jens Andreassen
 *
 */
public class Main {
	/**
	 * Reads words from file
	 * @param filename path
	 * @return ArraList containing words
	 */
	@SuppressWarnings("resource")
	private static ArrayList<String> reader(String filename) {
		ArrayList<String> words = new ArrayList<String>();
		try {
			BufferedReader r;
			r = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
			while (true) {
			String word = r.readLine();
			if (word == null) { break; }
			assert word.length() == 5; // indatakoll, om man kör med assertions på
			words.add(word);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return words;
	}
	/**
	 * Performs test based on words in file vs wordindex in ArrayList
	 * @param graph 
	 * @param testfile
	 * @param words
	 */
	private static void test(Digraph graph, String testfile, ArrayList<String> words) {
		BufferedReader r;
		try {
			r = new BufferedReader(new InputStreamReader(new FileInputStream(testfile)));
			while (true) {
				String line = r.readLine();
				if (line == null) { break; }
				String start = line.substring(0, 5);
				String goal = line.substring(6, 11);
				BreadthFirstDirectedPaths path = new BreadthFirstDirectedPaths(graph, words.indexOf(start));
				if(path.hasPathTo(words.indexOf(goal))) {
					System.out.println(path.distTo(words.indexOf(goal)));
				} else {
					System.out.println(-1);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	/**
	 * Adds edges between nodes in graph based on the words
	 * @param words 
	 * @param graph
	 */
	private static void addEdges(ArrayList<String> words, Digraph graph) {
		for(int i=0; i<words.size();i++) {
			for(int j=0; j<words.size();j++) {
				if(!words.get(i).equals(words.get(j))&&pair(words.get(i), words.get(j))) {
					graph.addEdge(i, j);
				}
			}
		}
	}
	
	/**
	 * Checks wether there should be a path from first to second.
	 * @param first
	 * @param second
	 * @return true/false
	 */
	private static boolean pair(String first, String second) {
		for (int i =1; i<first.length(); i++) {
			if(second.indexOf(first.charAt(i))>-1) {
				int index = second.indexOf(first.charAt(i));
				second = second.substring(0, index) + second.substring(index+1);
			}
		}
		return second.length()<2;
	}
	
	public static void main(String[] args) {
		ArrayList<String> words = reader("C:/Users/andre/Desktop/algoritmuppg/words-5757-data.txt");
		Digraph diGraph = new Digraph(words.size());												 
		addEdges(words, diGraph);																
		test(diGraph, "C:/Users/andre/Desktop/algoritmuppg/words-5757-test.txt", words);			
	}
}
