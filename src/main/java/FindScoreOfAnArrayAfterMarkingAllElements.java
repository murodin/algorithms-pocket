import java.util.PriorityQueue;

public class FindScoreOfAnArrayAfterMarkingAllElements {
    /*
        You are given an array nums consisting of positive integers.

        Starting with score = 0, apply the following algorithm:

        Choose the smallest integer of the array that is not marked. If there is a tie, choose the one with the smallest index.
        Add the value of the chosen integer to score.
        Mark the chosen element and its two adjacent elements if they exist.
        Repeat until all the array elements are marked.
        Return the score you get after applying the above algorithm.



        Example 1:

        Input: nums = [2,1,3,4,5,2]
        Output: 7
        Explanation: We mark the elements as follows:
        - 1 is the smallest unmarked element, so we mark it and its two adjacent elements: [2,1,3,4,5,2].
        - 2 is the smallest unmarked element, so we mark it and its left adjacent element: [2,1,3,4,5,2].
        - 4 is the only remaining unmarked element, so we mark it: [2,1,3,4,5,2].
        Our score is 1 + 2 + 4 = 7.
        Example 2:

        Input: nums = [2,3,5,1,3,2]
        Output: 5
        Explanation: We mark the elements as follows:
        - 1 is the smallest unmarked element, so we mark it and its two adjacent elements: [2,3,5,1,3,2].
        - 2 is the smallest unmarked element, since there are two of them, we choose the left-most one, so we mark the one at index 0 and its right adjacent element: [2,3,5,1,3,2].
        - 2 is the only remaining unmarked element, so we mark it: [2,3,5,1,3,2].
        Our score is 1 + 2 + 2 = 5.


        Constraints:

        1 <= nums.length <= 105
        1 <= nums[i] <= 106
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.findScore(new int[]{2,3,5,1,3,2}));
    }

    // Time: O(NLogN)
    // Space: O(N)
    static class Solution {
        public static long findScore(int[] nums) {
            long sum=0;
            PriorityQueue<int[]> q = new PriorityQueue<>((x, y)-> x[0]!=y[0]?x[0]-y[0]:x[1]-y[1]);
            for(int i=0;i<nums.length;i++){
                q.offer(new int[]{nums[i],i});
            }

            boolean[] vis = new boolean[nums.length];
            while(!q.isEmpty()){
                int val=q.peek()[0], index=q.peek()[1];
                q.poll();
                if(!vis[index]){
                    sum+=val;
                    if(index-1>=0)vis[index-1]=true;
                    if(index+1<nums.length)vis[index+1]=true;
                }
            }

            return sum;

        }
    }
}
