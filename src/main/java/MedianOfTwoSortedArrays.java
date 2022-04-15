public class MedianOfTwoSortedArrays {
    /*
        Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.
        The overall run time complexity should be O(log (m+n)).

        Example 1.
        Input: nums1 = [1,3], nums2 = [2]
        Output: 2.00000
        Explanation: merged array = [1,2,3] and median is 2.
        Example 2.
        Input: nums1 = [1,2], nums2 = [3,4]
        Output: 2.50000
        Explanation: merged array = [1,2,3,4] and median is (2 + 3) / 2 = 2.5.


        Constraints:

        nums1.length == m
        nums2.length == n
        0 <= m <= 1000
        0 <= n <= 1000
        1 <= m + n <= 2000
        -106 <= nums1[i], nums2[i] <= 106
     */

    public static void main(String[] args) {
        System.out.println("Solution I: " + Solution_I.findMedianSortedArrays(new int[] {1,2}, new int[] {4,5}));
        System.out.println("Solution II: " + Solution_II.findMedianSortedArrays(new int[] {1,2}, new int[] {4,5}));
        System.out.println("Solution III: " + Solution_III.findMedianSortedArrays(new int[] {1,2}, new int[] {4,5}));
    }

    // Time: O(NLogN)
    // Space: O(N)
    static class Solution_I {
        public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
            int n1 = nums1.length, n2 = nums2.length, total = n1 + n2;
            if(n1 == 0 || n2 == 0) {
                double sum = 0;
                if(n1 == 0) {
                    return median(n2, nums2);
                }else{
                    return median(n1, nums1);
                }
            }
            int[] temp = new int[total];
            int i = 0, j = 0, k = 0;
            while(i < n1 || j < n2) {
                if(nums1[i] > nums2[j]) {
                    temp[k++] = nums2[j++];
                } else {
                    temp[k++] = nums1[i++];
                }
                if(j == n2) {
                    while(i < n1) {
                        temp[k++] = nums1[i++];
                    }
                } if(i == n1) {
                    while(j < n2){
                        temp[k++] = nums2[j++];
                    }
                }
            }
            return median(total, temp);
        }

        private static double median(int total, int[] temp){
            double median = 0, sum = 0;
            if(total % 2 == 0) {
                int middle = total/2 - 1;
                sum = temp[middle] + temp[middle + 1];
                median = sum /2;
            } else {
                int middle = total/2;
                sum = temp[middle];
                median = sum;
            }
            return median;
        }
    }

    // Time: O(NLogN)
    // Space: O(1)
    static class Solution_II {
        public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
            if(nums2.length < nums1.length){
                return findMedianSortedArrays(nums2, nums1);
            }
            int n1 = nums1.length;
            int n2 = nums2.length;
            int low = 0, high = n1;

            while(low <= high) {
                int cut1 = (low+high)/2;
                int cut2 = (n1 + n2 +1) / 2 - cut1;

                int left1 = cut1 == 0 ? Integer.MIN_VALUE : nums1[cut1-1];
                int left2 = cut2 == 0 ? Integer.MIN_VALUE : nums2[cut2-1];

                int right1 = cut1 == n1 ? Integer.MAX_VALUE : nums1[cut1];
                int right2 = cut2 == n2 ? Integer.MAX_VALUE : nums2[cut2];


                if(left1 <= right2 && left2 <= right1)
                {
                    if( (n1 + n2) % 2 == 0 ) {
                        return (Math.max(left1, left2) + Math.min(right1, right2)) / 2.0;
                    }else{
                        return Math.max(left1, left2);
                    }
                }
                else if(left1 > right2) high = cut1 - 1;
                else low = cut1 + 1;
            }

            return 0.0;
        }
    }

    // Time: O(N)
    // Space: O(1)
    static class Solution_III {
        public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
            int[] nums = new int[nums1.length + nums2.length];
            int n = 0, m = 0;

            for(int i = 0; i < nums.length; i++){
                if(n < nums1.length && m < nums2.length)
                    nums[i] = (nums1[n] < nums2[m]) ? nums1[n++] : nums2[m++];
                else if (n < nums1.length)
                    nums[i] = nums1[n++];
                else
                    nums[i] = nums2[m++];
            }

            double median;
            if(nums.length % 2 == 0)
                median = (double)(nums[(nums.length / 2) - 1] + nums[nums.length / 2]) / 2;
            else
                median = nums[nums.length / 2];

            return median;
        }
    }
}
