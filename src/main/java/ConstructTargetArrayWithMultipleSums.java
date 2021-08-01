import java.util.PriorityQueue;

public class ConstructTargetArrayWithMultipleSums {

    /*
        You are given an array target of n integers. From a starting array arr consisting of n 1's, you may perform the following procedure :

        let x be the sum of all elements currently in your array.
        choose index i, such that 0 <= i < n and set the value of arr at index i to x.
        You may repeat this procedure as many times as needed.
        Return true if it is possible to construct the target array from arr, otherwise, return false.
        Example.
        Input: target = [9,3,5]
        Output: true
        Explanation: Start with arr = [1, 1, 1]
        [1, 1, 1], sum = 3 choose index 1
        [1, 3, 1], sum = 5 choose index 2
        [1, 3, 5], sum = 9 choose index 0
        [9, 3, 5] Done
     */

    public static void main(String[] args) {
        int[] trueTest = {9,3,5};
        int[] falseTest = {1,1,1,2};

        System.out.println("Test true: " + Solution.isPossible(trueTest) + " Test false: " + Solution.isPossible(falseTest));
    }

    /*
        Time Complexity: o(NLogMaxA)
        Space Complexity: o(MaxA)
     */
    static class Solution {
        public static boolean isPossible(int[] target) {
            long sum = 0;
            int max = 0;

            for(int i = 0; i < target.length; i++) {
                sum += target[i];
                if(target[max] < target[i]) {
                    max = i;
                }
            }

            long diff = sum - target[max];

            if(target[max] == 1 || diff == 1) return true;

            if(diff>target[max] || diff == 0 || target[max] % diff == 0) return false;

            target[max] %= diff;

            return isPossible(target);
        }

        /*
        Time Complexity: o(NLog)
        Space Complexity: o(N)
     */
        public static boolean isPossible_2(int[] target) {
            long sum = 0;

            PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b-a);

            for(int i = 0; i < target.length; i++) {
                sum += target[i];
                pq.offer(target[i]);
            }

            while (pq.peek() != 1) {
                int value = pq.poll();
                long diff = sum - value;

                if(value == 1 || diff == 1) return true;

                if(diff>value || diff == 0 || value % diff == 0) return false;

                value %= diff;
                sum = diff + value;
                pq.offer(value);
            }

            return true;
        }
    }
}
