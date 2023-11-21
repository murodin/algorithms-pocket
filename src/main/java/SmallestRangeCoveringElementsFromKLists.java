import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

public class SmallestRangeCoveringElementsFromKLists {
    /*
        You have k lists of sorted integers in non-decreasing order. Find the smallest range that includes at least one number from each of the k lists.

        We define the range [a, b] is smaller than range [c, d] if b - a < d - c or a < c if b - a == d - c.

        Example 1:
        Input: nums = [[4,10,15,24,26],[0,9,12,20],[5,18,22,30]]
        Output: [20,24]
        Explanation:
        List 1: [4, 10, 15, 24,26], 24 is in range [20,24].
        List 2: [0, 9, 12, 20], 20 is in range [20,24].
        List 3: [5, 18, 22, 30], 22 is in range [20,24].
        Example 2:
        Input: nums = [[1,2,3],[1,2,3],[1,2,3]]
        Output: [1,1]


        Constraints:

        nums.length == k
        1 <= k <= 3500
        1 <= nums[i].length <= 50
        -105 <= nums[i][j] <= 105
        nums[i] is sorted in non-decreasing order.
     */
    public static void main(String[] args) {
        System.out.println("Solution: " +
                Arrays.toString(Solution.smallestRange(List.of(List.of(4, 10, 15, 24, 26), List.of(0, 9, 12, 20), List.of(5, 18, 22, 30)))));
    }

    // Time: O(NLogN)
    // Space: O(N)
    static class Solution {
        public static int[] smallestRange(List<List<Integer>> nums) {
            TreeMap<Integer, List<Integer>> map = new TreeMap();
            for(int i = 0; i < nums.size(); i++) {
                for(int n : nums.get(i)) {
                    if(!map.containsKey(n)) map.put(n, new ArrayList<>());
                    map.get(n).add(i);
                }
            }
            List<Integer> list = new ArrayList(map.keySet());
            int l = 0, r = 0;
            int[] ans = new int[]{ list.get(0), list.get(list.size()-1) };
            int[] cnt = new int[nums.size()];
            while(l < list.size()) {
                while(r < list.size() && !allIn(cnt)) {
                    for(int m : map.get(list.get(r))) cnt[m]++;
                    r++;
                }
                if(allIn(cnt) && (list.get(r-1) - list.get(l)) < (ans[1] - ans[0])) {
                    ans = new int[]{ list.get(l), list.get(r-1) };
                }
                for(int m : map.get(list.get(l))) cnt[m]--;
                l++;
            }
            return ans;
        }
        private static boolean allIn(int[] cnt) {
            for(int c : cnt) {
                if(c == 0) return false;
            }
            return true;
        }
    }
}
