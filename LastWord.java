import java.util.Scanner;

public class LastWord {
    public int lengthOfLastWord(String s) {
        s = s.trim();
        int lastSpaceIndex = s.lastIndexOf(" ");
        String lastWord = s.substring(lastSpaceIndex + 1);
        return lastWord.length();
    }

    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        System.out.println("Enter string");
        String s = read.nextLine();
        LastWord lastWord = new LastWord();
        System.out.println(lastWord.lengthOfLastWord(s));
        read.close();
    }
}
