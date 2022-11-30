import java.util.HashMap;
import java.util.HashSet;

public class UniqueNumberOfOccurrences {
    /*
        Given an array of integers arr, return true if the number of occurrences of each value in the array is unique, or false otherwise.

        Example 1.
        Input: arr = [1,2,2,1,1,3]
        Output: true
        Explanation: The value 1 has 3 occurrences, 2 has 2 and 3 has 1. No two values have the same number of occurrences.
        Example 2.
        Input: arr = [1,2]
        Output: false
        Example 3:

        Input: arr = [-3,0,1,-3,1,1,1,-3,10,0]
        Output: true


        Constraints:

        1 <= arr.length <= 1000
        -1000 <= arr[i] <= 1000
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.uniqueOccurrences(new int[]{-3,0,1,-3,1,1,1,-3,10,0}));
    }

    // Time: O(N)
    // Space: O(N)
    static class Solution {
        public static boolean uniqueOccurrences(int[] arr) {
            HashMap<Integer,Integer> cnt = new HashMap<>();
            for (int n : arr)
                cnt.put(n, cnt.getOrDefault(n, 0)+1);
            HashSet<Integer> unq = new HashSet<>(cnt.values());
            return unq.size() == cnt.size();
        }
    }
}
