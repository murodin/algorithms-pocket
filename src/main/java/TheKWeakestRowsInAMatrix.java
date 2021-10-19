import java.util.Arrays;
import java.util.PriorityQueue;

public class TheKWeakestRowsInAMatrix {

    /*
        You are given an m x n binary matrix mat of 1's (representing soldiers) and 0's (representing civilians).
        The soldiers are positioned in front of the civilians. That is, all the 1's will appear to the left of all the 0's in each row.
        A row i is weaker than a row j if one of the following is true:
        The number of soldiers in row i is less than the number of soldiers in row j.
        Both rows have the same number of soldiers and i < j.
        Return the indices of the k weakest rows in the matrix ordered from weakest to strongest.

        Example 1.
        Input: mat =
        [[1,1,0,0,0],
         [1,1,1,1,0],
         [1,0,0,0,0],
         [1,1,0,0,0],
         [1,1,1,1,1]],
        k = 3
        Output: [2,0,3]
        Explanation:
        The number of soldiers in each row is:
        - Row 0: 2
        - Row 1: 4
        - Row 2: 1
        - Row 3: 2
        - Row 4: 5
        The rows ordered from weakest to strongest are [2,0,3,1,4].
        Example 2.
        Input: mat =
        [[1,0,0,0],
         [1,1,1,1],
         [1,0,0,0],
         [1,0,0,0]],
        k = 2
        Output: [0,2]
        Explanation:
        The number of soldiers in each row is:
        - Row 0: 1
        - Row 1: 4
        - Row 2: 1
        - Row 3: 1
        The rows ordered from weakest to strongest are [0,2,3,1].
     */

    public static void main(String[] args) {
        int[][] test = {
                    {1,1,0,0,0},
                    {1,1,1,1,0},
                    {1,0,0,0,0},
                    {1,1,0,0,0},
                    {1,1,1,1,1}
                };
        System.out.println("Weakest Rows - I: " + Arrays.toString(Solution_I.kWeakestRows(test, 3)));
        System.out.println("Weakest Rows - II: " + Arrays.toString(Solution_II.kWeakestRows(test, 3)));
        System.out.println("Weakest Rows - III: " + Arrays.toString(Solution_III.kWeakestRows(test, 3)));
    }

    /*

        PQ -> int[] --> 0 -> numOfSoldier, 1 -> indexOfRow
        maxHeap --> eliminate the maxValues
        a,b ---> a[0], b[0] in desc
                 a[o] == b[0] --> a[1] b[1] in desc
    */
    // Using Priority Queue
    // Time: O(MxN); M: Rows, N:Cols
    // Space: O(M)
    static class Solution_I {
        public static int[] kWeakestRows(int[][] mat, int k) {
            PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> a[0]!=b[0] ? b[0]-a[0] : b[1]-a[1]);
            int[] res = new int[k];

            for(int i = 0; i < mat.length; i++) {
                int soldiers = 0;
                for(int j = 0; j < mat[0].length; j++) {
                    if(mat[i][j] == 1) soldiers++;
                    else break;
                }
                pq.offer(new int[]{soldiers, i});
            }

            while(pq.size() > k){
                pq.poll();
            }

            while(k > 0) res[--k] = pq.poll()[1];

            return res;
        }
    }


    // Using Binary Search
    // Time: O(M*LogN); M: Rows, N:Cols
    // Space: O(M)
    static class Solution_II {
        public static int[] kWeakestRows(int[][] mat, int k) {
            PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] != b[0] ? b[0] - a[0] : b[1] - a[1]);
            int[] ans = new int[k];

            for (int i = 0; i < mat.length; i++) {
                pq.offer(new int[] {numOnes(mat[i]), i});
                if (pq.size() > k)
                    pq.poll();
            }

            while (k > 0) ans[--k] = pq.poll()[1];

            return ans;
        }

        private static int numOnes(int[] row) {
            int lo = 0;
            int hi = row.length;

            while (lo < hi) {
                int mid = lo + (hi - lo) / 2;
                if (row[mid] == 1) lo = mid + 1;
                else hi = mid;
            }
            return lo;
        }
    }


    // Using Array
    // Time: O(MxN); M: Rows, N:Cols
    // Space: O(M)
    static class Solution_III {
        public static int[] kWeakestRows(int[][] mat, int k) {
            int[] count = new int[mat.length];
            int[] res = new int[k];

            for(int i = 0; i < mat.length; i++) {
                int soldiers = 0;
                for(int j = 0; j < mat[0].length; j++) {
                    if(mat[i][j] == 1) soldiers++;
                    else break;
                }
                count[i] = soldiers*1000 + i;
            }

            Arrays.sort(count);

            for(int i =0; i < k; i++) res[i] = count[i]%1000;

            return res;
        }
    }
}
