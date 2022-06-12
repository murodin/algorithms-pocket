import java.util.HashMap;
import java.util.Map;

public class MaximumErasureValue {

    /*
        You are given an array of positive integers nums and want to erase a subarray containing unique elements.
        The score you get by erasing the subarray is equal to the sum of its elements.
        Return the maximum score you can get by erasing exactly one subarray.
        An array b is called to be a subarray of a if it forms a contiguous subsequence of a, that is, if it is equal to a[l],a[l+1],...,a[r] for some (l,r).

        Example 1.
        Input: nums = [4,2,4,5,6]
        Output: 17
        Explanation: The optimal subarray here is [2,4,5,6].
        Example 2.
        Input: nums = [5,2,1,2,5,2,1,2,5]
        Output: 8
        Explanation: The optimal subarray here is [5,2,1] or [1,2,5].


        Constraints:

        1 <= nums.length <= 105
        1 <= nums[i] <= 104
     */

    public static void main(String[] args) {
        int[] nums = {5,2,1,2,5,2,1,2,5};
        System.out.println("Solution I: " + Solution_I.maximumUniqueSubarray(nums));
        System.out.println("Solution II: " + Solution_II.maximumUniqueSubarray(nums));
    }

    // Time: O(N)
    // Space: O(N)
    static class Solution_I {
        public static int maximumUniqueSubarray(int[] A) {
            int n = A.length;
            Map<Integer, Integer> map = new HashMap<>(); // element, count

            int j = 0, sum = 0, res = 0;
            for (int k : A) {
                map.put(k, map.getOrDefault(k, 0) + 1);
                sum += k;

                while (map.get(k) > 1) {
                    map.put(A[j], map.get(A[j]) - 1);
                    sum -= A[j];
                    j++;
                }
                res = Math.max(res, sum);
            }
            return res;
        }
    };

    // Time: O(N)
    // Space: O(1)
    static class Solution_II {
        public static int maximumUniqueSubarray(int[] nums) {
            int len = nums.length;
            int[] freqNums = new int[10001];
            int repeatedNums = 0;
            int maxSubArraySum = 0, currSum = 0;
            int start = 0;
            for(int end = 0; end<len; end++) {
                currSum += nums[end];
                freqNums[nums[end]]++;

                if(freqNums[nums[end]] == 2) {
                    repeatedNums++;
                }

                while(start<end && repeatedNums !=0) {
                    freqNums[nums[start]]--;
                    currSum -= nums[start];

                    if(freqNums[nums[start]] == 1) {
                        repeatedNums--;
                    }
                    start++;
                }
                maxSubArraySum = Math.max(maxSubArraySum, currSum);
            }
            return maxSubArraySum;
        }
    };
}
