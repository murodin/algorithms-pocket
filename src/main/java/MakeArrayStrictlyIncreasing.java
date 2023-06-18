import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MakeArrayStrictlyIncreasing {
    /*
        Given two integer arrays arr1 and arr2, return the minimum number of operations (possibly zero) needed to make arr1 strictly increasing.
        In one operation, you can choose two indices 0 <= i < arr1.length and 0 <= j < arr2.length and do the assignment arr1[i] = arr2[j].
        If there is no way to make arr1 strictly increasing, return -1.


        Example 1:

        Input: arr1 = [1,5,3,6,7], arr2 = [1,3,2,4]
        Output: 1
        Explanation: Replace 5 with 2, then arr1 = [1, 2, 3, 6, 7].
        Example 2:

        Input: arr1 = [1,5,3,6,7], arr2 = [4,3,1]
        Output: 2
        Explanation: Replace 5 with 3 and then replace 3 with 4. arr1 = [1, 3, 4, 6, 7].
        Example 3:

        Input: arr1 = [1,5,3,6,7], arr2 = [1,6,3,3]
        Output: -1
        Explanation: You can't make arr1 strictly increasing.


        Constraints:

        1 <= arr1.length, arr2.length <= 2000
        0 <= arr1[i], arr2[i] <= 10^9
     */
    public static void main(String[] args) {
        System.out.println("Solution " + Solution.makeArrayIncreasing(new int[]{1,5,3,6,7}, new int[]{4,3,1}));
    }

    // Time: O(N^2)
    // Space: O(N)
    static class Solution {
        public static int makeArrayIncreasing(int[] arr1, int[] arr2) {
            int n = arr1.length;
            Arrays.sort(arr2);
            List<Integer> uniqueArr2 = new ArrayList<>();
            for (int i = 0; i < arr2.length; i++) {
                if (i + 1 < arr2.length && arr2[i] == arr2[i + 1]) {
                    continue;
                }
                uniqueArr2.add(arr2[i]);
            }
            int[] newArr2 = new int[uniqueArr2.size()];
            for (int i = 0; i < uniqueArr2.size(); i++) {
                newArr2[i] = uniqueArr2.get(i);
            }
            arr2 = newArr2;

            int[] newArr1 = new int[n + 2];
            System.arraycopy(arr1, 0, newArr1, 1, n);
            newArr1[n + 1] = Integer.MAX_VALUE;
            newArr1[0] = Integer.MIN_VALUE;
            arr1 = newArr1;

            int[] dp = new int[n + 2];
            Arrays.fill(dp, Integer.MAX_VALUE);
            dp[0] = 0;
            for (int i = 1; i < n + 2; i++) {
                for (int j = 0; j < i; j++) {
                    if (arr1[j] < arr1[i] && dp[j] != Integer.MAX_VALUE) {
                        int change = check(arr1, arr2, j, i);
                        if (change >= 0) {
                            dp[i] = Math.min(dp[i], dp[j] + change);
                        }
                    }
                }
            }
            return dp[n + 1] == Integer.MAX_VALUE ? -1 : dp[n + 1];
        }

        private static int check(int[] arr1, int[] arr2, int start, int end) {
            if (start + 1 == end) {
                return 0;
            }
            int min = arr1[start];
            int max = arr1[end];
            int idx = Arrays.binarySearch(arr2, min);
            if (idx < 0) {
                idx = -idx - 1;
            } else {
                idx = idx + 1;
            }

            int maxCount = end - start - 1;
            int endIdx = idx + maxCount - 1;
            if (endIdx < arr2.length && arr2[endIdx] < max) {
                return maxCount;
            } else {
                return -1;
            }
        }
    }
}
