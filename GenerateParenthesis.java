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

    // one possible solution is to generate all possible sequences of brackts and
    // figure out which ones are valid O(2^2n);

    // backtracking below: tc= O(4^n/root(n))
    public static List<String> generateParenthesis(int n) {
        List<String> list = new ArrayList<>();
        if (n == 0)
            return list;
        StringBuilder sb = new StringBuilder();
        backtrack(n, 0, 0, sb, list);
        System.out.println(list);
        return null;
    }

    private static void backtrack(int n, int openCount, int closeCount, StringBuilder sb, List<String> list) {
        if (openCount == n && closeCount == n) {
            list.add(sb.toString());
            return;
        }
        if (openCount < n) {
            sb.append("(");
            backtrack(n, openCount + 1, closeCount, sb, list);
            sb.deleteCharAt(sb.length() - 1);
        }
        if (closeCount < openCount) {
            sb.append(")");
            backtrack(n, openCount, closeCount + 1, sb, list);
            sb.deleteCharAt(sb.length() - 1);
        }

    }

    public static void main(String[] args) {
        generateParenthesis(2);
    }

}
