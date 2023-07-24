import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

public class MaximalScoreAfterApplyingKOperations {
    /*
        You are given a 0-indexed integer array nums and an integer k. You have a starting score of 0.

        In one operation:

        choose an index i such that 0 <= i < nums.length,
        increase your score by nums[i], and
        replace nums[i] with ceil(nums[i] / 3).
        Return the maximum possible score you can attain after applying exactly k operations.

        The ceiling function ceil(val) is the least integer greater than or equal to val.



        Example 1:

        Input: nums = [10,10,10,10,10], k = 5
        Output: 50
        Explanation: Apply the operation to each array element exactly once. The final score is 10 + 10 + 10 + 10 + 10 = 50.
        Example 2:

        Input: nums = [1,10,3,3,3], k = 3
        Output: 17
        Explanation: You can do the following operations:
        Operation 1: Select i = 1, so nums becomes [1,4,3,3,3]. Your score increases by 10.
        Operation 2: Select i = 1, so nums becomes [1,2,3,3,3]. Your score increases by 4.
        Operation 3: Select i = 2, so nums becomes [1,1,1,3,3]. Your score increases by 3.
        The final score is 10 + 4 + 3 = 17.


        Constraints:

        1 <= nums.length, k <= 105
        1 <= nums[i] <= 109
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.maxKelements(new int[]{1,10,3,3,3}, 3));
    }

    // Time: O(N^2LogN)
    // Space: O(N)
    static class Solution {
        public static long maxKelements(int[] nums, int k) {
            Arrays.sort(nums);
            long sum = 0;
            int num = k;
            PriorityQueue<Integer> pq = new PriorityQueue<Integer>(Collections.reverseOrder());
            if(nums.length <= k) {
                k = nums.length;
            }
            while(k > 0) {
                pq.add(nums[nums.length - k]);
                k--;
            }
            k = num;
            while(k > 0 && pq.size() > 0) {
                sum += pq.peek();
                int newNum = pq.peek() % 3 == 0 ? pq.peek() / 3 : pq.peek() / 3 + 1;
                pq.poll();
                pq.add(newNum);
                k--;
            }
            return sum;
        }
    }
}
