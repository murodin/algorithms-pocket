import java.util.Arrays;

public class CountingBits {
    /*
        Given an integer n, return an array ans of length n + 1 such that for each i (0 <= i <= n), ans[i] is the number of 1's in the binary representation of i.
        Example 1:

        Input: n = 2
        Output: [0,1,1]
        Explanation:
        0 --> 0
        1 --> 1
        2 --> 10
        Example 2:

        Input: n = 5
        Output: [0,1,1,2,1,2]
        Explanation:
        0 --> 0
        1 --> 1
        2 --> 10
        3 --> 11
        4 --> 100
        5 --> 101


        Constraints:

        0 <= n <= 105


        Follow up:

        It is very easy to come up with a solution with a runtime of O(n log n). Can you do it in linear time O(n) and possibly in a single pass?
        Can you do it without using any built-in function (i.e., like __builtin_popcount in C++)?
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Arrays.toString(Solution.countBits(5)));
    }

    // Time: O(N^2)
    // Space: O(N)
    static class Solution {
        public static int[] countBits(int n){
            int[] list1 = new int[n + 1];
            for (int i = 0; i <= n; i++) {
                int sum = CountOnesInBinary(i);
                list1[i] = sum;
            }
            return list1;
        }

        private static int CountOnesInBinary(int num) {
            int count = 0;
            while (num > 0) {
                count += num & 1;
                num >>= 1;
            }
            return count;
        }
    }
}
