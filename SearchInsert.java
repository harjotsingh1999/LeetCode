import java.util.Scanner;

public class SearchInsert {
    public int searchInsert(int[] nums, int target) {
        return binarySearch(nums, target, 0, nums.length - 1);
    }

    public int binarySearch(int[] nums, int target, int low, int high) {
        if (low > high)
            return low;
        int mid = (low + high) / 2;
        if (nums[mid] == target)
            return mid;
        else if (target < nums[mid])
            return binarySearch(nums, target, low, mid - 1);
        else if (target > nums[mid])
            return binarySearch(nums, target, mid + 1, high);
        else
            return low;
    }

    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        System.out.println("Enter length of array");
        int l = read.nextInt();
        int[] arr = new int[l];
        for (int i = 0; i < l; i++) {
            System.out.println("Enter element");
            arr[i] = read.nextInt();
        }
        System.out.println("Enter target");
        int target = read.nextInt();
        SearchInsert sInsert = new SearchInsert();
        System.out.println(sInsert.searchInsert(arr, target));
        read.close();
    }
}
