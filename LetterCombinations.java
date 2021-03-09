import java.util.ArrayList;
import java.util.List;

public class LetterCombinations {

    // Given a string containing digits from 2-9 inclusive, return all possible
    // letter combinations that the number could represent. Return the answer in any
    // order.

    // A mapping of digit to letters (just like on the telephone buttons) is given
    // below. Note that 1 does not map to any letters.

    // Input: digits = "23"
    // Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]

    // Example 2:

    // Input: digits = ""
    // Output: []

    // Example 3:

    // Input: digits = "2"
    // Output: ["a","b","c"]

    // Constraints:

    // 0 <= digits.length <= 4
    // digits[i] is a digit in the range ['2', '9'].

    public List<String> letterCombinations(String digits) {
        // base case
        List<String> out = new ArrayList<>();
        if (digits.isEmpty())
            return out;
        String[] strings = { "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };

        // for each string in the out list, we need to add all the characters of the
        // current number
        // to the end

        // initially we simply add an empty string
        // so that for the first number, all the characters will be added to the end of
        // the empty string
        out.add("");

        // for each character(number) in the "digits" variable
        for (char ch : digits.toCharArray()) {

            // create a new list to store the results of the current iteration
            List<String> temp = new ArrayList<>();

            // for every string present in the list already
            for (String str : out) {

                // we get the characters associated with the current digit
                String letters = strings[Integer.parseInt(String.valueOf(ch))];

                // and add each letter to the end of all the strings currently present in the
                // list
                for (char letter : letters.toCharArray()) {
                    temp.add(str + String.valueOf(letter));
                }
            }

            // then just have "out" point to the current temp list and repeat the process
            // for each digit
            out = temp;
        }
        return out;
    }

    public static void main(String[] args) {
        LetterCombinations letterCombinations = new LetterCombinations();
        System.out.println(letterCombinations.letterCombinations("27"));
    }
}
