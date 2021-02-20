import java.util.Arrays;

public class RansomNote {

    // Given an arbitrary ransom note string and another string containing letters
    // from all the magazines, write a function that will return true if the ransom
    // note can be constructed from the magazines ; otherwise, it will return false.

    // Each letter in the magazine string can only be used once in your ransom note.

    // Example 1:

    // Input: ransomNote = "a", magazine = "b"
    // Output: false

    // Example 2:

    // Input: ransomNote = "aa", magazine = "ab"
    // Output: false

    // Example 3:

    // Input: ransomNote = "aa", magazine = "aab"
    // Output: true

    public boolean canConstruct(String ransomNote, String magazine) {
        int[] count = new int[26];
        System.out.println(Arrays.toString(count));
        for (char ch : magazine.toCharArray()) {
            count[(int) ch - (int) 'a'] += 1;
        }
        System.out.println(Arrays.toString(count));
        for (char ch : ransomNote.toCharArray()) {
            count[(int) ch - (int) 'a'] -= 1;
        }
        System.out.println(Arrays.toString(count));
        for (int i : count) {
            if (i < 0)
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        RansomNote ransomNote = new RansomNote();
        System.out.println(ransomNote.canConstruct("aa", "aba"));
    }
}
