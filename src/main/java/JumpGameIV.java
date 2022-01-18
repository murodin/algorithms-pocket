import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class JumpGameIV {
    /*
        Given an array of integers arr, you are initially positioned at the first index of the array.
        In one step you can jump from index i to index:
        i + 1 where: i + 1 < arr.length.
        i - 1 where: i - 1 >= 0.
        j where: arr[i] == arr[j] and i != j.
        Return the minimum number of steps to reach the last index of the array.

        Notice that you can not jump outside of the array at any time.

        Example 1.
        Input: arr = [100,-23,-23,404,100,23,23,23,3,404]
        Output: 3
        Explanation: You need three jumps from index 0 --> 4 --> 3 --> 9. Note that index 9 is the last index of the array.
        Example 2.
        Input: arr = [7]
        Output: 0
        Explanation: Start index is the last index. You do not need to jump.
        Example 3:

        Input: arr = [7,6,9,6,9,6,9,7]
        Output: 1
        Explanation: You can jump directly from index 0 to index 7 which is last index of the array.
     */

    public static void main(String[] args) {
        int[] arr = {100,-23,-23,404,100,23,23,23,3,404};
        System.out.println("Min Jump: " + Solution.minJumps(arr));
    }

    // Time: O(N)
    // Space: O(N)
    static class Solution {
        public static int minJumps(int[] arr) {
            int n = arr.length;
            if(n == 0) return 0;

            int step = 0;
            Map<Integer, List<Integer>> map = new HashMap<>();

            AtomicInteger index = new AtomicInteger(0);
            Arrays.stream(arr).forEach(a -> {
                map.computeIfAbsent(a, v -> new ArrayList<>()).add(index.getAndIncrement());
            });

            Queue<Integer> queue = new LinkedList<>();
            queue.offer(0);

            while (!queue.isEmpty()) {
                step++;
                int size = queue.size();

                for(int i=0; i<size; i++) {
                    int j = queue.poll();

                    // jump to j - 1
                    if(j-1 >= 0 && map.containsKey(arr[j-1]))
                        queue.offer(j-1);

                    // jump to j + 1
                    if(j+1 <= n && map.containsKey(arr[j+1])) {
                        if(j+1 == n-1) return step;
                        queue.offer(j+1);
                    }

                    // jump to k --> arr[j] == arr[k]
                    if(map.containsKey(arr[j])) {
                        for(int k : map.get(arr[j])) {
                            if(k != j) {
                                if (k == n - 1) return step;
                                queue.offer(k);
                            }
                        }
                    }
                    map.remove(arr[j]);
                }
            }
            return step;
        }
    }
}
