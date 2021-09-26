import java.util.Arrays;

public class MergeSort {

    public void mergeSort(int[] arr, int l, int r) {
        if (l < r) {

            // divide
            int mid = l + (r - l) / 2;

            // recur for each part
            mergeSort(arr, l, mid);
            mergeSort(arr, mid + 1, r);

            // then merge the two parts
            mergeSortedArray(arr, l, mid, r);
        }
    }

    private void mergeSortedArray(int[] arr, int l, int mid, int r) {
        int size1 = mid - l + 1;
        int size2 = r - mid;

        // create 2 new arrays to store elements
        int[] a1 = new int[size1];
        int[] a2 = new int[size2];

        // copy over the two arrays from respective ranges
        int k = l;
        for (int i = 0; i < size1; i++) {
            a1[i] = arr[k++];
        }
        for (int i = 0; i < size2; i++) {
            a2[i] = arr[k++];
        }

        int i = 0, j = 0;
        k = l;

        // this is the merge process of two sorted arrays
        while (i < size1 && j < size2) {
            if (a1[i] < a2[j]) {
                arr[k++] = a1[i++];
            } else {
                arr[k++] = a2[j++];
            }
        }

        while (i < size1) {
            arr[k++] = a1[i++];
        }
        while (j < size2) {
            arr[k++] = a2[j++];
        }
    }

    public static void main(String[] args) {
        MergeSort mSort = new MergeSort();
        int[] arr = { 5, 62, 45, 220, -1, 2 };
        mSort.mergeSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }
}
