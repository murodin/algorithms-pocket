import java.util.ArrayList;

public class DataStreamAsDisjointIntervals {
    /*
        Given a data stream input of non-negative integers a1, a2, ..., an, summarize the numbers seen so far as a list of disjoint intervals.
        Implement the SummaryRanges class:
        SummaryRanges() Initializes the object with an empty stream.
        void addNum(int val) Adds the integer val to the stream.
        int[][] getIntervals() Returns a summary of the integers in the stream currently as a list of disjoint intervals [starti, endi].

        Example 1.
        Input
        ["SummaryRanges", "addNum", "getIntervals", "addNum", "getIntervals", "addNum", "getIntervals", "addNum", "getIntervals", "addNum", "getIntervals"]
        [[], [1], [], [3], [], [7], [], [2], [], [6], []]
        Output
        [null, null, [[1, 1]], null, [[1, 1], [3, 3]], null, [[1, 1], [3, 3], [7, 7]], null, [[1, 3], [7, 7]], null, [[1, 3], [6, 7]]]

        Explanation
        SummaryRanges summaryRanges = new SummaryRanges();
        summaryRanges.addNum(1);      // arr = [1]
        summaryRanges.getIntervals(); // return [[1, 1]]
        summaryRanges.addNum(3);      // arr = [1, 3]
        summaryRanges.getIntervals(); // return [[1, 1], [3, 3]]
        summaryRanges.addNum(7);      // arr = [1, 3, 7]
        summaryRanges.getIntervals(); // return [[1, 1], [3, 3], [7, 7]]
        summaryRanges.addNum(2);      // arr = [1, 2, 3, 7]
        summaryRanges.getIntervals(); // return [[1, 3], [7, 7]]
        summaryRanges.addNum(6);      // arr = [1, 2, 3, 6, 7]
        summaryRanges.getIntervals(); // return [[1, 3], [6, 7]]


        Constraints:

        0 <= val <= 104
        At most 3 * 104 calls will be made to addNum and getIntervals.


        Follow up: What if there are lots of merges and the number of disjoint intervals is small compared to the size of the data stream?
     */

    public static void main(String[] args) {
        System.out.println("Done...");
    }

    // Time:
    // Space:
    static class SummaryRanges {
        static ArrayList<int[]> intervals;
        public SummaryRanges() {
            intervals = new ArrayList<>();
        }

        public static void addNum(int val) {
            if ( intervals.size()==0 ) {
                intervals.add( new int[] {val, val} );
                return;
            }
            int l = 0;
            int r = intervals.size();
            while ( l<r ) {
                int mid = l + ( r-l ) / 2;
                if ( intervals.get(mid)[0]<=val && intervals.get(mid)[1]>=val ) {
                    return;
                }
                if ( intervals.get(mid)[1]<=val ) {
                    l = mid + 1;
                } else {
                    r = mid;
                }
            }
            if ( l==0 ) {
                if ( intervals.get(0)[0]-val<=1 ) {
                    intervals.get(0)[0] = val;
                } else {
                    intervals.add(0, new int[]{val, val});
                }
            } else if ( l==intervals.size() ) {
                if ( val-intervals.get(l-1)[1]<=1 ) {
                    intervals.get(l-1)[1] = val;
                } else {
                    intervals.add(new int[]{val, val});
                }
            } else {
                if ( val-intervals.get(l-1)[1]<=1 && intervals.get(l)[0]-val<=1 ) {
                    intervals.get(l-1)[1] = intervals.get(l)[1];
                    intervals.remove(l);
                } else if ( val-intervals.get(l-1)[1]<=1 ) {
                    intervals.get(l-1)[1] = Math.max(val, intervals.get(l-1)[1]);
                } else if ( intervals.get(l)[0]-val<=1 ) {
                    intervals.get(l)[0] = Math.min(val, intervals.get(l)[0]);
                } else {
                    intervals.add(l, new int[]{val, val});
                }
            }
        }

        public static int[][] getIntervals() {
            int[][] res = new int[intervals.size()][2];
            int i = 0;
            for ( int[] t: intervals ) {
                res[i++] = t;
            }
            return res;
        }
    }
}
