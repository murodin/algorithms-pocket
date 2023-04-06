import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MinimumAbsoluteDifference {
    /*
        Given an array of distinct integers arr, find all pairs of elements with the minimum absolute difference of any two elements.
        Return a list of pairs in ascending order(with respect to pairs), each pair [a, b] follows
        a, b are from arr
        a < b
        b - a equals to the minimum absolute difference of any two elements in arr


        Example 1.
        Input: arr = [4,2,1,3]
        Output: [[1,2],[2,3],[3,4]]
        Explanation: The minimum absolute difference is 1. List all pairs with difference equal to 1 in ascending order.
        Example 2.
        Input: arr = [1,3,6,10,15]
        Output: [[1,3]]
        Example 3.
        Input: arr = [3,8,-10,23,19,-4,-14,27]
        Output: [[-14,-10],[19,23],[23,27]]


        Constraints:

        2 <= arr.length <= 105
        -106 <= arr[i] <= 106
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.minimumAbsDifference(new int[]{3,8,-10,23,19,-4,-14,27}));
    }

    // Time: O(N)
    // Space: O(1)
    static class Solution {
        public static List<List<Integer>> minimumAbsDifference(int[] arr) {
            Arrays.sort(arr);
            int minD = IntStream.range(1, arr.length)
                    .map(t -> arr[t] - arr[t - 1])
                     .reduce(Integer::min).getAsInt();
            return IntStream.range(1 , arr.length)
                    .filter(t -> arr[t] - arr[t - 1] == minD)
                    .mapToObj(t -> Arrays.asList(arr[t - 1], arr[t]))
                    .collect(Collectors.toList());
        }
    }
}
