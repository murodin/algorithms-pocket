import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.IntStream;

public class DiagonalTraverseII {
    /*
        Given a 2D integer array nums, return all elements of nums in diagonal order as shown in the below images.
        Example 1:
        Input: nums = [[1,2,3],[4,5,6],[7,8,9]]
        Output: [1,4,2,7,5,3,8,6,9]
        Example 2:
        Input: nums = [[1,2,3,4,5],[6,7],[8],[9,10,11],[12,13,14,15,16]]
        Output: [1,6,2,8,7,3,9,4,12,10,5,13,11,14,15,16]


        Constraints:

        1 <= nums.length <= 105
        1 <= nums[i].length <= 105
        1 <= sum(nums[i].length) <= 105
        1 <= nums[i][j] <= 105
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Arrays.toString(Solution.findDiagonalOrder(List.of(List.of(1, 2, 3), List.of(4, 5, 6), List.of(7, 8, 9)))));
    }

    // Time: O(NLogN)
    // Space: O(1)
    static class Solution {
        public static int[] findDiagonalOrder(List<List<Integer>> nums) {
            return IntStream.range(0, nums.size())
                    .mapToObj(i->IntStream.range(0, nums.get(i).size()).mapToObj(j->new int[]{nums.get(i).get(j), i, j}))
                    .flatMap(Function.identity())
                    .sorted(Comparator.comparingInt(a -> a[2]))
                    .sorted((a, b) -> Integer.compare(b[1], a[1]))
                    .sorted(Comparator.comparingInt(a -> a[1] + a[2]))
                    .mapToInt(i->i[0]).toArray();
        }
    }
}
