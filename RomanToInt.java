import java.util.HashMap;
import java.util.Scanner;

public class RomanToInt {
    public int romanToInt(String s) {
        HashMap<Character, Integer> singles = new HashMap<>();
        singles.put('I', 1);
        singles.put('V', 5);
        singles.put('X', 10);
        singles.put('L', 50);
        singles.put('C', 100);
        singles.put('D', 500);
        singles.put('M', 1000);
        HashMap<String, Integer> doubles = new HashMap<>();
        doubles.put("IV", 4);
        doubles.put("IX", 9);
        doubles.put("XL", 40);
        doubles.put("XC", 90);
        doubles.put("CD", 400);
        doubles.put("CM", 900);

        int num = 0;
        for (int i = 0; i < s.length(); i++) {
            System.out.println("position= " + i);
            if (i < s.length() - 1) {
                String str = "" + s.charAt(i) + s.charAt(i + 1);
                if (doubles.containsKey(str)) {
                    System.out.println("found " + str + " with value " + doubles.get(str));
                    num += doubles.get(str);
                    System.out.println("number= " + num);
                    i = i + 1;
                    continue;
                } else {
                    System.out.println("found " + s.charAt(i) + " with value " + singles.get(s.charAt(i)));
                    num += singles.get(s.charAt(i));
                    System.out.println("number= " + num);
                }
            } else {
                System.out.println("found " + s.charAt(i) + " with value " + singles.get(s.charAt(i)));
                num += singles.get(s.charAt(i));
                System.out.println("number= " + num);
            }
        }
        return num;
    }

    public String intToRoman(int num) {
        String out = "";
        int thou = num / 1000, hund = (num % 1000) / 100, tens = ((num % 1000) % 100) / 10,
                ones = ((num % 1000) % 100) % 10;

        if (thou > 0) {
            while (thou > 0) {
                out += "M";
                thou -= 1;
            }
        }
        if (hund > 0) {
            if (hund == 4)
                out += "CD";
            else if (hund == 9)
                out += "CM";
            else {
                while (hund > 0) {
                    if (hund >= 5) {
                        out += "D";
                        hund -= 5;
                    } else {
                        out += "C";
                        hund -= 1;
                    }
                }
            }
        }
        if (tens > 0) {
            if (tens == 4)
                out += "XL";
            else if (tens == 9)
                out += "XC";
            else {
                while (tens > 0) {
                    if (tens >= 5) {
                        out += "L";
                        tens -= 5;
                    } else {
                        out += "X";
                        tens -= 1;
                    }
                }
            }
        }
        if (ones > 0) {
            if (ones == 4)
                out += "IV";
            else if (ones == 9)
                out += "IX";
            else {
                while (ones > 0) {
                    if (ones >= 5) {
                        out += "V";
                        ones -= 5;
                    } else {
                        out += "I";
                        ones -= 1;
                    }
                }
            }
        }
        return out;
    }

    public static void main(String[] args) {
        // System.out.println("Enter a roman number");
        // Scanner read = new Scanner(System.in);
        // String number = read.next();
        RomanToInt rToInt = new RomanToInt();
        System.out.println(rToInt.intToRoman(1994));
        // read.close();
    }
}