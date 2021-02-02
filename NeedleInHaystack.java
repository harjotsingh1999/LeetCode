import java.util.Scanner;

public class NeedleInHaystack {
    public int strStr(String haystack, String needle) {
        if (needle.isEmpty())
            return 0;
        return haystack.indexOf(needle);
    }

    public static void main(String[] args) {
        NeedleInHaystack nInHaystack = new NeedleInHaystack();
        Scanner read = new Scanner(System.in);
        System.out.println("Enter haystack string");
        String hay = read.next();
        System.out.println("Enter needle String");
        String n = read.next();
        System.out.println(nInHaystack.strStr(hay, n));
        read.close();
    }
}
