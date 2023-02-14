import java.util.ArrayList;
import java.util.HashSet;

public class NumbersWithRepeatedDigits {
    /*
        Given an integer n, return the number of positive integers in the range [1, n] that have at least one repeated digit.

        Example 1.
        Input: n = 20
        Output: 1
        Explanation: The only positive number (<= 20) with at least 1 repeated digit is 11.
        Example 2.
        Input: n = 100
        Output: 10
        Explanation: The positive numbers (<= 100) with atleast 1 repeated digit are 11, 22, 33, 44, 55, 66, 77, 88, 99, and 100.
        Example 3.
        Input: n = 1000
        Output: 262


        Constraints:

        1 <= n <= 109
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.numDupDigitsAtMostN(100));
    }

    // Time: O(N^2)
    // Space: O(N)
    static class Solution {
        public static int numDupDigitsAtMostN(int N) {
            // Transform N + 1 to arrayList
            ArrayList<Integer> L = new ArrayList<Integer>();
            for (int x = N + 1; x > 0; x /= 10)
                L.add(0, x % 10);

            int res = 0, n = L.size();
            for (int i = 1; i < n; ++i)
                res += 9 * A(9, i - 1);

            HashSet<Integer> seen = new HashSet<>();
            for (int i = 0; i < n; ++i) {
                for (int j = i > 0 ? 0 : 1; j < L.get(i); ++j)
                    if (!seen.contains(j))
                        res += A(9 - i, n - i - 1);
                if (seen.contains(L.get(i))) break;
                seen.add(L.get(i));
            }
            return N - res;
        }
        public static int A(int m, int n) {
            return n == 0 ? 1 : A(m, n - 1) * (m - n + 1);
        }
    }
}
