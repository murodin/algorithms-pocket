public class MinimumNumberOfRemovalsToMakeMountainArray {
    /*
        You may recall that an array arr is a mountain array if and only if:

        arr.length >= 3
        There exists some index i (0-indexed) with 0 < i < arr.length - 1 such that:
        arr[0] < arr[1] < ... < arr[i - 1] < arr[i]
        arr[i] > arr[i + 1] > ... > arr[arr.length - 1]
        Given an integer array nums​​​, return the minimum number of elements to remove to make nums​​​ a mountain array.



        Example 1:

        Input: nums = [1,3,1]
        Output: 0
        Explanation: The array itself is a mountain array so we do not need to remove any elements.
        Example 2:

        Input: nums = [2,1,1,5,6,2,3,1]
        Output: 3
        Explanation: One solution is to remove the elements at indices 0, 1, and 5, making the array nums = [1,5,6,3,1].


        Constraints:

        3 <= nums.length <= 1000
        1 <= nums[i] <= 109
        It is guaranteed that you can make a mountain array out of nums.
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.minimumMountainRemovals(new int[]{2,1,1,5,6,2,3,1}));
    }

    // Time: O(N^2)
    // Space: O(N)
    static class Solution {
        public static int minimumMountainRemovals(int[] nums) {
            int n=nums.length;
            int[] left =new int[n];
            for(int i=0;i<n;i++) {
                left[i]=1;
                for(int j=0;j<i;j++) {
                    if(nums[i]>nums[j]) {
                        left[i]=Math.max(left[i],left[j]+1);
                    }
                }
            }
            int[] right =new int[n];
            for(int i=n-1;i>=0;--i) {
                right[i]=1;
                for(int j=n-1;j>i;--j) {
                    if(nums[i]>nums[j]) {
                        right[i]=Math.max(right[i],right[j]+1);
                    }
                }
            }
            int maxLen=0;
            for(int i=0;i<n;++i) {
                if(left[i]>=2 && right[i]>=2) {
                    int len=left[i]+right[i]-1;
                    maxLen=Math.max(maxLen,len);
                }
            }
            return n-maxLen;
        }
    }
}
