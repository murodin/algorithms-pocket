import java.util.ArrayList;
import java.util.List;

public class CountTheNumberOfInfectionSequences {
    /*
        You are given an integer n and a 0-indexed integer array sick which is sorted in increasing order.
        There are n children standing in a queue with positions 0 to n - 1 assigned to them.
        The array sick contains the positions of the children who are infected with an infectious disease. An infected child
        at position i can spread the disease to either of its immediate neighboring children at positions i - 1 and i + 1
        if they exist and are currently not infected. At most one child who was previously not infected can get infected with the disease in one second.
        It can be shown that after a finite number of seconds, all the children in the queue will get infected with the disease.
        An infection sequence is the sequential order of positions in which all of the non-infected children get infected with the disease.
        Return the total number of possible infection sequences.
        Since the answer may be large, return it modulo 109 + 7.
        Note that an infection sequence does not contain positions of children who were already infected with the disease in the beginning.

        Example 1:
        Input: n = 5, sick = [0,4]
        Output: 4
        Explanation: Children at positions 1, 2, and 3 are not infected in the beginning. There are 4 possible infection sequences:
        - The children at positions 1 and 3 can get infected since their positions are adjacent to the infected children 0 and 4. The child at position 1 gets infected first.
        Now, the child at position 2 is adjacent to the child at position 1 who is infected and
        the child at position 3 is adjacent to the child at position 4 who is infected, hence either of them can get infected. The child at position 2 gets infected.
        Finally, the child at position 3 gets infected because it is adjacent to children at positions 2 and 4 who are infected. The infection sequence is [1,2,3].
        - The children at positions 1 and 3 can get infected because their positions are adjacent to the infected children 0 and 4. The child at position 1 gets infected first.
        Now, the child at position 2 is adjacent to the child at position 1 who is infected and the child at position 3 is adjacent to the child at position 4 who is infected,
        hence either of them can get infected. The child at position 3 gets infected.
        Finally, the child at position 2 gets infected because it is adjacent to children at positions 1 and 3 who are infected. The infection sequence is [1,3,2].
        - The infection sequence is [3,1,2]. The order of infection of disease in the children can be seen as: [0,1,2,3,4] => [0,1,2,3,4] => [0,1,2,3,4] => [0,1,2,3,4].
        - The infection sequence is [3,2,1]. The order of infection of disease in the children can be seen as: [0,1,2,3,4] => [0,1,2,3,4] => [0,1,2,3,4] => [0,1,2,3,4].
        Example 2:

        Input: n = 4, sick = [1]
        Output: 3
        Explanation: Children at positions 0, 2, and 3 are not infected in the beginning. There are 3 possible infection sequences:
        - The infection sequence is [0,2,3]. The order of infection of disease in the children can be seen as: [0,1,2,3] => [0,1,2,3] => [0,1,2,3] => [0,1,2,3].
        - The infection sequence is [2,0,3]. The order of infection of disease in the children can be seen as: [0,1,2,3] => [0,1,2,3] => [0,1,2,3] => [0,1,2,3].
        - The infection sequence is [2,3,0]. The order of infection of disease in the children can be seen as: [0,1,2,3] => [0,1,2,3] => [0,1,2,3] => [0,1,2,3].


        Constraints:

        2 <= n <= 105
        1 <= sick.length <= n - 1
        0 <= sick[i] <= n - 1
        sick is sorted in increasing order.
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.numberOfSequence(5, new int[]{0, 4}));
    }

    // Time: O(N^2)
    // Space: O(N)
    static class Solution {
        private static int MOD = (int) Math.pow(10, 9) + 7;
        public static int numberOfSequence(int n, int[] sick) {
            List<Integer> segments = new ArrayList<>();
            int startSegmentLen = sick[0];
            int endSegmentLen = n - sick[sick.length - 1] - 1;

            for (int i = 1; i < sick.length; i++) {
                if (sick[i] - sick[i-1] - 1 > 0) {
                    segments.add(sick[i] - sick[i-1] - 1);
                }
            }

            int[] fac = prepareFactorial(n);
            int[] pow2 = preparePow2(n);
            int totalNotSick = n - sick.length;
            int ans = fac[totalNotSick];
            ans = multiply(ans, inverse(fac[startSegmentLen]));
            ans = multiply(ans, inverse(fac[endSegmentLen]));

            for (int segment : segments) {
                ans = multiply(ans, pow2[segment - 1]);
                ans = multiply(ans, inverse(fac[segment]));
            }
            return ans;
        }

        private static int inverse(int n) {
            return exp(n, MOD - 2);
        }

        private static int exp(int base, int exp) {
            int res = 1;
            while (exp > 0) {
                if (exp % 2 == 1) {
                    res = multiply(res, base);
                }
                base = multiply(base, base);
                exp /= 2;
            }
            return res;
        }

        private static int multiply(int a, int b) {
            return (int) (((long) a * b) % MOD);
        }

        private static int[] preparePow2(int n) {
            int[] pow2 = new int[n + 1];
            pow2[0] = 1;
            for (int i = 1; i <= n; i++) {
                pow2[i] = multiply(pow2[i - 1], 2);
            }
            return pow2;
        }

        private static int[] prepareFactorial(int n) {
            int[] fac = new int[n + 1];
            fac[0] = 1;
            for (int i = 1; i <= n; i++) {
                fac[i] = multiply(fac[i - 1], i);
            }
            return fac;
        }
    }
}
