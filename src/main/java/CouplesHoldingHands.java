public class CouplesHoldingHands {
    /*
        There are n couples sitting in 2n seats arranged in a row and want to hold hands.
        The people and seats are represented by an integer array row where row[i] is the ID of the person sitting in the ith seat.
        The couples are numbered in order, the first couple being (0, 1), the second couple being (2, 3), and so on with the last couple being (2n - 2, 2n - 1).
        Return the minimum number of swaps so that every couple is sitting side by side. A swap consists of choosing any two people, then they stand up and switch seats.

        Example 1:
        Input: row = [0,2,1,3]
        Output: 1
        Explanation: We only need to swap the second (row[1]) and third (row[2]) person.
        Example 2:
        Input: row = [3,2,0,1]
        Output: 0
        Explanation: All couples are already seated side by side.


        Constraints:

        2n == row.length
        2 <= n <= 30
        n is even.
        0 <= row[i] < 2n
        All the elements of row are unique.
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.minSwapsCouples(new int[]{3,2,0,1}));
    }

    // Time: O(N)
    // Space: O(N)
    static class Solution {
        public static int minSwapsCouples(int[] row) {
            int res = 0, N = row.length;
            int[] ptn = new int[N];
            int[] pos = new int[N];
            for (int i = 0; i < N; i++) {
                ptn[i] = (i % 2 == 0 ? i + 1 : i - 1);
                pos[row[i]] = i;
            }
            for (int i = 0; i < N; i++) {
                for (int j = ptn[pos[ptn[row[i]]]]; i != j; j = ptn[pos[ptn[row[i]]]]) {
                    swap(row, i, j);
                    swap(pos, row[i], row[j]);
                    res++;
                }
            }
            return res;
        }

        private static void swap(int[] arr, int i, int j) {
            int t = arr[i];
            arr[i] = arr[j];
            arr[j] = t;
        }
    }
}
