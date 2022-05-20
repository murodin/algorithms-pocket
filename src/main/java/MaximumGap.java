import java.util.Arrays;

public class MaximumGap {
    /*
        Given an integer array nums, return the maximum difference between two successive elements in its sorted form. If the array contains less than two elements, return 0.
        You must write an algorithm that runs in linear time and uses linear extra space.

        Example 1.
        Input: nums = [3,6,9,1]
        Output: 3
        Explanation: The sorted form of the array is [1,3,6,9], either (3,6) or (6,9) has the maximum difference 3.
        Example 2.
        Input: nums = [10]
        Output: 0
        Explanation: The array contains less than 2 elements, therefore return 0.


        Constraints:

        1 <= nums.length <= 105
        0 <= nums[i] <= 109
     */

    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.maximumGap(new int[] {3,6,9,1}));
    }

    // Time:
    // Space:
    static class Solution {
        public static int maximumGap(int[] nums) {
            if(nums.length <= 1) return 0;
            if(nums.length == 2) return Math.abs(nums[1] - nums[0]);
            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;

            for(int n: nums){
                min = Math.min(min, n);
                max = Math.max(max,n);
            }
            int maxDiff = max-min;

        /* Now we know the max diff b/w any numbers could be maxDiff
           1 min and 1 max element, remaining elemenet (N-2). Lets try assign them to N-1 buckets
           As ex if we have total 6(N) elements (we have figured out 1 min and 1 max element) so remaining 4(N-2) elements, lets place them in 5(N-1) buckets. As bucket is 1 extra than total remaining element so we will have for sure 1 emoty bucket. So now the answer would be gap b/w max element from left bucket and min element from right bucket of empty bucket.
        */
            int totalNoBucket = nums.length-1;
            int singleBucketSize = (int)Math.ceil((double)(maxDiff)/(totalNoBucket));

            // Now keep 2 arrays/bucket to keep track of min and max element in each bucket
            int[] minBucket = new int[totalNoBucket];
            Arrays.fill(minBucket, Integer.MAX_VALUE);
            int[] maxBucket = new int[totalNoBucket];
            Arrays.fill(maxBucket, Integer.MIN_VALUE);

            for(int n: nums){
                if(n == min || n == max) continue; // Only process for remaining N-2 elements which are not min/max
                int currEleBucketIdx = (n-min)/singleBucketSize;
                int currMin = minBucket[currEleBucketIdx];
                int currMax = maxBucket[currEleBucketIdx];
                minBucket[currEleBucketIdx] = Math.min(currMin,n);
                maxBucket[currEleBucketIdx] = Math.max(currMax,n);
            }
            // Now we have min/max element amongst all element in a bucket
            int res = Integer.MIN_VALUE;
            int prevMax = min;
            for(int i = 0; i < totalNoBucket; i++){
                int currMin = minBucket[i];
                if(currMin != Integer.MAX_VALUE){
                    int currMax = maxBucket[i];
                    res = Math.max(res, currMin-prevMax);
                    prevMax = currMax;
                }
            }
            // if we did not find max gap and res=0, check the final gap
            return Math.max(res, max-prevMax);
        }
    }
}
