package suffixtries;
/**
 * Written with inspo from Booksite
 * supports put and get operations on a Trie-datastructure for Strings
 * @author Jens Andreassen
 *
 */
public class Trie {
	public Node root; 

	/**
	 * Node subclass
	 * @author Jens Andreassen
	 *
	 */
	public static class Node {
		public char c; 
		public Node left, mid, right; 
		public int val; 
	}
	/**
	 * returns the index of key or -1 if not contained
	 * @param key key to serch for
	 * @return index or -1
	 */
	public int get(String key) {
		Node x = get(root, key, 0);
		if (x == null) {
			return -1;
		}
		return x.val;
	}
	/**
	 * Used by other get-method. searches from root and downward
	 * until hit or null-node. Returns either ending node of key or null-node
	 * @param x node to start search from
	 * @param key key to search for
	 * @param d index of key at the moment of call
	 * @return Node, end node of key or null
	 */
	private Node get(Node x, String key, int d) {
		if (x == null) {
			return null;
		}
		if (key.length() == 0) {
			return null;
		}
		char c = key.charAt(d);
		if (c < x.c) {
			if(x.left == null) {
				return x.left;
			}
			return get(x.left, key, d);
		}
		else if (c > x.c) {
			if(x.right == null) {
				return x.right;
			}
			return get(x.right, key, d);
		}
		else if (d < key.length() - 1) {
			if(x.mid == null) {
				return x.mid;
			}
			return get(x.mid, key, d + 1);
		}
		else {
			return x;
		}
	}
	/**
	 * puts key in trie of not already existing
	 * @param key to put in
	 * @param val index/value of key
	 */
	public void put(String key, int val) {
		root = put(root, key, val, 0);
	}
	/**
	 * Starts from x and searches for where to start or starts at x if not same
	 * char in x-node
	 * @param x node to start from
	 * @param key key to put in
	 * @param val 
	 * @param d
	 * @return
	 */
	private Node put(Node x, String key, int val, int d) {
		char c = key.charAt(d);
		if (x == null) {
			x = new Node();
			x.c = c;
		}
		if (c < x.c) {
			x.left = put(x.left, key, val, d);
		}
		else if (c > x.c) {
			x.right = put(x.right, key, val, d);
		}
		else if (d < key.length() - 1) {
			x.mid = put(x.mid, key, val, d + 1);
		}
		else {
			x.val = val;
		}
		return x;
	}
}
