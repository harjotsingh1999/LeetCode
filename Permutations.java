import java.math.BigInteger;
import java.util.*;

public class Permutations {

    // not possible for descending order
    // in that case, return it sorted in ascending order
    static void nextPermutation(int[] arr) {
        // from the end, find the first index i such that arr[i]<arr[i+1]
        int n = arr.length;

        int i = n - 2;
        while (i >= 0 && arr[i] >= arr[i + 1]) {
            i -= 1;
        }

        if (i < 0) {
            // array is sorted in descending order
            // return sorted in ascending
            // or reverse the array
            System.out.println("array sorted in desc");
            reverseArray(arr, 0, n - 1);
            return;
        }
        System.out.println("rightmose index not in order= " + i + " ele= " + arr[i]);

        // otherwise, starting from 'i' find the index of the closest greater element
        // i.e, if arr[i]==1, find index of 2, if not 3, if not 4, and so on towards end

        int closest = arr[i + 1];
        int closestIndex = i + 1;
        for (int j = i + 1; j < n; j++) {
            if (arr[j] > arr[i] && arr[j] <= closest) {
                closest = arr[j];
                closestIndex = j;
            }
        }

        System.out.println("closest greater index " + closestIndex + " that contains " + closest);

        // swap indices i and j
        swap(arr, i, closestIndex);
        System.out.println("array after swapping= " + Arrays.toString(arr));

        // reverse order from i+1 to end
        reverseArray(arr, i + 1, n - 1);
        System.out.println("next permutaion= " + Arrays.toString(arr));
    }

    static void previousPermutation(int[] arr) {
        int n = arr.length;
        System.out.println("original = " + Arrays.toString(arr));
        int i = n - 2;
        while (i >= 0 && arr[i] <= arr[i + 1])
            i -= 1;
        if (i >= 0) {
            System.out.println("endmost invalid index= " + i + " containing " + arr[i]);
            int closestSmaller = arr[i + 1], closestSmallerIndex = i + 1;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[i] && arr[j] >= closestSmaller) {
                    closestSmallerIndex = j;
                    closestSmaller = arr[j];
                }
            }
            swap(arr, i, closestSmallerIndex);
            System.out.println("closest Smaller = " + closestSmaller + " at " + closestSmallerIndex);
        }
        reverseArray(arr, i + 1, n - 1);
        System.out.println("finally= " + Arrays.toString(arr));
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private static void reverseArray(int[] arr, int l, int h) {
        while (l <= h) {
            swap(arr, l, h);
            l += 1;
            h -= 1;
        }
    }

    // leetcode solution
    public void nextPermutationLeetcode(int[] nums) {
        int i = nums.length - 2;
        while (i >= 0 && nums[i + 1] <= nums[i]) {
            i--;
        }
        if (i >= 0) {
            int j = nums.length - 1;
            while (nums[j] <= nums[i]) {
                j--;
            }
            swapLeetcode(nums, i, j);
        }
        reverse(nums, i + 1);
    }

    private void reverse(int[] nums, int start) {
        int i = start, j = nums.length - 1;
        while (i < j) {
            swapLeetcode(nums, i, j);
            i++;
            j--;
        }
    }

    private void swapLeetcode(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void nextPermutationString(String str) {
        char[] s = str.toCharArray();
        int i = s.length - 2;
        while (i >= 0 && s[i + 1] <= s[i]) {
            i--;
        }
        if (i >= 0) {
            int j = s.length - 1;
            while (s[j] <= s[i]) {
                j--;
            }
            swapString(s, i, j);
        }
        reverseString(s, i + 1);
        System.out.println(s);
    }

    private static void reverseString(char[] str, int start) {
        int i = start, j = str.length - 1;
        while (i < j) {
            swapString(str, i, j);
            i++;
            j--;
        }
    }

    private static void swapString(char[] str, int i, int j) {
        char temp = str[i];
        str[i] = str[j];
        str[j] = temp;
    }

    public static void printAllPermutations(int[] arr) {
        ArrayList<Integer> curList = new ArrayList<Integer>();
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        boolean[] isTaken = new boolean[arr.length];
        premuteRecur(arr, isTaken, curList, ans);
        System.out.println(ans);
    }

    private static void premuteRecur(int[] arr, boolean[] isTaken, ArrayList<Integer> curList,
            ArrayList<ArrayList<Integer>> ans) {

        // if current list is same as lngth of array
        // means all element have been added
        // and this is one possible permutation
        if (curList.size() == arr.length) {
            ans.add(new ArrayList<>(curList));
            return;
        }

        // for all the elements in array
        for (int i = 0; i < arr.length; i++) {
            // if they have not been peviously added
            if (!isTaken[i]) {

                // add and mark as taken
                // and recur for the rest
                curList.add(arr[i]);
                isTaken[i] = true;
                premuteRecur(arr, isTaken, curList, ans);

                // then remove and mark as untaken
                curList.remove(curList.size() - 1);
                isTaken[i] = false;
            }
        }
    }

    public static void allStringPermutations(String str) {
        ArrayList<String> ans = new ArrayList<>();
        StringBuilder curr = new StringBuilder();
        boolean[] added = new boolean[str.length()];
        permuteString(str, added, curr, ans);
        Collections.sort(ans);
        System.out.println(ans);
    }

    private static void permuteString(String str, boolean[] added, StringBuilder curr, ArrayList<String> ans) {
        if (curr.length() == str.length()) {
            ans.add(curr.toString());
            return;
        }

        for (int i = 0; i < str.length(); i++) {
            if (!added[i]) {
                curr.append(str.charAt(i));
                added[i] = true;
                permuteString(str, added, curr, ans);
                curr.deleteCharAt(curr.length() - 1);
                added[i] = false;
            }
        }
    }

    // Given n and k, return the kth permutation sequence.
    public static void nthPermutation(int n, int k) {
        int[] factorial = new int[10];
        int pro = 1;
        for (int i = 1; i < 10; i++) {
            pro *= i;
            factorial[i] = pro;
        }
        ArrayList<Integer> digits = new ArrayList<>(n);
        for (int i = 1; i <= n; i++) {
            digits.add(i);
        }
        StringBuilder sb = new StringBuilder();
        findPermutation(digits, k, sb, factorial);
        System.out.println(sb.toString());
    }

    private static void findPermutation(ArrayList<Integer> digits, int k, StringBuilder sb, int[] factorial) {
        System.out.println("k= " + k + " digits= " + digits + " size= " + digits.size() + " fact= "
                + factorial[digits.size() - 1]);
        if (k % factorial[digits.size() - 1] != 0) {
            int index = k / factorial[digits.size() - 1];
            sb.append(digits.get(index));
            digits.remove(index);
            findPermutation(digits, k % factorial[digits.size()], sb, factorial);
        } else {
            int index = k / factorial[digits.size() - 1] - 1;
            sb.append(digits.get(index));
            digits.remove(index);
            for (int i = digits.size() - 1; i >= 0; i--) {
                sb.append(digits.get(i));
            }
        }
    }

    public static int findLexicographicRank(String str) {
        int rank = 1;

        boolean[] present = new boolean[26];
        for (char ch : str.toCharArray()) {
            present[ch - 'a'] = true;
        }

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            int count = numberOfCharsThatOccurBefore(ch, present);
            rank = rank + count * factorial(str.length() - i - 1);
            present[ch - 'a'] = false;
            System.out.println("rank of " + str.substring(0, i + 1) + " is " + rank);
        }
        return rank;
    }

    private static int factorial(int i) {
        int fact = 1;
        for (int j = 1; j <= i; j++) {
            fact *= j;
        }
        return fact;
    }

    private static int numberOfCharsThatOccurBefore(char ch, boolean[] present) {
        int count = 0;
        for (int i = 0; i < ch - 'a'; i++) {
            if (present[i])
                count += 1;
        }
        return count;
    }

    public static void main(String[] args) {
        // Permutations.previousPermutation(new int[] { 2, 3, 1, 5, 4 });
        // Permutations.previousPermutation(new int[] { 1, 3, 2 });
        // Permutations.previousPermutation(new int[] { 1, 3, 2, 1 });
        // Permutations.nextPermutation(new int[] { 1, 1 });
        // Permutations.previousPermutation(new int[] { 1, 5, 1 });
        // Permutations.previousPermutation(new int[] { 2, 3, 1, 3, 3 });
        // Permutations.previousPermutation(new int[] { 3, 4, 4, 5, 5, 7, 9, 1, 4 });
        // Permutations.nextPermutationString("ba");

        // Permutations.printAllPermutations(new int[] { 1, 2, 3, 4 });
        Permutations.allStringPermutations("star");
        System.out.println(Permutations.findLexicographicRank("star"));
        // Permutations.nthPermutation(3, 2);
    }
}
