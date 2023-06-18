import java.util.ArrayList;
import java.util.List;

public class NumberOfWaysToReorderArrayToGetSameBST {
    /*
        Given an array nums that represents a permutation of integers from 1 to n.
        We are going to construct a binary search tree (BST) by inserting the elements of nums in order into an initially empty BST.
        Find the number of different ways to reorder nums so that the constructed BST is identical to that formed from the original array nums.
        For example, given nums = [2,1,3], we will have 2 as the root, 1 as a left child, and 3 as a right child.
        The array [2,3,1] also yields the same BST but [3,2,1] yields a different BST.
        Return the number of ways to reorder nums such that the BST formed is identical to the original BST formed from nums.

        Since the answer may be very large, return it modulo 109 + 7.


        Example 1:


        Input: nums = [2,1,3]
        Output: 1
        Explanation: We can reorder nums to be [2,3,1] which will yield the same BST. There are no other ways to reorder nums which will yield the same BST.
        Example 2:


        Input: nums = [3,4,5,1,2]
        Output: 5
        Explanation: The following 5 arrays will yield the same BST:
        [3,1,2,4,5]
        [3,1,4,2,5]
        [3,1,4,5,2]
        [3,4,1,2,5]
        [3,4,1,5,2]
        Example 3:


        Input: nums = [1,2,3]
        Output: 0
        Explanation: There are no other orderings of nums that will yield the same BST.


        Constraints:

        1 <= nums.length <= 1000
        1 <= nums[i] <= nums.length
        All integers in nums are distinct.
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.numOfWays(new int[]{3,4,5,1,2}));
    }

    // Time: O(N^2)
    // Space: O(N^2)
    static class Solution {
        private static final int MOD = 1000000007;
        public static int numOfWays(int[] nums) {
            List<Integer> list = new ArrayList<>();
            for (int num : nums) {
                list.add(num);
            }
            return countWays(list) - 1;
        }
        private static int countWays(List<Integer> nums) {
            if (nums.size() <= 2) {
                return 1;
            }

            List<Integer> left = new ArrayList<>();
            List<Integer> right = new ArrayList<>();
            int root = nums.get(0);

            for (int i = 1; i < nums.size(); i++) {
                if (nums.get(i) < root) {
                    left.add(nums.get(i));
                } else {
                    right.add(nums.get(i));
                }
            }

            long leftCount = countWays(left);
            long rightCount = countWays(right);

            return (int) ((comb(nums.size() - 1, left.size()) % MOD) * (leftCount % MOD) % MOD * (rightCount % MOD) % MOD);
        }

        private static long comb(int n, int k) {
            long[][] dp = new long[n + 1][k + 1];
            for (int i = 0; i <= n; i++) {
                dp[i][0] = 1;
                for (int j = 1; j <= Math.min(i, k); j++) {
                    dp[i][j] = (dp[i - 1][j - 1] + dp[i - 1][j]) % MOD;
                }
            }
            return dp[n][k];
        }
    }
}
