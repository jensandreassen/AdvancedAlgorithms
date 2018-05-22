package sortingAlgorithms;

import java.util.Random;

public class Quick {
	private static int m;
	private static Random rand = new Random();

	public static void quickSort(byte[] arr, int lo, int hi, int insertion) {
		m = insertion;
		int i = lo, j = hi;
		int p = arr[lo];
//		int p = arr[hi]; // För test uppgift 2c
//		int p = arr[rand.nextInt((hi - lo) + 1) +lo]; // För test uppgift 2c
		
		while (i <= j) {
			while (arr[i] < p) {
				i++;
			}
			while (arr[j] > p) {
				j--;
			}
			if (i <= j) {
				exchange(arr, i, j);
				i++;
				j--;
			}
		}
		if (hi - lo > m) {
			if (lo < j) {
				quickSort(arr, lo, j, m);
			}
			if (i < hi) {
				quickSort(arr, i, hi, m);
			}
		} else {
			MyInsertionTest.insertionSort(arr, lo, hi);
		}

	}

	private static void exchange(byte[] arr, int i, int j) {
		byte temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
}
