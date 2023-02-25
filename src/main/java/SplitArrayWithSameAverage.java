import java.util.HashMap;

public class SplitArrayWithSameAverage {
    /*
        You are given an integer array nums.
        You should move each element of nums into one of the two arrays A and B such that A and B are non-empty, and average(A) == average(B).
        Return true if it is possible to achieve that and false otherwise.
        Note that for an array arr, average(arr) is the sum of all the elements of arr over the length of arr.

        Example 1.
        Input: nums = [1,2,3,4,5,6,7,8]
        Output: true
        Explanation: We can split the array into [1,4,5,8] and [2,3,6,7], and both of them have an average of 4.5.
        Example 2.
        Input: nums = [3,1]
        Output: false


        Constraints:

        1 <= nums.length <= 30
        0 <= nums[i] <= 104
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.splitArraySameAverage(new int[]{1,2,3,4,5,6,7,8}));
    }

    // Time: O(N)
    // Space: O(N)
    static class Solution {
        private static HashMap<String, Boolean> map;
        public static boolean splitArraySameAverage(int[] arr){
            map = new HashMap<>();
            int n = arr.length, total = 0;
            for(int i: arr) total+=i;
            for(int i=1; i<n; i++){
                int sum = (i*total)/n;
                if((i*total)%n!=0) continue;

                if(canSplit(i,arr,sum,n)) return true;
            }
            return false;
        }

        public static boolean canSplit(int len, int[] arr, float sum, int n){
            StringBuffer sb = new StringBuffer();
            sb.append(String.valueOf(len));
            sb.append(" ");
            sb.append(String.valueOf(sum));
            sb.append(" ");
            sb.append(String.valueOf(n));
            String key = sb.toString();
            if(n==0){
                return sum == 0 && len == 0;
            }else if(len==0){
                return sum == 0;
            }
            if(map.containsKey(key)) return map.get(key);
            boolean val = (canSplit(len-1,arr,sum-arr[n-1],n-1)) || (canSplit(len,arr,sum,n-1));
            map.put(key, val);
            return map.get(key);
        }
    }
}
