import java.util.Scanner;

public class RemoveDuplicates {
    public int removeDuplicates(int[] nums) {
        int uniqueIndex = -1, value = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > value) {
                uniqueIndex += 1;
                value = nums[i];
                nums[uniqueIndex] = value;
            }
        }
        return uniqueIndex + 1;
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
        RemoveDuplicates removeDuplicates = new RemoveDuplicates();
        System.out.println(removeDuplicates.removeDuplicates(arr));
        read.close();
    }
}
