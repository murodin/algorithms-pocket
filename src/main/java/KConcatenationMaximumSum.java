import java.util.ArrayList;
import java.util.List;

public class KConcatenationMaximumSum {
    /*
        Given an integer array arr and an integer k, modify the array by repeating it k times.
        For example, if arr = [1, 2] and k = 3 then the modified array will be [1, 2, 1, 2, 1, 2].
        Return the maximum sub-array sum in the modified array. Note that the length of the sub-array can be 0 and its sum in that case is 0.
        As the answer can be very large, return the answer modulo 109 + 7.

        Example 1:

        Input: arr = [1,2], k = 3
        Output: 9
        Example 2:

        Input: arr = [1,-2,1], k = 5
        Output: 2
        Example 3:

        Input: arr = [-1,-2], k = 7
        Output: 0


        Constraints:

        1 <= arr.length <= 105
        1 <= k <= 105
        -104 <= arr[i] <= 104
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.kConcatenationMaxSum(new int[]{1,-2,1}, 5));
    }

    // Time: O(N)
    // Space: O(N)
    static class Solution {
        public static int kConcatenationMaxSum(int[] arr, int k) {
            List<Integer> l=new ArrayList<>();
            long mod=(long)(1e9)+7;
            long sum=0;
            for(int x:arr){
                sum+=(long)x;
                l.add(x);
            }

            if(k==1){
                long z=kadanes(l);
                return (int)(z%mod);
            } else if(sum<0){
                for(int x:arr)l.add(x);
                long z=kadanes(l);
                return (int)(z%mod);
            } else{
                for(int x:arr)l.add(x);
                long z=kadanes(l);
                long p1=z+(k-2)*sum;
                return (int)(p1%mod);
            }

        }
        public static long kadanes(List<Integer> l){
            long prev=0;long max=0;
            for (Integer integer : l) {
                prev = Math.max(prev + (long) integer, (long) integer);
                max = Math.max(max, prev);
            }
            return max;
        }
    }
}
