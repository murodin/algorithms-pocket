import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class FindKPairsWithSmallestSums {
    /*
        You are given two integer arrays nums1 and nums2 sorted in ascending order and an integer k.

        Define a pair (u, v) which consists of one element from the first array and one element from the second array.

        Return the k pairs (u1, v1), (u2, v2), ..., (uk, vk) with the smallest sums.



        Example 1:

        Input: nums1 = [1,7,11], nums2 = [2,4,6], k = 3
        Output: [[1,2],[1,4],[1,6]]
        Explanation: The first 3 pairs are returned from the sequence: [1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
        Example 2:

        Input: nums1 = [1,1,2], nums2 = [1,2,3], k = 2
        Output: [[1,1],[1,1]]
        Explanation: The first 2 pairs are returned from the sequence: [1,1],[1,1],[1,2],[2,1],[1,2],[2,2],[1,3],[1,3],[2,3]
        Example 3:

        Input: nums1 = [1,2], nums2 = [3], k = 3
        Output: [[1,3],[2,3]]
        Explanation: All possible pairs are returned from the sequence: [1,3],[2,3]


        Constraints:

        1 <= nums1.length, nums2.length <= 105
        -109 <= nums1[i], nums2[i] <= 109
        nums1 and nums2 both are sorted in ascending order.
        1 <= k <= 104
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.kSmallestPairs(new int[]{1,2}, new int[]{3}, 3));
    }

    // Time: O(N^2LogN)
    // Space: O(N)
    static class Solution {
        public static List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
            List<List<Integer>> result = new ArrayList<>();
            PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[2] - a[2]);

            for (int j : nums1) {
                for (int value : nums2) {
                    int sum = j + value;
                    if (pq.size() < k) {
                        pq.add(new int[]{j, value, sum});
                    } else {
                        assert pq.peek() != null;
                        if (sum < pq.peek()[2]) {
                            pq.poll();
                            pq.add(new int[]{j, value, sum});
                        } else break;
                    }
                }
            }

            while(!pq.isEmpty()) {
                int[] pair = pq.poll();
                List<Integer> pairList = new ArrayList<>();
                pairList.add(pair[0]);
                pairList.add(pair[1]);
                result.add(0, pairList);
            }

            return result;
        }
    }
}
