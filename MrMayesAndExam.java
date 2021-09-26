import java.util.*;

public class MrMayesAndExam {

    /**
     * Mr. Myers and the Exam
     * 
     * A mathematics question paper has certain number of questions and each
     * question is assigned some random maximum marks. Mr. Myers wants to edit the
     * marks assigned to the questions such that
     * 
     * 1. All questions in the paper should have distinct maximum marks. 2. The
     * total marks of all the questions should be as low as possible.
     * 
     * Mr. Myers wants to achieve this by making minimal changes in the original
     * format, assigning the question at least as much marks as it originally had.
     * Find the minimum total marks that he can set the paper for.
     * 
     * Input Specification:
     * 
     * input1: The number of questions in the paper
     * 
     * input2: The array representing the original marks assigned to every question
     * 
     * Output Specification:
     * 
     * The minimum total marks Mr. Myers can set the paper for
     * 
     * Example 1:
     * 
     * 5 input2: (1,2,3,4,5)
     * 
     * Output: 15
     * 
     * Explanation:
     * 
     * As all the questions already have distinct marks, he can set the paper as it
     * is with minimum marks as 1+2+3+4+5 = 15.
     * 
     * Example 2:
     * 
     * input1: 15
     * 
     * input2: (1,4,5,4,5)
     * 
     * Output: 23
     * 
     * Explanation:
     * 
     * Here, the question 1 would have at least 1 mark, question 2 would have at
     * least 4 marks, question 3 would have at least 5 marks, so the new set of
     * marks will have to be (1,4,5,6,7), such that all the conditions are
     * satisfied.
     */
    static int editMarks(int[] marks) {
        int ans = 0;
        HashSet<Integer> distinct = new HashSet<>();
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < marks.length; i++) {
            distinct.add(marks[i]);
            max = Math.max(max, marks[i]);
        }

        Arrays.sort(marks);
        for (int i = 1; i < marks.length; i++) {
            if (marks[i] == marks[i - 1]) {
                max = Math.max(max, incrementToLowestPossible(marks[i], max, distinct));
            }
        }

        System.out.println(distinct);
        for (Integer integer : distinct) {
            ans += integer;
        }
        return ans;
    }

    private static int incrementToLowestPossible(int i, int max, HashSet<Integer> distinct) {
        int j = i + 1;
        for (j = i + 1; j <= max + 1; j++) {
            if (!distinct.contains(j)) {
                distinct.add(j);
                break;
            }
        }
        System.out.println("changed " + i + " to " + j);
        return j;
    }

    public static void main(String[] args) {
        System.out.println(editMarks(new int[] { 1, 2, 3, 4, 5 }));
        System.out.println(editMarks(new int[] { 1, 4, 5, 4, 5 }));
    }
}
