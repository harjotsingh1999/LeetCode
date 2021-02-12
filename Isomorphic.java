import java.util.HashMap;

public class Isomorphic {

    // Given two strings s and t, determine if they are isomorphic.

    // Two strings s and t are isomorphic if the characters in s can be replaced to
    // get t.

    // All occurrences of a character must be replaced with another character while
    // preserving the order of characters. No two characters may map to the same
    // character, but a character may map to itself.

    // Example 1:

    // Input: s = "egg", t = "add"
    // Output: true

    // Example 2:

    // Input: s = "foo", t = "bar"
    // Output: false

    // Example 3:

    // Input: s = "paper", t = "title"
    // Output: true

    public boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length())
            return false;
        System.out.println(s + " " + t);
        HashMap<Character, Character> replaceMap = new HashMap<>();
        String newString = "";
        for (int i = 0; i < t.length(); i++) {
            char ch1 = s.charAt(i);
            char ch2 = t.charAt(i);
            if (replaceMap.containsKey(ch1)) {
                newString += replaceMap.get(ch1);
            } else {
                if (!replaceMap.containsValue(ch2)) {
                    replaceMap.put(ch1, ch2);
                    newString += ch2;
                } else
                    return false;
            }
        }
        System.out.println(replaceMap);
        System.out.println(newString);
        return newString.equals(t);
    }

    public static void main(String[] args) {
        Isomorphic isomorphic = new Isomorphic();
        System.out.println(isomorphic.isIsomorphic("paper", "title"));
    }
}
