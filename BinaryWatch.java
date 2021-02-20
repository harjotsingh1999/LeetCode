import java.util.ArrayList;
import java.util.List;

public class BinaryWatch {
    public List<String> readBinaryWatch(int num) {
        List<String> out = new ArrayList<>();
        int[] setBitCounts = new int[60];

        // counting and storing the number of set bits(1) of all numbers upto 59
        for (int i = 1; i < 60; i++) {
            setBitCounts[i] = Integer.bitCount(i);
        }
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 60; j++) {
                // if hour and min set bits add up to num
                // add that time to the list of answers
                if (setBitCounts[i] + setBitCounts[j] == num) {
                    String h = String.valueOf(i);
                    String m = String.valueOf(j);
                    String time = h + ":" + ((m.length() == 2) ? m : "0" + m);
                    out.add(time);
                }
            }
        }
        return out;
    }

    public static void main(String[] args) {
        BinaryWatch binaryWatch = new BinaryWatch();
        System.out.println(binaryWatch.readBinaryWatch(1));
    }
}
