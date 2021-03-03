import java.util.HashMap;

public class Fibonacci {
    public int fib(int n) {
        HashMap<Integer, Integer> map = new HashMap<>();
        return fibMemo(n, map);
    }

    public int fibMemo(int n, HashMap<Integer, Integer> map) {
        if (map.containsKey(n))
            return map.get(n);
        if (n == 0)
            return 0;
        if (n == 1)
            return 1;
        map.put(n, fibMemo(n - 1, map) + fibMemo(n - 2, map));
        return map.get(n);
    }

    public int fibo(int n) {
        if (n == 0)
            return 0;
        if (n == 1)
            return 1;
        int a = 0, b = 1, c = 0;
        for (int i = 2; i <= n; i++) {
            c = a + b;
            a = b;
            b = c;
        }
        return c;
    }

    public static void main(String[] args) {
        Fibonacci fibonacci = new Fibonacci();
        System.out.println(fibonacci.fib(50));
    }
}
