import java.util.HashMap;

public class Anagrams {

    // Given two strings s and t , write a function to determine if t is an anagram
    // of s.

    // Example 1:

    // Input: s = "anagram", t = "nagaram"
    // Output: true

    // Example 2:

    // Input: s = "rat", t = "car"
    // Output: false

    // Note:
    // You may assume the string contains only lowercase alphabets.
    public boolean isAnagram(String s, String t) {
        HashMap<Character, Integer> map1 = new HashMap<>();
        HashMap<Character, Integer> map2 = new HashMap<>();
        for (char ch : s.toCharArray()) {
            map1.put(ch, map1.getOrDefault(ch, 0) + 1);
        }
        for (char ch : t.toCharArray()) {
            map2.put(ch, map2.getOrDefault(ch, 0) + 1);
        }
        return map1.equals(map2);
    }

    public static void main(String[] args) {
        Anagrams anagrams = new Anagrams();
        System.out.println(anagrams.isAnagram("rat", "car"));
    }
}
