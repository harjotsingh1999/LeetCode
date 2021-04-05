import java.util.Comparator;
import java.util.PriorityQueue;

public class Solution {
    public static void main(String[] args) {
        double[] spend1 = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        double[] spend2 = { 0, 2.5, 3, 4, 5, 6, 7, 8, 9, 10, 11 };
        double[] spend3 = { 0, 20, 25, 30, 35, 40, 45, 50, 55, 60, 65 };
        double[] spend4 = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        double[] spend5 = { 0, 3, 6, 9, 12, 15, 18, 21, 24, 27, 30 };
        double[] rev1 = { 0, 5, 10, 15, 20, 22.5, 30, 22.5, 40, 40, 40 };
        double[] rev2 = { 0, 10, 15, 20, 25, 35, 40, 40, 40, 40, 40 };
        double[] rev3 = { 0, 0, 10, 80, 150, 200, 270, 300, 320, 320, 320 };
        double[] rev4 = { 0, 2.5, 7.5, 10, 15, 20, 25, 27.5, 30, 32.5, 35 };
        double[] rev5 = { 0, 2.5, 5, 10, 20, 25, 27.5, 30, 30, 30, 30 };

        PriorityQueue<Pair2> pq = new PriorityQueue<>();

        // 1-tv, 2- print, 3- website, 4-social, 5- event

        // for no constraint on number of channels
        // for (int i = 0; i < rev1.length; i++) {
        // for (int j = 0; j < rev1.length; j++) {
        // for (int j2 = 0; j2 < rev1.length; j2++) {
        // for (int k = 0; k < rev1.length; k++) {
        // for (int k2 = 0; k2 < rev1.length; k2++) {
        // if (spend1[i] + spend2[j] + spend3[j2] + spend4[k] + spend5[k2] <= 50) {
        // String str = String.valueOf(i) + "-" + String.valueOf(spend1[i]) + "-"
        // + String.valueOf(rev1[i]) + " " + String.valueOf(j) + "-"
        // + String.valueOf(spend2[j]) + "-" + String.valueOf(rev2[j]) + " "
        // + String.valueOf(j2) + "-" + String.valueOf(spend3[j2]) + "-"
        // + String.valueOf(rev3[j2]) + " " + String.valueOf(k) + "-"
        // + String.valueOf(spend4[k]) + "-" + String.valueOf(rev4[k]) + " "
        // + String.valueOf(k2) + "-" + String.valueOf(spend5[k2]) + "-"
        // + String.valueOf(rev5[k2]);
        // double spend = spend1[i] + spend2[j] + spend3[j2] + spend4[k] + spend5[k2];
        // double rev = rev1[i] + rev2[j] + rev3[j2] + rev4[k] + rev5[k2];
        // pq.add(new Pair2(rev, spend, str));
        // }
        // }
        // }
        // }
        // }
        // }

        // for 3 channels
        // 1-tv, 2- print, 3- website, 4-social, 5- event
        // channel 1,2,3
        int i = 0, j = 0, j2 = 0, k = 0, k2 = 0;
        for (i = 1; i < rev1.length; i++) {
            for (j = 1; j < rev1.length; j++) {
                for (j2 = 1; j2 < rev1.length; j2++) {
                    if (spend1[i] + spend2[j] + spend3[j2] + spend4[k] + spend5[k2] <= 50) {
                        String str = String.valueOf(i) + "-" + String.valueOf(spend1[i]) + "-" + String.valueOf(rev1[i])
                                + " " + String.valueOf(j) + "-" + String.valueOf(spend2[j]) + "-"
                                + String.valueOf(rev2[j]) + " " + String.valueOf(j2) + "-" + String.valueOf(spend3[j2])
                                + "-" + String.valueOf(rev3[j2]) + " " + String.valueOf(k) + "-"
                                + String.valueOf(spend4[k]) + "-" + String.valueOf(rev4[k]) + " " + String.valueOf(k2)
                                + "-" + String.valueOf(spend5[k2]) + "-" + String.valueOf(rev5[k2]);
                        double spend = spend1[i] + spend2[j] + spend3[j2] + spend4[k] + spend5[k2];
                        double rev = rev1[i] + rev2[j] + rev3[j2] + rev4[k] + rev5[k2];
                        pq.add(new Pair2(rev, spend, str));
                    }
                }
            }
        }
        i = 0;
        j = 0;
        j2 = 0;
        k = 0;
        k2 = 0;
        // channel 1,2,4
        for (i = 1; i < rev1.length; i++) {
            for (j = 1; j < rev1.length; j++) {
                for (k = 1; k < rev1.length; k++) {
                    if (spend1[i] + spend2[j] + spend3[j2] + spend4[k] + spend5[k2] <= 50) {
                        String str = String.valueOf(i) + "-" + String.valueOf(spend1[i]) + "-" + String.valueOf(rev1[i])
                                + " " + String.valueOf(j) + "-" + String.valueOf(spend2[j]) + "-"
                                + String.valueOf(rev2[j]) + " " + String.valueOf(j2) + "-" + String.valueOf(spend3[j2])
                                + "-" + String.valueOf(rev3[j2]) + " " + String.valueOf(k) + "-"
                                + String.valueOf(spend4[k]) + "-" + String.valueOf(rev4[k]) + " " + String.valueOf(k2)
                                + "-" + String.valueOf(spend5[k2]) + "-" + String.valueOf(rev5[k2]);
                        double spend = spend1[i] + spend2[j] + spend3[j2] + spend4[k] + spend5[k2];
                        double rev = rev1[i] + rev2[j] + rev3[j2] + rev4[k] + rev5[k2];
                        pq.add(new Pair2(rev, spend, str));
                    }
                }
            }
        }
        i = 0;
        j = 0;
        j2 = 0;
        k = 0;
        k2 = 0;
        // channel 1,2,5
        for (i = 1; i < rev1.length; i++) {
            for (j = 1; j < rev1.length; j++) {
                for (k2 = 1; k2 < rev1.length; k2++) {
                    if (spend1[i] + spend2[j] + spend3[j2] + spend4[k] + spend5[k2] <= 50) {
                        String str = String.valueOf(i) + "-" + String.valueOf(spend1[i]) + "-" + String.valueOf(rev1[i])
                                + " " + String.valueOf(j) + "-" + String.valueOf(spend2[j]) + "-"
                                + String.valueOf(rev2[j]) + " " + String.valueOf(j2) + "-" + String.valueOf(spend3[j2])
                                + "-" + String.valueOf(rev3[j2]) + " " + String.valueOf(k) + "-"
                                + String.valueOf(spend4[k]) + "-" + String.valueOf(rev4[k]) + " " + String.valueOf(k2)
                                + "-" + String.valueOf(spend5[k2]) + "-" + String.valueOf(rev5[k2]);
                        double spend = spend1[i] + spend2[j] + spend3[j2] + spend4[k] + spend5[k2];
                        double rev = rev1[i] + rev2[j] + rev3[j2] + rev4[k] + rev5[k2];
                        pq.add(new Pair2(rev, spend, str));
                    }
                }

            }
        }
        i = 0;
        j = 0;
        j2 = 0;
        k = 0;
        k2 = 0;
        // channel 2,3,4

        for (j = 1; j < rev1.length; j++) {
            for (j2 = 1; j2 < rev1.length; j2++) {
                for (k = 1; k < rev1.length; k++) {
                    if (spend1[i] + spend2[j] + spend3[j2] + spend4[k] + spend5[k2] <= 50) {
                        String str = String.valueOf(i) + "-" + String.valueOf(spend1[i]) + "-" + String.valueOf(rev1[i])
                                + " " + String.valueOf(j) + "-" + String.valueOf(spend2[j]) + "-"
                                + String.valueOf(rev2[j]) + " " + String.valueOf(j2) + "-" + String.valueOf(spend3[j2])
                                + "-" + String.valueOf(rev3[j2]) + " " + String.valueOf(k) + "-"
                                + String.valueOf(spend4[k]) + "-" + String.valueOf(rev4[k]) + " " + String.valueOf(k2)
                                + "-" + String.valueOf(spend5[k2]) + "-" + String.valueOf(rev5[k2]);
                        double spend = spend1[i] + spend2[j] + spend3[j2] + spend4[k] + spend5[k2];
                        double rev = rev1[i] + rev2[j] + rev3[j2] + rev4[k] + rev5[k2];
                        pq.add(new Pair2(rev, spend, str));
                    }
                }
            }
        }
        i = 0;
        j = 0;
        j2 = 0;
        k = 0;
        k2 = 0;

        // channel 2,3,5
        for (j = 1; j < rev1.length; j++) {
            for (j2 = 1; j2 < rev1.length; j2++) {
                for (k2 = 1; k2 < rev1.length; k2++) {
                    if (spend1[i] + spend2[j] + spend3[j2] + spend4[k] + spend5[k2] <= 50) {
                        String str = String.valueOf(i) + "-" + String.valueOf(spend1[i]) + "-" + String.valueOf(rev1[i])
                                + " " + String.valueOf(j) + "-" + String.valueOf(spend2[j]) + "-"
                                + String.valueOf(rev2[j]) + " " + String.valueOf(j2) + "-" + String.valueOf(spend3[j2])
                                + "-" + String.valueOf(rev3[j2]) + " " + String.valueOf(k) + "-"
                                + String.valueOf(spend4[k]) + "-" + String.valueOf(rev4[k]) + " " + String.valueOf(k2)
                                + "-" + String.valueOf(spend5[k2]) + "-" + String.valueOf(rev5[k2]);
                        double spend = spend1[i] + spend2[j] + spend3[j2] + spend4[k] + spend5[k2];
                        double rev = rev1[i] + rev2[j] + rev3[j2] + rev4[k] + rev5[k2];
                        pq.add(new Pair2(rev, spend, str));
                    }
                }
            }
        }
        i = 0;
        j = 0;
        j2 = 0;
        k = 0;
        k2 = 0;
        // channel 3,4,5
        for (j2 = 1; j2 < rev1.length; j2++) {
            for (k = 1; k < rev1.length; k++) {
                for (k2 = 1; k2 < rev1.length; k2++) {
                    if (spend1[i] + spend2[j] + spend3[j2] + spend4[k] + spend5[k2] <= 50) {
                        String str = String.valueOf(i) + "-" + String.valueOf(spend1[i]) + "-" + String.valueOf(rev1[i])
                                + " " + String.valueOf(j) + "-" + String.valueOf(spend2[j]) + "-"
                                + String.valueOf(rev2[j]) + " " + String.valueOf(j2) + "-" + String.valueOf(spend3[j2])
                                + "-" + String.valueOf(rev3[j2]) + " " + String.valueOf(k) + "-"
                                + String.valueOf(spend4[k]) + "-" + String.valueOf(rev4[k]) + " " + String.valueOf(k2)
                                + "-" + String.valueOf(spend5[k2]) + "-" + String.valueOf(rev5[k2]);
                        double spend = spend1[i] + spend2[j] + spend3[j2] + spend4[k] + spend5[k2];
                        double rev = rev1[i] + rev2[j] + rev3[j2] + rev4[k] + rev5[k2];
                        pq.add(new Pair2(rev, spend, str));
                    }
                }

            }
        }
        i = 0;
        j = 0;
        j2 = 0;
        k = 0;
        k2 = 0;
        // channel 1,3,4
        for (i = 1; i < rev1.length; i++) {
            for (j2 = 1; j2 < rev1.length; j2++) {
                for (k = 1; k < rev1.length; k++) {
                    if (spend1[i] + spend2[j] + spend3[j2] + spend4[k] + spend5[k2] <= 50) {
                        String str = String.valueOf(i) + "-" + String.valueOf(spend1[i]) + "-" + String.valueOf(rev1[i])
                                + " " + String.valueOf(j) + "-" + String.valueOf(spend2[j]) + "-"
                                + String.valueOf(rev2[j]) + " " + String.valueOf(j2) + "-" + String.valueOf(spend3[j2])
                                + "-" + String.valueOf(rev3[j2]) + " " + String.valueOf(k) + "-"
                                + String.valueOf(spend4[k]) + "-" + String.valueOf(rev4[k]) + " " + String.valueOf(k2)
                                + "-" + String.valueOf(spend5[k2]) + "-" + String.valueOf(rev5[k2]);
                        double spend = spend1[i] + spend2[j] + spend3[j2] + spend4[k] + spend5[k2];
                        double rev = rev1[i] + rev2[j] + rev3[j2] + rev4[k] + rev5[k2];
                        pq.add(new Pair2(rev, spend, str));
                    }
                }
            }
        }
        i = 0;
        j = 0;
        j2 = 0;
        k = 0;
        k2 = 0;
        // channel 1,3,5
        for (i = 1; i < rev1.length; i++) {

            for (j2 = 1; j2 < rev1.length; j2++) {

                for (k2 = 1; k2 < rev1.length; k2++) {
                    if (spend1[i] + spend2[j] + spend3[j2] + spend4[k] + spend5[k2] <= 50) {
                        String str = String.valueOf(i) + "-" + String.valueOf(spend1[i]) + "-" + String.valueOf(rev1[i])
                                + " " + String.valueOf(j) + "-" + String.valueOf(spend2[j]) + "-"
                                + String.valueOf(rev2[j]) + " " + String.valueOf(j2) + "-" + String.valueOf(spend3[j2])
                                + "-" + String.valueOf(rev3[j2]) + " " + String.valueOf(k) + "-"
                                + String.valueOf(spend4[k]) + "-" + String.valueOf(rev4[k]) + " " + String.valueOf(k2)
                                + "-" + String.valueOf(spend5[k2]) + "-" + String.valueOf(rev5[k2]);
                        double spend = spend1[i] + spend2[j] + spend3[j2] + spend4[k] + spend5[k2];
                        double rev = rev1[i] + rev2[j] + rev3[j2] + rev4[k] + rev5[k2];
                        pq.add(new Pair2(rev, spend, str));
                    }
                }

            }
        }
        i = 0;
        j = 0;
        j2 = 0;
        k = 0;
        k2 = 0;
        // channel 1,4,5
        for (i = 1; i < rev1.length; i++) {
            for (k = 1; k < rev1.length; k++) {
                for (k2 = 1; k2 < rev1.length; k2++) {
                    if (spend1[i] + spend2[j] + spend3[j2] + spend4[k] + spend5[k2] <= 50) {
                        String str = String.valueOf(i) + "-" + String.valueOf(spend1[i]) + "-" + String.valueOf(rev1[i])
                                + " " + String.valueOf(j) + "-" + String.valueOf(spend2[j]) + "-"
                                + String.valueOf(rev2[j]) + " " + String.valueOf(j2) + "-" + String.valueOf(spend3[j2])
                                + "-" + String.valueOf(rev3[j2]) + " " + String.valueOf(k) + "-"
                                + String.valueOf(spend4[k]) + "-" + String.valueOf(rev4[k]) + " " + String.valueOf(k2)
                                + "-" + String.valueOf(spend5[k2]) + "-" + String.valueOf(rev5[k2]);
                        double spend = spend1[i] + spend2[j] + spend3[j2] + spend4[k] + spend5[k2];
                        double rev = rev1[i] + rev2[j] + rev3[j2] + rev4[k] + rev5[k2];
                        pq.add(new Pair2(rev, spend, str));
                    }
                }

            }
        }
        i = 0;
        j = 0;
        j2 = 0;
        k = 0;
        k2 = 0;
        // channel 2,4,5
        for (j = 0; j < rev1.length; j++) {
            for (k = 0; k < rev1.length; k++) {
                for (k2 = 0; k2 < rev1.length; k2++) {
                    if (spend1[i] + spend2[j] + spend3[j2] + spend4[k] + spend5[k2] <= 50) {
                        String str = String.valueOf(i) + "-" + String.valueOf(spend1[i]) + "-" + String.valueOf(rev1[i])
                                + " " + String.valueOf(j) + "-" + String.valueOf(spend2[j]) + "-"
                                + String.valueOf(rev2[j]) + " " + String.valueOf(j2) + "-" + String.valueOf(spend3[j2])
                                + "-" + String.valueOf(rev3[j2]) + " " + String.valueOf(k) + "-"
                                + String.valueOf(spend4[k]) + "-" + String.valueOf(rev4[k]) + " " + String.valueOf(k2)
                                + "-" + String.valueOf(spend5[k2]) + "-" + String.valueOf(rev5[k2]);
                        double spend = spend1[i] + spend2[j] + spend3[j2] + spend4[k] + spend5[k2];
                        double rev = rev1[i] + rev2[j] + rev3[j2] + rev4[k] + rev5[k2];
                        pq.add(new Pair2(rev, spend, str));
                    }
                }
            }
        }

        System.out.println(pq.poll().toString());
        System.out.println(pq.poll().toString());
        System.out.println(pq.poll().toString());
        System.out.println(pq.poll().toString());
        System.out.println(pq.poll().toString());
        System.out.println(pq.poll().toString());
        System.out.println(pq.poll().toString());
        System.out.println(pq.poll().toString());
        System.out.println(pq.poll().toString());
        System.out.println(pq.poll().toString());
        System.out.println(pq.poll().toString());
    }

    static class Pair2 implements Comparator<Pair2>, Comparable<Pair2> {
        double roi, revenue, spend;
        String str;

        Pair2(double rev, double spend, String str) {
            this.roi = rev / spend;
            this.revenue = rev;
            this.spend = spend;
            this.str = str;
        }

        @Override
        public String toString() {
            return "Revenue= " + this.revenue + " spend= " + this.spend + " ROI= " + this.roi + " indices= " + this.str;
        }

        @Override
        public int compare(Pair2 o1, Pair2 o2) {
            return (int) Math.signum(o1.revenue - o2.revenue) * -1;
        }

        @Override
        public int compareTo(Pair2 o) {
            return (int) Math.signum(this.revenue - o.revenue) * -1;
        }
    }
}