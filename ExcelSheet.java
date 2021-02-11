public class ExcelSheet {

    // Given a positive integer, return its corresponding column title as appear in
    // an Excel sheet.

    // For example:

    // 1 -> A
    // 2 -> B
    // 3 -> C
    // ...
    // 26 -> Z
    // 27 -> AA
    // 28 -> AB
    // ...

    // 'AB'= 26 = 26^(2-0-1)*(distance from 'A' +1) + 26^(2-1-1)*(distance from
    // 'A'+1)
    // "ZY"= 6=701= 26^(2-0-1)*(25+1) + 26^(2-1-1)*(24+1)= 26^26 + 1*25;
    // and so on
    public int titleToNumber(String s) {
        int out = 0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            out = out + ((int) Math.pow(26, s.length() - i - 1) * (((int) ch - (int) 'A') + 1));
            System.out.println("ExcelSheet.titleToNumber()" + out);
        }
        return out;
    }

    public static void main(String[] args) {
        ExcelSheet excelSheet = new ExcelSheet();
        System.out.println(excelSheet.titleToNumber("ZY"));
    }
}
