import java.util.*;

public class LeastNumberOfUniqueIntegersAfterKRemovals {
    /*
        Given an array of integers arr and an integer k. Find the least number of unique integers after removing exactly k elements.
        Example 1:
        Input: arr = [5,5,4], k = 1
        Output: 1
        Explanation: Remove the single 4, only 5 is left.
        Example 2:
        Input: arr = [4,3,1,1,3,3,2], k = 3
        Output: 2
        Explanation: Remove 4, 2 and either one of the two 1s or three 3s. 1 and 3 will be left.

        Constraints:

        1 <= arr.length <= 10^5
        1 <= arr[i] <= 10^9
        0 <= k <= arr.length
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.findLeastNumOfUniqueInts(new int[]{4,3,1,1,3,3,2}, 3));
    }

    // Time: (NLogN)
    // Space: O(N)
    static class Solution {
        public static int findLeastNumOfUniqueInts(int[] arr, int k) {
            Map<Integer, Integer> mp = new HashMap<>();
            for (int a : arr) mp.put(a, mp.getOrDefault(a, 0) + 1);

            int cnt = 0;
            List<Integer> v = new ArrayList<>(mp.values());

            Collections.sort(v);
            for (int i = 0; i < v.size(); i++) {
                if (k > v.get(i)) {
                    k -= v.get(i);
                    v.set(i, 0);
                } else {
                    v.set(i, v.get(i) - k);
                    k = 0;
                }
                if (v.get(i) != 0) cnt++;
            }
            return cnt;
        }
    }
}
