import java.util.HashMap;

public class EqualArrays {
    public boolean check(long[] arr, long[] brr) {
        HashMap<Long, Integer> map1 = new HashMap<>();
        HashMap<Long, Integer> map2 = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            map1.put(arr[i], map1.getOrDefault(arr[i], 0) + 1);
            map2.put(brr[i], map2.getOrDefault(brr[i], 0) + 1);
        }
        System.out.println(map1);
        System.out.println(map2);
        return map1.equals(map2);
    }

    public static void main(String[] args) {
        EqualArrays equalArrays = new EqualArrays();
        long[] arr = { 1, 0, 2, 4, 5 };
        long[] brr = { 0, 2, 1, 5, 4 };
        System.out.println(equalArrays.check(arr, brr));
    }
}
