package sortingAlgorithms;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;

public class MyInsertionTest {
    static void insertionSort(byte[] a, int lo, int hi) {
        for (int i = lo; i <= hi; i++) {
            for (int j = i; j > lo && a[j] < a[j-1]; j--) {
                byte x = a[j]; a[j] = a[j-1]; a[j-1] = x;
            }
        }
    }
 
    // Checks if the first n element of a are in sorted order.
    private static boolean isSorted(byte[] a, int lo, int hi) {
        int flaws = 0;
        for (int i = lo+1; i <= hi; i++) {
            if (a[i] < a[i-1]) {
                if (flaws++ >= 10) {
                    System.out.println("...");
                    break;
                }
                System.out.println("a["+i+"] = "+a[i]+", a["+(i-1)+"] = "+a[i+1]);
            }
        }
        return flaws == 0;
    }

    // Shuffles the first n elements of a.
    public static void shuffle(byte[] a, int lo, int hi) {
        Random rand = new Random();
        for (int i = lo; i <= hi; i++) {
            int r = i + rand.nextInt(hi+1-i);     // between i and hi
            byte t = a[i]; a[i] = a[r]; a[r] = t;
        }
    }

    public static void main(String[] args) throws Exception {
    	byte[] encoded = Files.readAllBytes(Paths.get("C:\\Users\\andre\\Downloads\\GetFile.txt"));
        int N = 1000000; // Change to some number to test on part of array.
        int M = 0; // Test with insertionsort in arrays containing smaller amounts than this
       

//        long before = System.currentTimeMillis();
//        insertionSort(encoded, 0, N-1);
//        long after = System.currentTimeMillis();

        
//        long before = System.currentTimeMillis();
//        Merge.mergeSort(encoded, 0, N-1, M);
//        long after = System.currentTimeMillis();

        shuffle(encoded, 0, N-1);
        long before = System.currentTimeMillis();
        Quick.quickSort(encoded, 0, N-1, M);
        long after = System.currentTimeMillis();
        
        // Write sorted to file, in case we want to check it.
        Files.write(Paths.get("processed.txt"), encoded);        

        if (isSorted(encoded, 0, N-1)) {
            System.out.println((after-before) / 1000.0000 + " seconds");
        }
    }
}