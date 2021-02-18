public class MissingNumber {

    // Given an array nums containing n distinct numbers in the range [0, n], return
    // the only number in the range that is missing from the array.

    // if we sum all the numbers till n
    // then keep on subtracting the numbers we saw
    // at the end we'll have the number which was not present

    public int missingNumber(int[] nums) {
        int sum = (nums.length * (nums.length + 1)) / 2;
        for (int i : nums) {
            sum -= i;
        }
        return sum;
    }

    // other ways
    // 1. sorting and finding index where index!= nums[index] time- O(nlogn) space-
    // O(1)
    // 2. HashSet approach add all numbers in nums to the set space- O(n) time- O(n)
    // then loop from 0-n to ans check if hashset does not contain that number,
    // return it
    // 3. XOR all numbers with their indices and finally XOR with nums.length
    // to get missing number

    // a^a=0 using this
    // and a^0=a

    // Index 0 1 2 3
    // Value 0 1 3 4

    // missing=
    // 4 ^ (0 ^ 0) ^ (1 ^ 1) ^ (2 ^ 3) ^ (3 ^ 4)
    // =(4 ^ 4) ^ (0 ^ 0) ^ (1 ^ 1) ^ (3 ^ 3) ^ 2
    // =0 ^ 0 ^ 0 ^ 0 ^ 2=2
    public int missingNumber2(int[] nums) {
        int xor = nums.length;
        for (int i = 0; i < nums.length; i++) {
            xor = xor ^ i ^ nums[i];
        }
        return xor;
    }

}
