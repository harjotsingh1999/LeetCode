import java.util.HashSet;

public class MarsStone {
    /**
     * Mars Stone
     * 
     * Rob has gone to Mars to collect some stones. The bag he is carrying can hold
     * a maximum weight of M. There are M stones weighing from 1 to M in Mars. There
     * are N stones on Mars that are similar to the ones on Earth. Find the number
     * of stones he can bring from Mars such that none of them are similar to any
     * stone on Earth.
     * 
     * Input Specification:
     * 
     * input1: M, denoting the size of the bag and the number of different stone
     * weights present on Mars.
     * 
     * input2: N, denoting the number of common stones on Earth and Mars
     * 
     * Input3: An N element array containing the list of the weights of the common
     * stones.
     * 
     * Output Specification:
     * 
     * Your function should return the maximum unique stones that can be collected
     * from Mars
     * 
     * Example 1: input1: 10 input2: 3 input3: (1, 3, 5)
     * 
     * Output: 2
     * 
     * Explanation: Rob collects one of the following stone weight sets: (2, 4),
     * (2,6) or (2,8).
     * 
     * Example 2:
     * 
     * input1: 14 input2: 4 input3: (4, 6, 8, 9)
     * 
     * Output: 4
     * 
     * Explanation: Rob collects one of the following stone weight sets: (1, 2, 3,
     * 7) or (1,2,3,5).
     */

    static int maxStones(int m, int n, int[] commonStones) {
        HashSet<Integer> common = new HashSet<>();
        int ans = 0;
        for (int i : commonStones) {
            common.add(i);
        }

        int cap = m;
        for (int i = 1; i <= m; i++) {
            if (!common.contains(i) && i < cap) {
                ans += 1;
                cap -= i;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(maxStones(10, 3, new int[] { 1, 3, 5 }));
        System.out.println(maxStones(14, 4, new int[] { 4, 6, 8, 9 }));
    }
}
