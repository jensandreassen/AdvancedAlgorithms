package suffixtries;

import javax.swing.JOptionPane;

public class TestClass {
//	public static void main(String[] args) {
//		TrieNoType trie = new TrieNoType();
//		String[] arr = {"Skorsten", "kors", "skor", "sten", "en"};
//		for(int i =0; i<arr.length; i++) {
//			trie.put(arr[i], i);
//		}
//		System.out.println("Satte in: " + arr[0] + " " + arr[1] + " " + arr[2] + " " + arr[3] + " " + arr[4]);
//		System.out.println("Söker efter: " + "fest" + " fanns på pos : "  + trie.get("Skorstens"));
//	}
	public static void main(String[] args) {
		String text = JOptionPane.showInputDialog("Need input!!");
		SuffixArray suffix = new SuffixArray(text);
		String search = JOptionPane.showInputDialog("Search For what??");
		int index = suffix.search(search);
		JOptionPane.showMessageDialog(null, "Text: " + text + " Search: " + search + " index: " + index);
	}
}
	