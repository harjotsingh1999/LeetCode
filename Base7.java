public class Base7 {
    // Given an integer, return its base 7 string representation.

    // Example 1:

    // Input: 100
    // Output: "202"

    // Example 2:

    // Input: -7
    // Output: "-10"

    // Note: The input will be in range of [-1e7, 1e7].

    public String convertToBase7(int num) {
        if (num == 0)
            return "0";
        boolean negative = false;
        if (num < 0)
            negative = true;
        int n = (int) Math.abs(num);
        StringBuilder sb = new StringBuilder();
        while (n != 0) {
            int d = n % 7;
            n /= 7;
            sb.append(d);
            System.out.println(sb.toString());
        }
        if (negative)
            return sb.append("-").reverse().toString();
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        Base7 base7 = new Base7();
        System.out.println(base7.convertToBase7(-100));
    }
}
