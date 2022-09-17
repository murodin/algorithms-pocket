import java.util.Arrays;

public class FindOriginalArrayFromDoubledArray {
    /*
        An integer array original is transformed into a doubled array changed by appending twice the value of every element in original,
        and then randomly shuffling the resulting array.
        Given an array changed, return original if changed is a doubled array. If changed is not a doubled array, return an empty array.
        The elements in original may be returned in any order.

        Example 1.
        Input: changed = [1,3,4,2,6,8]
        Output: [1,3,4]
        Explanation: One possible original array could be [1,3,4]:
        - Twice the value of 1 is 1 * 2 = 2.
        - Twice the value of 3 is 3 * 2 = 6.
        - Twice the value of 4 is 4 * 2 = 8.
        Other original arrays could be [4,3,1] or [3,1,4].
        Example 2.
        Input: changed = [6,3,0,1]
        Output: []
        Explanation: changed is not a doubled array.
        Example 3.
        Input: changed = [1]
        Output: []
        Explanation: changed is not a doubled array.


        Constraints:

        1 <= changed.length <= 105
        0 <= changed[i] <= 105
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Arrays.toString(Solution.findOriginalArray(new int[]{1, 3, 4, 2, 6, 8})));
    }

    // Time: O(N)
    // Space: O(N)
    static class Solution {
        static int[] res;
        static int index=0;
        public static int[] findOriginalArray(int[] changed) {
            if(changed.length%2==1) return new int[0];
            res = new int[changed.length/2];

            int max = 0;
            for(int num: changed) max = Math.max(max, num);
            int[] nums = new int[max+1];
            for(int num: changed) nums[num]++;

            if(nums[0]%2==1) return new int[0];
            fill(0, nums[0]/2);

            for(int i=1; i<=max/2; i++){
                if(nums[2*i] < nums[i]) return new int[0];
                nums[2*i] -= nums[i];
                fill(i, nums[i]);
            }
            return index != res.length ? new int[0] : res;
        }

        private static void fill(int num, int count){
            while(count-->0)
                res[index++] = num;
        }
    }
}
