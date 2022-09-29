import java.util.ArrayList;
import java.util.List;

public class FindKClosestElements {
    /*
        Given a sorted integer array arr, two integers k and x, return the k closest integers to x in the array.
         The result should also be sorted in ascending order.
        An integer a is closer to x than an integer b if:

        |a - x| < |b - x|, or
        |a - x| == |b - x| and a < b

        Example 1.
        Input: arr = [1,2,3,4,5], k = 4, x = 3
        Output: [1,2,3,4]
        Example 2:
        Input: arr = [1,2,3,4,5], k = 4, x = -1
        Output: [1,2,3,4]


        Constraints:

        1 <= k <= arr.length
        1 <= arr.length <= 104
        arr is sorted in ascending order.
        -104 <= arr[i], x <= 104
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.findClosestElements(new int[]{1,2,3,4,5}, 4, 3));
    }

    // Time: O(N)
    // Space: O(N)
    static class Solution {
        public static List<Integer> findClosestElements(int[] arr, int k, int x) {
            List<Integer>ans=new ArrayList();
            int n=arr.length;
            int start=0;
            int end=n-1;
            while(start<=end && end-start+1>k){
                int a=Math.abs(arr[start]-x);
                int b=Math.abs(arr[end]-x);
                if(a>b){
                    start++;
                }else{
                    end--;
                }
            }

            for(int i=start;i<=end;i++){
                ans.add(arr[i]);
            }
            return ans;
        }
    }
}
