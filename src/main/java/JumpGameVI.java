import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;

public class JumpGameVI {
    /*
        You are given a 0-indexed integer array nums and an integer k.
        You are initially standing at index 0.
        In one move, you can jump at most k steps forward without going outside the boundaries of the array.
        That is, you can jump from index i to any index in the range [i + 1, min(n - 1, i + k)] inclusive.
        You want to reach the last index of the array (index n - 1). Your score is the sum of all nums[j] for each index j you visited in the array.

        Return the maximum score you can get.

        Example 1.
        Input: nums = [1,-1,-2,4,-7,3], k = 2
        Output: 7
        Explanation: You can choose your jumps forming the subsequence [1,-1,4,3] (underlined above). The sum is 7.
        Example 2.
        Input: nums = [10,-5,-2,4,0,3], k = 3
        Output: 17
        Explanation: You can choose your jumps forming the subsequence [10,4,3] (underlined above). The sum is 17.
        Example 3.
        Input: nums = [1,-5,-20,4,-1,3,-6,-3], k = 2
        Output: 0

        Constraints.
        1 <= nums.length, k <= 105
        -104 <= nums[i] <= 104
     */

    public static void main(String[] args) {
        int[] nums = {10,-5,-2,4,0,3};
        System.out.println("Max Result -> Solution I: " + Solution_I.maxResult(nums, 3));
        System.out.println("Max Result -> Solution II: " + Solution_II.maxResult(nums, 3));
    }

    // Time: O(NLogK)
    // Space: O(K)
    static class Solution_I {
        public static int maxResult(int[] nums, int k) {
            /*
                 For every index starting from 1: --- n
                 Find out the max sum from all { i -1 } to  { i –k } index -- klogk
                 sum[index] = value[index] + maxfound
                 Result is sum[length -1]
             */

            int n = nums.length;
            int max = nums[0];
            // index --- maxSum
            PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[1]-a[1]);
            pq.offer(new int[]{0,nums[0]});

            for(int i=1;i<n;i++){

                while(i-pq.peek()[0]>k){
                    pq.poll();
                }

                int[] top = pq.peek();

                max = nums[i] + top[1];

                pq.offer(new int[]{i,max});

            }

            return max;
        }
    }

    // Time: O(N)
    // Space: O(K)
    static class Solution_II {
        public static int maxResult(int[] nums, int k) {
            int n = nums.length;

            Deque<Integer> dq = new ArrayDeque<>();
            dq.offerLast(0);

            for(int i=1;i<n;i++){

                nums[i] = nums[i] + nums[dq.peekFirst()];

                while(!dq.isEmpty() && nums[i]>=nums[dq.peekLast()]){
                    dq.pollLast();
                }

                dq.offerLast(i);
                if(i - dq.peekFirst() >=k){
                    dq.pollFirst();
                }

            }
            return nums[n-1];
        }
    }


}
