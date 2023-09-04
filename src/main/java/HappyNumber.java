public class HappyNumber {
    /*
        Write an algorithm to determine if a number n is happy.

        A happy number is a number defined by the following process:

        Starting with any positive integer, replace the number by the sum of the squares of its digits.
        Repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1.
        Those numbers for which this process ends in 1 are happy.
        Return true if n is a happy number, and false if not.



        Example 1:

        Input: n = 19
        Output: true
        Explanation:
        12 + 92 = 82
        82 + 22 = 68
        62 + 82 = 100
        12 + 02 + 02 = 1
        Example 2:

        Input: n = 2
        Output: false


        Constraints:

        1 <= n <= 231 - 1
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.isHappy(19));
    }

    // Time: O(N)
    // Space: O(1)
    static class Solution {
        public static boolean isHappy(int n) {
            int slow = n;
            int fast = n;
            do {
                slow = find(slow);
                fast = find(find(fast));
            } while(slow != fast);
            return slow == 1;
        }
        private static int find(int num){
            int ans = 0;
            while(num > 0){
                int rem = num % 10;
                ans += rem * rem;
                num /= 10;
            }
            return ans;
        }
    }
}
