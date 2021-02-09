import java.util.HashSet;

public class SingleNumber {
    public int singleNumber(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        int total = 0;
        for (int i = 0; i < nums.length; i++) {
            total += nums[i];
            if (!set.contains(nums[i])) {
                set.add(nums[i]);
            } else {
                total = total - 2 * nums[i];
            }
        }
        return total;

        // Runtime: 6 ms, faster than 50.21% of Java online submissions for Single
        // Number.
        // Memory Usage: 39.5 MB, less than 47.44% of Java online submissions for Single
        // Number.
    }

    // Concept

    // If we take XOR of zero and some bit, it will return that bit
    // a⊕0=aa \oplus 0 = aa⊕0=a
    // If we take XOR of two same bits, it will return 0
    // a⊕a=0a \oplus a = 0a⊕a=0
    // a⊕b⊕a=(a⊕a)⊕b=0⊕b=ba \oplus b \oplus a = (a \oplus a) \oplus b = 0 \oplus b =
    // ba⊕b⊕a=(a⊕a)⊕b=0⊕b=b

    // So we can XOR all bits together to find the unique number.
    public int singleNumber2(int[] nums) {
        int num = 0;
        for (int i = 0; i < nums.length; i++) {
            num = num ^ nums[i];
        }
        return num;
    }

    public static void main(String[] args) {
        SingleNumber sNumber = new SingleNumber();
        int[] arr = { 1 };
        System.out.println(sNumber.singleNumber(arr));
    }
}
