import java.util.*;

public class BreakTheNode {
    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        int N = read.nextInt();
        String M = read.next();

        int[] freq = new int[10];

        for (char ch : M.toCharArray()) {
            int digit = Integer.parseInt(String.valueOf(ch));
            if (digit == 2 || digit == 5) {
                freq[2] += 1;
                freq[5] += 1;
            } else if (digit == 6 || digit == 9) {
                freq[6] += 1;
                freq[9] += 1;
            } else
                freq[digit] += 1;
        }
        int[] req = new int[10];
        int n = N;
        while (n > 0) {
            req[n % 10] += 1;
            n /= 10;
        }

        System.out.println("freq=" + Arrays.toString(freq));
        System.out.println("count=" + Arrays.toString(req));

        // min multiples of frequency
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            if (req[i] > 0) {
                int count = 0;
                if (i == 2 || i == 5) {
                    count = freq[i] / req[i];
                    if (i == 2)
                        freq[5] = freq[5] - count;
                } else if (i == 6 || i == 9) {
                    count = freq[i] / req[i];
                    if (i == 6)
                        freq[9] = freq[9] - count;
                } else {
                    count = freq[i] / req[i];
                }
                min = Math.min(count, min);
            }
        }
        System.out.println(Arrays.toString(freq));
        System.out.println("max= " + min);
        read.close();
    }
}
