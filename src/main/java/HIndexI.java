public class HIndexI {

    /*
        Given an array of integers citations where citations[i] is the number of citations a researcher received for their ith paper, return compute the researcher's h-index.
        According to the definition of h-index on Wikipedia:
         A scientist has an index h if h of their n papers have at least h citations each, and the other n − h papers have no more than h citations each.
        If there are several possible values for h, the maximum one is taken as the h-index.

        Example 1.
        Input: citations = [3,0,6,1,5]
        Output: 3
        Explanation: [3,0,6,1,5] means the researcher has 5 papers in total and each of them had received 3, 0, 6, 1, 5 citations respectively.
        Since the researcher has 3 papers with at least 3 citations each and the remaining two with no more than 3 citations each, their h-index is 3.
        Example 2.
        Input: citations = [1,3,1]
        Output: 1


        Constraints:

        n == citations.length
        1 <= n <= 5000
        0 <= citations[i] <= 1000
     */

    public static void main(String[] args) {
        int[] citations = {3,0,6,1,5};
        System.out.println("Solution I: " + Solution_I.hIndex(citations));
        System.out.println("Solution II: " + Solution_II.hIndex(citations));
    }

    // Time: O(N)
    // Space: O(N)
    static class Solution_I {
        public static int hIndex(int[] citations) {
            // observation#1 there will be no hIndex which is greater than size
            // of the array?
            int N = citations.length;
            int []arr = new int[N+1]; // this will keep track of all elements
            // or citations which are there
            for (int citation : citations) {
                if (citation >= N) {
                    arr[N]++;
                } else {
                    arr[citation]++;
                }
            }
            // This array tells me how many citation>=i i've recieved/
            int total = 0;
            // This total signifies that how many total papers are there with atleast i citations to right.( >= ith basically h-index)
            for (int i = N; i >= 0; i--) {
                total += arr[i];
                if (total >= i) return i;
            }
            return 0;
        }
    }

    // Time: O(N)
    // Space: O(1)
    static class Solution_II {
        public static int hIndex(int[] citations) {
            int[] buckets = new int[1001];
            for (int c: citations) {
                buckets[c]++;
            }
            int total = 0;
            for (int i = 1000; i>=0; i--) {
                total+=buckets[i];
                if (total>=i){
                    return i;
                }
            }
            return -1;
        }
    }
}
