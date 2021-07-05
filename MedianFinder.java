import java.util.*;

// Runtime: 44 ms, faster than 90.50% of Java online submissions for Find Median from Data Stream.
// Memory Usage: 50.5 MB, less than 50.16% of Java online submissions for Find Median from Data Stream.
public class MedianFinder {

    /** initialize your data structure here. */
    PriorityQueue<Integer> maxpq, minpq;

    public MedianFinder() {
        maxpq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        minpq = new PriorityQueue<>();
    }

    public void addNum(int num) {
        System.out.println("pqs before num=" + num);
        System.out.println(maxpq);
        System.out.println(minpq);
        if (maxpq.size() == minpq.size()) {
            if (!minpq.isEmpty() && minpq.peek() < num) {
                maxpq.offer(minpq.poll());
                minpq.offer(num);
            } else {
                maxpq.offer(num);
            }
        } else {
            if (!maxpq.isEmpty() && maxpq.peek() > num) {
                minpq.offer(maxpq.poll());
                maxpq.offer(num);
            } else {
                minpq.offer(num);
            }
        }
        System.out.println("pqs after num=" + num);
        System.out.println(maxpq);
        System.out.println(minpq);
    }

    public double findMedian() {
        System.out.println("median");
        System.out.println(maxpq);
        System.out.println(minpq);
        if ((maxpq.size() + minpq.size()) % 2 == 0)
            return (minpq.peek() + maxpq.peek()) / 2.0;
        else
            return maxpq.peek();
    }

    public static void main(String[] args) {
        MedianFinder medianFinder = new MedianFinder();
        medianFinder.addNum(5);
        System.out.println(medianFinder.findMedian());
        medianFinder.addNum(3);
        System.out.println(medianFinder.findMedian());
        medianFinder.addNum(1);
        System.out.println(medianFinder.findMedian());
        medianFinder.addNum(2);
        System.out.println(medianFinder.findMedian());
        medianFinder.addNum(4);
        System.out.println(medianFinder.findMedian());
        medianFinder.addNum(6);
        System.out.println(medianFinder.findMedian());
    }

}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder(); obj.addNum(num); double param_2 =
 * obj.findMedian();
 */