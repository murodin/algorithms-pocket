public class CircularArrayLoop {
    /*
        You are playing a game involving a circular array of non-zero integers nums. Each nums[i] denotes the number of indices forward/backward
        you must move if you are located at index i:

        If nums[i] is positive, move nums[i] steps forward, and
        If nums[i] is negative, move nums[i] steps backward.
        Since the array is circular, you may assume that moving forward from the last element puts you on the first element,
        and moving backwards from the first element puts you on the last element.

        A cycle in the array consists of a sequence of indices seq of length k where:

        Following the movement rules above results in the repeating index sequence seq[0] -> seq[1] -> ... -> seq[k - 1] -> seq[0] -> ...
        Every nums[seq[j]] is either all positive or all negative.
        k > 1
        Return true if there is a cycle in nums, or false otherwise.



        Example 1:


        Input: nums = [2,-1,1,2,2]
        Output: true
        Explanation: The graph shows how the indices are connected. White nodes are jumping forward, while red is jumping backward.
        We can see the cycle 0 --> 2 --> 3 --> 0 --> ..., and all of its nodes are white (jumping in the same direction).
        Example 2:


        Input: nums = [-1,-2,-3,-4,-5,6]
        Output: false
        Explanation: The graph shows how the indices are connected. White nodes are jumping forward, while red is jumping backward.
        The only cycle is of size 1, so we return false.
        Example 3:


        Input: nums = [1,-1,5,1,4]
        Output: true
        Explanation: The graph shows how the indices are connected. White nodes are jumping forward, while red is jumping backward.
        We can see the cycle 0 --> 1 --> 0 --> ..., and while it is of size > 1, it has a node jumping forward and a node jumping backward, so it is not a cycle.
        We can see the cycle 3 --> 4 --> 3 --> ..., and all of its nodes are white (jumping in the same direction).


        Constraints:

        1 <= nums.length <= 5000
        -1000 <= nums[i] <= 1000
        nums[i] != 0


        Follow up: Could you solve it in O(n) time complexity and O(1) extra space complexity?
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.circularArrayLoop(new int[]{1,-1,5,1,4}));
    }

    // Time: O(N^2)
    // Space: O(1)
    static class Solution {
        public static boolean circularArrayLoop(int[] nums) {
            for (var i = 0; i < nums.length; i++) {
                var direction = Math.signum(nums[i]);
                var slow = i;
                var fast = i;

                do {
                    slow = getNextIndex(nums, direction, slow);
                    fast = getNextIndex(nums, direction, fast);
                    if (fast != -1)
                        fast = getNextIndex(nums, direction, fast);
                    if (fast == -1 || nums[slow] == 0 || nums[fast] == 0)
                        break;
                } while (slow != fast);

                if (slow != -1 && slow == fast)
                    return true;
                nums[i] = 0;
            }
            return false;
        }
        /**
         * Math.signum(x) will return 1, -1, 0 depending on whether x is positive, negative or zero.
         * value of currentDirection * direction can be:
         * < 0, means that currentDirection and direction are of opposite signs, and thus opposite in nature, e.g. left/right, right/left
         * > 0, means they're of the same sign, e.g. left/left, right/right
         *  0, means at least one of them is zero. For this problem, its not a possibility to have 0 as direction
         */
        private static int getNextIndex(int[] nums, float direction, int i) {
            var currentDirection = Math.signum(nums[i]);
            if (currentDirection * direction < 0)
                return -1;
            var n = nums.length;
            var nextIndex = (i + nums[i]) % n;
            if (nextIndex < 0)
                nextIndex += n;
            return nextIndex == i ? -1 : nextIndex;
        }
    }
}
