public class SumOfFlooredPairs {
    /*
        Given an integer array nums, return the sum of floor(nums[i] / nums[j]) for all pairs of indices 0 <= i, j < nums.length in the array.
        Since the answer may be too large, return it modulo 109 + 7.
        The floor() function returns the integer part of the division.

        Example 1:
        Input: nums = [2,5,9]
        Output: 10
        Explanation:
        floor(2 / 5) = floor(2 / 9) = floor(5 / 9) = 0
        floor(2 / 2) = floor(5 / 5) = floor(9 / 9) = 1
        floor(5 / 2) = 2
        floor(9 / 2) = 4
        floor(9 / 5) = 1
        We calculate the floor of the division for every pair of indices in the array then sum them up.
        Example 2:
        Input: nums = [7,7,7,7,7,7,7]
        Output: 49


        Constraints:

        1 <= nums.length <= 105
        1 <= nums[i] <= 105
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.sumOfFlooredPairs(new int[]{2,5,9}));
    }

    // Time: O(NLogN)
    // Space: O(N)
    static class Solution {
        static int sumOfFlooredPairs(int[] arr) {
            int n = arr.length;
            long ans = 0;
            long mod = 1000000007;
            int max = Integer.MIN_VALUE;

            for (int k : arr) {
                max = Math.max(k, max);
            }

            int[] freq = new int[max+1];
            for (int k : arr) {
                freq[k]++;
            }

            int[] pre = new int[max+1];
            for(int i=1; i<=max; i++){
                pre[i] = pre[i-1]+freq[i];
            }

            for(int i=1; i<=max; i++){
                if(freq[i]==0) continue;
                long sum = 0;
                for(int j=1; j*i<=max; j++){
                    int left = j*i;
                    int right = i*(j+1)-1;
                    right = Math.min(right,max);
                    int count = pre[right]-pre[left-1];
                    sum = (sum+((long) j *count)%mod)%mod;
                }
                ans = (ans + (long)(freq[i]*sum)%mod)%mod;
            }
            return (int)(ans%mod);
        }
    }
}
