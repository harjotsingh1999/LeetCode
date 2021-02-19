import java.util.HashMap;
import java.util.StringTokenizer;

public class WordPattern {

    // Given a pattern and a string s, find if s follows the same pattern.

    // Here follow means a full match, such that there is a bijection between a
    // letter in pattern and a non-empty word in s.

    // Example 1:

    // Input: pattern = "abba", s = "dog cat cat dog"
    // Output: true

    // Example 2:

    // Input: pattern = "abba", s = "dog cat cat fish"
    // Output: false

    // Example 3:

    // Input: pattern = "aaaa", s = "dog cat cat dog"
    // Output: false

    // Example 4:

    // Input: pattern = "abba", s = "dog dog dog dog"
    // Output: false

    public boolean wordPattern(String pattern, String s) {
        HashMap<Character, String> map = new HashMap<>();
        String[] words = s.split(" ");
        if (words.length != pattern.length())
            return false;
        int i = 0;
        while (i < words.length) {
            char ch = pattern.charAt(i);
            String word = words[i];
            System.out.println("WordPattern.wordPattern() ch= " + ch + " and word= " + word + " and map= " + map);
            i += 1;
            if (map.containsKey(ch)) {

                // if this ch is present
                // but its value is not equal to the current word
                // we return false
                System.out.println("WordPattern.wordPattern() map contains key " + ch + " with value " + map.get(ch)
                        + " equals word" + word + " " + word.equals(map.get(ch)));
                if (!word.equalsIgnoreCase(map.get(ch)))
                    return false;
            } else {
                // if ch not in map and word does not exist in map, add it
                if (!map.containsValue(word)) {
                    map.put(ch, word);
                } else {
                    // this is done so that two characters don't map to the same word
                    // cuz for wordPattern("abba", "cat cat cat cat") we return false
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        WordPattern wordPattern = new WordPattern();
        System.out.println(wordPattern.wordPattern("abba", "cat dog dog cat"));
    }
}
