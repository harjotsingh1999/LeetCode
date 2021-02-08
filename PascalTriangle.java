import java.util.ArrayList;
import java.util.List;

public class PascalTriangle {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> out = new ArrayList<List<Integer>>();
        List<Integer> row1 = new ArrayList<>();
        row1.add(1);
        List<Integer> row2 = new ArrayList<>();
        row2.add(1);
        row2.add(1);
        if (numRows == 0)
            return out;
        if (numRows == 1) {
            out.add(row1);
            return out;
        }
        if (numRows == 2) {
            out.add(row1);
            out.add(row2);
            return out;
        }
        out.add(row1);
        out.add(row2);
        for (int i = 2; i < numRows; i++) {
            List<Integer> currRow = new ArrayList<>();

            // add the first element of the previous row to the start of this row
            currRow.add(out.get(i - 1).get(0));
            for (int j = 1; j <= i - 1; j++) {
                currRow.add(out.get(i - 1).get(j) + out.get(i - 1).get(j - 1));
            }
            // add the last element of the previous row to the last of this row
            currRow.add(out.get(i - 1).get(out.get(i - 1).size() - 1));
            out.add(currRow);
        }
        return out;
    }

    public static void main(String[] args) {
        PascalTriangle pTriangle = new PascalTriangle();
        System.out.println(pTriangle.generate(0));
    }
}
