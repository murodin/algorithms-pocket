import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class CountWaysToMakeArrayWithProduct {
    /*
        You are given a 2D integer array, queries. For each queries[i], where queries[i] = [ni, ki],
        find the number of different ways you can place positive integers into an array of size ni such that the product of the integers is ki.
        As the number of ways may be too large, the answer to the ith query is the number of ways modulo 109 + 7.
        Return an integer array answer where answer.length == queries.length, and answer[i] is the answer to the ith query.

        Example 1:

        Input: queries = [[2,6],[5,1],[73,660]]
        Output: [4,1,50734910]
        Explanation: Each query is independent.
        [2,6]: There are 4 ways to fill an array of size 2 that multiply to 6: [1,6], [2,3], [3,2], [6,1].
        [5,1]: There is 1 way to fill an array of size 5 that multiply to 1: [1,1,1,1,1].
        [73,660]: There are 1050734917 ways to fill an array of size 73 that multiply to 660. 1050734917 modulo 109 + 7 = 50734910.
        Example 2:

        Input: queries = [[1,1],[2,2],[3,3],[4,4],[5,5]]
        Output: [1,2,3,10,5]


        Constraints:

        1 <= queries.length <= 104
        1 <= ni, ki <= 104
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Arrays.toString(Solution.waysToFillArray(new int[][]{{1, 1}, {2, 2}, {3, 3}, {4, 4}, {5, 5}})));
    }

    // Time: O(N^2)
    // Space: O(N)
    static class Solution {
        static int modulo=1000000007;
        static Integer[] getPrimes(int max) {
            TreeSet<Integer> primes = new TreeSet<>();
            primes.add(2);
            for (int i=3; i<=max; i+=2) primes.add(i);
            Integer min = 3;
            while (min != null && min<=max) {
                for (int i=min; i*min <=max; i+=2) primes.remove(i*min);
                min = primes.ceiling(min+1);
            }

            return primes.toArray(new Integer[primes.size()]);
        }

        static int choose(int n, int k, Integer[][] memo) {
            if (n==0 || k==0 || k==n) return 1;
            if (k > n/2) return choose(n, n-k, memo);
            if (memo[n][k]!=null) return memo[n][k];
            return memo[n][k] = (choose(n-1, k, memo) + choose(n-1, k-1, memo))%modulo;
        }

        static public int[] waysToFillArray(int[][] queries) {
            int n=queries.length;
            int[] ans = new int[n];
            Integer[] pList = getPrimes(10000);
            Integer[][] memo = new Integer[10024][24];

            for (int i=0; i<queries.length; i++) {
                int[] query = queries[i];
                if (query[1]==1 || query[0]==1) ans[i]=1;
                else {
                    int idx = 0, num=query[1];
                    Map<Integer, Integer> factorMap = new HashMap<>();
                    while (pList[idx] <= num) {
                        if ( (num % pList[idx]) ==0) {
                            factorMap.merge(pList[idx], 1, (a,b)-> a+b);
                            num /= pList[idx];
                        } else idx++;
                    }
                    long result = 1;
                    for (int f: factorMap.values())
                        result = (result * choose(query[0]+f-1, f, memo)) % modulo;
                    ans[i] = (int) result;
                }
            }

            return ans;
        }
    }
}
