package sortingAlgorithms;

public class Merge {
	private static int m;

	private static void sort(byte[] arr, byte[] temp, int lo, int hi) {
		int mid;
		if (lo < hi) {
			mid = lo + (hi - lo) / 2;
			if (hi - lo > m) {
				sort(arr, temp, lo, mid);
				sort(arr, temp, mid + 1, hi);
				merge(arr, temp, lo, mid, hi);
			} else {
				MyInsertionTest.insertionSort(arr, lo, hi);
			}
		}
	}

	private static void merge(byte[] arr, byte[] temp, int lo, int mid, int hi) {

		for (int i = lo; i <= hi; i++) {
			temp[i] = arr[i];
		}

		int i = lo, j = mid + 1;
		for (int k = lo; k <= hi; k++) {
			if (i > mid) {
				arr[k] = temp[j++];
			} else if (j > hi) {
				arr[k] = temp[i++];
			} else if (temp[i] < temp[j]) {
				arr[k] = temp[i++];
			} else {
				arr[k] = temp[j++];
			}
		}
	}

	public static void mergeSort(byte[] arr, int lo, int hi, int insertion) {
		m = insertion;
		byte[] temp = new byte[arr.length];
		sort(arr, temp, lo, hi);
	}
}
