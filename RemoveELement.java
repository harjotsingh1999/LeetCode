import java.util.Arrays;
import java.util.Scanner;

public class RemoveELement {
    public int removeElement2(int[] nums, int val) {

        // Constraints:
        // 0 <= nums.length <= 100
        // 0 <= nums[i] <= 50
        // 0 <= val <= 100

        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == val) {
                count += 1;
                nums[i] = 200;
            }
        }
        Arrays.sort(nums);
        return nums.length - count;
    }

    public int removeElement(int[] nums, int val) {

        int i = 0;
        for (int ele : nums) {
            if (ele != val) {
                nums[i] = ele;
                i += 1;
            }
        }
        return i;
    }

    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        System.out.println("Enter length");
        int l = read.nextInt();
        int[] arr = new int[l];
        for (int i = 0; i < l; i++) {
            System.out.println("Enter element");
            arr[i] = read.nextInt();
        }
        RemoveELement removeELement = new RemoveELement();
        System.out.println(removeELement.removeElement(arr, 2));
        read.close();
    }
}
