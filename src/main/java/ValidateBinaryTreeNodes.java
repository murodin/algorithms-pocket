import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

public class ValidateBinaryTreeNodes {
    /*
        You have n binary tree nodes numbered from 0 to n - 1 where node i has two children leftChild[i] and rightChild[i],
        return true if and only if all the given nodes form exactly one valid binary tree.

        If node i has no left child then leftChild[i] will equal -1, similarly for the right child.

        Note that the nodes have no values and that we only use the node numbers in this problem.



        Example 1:


        Input: n = 4, leftChild = [1,-1,3,-1], rightChild = [2,-1,-1,-1]
        Output: true
        Example 2:


        Input: n = 4, leftChild = [1,-1,3,-1], rightChild = [2,3,-1,-1]
        Output: false
        Example 3:


        Input: n = 2, leftChild = [1,0], rightChild = [-1,-1]
        Output: false


        Constraints:

        n == leftChild.length == rightChild.length
        1 <= n <= 104
        -1 <= leftChild[i], rightChild[i] <= n - 1
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.validateBinaryTreeNodes(2, new int[]{1, 0}, new int[]{-1,-1}));
    }

    // Time: O(N^2LogN)
    // Space: O(N)
    static class Solution {
        public static boolean validateBinaryTreeNodes(int n, int[] leftChild, int[] rightChild) {
            Deque<Integer> queue = new ArrayDeque<>();
            Set<Integer> visited = new HashSet<>();
            int root = findRoot(n, leftChild, rightChild);
            if (root == -1) return false;

            queue.offer(root);
            while (!queue.isEmpty()) {
                int node = queue.poll();
                if (node == -1) continue;

                if (!visited.add(node)) return false;

                int left = leftChild[node];
                int right = rightChild[node];

                queue.offer(left);
                queue.offer(right);
            }

            return visited.size() == n;
        }

        private static int findRoot(int n, int[] left, int[] right) {
            Set<Integer> set = new HashSet<>();
            for (int i = 0; i < n; i++) set.add(i);
            for (int node : left) set.remove(node);
            for (int node : right) set.remove(node);
            return set.size() == 1 ? set.iterator().next() : -1;
        }
    }
}
