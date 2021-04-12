import java.util.Scanner;
import java.util.Stack;

public class ValidBrackets {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (char ch : s.toCharArray()) {
            if (stack.isEmpty() && isClosingBracket(ch))
                return false;
            else if (isOpeningBracket(ch))
                stack.add(ch);
            else if (isClosingBracket(ch) && isOfSameType(stack.peek(), ch))
                stack.pop();
            else
                return false;
        }
        if (stack.isEmpty())
            return true;
        else
            return false;
    }

    public boolean isOpeningBracket(char ch) {
        return ch == '(' || ch == '{' || ch == '[';
    }

    public boolean isClosingBracket(char ch) {
        return ch == ')' || ch == '}' || ch == ']';
    }

    public boolean isOfSameType(char ch1, char ch2) {
        return (ch1 == '(' && ch2 == ')') || (ch1 == '[' && ch2 == ']') || (ch1 == '{' && ch2 == '}');
    }

    public String removeOuterParentheses(String S) {
        Stack<Character> stack = new Stack<>();
        String s = "";
        String out = "";
        for (char ch : S.toCharArray()) {
            if (ch == '(') {
                s += "(";
                stack.push('(');
                System.out.println("Opening bracket s=" + s + " out= " + out);
            } else {
                s += ")";
                stack.pop();
                System.out.println("Closing bracket s=" + s + " out= " + out);
            }
            if (stack.isEmpty()) {
                out += s.substring(1, s.length() - 1);
                s = "";
                System.out.println("CEmpty stack s=" + s + " out= " + out);
            }
        }
        return out;
    }

    public static void main(String[] args) {
        ValidBrackets vBrackets = new ValidBrackets();
        System.out.println("Enter a parenthesis string");
        Scanner read = new Scanner(System.in);
        String str = read.next();
        System.out.println("is expression " + str + " valid: " + vBrackets.removeOuterParentheses(str));
        read.close();
    }
}
