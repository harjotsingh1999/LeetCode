import java.util.Arrays;

public class MoveZeroes {

    // Given an array nums, write a function to move all 0's to the end of it while
    // maintaining the relative order of the non-zero elements.

    // Example:

    // Input: [0,1,0,3,12]
    // Output: [1,3,12,0,0]
    public int[] moveZeroesExtraSpace(int[] nums) {
        int[] arr = new int[nums.length];
        Arrays.fill(arr, 0);
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                arr[index] = nums[i];
                index += 1;
            }
        }
        return arr;
    }

    public void moveZeroes(int[] nums) {
        int currentIndex = 0, searchIndex = 0;
        while (currentIndex != nums.length - 1) {
            System.out.println("MoveZeroes.moveZeroes() ci= " + currentIndex + " and si= " + searchIndex);
            if (nums[currentIndex] == 0) {
                while (nums[searchIndex] == 0 && searchIndex < nums.length - 1) {
                    System.out.println("MoveZeroes.moveZeroes() while ci= " + currentIndex + " and si= " + searchIndex);
                    searchIndex += 1;
                }
                // swap
                nums[currentIndex] = nums[searchIndex];
                nums[searchIndex] = 0;
                System.out.println("MoveZeroes.moveZeroes() ci= " + currentIndex + " and si= " + searchIndex
                        + " and array= " + Arrays.toString(nums));
            }
            currentIndex += 1;
            searchIndex = currentIndex;
        }
    }
}
