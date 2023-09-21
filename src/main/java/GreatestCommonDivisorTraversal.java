import java.util.*;

public class GreatestCommonDivisorTraversal {
    /*
        You are given a 0-indexed integer array nums, and you are allowed to traverse between its indices.
        You can traverse between index i and index j, i != j, if and only if gcd(nums[i], nums[j]) > 1, where gcd is the greatest common divisor.
        Your task is to determine if for every pair of indices i and j in nums, where i < j, there exists a sequence of traversals that can take us from i to j.
        Return true if it is possible to traverse between all such pairs of indices, or false otherwise.

        Example 1:

        Input: nums = [2,3,6]
        Output: true
        Explanation: In this example, there are 3 possible pairs of indices: (0, 1), (0, 2), and (1, 2).
        To go from index 0 to index 1, we can use the sequence of traversals 0 -> 2 -> 1, where we move from index 0 to index 2
        because gcd(nums[0], nums[2]) = gcd(2, 6) = 2 > 1, and then move from index 2 to index 1 because gcd(nums[2], nums[1]) = gcd(6, 3) = 3 > 1.
        To go from index 0 to index 2, we can just go directly because gcd(nums[0], nums[2]) = gcd(2, 6) = 2 > 1. Likewise, to go from index 1 to index 2,
        we can just go directly because gcd(nums[1], nums[2]) = gcd(3, 6) = 3 > 1.
        Example 2:

        Input: nums = [3,9,5]
        Output: false
        Explanation: No sequence of traversals can take us from index 0 to index 2 in this example. So, we return false.
        Example 3:

        Input: nums = [4,3,12,8]
        Output: true
        Explanation: There are 6 possible pairs of indices to traverse between: (0, 1), (0, 2), (0, 3), (1, 2), (1, 3), and (2, 3). A valid sequence of traversals exists for each pair, so we return true.


        Constraints:

        1 <= nums.length <= 105
        1 <= nums[i] <= 105
     */
    public static void main(String[] args) {
        System.out.println("Done...");
    }

    // Time: O(N^2)
    // Space: O(N)
    class Graph {
        private int n;
        private Map<Integer, List<Integer>> edges;
        private boolean[] visited;

        private void traverse(int x) {
            visited[x] = true;
            if (!edges.containsKey(x)) {
                return;
            }
            for (int y: edges.get(x)) {
                if (!visited[y]) {
                    traverse(y);
                }
            }
        }

        public Graph(int n) {
            this.n = n;
            edges = new HashMap<>();
            visited = new boolean[n];
        }

        public void addEdge(int x, int y) {
            edges.computeIfAbsent(x, value -> new ArrayList<Integer>()).add(y);
            edges.computeIfAbsent(y, value -> new ArrayList<Integer>()).add(x);
        }

        public boolean isConnected() {
            Arrays.fill(visited, false);
            traverse(0);
            int count = 0;
            for (boolean v: visited) {
                count += v ? 1 : 0;
            }
            return count == n;
        }
    }

    class Solution {
        private List<Integer> getPrimeFactors(int x) {
            List<Integer> primeFactors = new ArrayList<>();
            for (int i = 2; i * i <= x; i++) {
                if (x % i == 0) {
                    primeFactors.add(i);
                    while (x % i == 0) {
                        x /= i;
                    }
                }
            }
            if (x != 1) {
                primeFactors.add(x);
            }
            return primeFactors;
        }

        public boolean canTraverseAllPairs(int[] nums) {
            int n = nums.length;
            if (n == 1) {
                return true;
            }
            Graph g = new Graph(n);
            HashMap<Integer, Integer> seen = new HashMap<>();
            for (int i = 0; i < n; i++) {
                if (nums[i] == 1) {
                    return false;
                }
                List<Integer> primeFactors = getPrimeFactors(nums[i]);
                for (int prime: primeFactors) {
                    if (seen.containsKey(prime)) {
                        g.addEdge(i, seen.get(prime));
                    } else {
                        seen.put(prime, i);
                    }
                }
            }
            return g.isConnected();
        }
    }
}
