import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class IntersectionArrays {
    // Given two arrays, write a function to compute their intersection.

    // Example 1:

    // Input: nums1 = [1,2,2,1], nums2 = [2,2]
    // Output: [2]

    // Example 2:

    // Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
    // Output: [9,4]

    // Note:

    // Each element in the result must be unique.
    // The result can be in any order.

    // Runtime: 2 ms, faster than 97.42% of Java online submissions for Intersection
    // of Two Arrays.
    // Memory Usage: 38.9 MB, less than 94.28% of Java online submissions for
    // Intersection of Two Arrays.
    public int[] intersection(int[] nums1, int[] nums2) {
        int l1 = nums1.length;
        int l2 = nums2.length;
        HashSet<Integer> set1 = new HashSet<>();
        HashSet<Integer> set2 = new HashSet<>();
        if (l1 < l2) {
            for (int ele : nums1) {
                if (!set1.contains(ele))
                    set1.add(ele);
            }

            for (int ele : nums2) {
                if (set1.contains(ele) && !set2.contains(ele))
                    set2.add(ele);
            }
        } else {
            for (int ele : nums2) {
                if (!set1.contains(ele))
                    set1.add(ele);
            }
            for (int ele : nums1) {
                if (set1.contains(ele) && !set2.contains(ele))
                    set2.add(ele);
            }
        }
        int[] out = new int[set2.size()];
        int i = 0;
        for (Integer integer : set2) {
            out[i] = integer;
            i++;
        }
        return out;
    }

    // Given two arrays, write a function to compute their intersection.

    // Example 1:

    // Input: nums1 = [1,2,2,1], nums2 = [2,2]
    // Output: [2,2]

    // Example 2:

    // Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
    // Output: [4,9]

    // Note:

    // Each element in the result should appear as many times as it shows in both
    // arrays.
    // The result can be in any order.

    public int[] intersect(int[] nums1, int[] nums2) {
        HashMap<Integer, Integer> map1 = new HashMap<>();
        HashMap<Integer, Integer> map2 = new HashMap<>();
        for (int i : nums1) {
            map1.put(i, map1.getOrDefault(i, 0) + 1);
        }
        for (int i : nums2) {
            map2.put(i, map2.getOrDefault(i, 0) + 1);
        }

        System.out.println("IntersectionArrays.intersect() " + map1);
        System.out.println("IntersectionArrays.intersect() " + map2);

        ArrayList<Integer> list = new ArrayList<>();
        for (Integer i : map1.keySet()) {
            System.out.println("IntersectionArrays.intersect() ele= " + i);
            if (map2.containsKey(i)) {
                int minOccurance = Math.min(map1.get(i), map2.get(i));
                System.out.println("IntersectionArrays.intersect() " + i + " occurs " + minOccurance + " times");
                for (int j = 0; j < minOccurance; j++) {
                    list.add(i);
                }
            }
        }
        System.out.println("IntersectionArrays.intersect() list= " + list);
        int[] arr = new int[list.size()];
        int ind = 0;
        for (int i : list) {
            arr[ind] = i;
            ind += 1;
        }
        return arr;
    }

    // with single map
    public int[] intersect2(int[] nums1, int[] nums2) {
        HashMap<Integer, Integer> map1 = new HashMap<>();
        ArrayList<Integer> list = new ArrayList<>();
        for (int i : nums1) {
            map1.put(i, map1.getOrDefault(i, 0) + 1);
        }
        for (int i : nums2) {

            // decrementing the count of that number
            if (map1.containsKey(i) && map1.get(i) > 0) {
                list.add(i);
                map1.put(i, map1.get(i) - 1);
            }
        }
        System.out.println("IntersectionArrays.intersect() list= " + list);
        int[] arr = new int[list.size()];
        int ind = 0;
        for (int i : list) {
            arr[ind] = i;
            ind += 1;
        }
        return arr;
    }

    public static void main(String[] args) {
        IntersectionArrays intersectionArrays = new IntersectionArrays();
        int[] nums1 = { 9, 4, 9, 8, 4 };
        int[] nums2 = { 4, 9, 5 };
        System.out.println(Arrays.toString(intersectionArrays.intersect(nums1, nums2)));
    }
}
