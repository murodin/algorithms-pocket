import java.util.Arrays;
import java.util.PriorityQueue;

public class KthLargestElementInAnArray {

    /*
        Given an integer array nums and an integer k, return the kth largest element in the array.
        Note that it is the kth largest element in the sorted order, not the kth distinct element.

        Example 1.
        Input: nums = [3,2,1,5,6,4], k = 2
        Output: 5
        Example 2.
        Input: nums = [3,2,3,1,2,4,5,5,6], k = 4
        Output: 4
     */

    public static void main(String[] args) {
        System.out.println("Solution I: " + Solution_I.findKthLargest(new int[]{3,2,1,5,6,4}, 2));
        System.out.println("Solution II: " + Solution_II.findKthLargest(new int[]{3,2,1,5,6,4}, 2));
        System.out.println("Solution III: " + Solution_III.findKthLargest(new int[]{3,2,1,5,6,4}, 2));
    }

    // Time: O(NLogN)
    // Space: O(1)
    static class Solution_I {
        public static int findKthLargest(int[] nums, int k) {
            Arrays.sort(nums);
            return nums[nums.length-k];
        }
    }

    // Time: O(NLogK)
    // Space: O(K)
    static class Solution_II {
        public static int findKthLargest(int[] nums, int k) {
            PriorityQueue<Integer> pq=new PriorityQueue<>(k+1);

            for(int i:nums){
                pq.add(i);
                if(pq.size()>k){
                    pq.poll();
                }
            }

            return pq.poll();
        }
    }

    // Time: O(N)
    // Space: O(1)
    static class Solution_III {
        public static int findKthLargest(int[] nums, int k) {
            return quickselect(nums,0,nums.length-1,k);
        }

        static int quickselect(int[] nums,int left,int right,int k){
            int pivot = left;
            for(int i=left; i<right; i++){
                if(nums[i] <= nums[right]){
                    swap(nums, pivot++, i);
                }
            }
            swap(nums, pivot, right);
            int count = right-pivot+1;

            if(count == k){
                return nums[pivot];
            } else if(count > k) {
                return quickselect(nums,pivot+1,right,k);
            }

            return quickselect(nums,left,pivot-1,k-count);
        }

        static void swap(int[] nums,int l,int r){
            int temp = nums[l];
            nums[l] = nums[r];
            nums[r] = temp;
        }
    }
}
