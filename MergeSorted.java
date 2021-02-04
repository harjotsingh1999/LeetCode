import java.util.Scanner;

public class MergeSorted {
    public int[] merge(int[] a, int[] b) {
        int i = 0, j = 0, k = 0;
        int[] c = new int[a.length + b.length];
        while (i < a.length && j < b.length) {
            if (a[i] < b[j]) {
                c[k] = a[i];
                i += 1;
                k += 1;
            } else {
                c[k] = b[j];
                j += 1;
                k += 1;
            }
        }
        if (i < a.length) {
            System.out.println("elements remain in array1");
            for (int l = i; l < a.length; l++) {
                c[k] = a[l];
                k += 1;
            }
            return c;
        } else if (j < b.length) {
            System.out.println("elements remain in array2");
            for (int l = j; l < b.length; l++) {
                c[k] = b[l];
                k += 1;
            }
            return c;
        } else
            return c;
    }

    void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + ", ");
        }
        System.out.println();
    }

    // Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as
    // one sorted array.

    // The number of elements initialized in nums1 and nums2 are m and n
    // respectively.
    // You may assume that nums1 has a size equal to m + n such that it has enough
    // space to hold additional elements from nums2.

    // Input: nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
    // Output: [1,2,2,3,5,6]
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        // merging from the end..

        int i = m - 1, j = n - 1, k = m + n - 1;
        while (j > -1) {
            if (nums1[i] >= nums2[j]) {
                nums1[k] = nums1[i];
                i -= 1;
                k -= 1;
            } else {
                nums1[k] = nums1[j];
                j -= 1;
                k -= 1;
            }
        }
    }

    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        System.out.println("Enter length of first array");
        int n1 = read.nextInt();
        int[] a = new int[n1];
        for (int i = 0; i < n1; i++) {
            System.out.println("enter element for array 1");
            a[i] = read.nextInt();
        }

        System.out.println("Enter length of second array");
        int n2 = read.nextInt();
        int[] b = new int[n2];
        for (int i = 0; i < n2; i++) {
            System.out.println("enter element for array 2");
            b[i] = read.nextInt();
        }

        MergeSorted mSorted = new MergeSorted();
        mSorted.printArray(a);
        mSorted.printArray(b);
        mSorted.printArray(mSorted.merge(a, b));
        read.close();
    }
}
