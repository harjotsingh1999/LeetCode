import java.util.ArrayList;
import java.util.List;

public class GenerateParenthesis {
    // Given n pairs of parentheses, write a function to generate all combinations
    // of well-formed parentheses.

    // Example 1:

    // Input: n = 3
    // Output: ["((()))","(()())","(())()","()(())","()()()"]

    // Example 2:

    // Input: n = 1
    // Output: ["()"]

    public List<String> generateParenthesis(int n) {
        List<String> list = new ArrayList<>();
        if (n == 0)
            return list;
        for (int i = 1; i <= n; i++) {

        }
        return null;
    }

}
