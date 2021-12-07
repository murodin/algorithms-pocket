public class KthMissingPositiveNumber {

    /*
        Given an array arr of positive integers sorted in a strictly increasing order, and an integer k.
        Find the kth positive integer that is missing from this array.

        Example 1.
        Input: arr = [2,3,4,7,11], k = 5
        Output: 9
        Explanation: The missing positive integers are [1,5,6,8,9,10,12,13,...]. The 5th missing positive integer is 9.
        Example 2.
        Input: arr = [1,2,3,4], k = 2
        Output: 6
        Explanation: The missing positive integers are [5,6,7,...]. The 2nd missing positive integer is 6.

        Constraints:
            1 <= arr.length <= 1000
            1 <= arr[i] <= 1000
            1 <= k <= 1000
            arr[i] < arr[j] for 1 <= i < j <= arr.length
     */

    public static void main(String[] args) {
        System.out.println("Kth Missing Positive Number: " + Solution.findKthPositive(new int[]{2,3,4,7,11}, 5));
    }

    // Time: O(LogN)
    // Space: O(1)
    static class Solution {
        public static int findKthPositive(int[] arr, int k) {
            int n = arr.length;
            int left = 0, right = n-1;
            int missing  = compute(arr[n-1], n);
            while(left <= right) {
                int mid = left + (right-left)/2;
                missing = compute(arr[mid], mid+1);

                if(missing >= k) right = mid-1;
                else left = mid+1;
            }

            // Right -> -1;
            if(right == -1) return k;
            return arr[right] + k-compute(arr[right], right+1);

        }

        static int compute(int actual, int expected){
            return actual - expected;
        }
    }
}
