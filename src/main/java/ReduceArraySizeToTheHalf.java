import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReduceArraySizeToTheHalf {
    /*
        You are given an integer array arr. You can choose a set of integers and remove all the occurrences of these integers in the array.
        Return the minimum size of the set so that at least half of the integers of the array are removed.

        Example 1.
        Input: arr = [3,3,3,3,5,5,5,2,2,7]
        Output: 2
        Explanation: Choosing {3,7} will make the new array [5,5,5,2,2] which has size 5 (i.e equal to half of the size of the old array).
        Possible sets of size 2 are {3,5},{3,2},{5,2}.
        Choosing set {2,7} is not possible as it will make the new array [3,3,3,3,5,5,5] which has a size greater than half of the size of the old array.
        Example 2.
        Input: arr = [7,7,7,7,7,7]
        Output: 1
        Explanation: The only possible set you can choose is {7}. This will make the new array empty.


        Constraints:

        2 <= arr.length <= 105
        arr.length is even.
        1 <= arr[i] <= 105
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.minSetSize(new int[] {3,3,3,3,5,5,5,2,2,7}));
    }

    // Time: O(N)
    // Space: O(N)
    static class Solution {
        public static int minSetSize(int[] arr) {
            Map<Integer, Integer> map = new HashMap<>();
            List[] list = new ArrayList[arr.length + 1];

            for (int num : arr) {
                map.put(num, map.getOrDefault(num, 0) + 1);
            }

            for (int num : map.keySet()) {
                int count = map.get(num);
                if (list[count] == null) {
                    list[count] = new ArrayList<Integer>();
                }
                list[count].add(num);
            }

            int steps = 0, res = 0;
            for (int i = arr.length; i > 0; i--) {
                List<Integer> cur = list[i];
                if (cur == null || cur.size() == 0) continue;
                for (int num : cur) {
                    steps += i;
                    res++;
                    if (steps >= arr.length / 2)
                        return res;
                }
            }
            return arr.length;
        }
    }
}
