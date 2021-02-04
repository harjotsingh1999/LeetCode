import java.util.Scanner;

public class PlusOne {
    public int[] plusOne(int[] digits) {
        for (int i = digits.length - 1; i >= 0; i--) {
            if (digits[i] != 9) {
                // if you encounter a digit less than 9, increment it and return the array
                digits[i] += 1;
                return digits;
            } else {
                // otherwise replace it with zero
                digits[i] = 0;
            }
        }

        // if there are all nines.. create an array with one extra element, with 1 at
        // start
        int[] arr = new int[digits.length + 1];
        arr[0] = 1;
        return arr;
    }

    public void printArray(int[] arr) {
        for (int i : arr) {
            System.out.print(i + ", ");
        }
    }

    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        System.out.println("Enter length of array");
        int n1 = read.nextInt();
        int[] a = new int[n1];
        for (int i = 0; i < n1; i++) {
            System.out.println("enter element for array");
            a[i] = read.nextInt();
        }
        PlusOne plusOne = new PlusOne();
        plusOne.printArray(plusOne.plusOne(a));
        read.close();
    }
}
