public class Primes {
    public void countPrimes(int n) {
        boolean[] primes = new boolean[n + 1];

        for (int i = 2; i < primes.length; i++) {
            primes[i] = true;
        }

        for (int i = 2; i * i < primes.length; i++) {
            if (!primes[i])
                continue;

            // In fact, we can mark off multiples of 5 starting at 5 × 5 = 25, because 5 × 2
            // = 10 was already marked off by multiple of 2, similarly 5 × 3 = 15 was
            // already marked off by multiple of 3. Therefore, if the current number is p,
            // we can always mark off multiples of p starting at p2, then in increments of
            // p: p2 + p, p2 + 2p,....

            for (int j = i * i; j < primes.length; j += i) {
                primes[j] = false;
            }
        }
        for (int i = 2; i < primes.length; i++) {
            System.out.println(i + " is prime: " + primes[i]);
        }
    }

    public int primes2(int n) {
        boolean[] primes = new boolean[n + 1];

        for (int i = 2; i < primes.length; i++) {
            primes[i] = true;
        }

        for (int i = 2; i * i < primes.length; i++) {
            if (!primes[i])
                continue;
            for (int j = i * i; j < primes.length; j += i) {
                primes[j] = false;
            }
        }
        int c = 0;
        for (int i = 2; i < primes.length; i++) {
            if (primes[i])
                c += 1;
        }
        return c;
    }

    public static void main(String[] args) {
        Primes primes = new Primes();
        primes.countPrimes(20);
    }
}