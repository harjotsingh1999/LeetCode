public class ArrayProblems {

    // Given integer array nums, return the third maximum number in this array. If
    // the third maximum does not exist, return the maximum number.

    // could also be done with HashSet since it sorts and keeps the elements
    public int thirdMax(int[] nums) {
        long max = Long.MIN_VALUE, sMax = Long.MIN_VALUE, tMax = Long.MIN_VALUE;
        for (int i : nums) {
            if (i > max) {
                tMax = sMax;
                sMax = max;
                max = i;
            } else if (i > sMax && i != max) {
                tMax = sMax;
                sMax = i;
            } else if (i > tMax && i != sMax && i != max) {
                tMax = i;
            }
            System.out.println("ArrayProblems.thirdMax() max= " + max + " smax= " + sMax + " tmax= " + tMax);
        }
        return tMax == Long.MIN_VALUE ? (int) max : (int) tMax;
    }

    public static void main(String[] args) {
        ArrayProblems arrayProblems = new ArrayProblems();
        int[] arr = { 1, 2, -2147483648 };
        System.out.println(arrayProblems.thirdMax(arr));
    }
}
