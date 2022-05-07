import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Stack;

public class NumberOfVisiblePeopleInAQueue {
    /*
        There are n people standing in a queue, and they numbered from 0 to n - 1 in left to right order.
        You are given an array heights of distinct integers where heights[i] represents the height of the ith person.
        A person can see another person to their right in the queue if everybody in between is shorter than both of them.
         More formally, the ith person can see the jth person if i < j and min(heights[i], heights[j]) > max(heights[i+1], heights[i+2], ..., heights[j-1]).
        Return an array answer of length n where answer[i] is the number of people the ith person can see to their right in the queue.

        Example 1.
        Input: heights = [10,6,8,5,11,9]
        Output: [3,1,2,1,1,0]
        Explanation:
        Person 0 can see person 1, 2, and 4.
        Person 1 can see person 2.
        Person 2 can see person 3 and 4.
        Person 3 can see person 4.
        Person 4 can see person 5.
        Person 5 can see no one since nobody is to the right of them.
        Example 2.
        Input: heights = [5,1,2,3,10]
        Output: [4,1,1,1,0]


        Constraints:

        n == heights.length
        1 <= n <= 105
        1 <= heights[i] <= 105
        All the values of heights are unique.
     */
    public static void main(String[] args) {
        int[] heights = {10,6,8,5,11,9};
        System.out.println("No Of Visible People In A Queue: " + Arrays.toString(Solution_I.canSeePersonsCount(heights)));
        System.out.println("No Of Visible People In A Queue: " + Arrays.toString(Solution_II.canSeePersonsCount(heights)));
        System.out.println("No Of Visible People In A Queue: " + Arrays.toString(Solution_III.canSeePersonsCount(heights)));
    }

    // Time: O(NLogN)
    // Space: O(N)
    static class Solution_I {
        public static int[] canSeePersonsCount(int[] heights) {
            PriorityQueue<Integer> pq = new PriorityQueue<>();
            int[] result = new int[heights.length];
            for(int i = heights.length - 1; i >= 0; i --) {
                int count = 0;
                while(!pq.isEmpty() && pq.peek() <= heights[i]) {
                    pq.poll();
                    count++;
                }
                if(!pq.isEmpty()) {
                    count ++;
                }
                pq.offer(heights[i]);
                result[i] = count;
            }
            return result;
        }
    }

    // Time: O(N)
    // Space: O(N)
    static class Solution_II {
        public static int[] canSeePersonsCount(int[] heights) {
            Stack<Integer> stack = new Stack<>();
            int[] result = new int[heights.length];
            for(int i = heights.length - 1; i >= 0; i--) {
                int visibility = 0;
                while(!stack.isEmpty() && heights[i] > stack.peek()) {
                    stack.pop();
                    visibility++;
                }
                if(!stack.isEmpty()) visibility++;
                stack.push(heights[i]);
                result[i] = visibility;
            }
            return result;
        }
    }

    // Time: O(N)
    // Space: O(N)
    static class Solution_III {
        public static int[] canSeePersonsCount(int[] heights) {
            int size=heights.length;
            Stack<Integer> stack=new Stack<>();
            int[] visible=new int[size];
            for(int i=size-1;i>=0;i--){
                int pc=0;
                while(!stack.isEmpty()&&stack.peek()<heights[i]){
                    pc++;
                    stack.pop();
                }
                visible[i]=pc+(stack.isEmpty()?0:1);
                stack.push(heights[i]);

            }
            return visible;
        }
    }
}
