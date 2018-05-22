package suffixtries;

import java.util.Arrays;
/**
 * Builds an Suffixarray from text on creation and supports searcing
 * for longest suffix of pattern. 
 * @author Jens Andreassen
 *
 */
public class SuffixArray {
	private Suffix[] suffixes;
	
	/**
	 * Builds the SuffixArray
	 * @param text to build from.
	 */
    public SuffixArray(String text) {
        int n = text.length();
        this.suffixes = new Suffix[n];
        for (int i = 0; i < n; i++) {
            suffixes[i] = new Suffix(text, i);
        }
        Arrays.sort(suffixes); //N LOG(N)
    }
    
    /**
     * Checks if the first char in pattern is the first in a suffix via binaryserach
     * then checks if it is the longest with the compare-method, returns the index
     * of the longest suffix.
     * @param pattern
     * @return index of start of the longest prefix
     */
    public int search(String pattern) {
    	int max = 0, finalIndex = -1;
    	int index = binarySearch(suffixes, pattern);
    	char c = pattern.charAt(0);
    	if (index<0) {
    		return -1;
    	} else {
    		while (index>0 && c==suffixes[index-1].toString().charAt(0)) {
    			index--;
    		}
    		while (index<suffixes.length && c==suffixes[index].toString().charAt(0)) {
    			int value = compare(pattern, suffixes[index]);
    			if (value>max) {
    				max = value;
    				finalIndex = index;
    			}
    			index++;
    		}
    	}
		return suffixes[finalIndex].index;
    }
    /**
     * BinarySearch implementation for subclass Suffixes
     * @param suffixes array to search
     * @param pattern to search for
     * @return index of hit or -1
     */
    public static int binarySearch(Suffix[] suffixes, String pattern) {
    	char c = pattern.charAt(0);
        int lo = 0;
        int hi = suffixes.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (c < suffixes[mid].toString().charAt(0)) {
            	hi = mid - 1;
            }
            else if (c > suffixes[mid].toString().charAt(0)) {
            	lo = mid + 1;
            }
            else {
            	return mid;
            }
        }
        return -1;
    }
    /**
     * Method for comparing pattern to suffix, used to find longest match
     * @param pattern to compare 
     * @param suffix to this
     * @return return number of matching chars from start
     */
    public int compare(String pattern, Suffix suffix) {
    	int n = Math.min(pattern.length(), suffix.length());
    	int count = 0;
    	for(int i=0;i<n;i++) {
    		if(pattern.charAt(i)==suffix.toString().charAt(i)) {
    			count++;
    		}
    	}
    	return count;
    }
    /**
     * Subclass for containing Suffixes, borrowed from booksite
     */
    private static class Suffix implements Comparable<Suffix> {
        private final String text;
        private final int index;

        private Suffix(String text, int index) {
            this.text = text;
            this.index = index;
        }
        
        private int length() {
            return text.length() - index;
        }
        
        private char charAt(int i) {
            return text.charAt(index + i);
        }

        public int compareTo(Suffix that) {
            if (this == that) return 0;  // optimization
            int n = Math.min(this.length(), that.length());
            for (int i = 0; i < n; i++) {
                if (this.charAt(i) < that.charAt(i)) return -1;
                if (this.charAt(i) > that.charAt(i)) return +1;
            }
            return this.length() - that.length();
        }
        public String toString() {
            return text.substring(index);
        }
    }
}
