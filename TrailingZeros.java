public class TrailingZeros {
    public int trailingZeroes(int n) {

        int numberOfFives = 0;
        for (int i = 1; i < Integer.MAX_VALUE; i++) {
            int num = (int) Math.pow(5, i);
            if (n / num < 1)
                break;
            numberOfFives += (n / num);
        }
        return numberOfFives;
    }

    public int trailingZeroes2(int n) {

        int numberOfFives = 0;
        int exp = 5;
        while ((n / exp) > 0) {
            numberOfFives = numberOfFives + (n / exp);
            exp = exp * 5;
        }
        return numberOfFives;
    }

    public static void main(String[] args) {
        TrailingZeros trailingZeros = new TrailingZeros();
        System.out.println(trailingZeros.trailingZeroes2(101));
    }
}
