public class KthAncestorOfATreeNode {
    /*
        You are given a tree with n nodes numbered from 0 to n - 1 in the form of a parent array parent where parent[i] is the parent of ith node.
        The root of the tree is node 0. Find the kth ancestor of a given node.

        The kth ancestor of a tree node is the kth node in the path from that node to the root node.

        Implement the TreeAncestor class:

        TreeAncestor(int n, int[] parent) Initializes the object with the number of nodes in the tree and the parent array.
        int getKthAncestor(int node, int k) return the kth ancestor of the given node node. If there is no such ancestor, return -1.


        Example 1:


        Input
        ["TreeAncestor", "getKthAncestor", "getKthAncestor", "getKthAncestor"]
        [[7, [-1, 0, 0, 1, 1, 2, 2]], [3, 1], [5, 2], [6, 3]]
        Output
        [null, 1, 0, -1]

        Explanation
        TreeAncestor treeAncestor = new TreeAncestor(7, [-1, 0, 0, 1, 1, 2, 2]);
        treeAncestor.getKthAncestor(3, 1); // returns 1 which is the parent of 3
        treeAncestor.getKthAncestor(5, 2); // returns 0 which is the grandparent of 5
        treeAncestor.getKthAncestor(6, 3); // returns -1 because there is no such ancestor


        Constraints:

        1 <= k <= n <= 5 * 104
        parent.length == n
        parent[0] == -1
        0 <= parent[i] < n for all 0 < i < n
        0 <= node < n
        There will be at most 5 * 104 queries.
     */
    public static void main(String[] args) {
        System.out.println("Done.");
    }

    class TreeAncestor {
        int[][] jump;
        int maxPow;
        public TreeAncestor(int n, int[] parent) {
            maxPow = (int) (Math.log(n) / Math.log(2)) + 1;
            jump = new int[maxPow][n];
            jump[0] = parent;
            for (int p = 1; p < maxPow; p++) {
                for (int j = 0; j < n; j++) {
                    int pre = jump[p - 1][j];
                    jump[p][j] = pre == -1 ? -1 : jump[p - 1][pre];
                }
            }
        }

        public int getKthAncestor(int node, int k) {
            int maxPow = this.maxPow;
            while (k > 0 && node > -1) {
                if (k >= 1 << maxPow) {
                    node = jump[maxPow][node];
                    k -= 1 << maxPow;
                } else
                    maxPow -= 1;
            }
            return node;
        }

    }

    /**
     * Your TreeAncestor object will be instantiated and called as such:
     * TreeAncestor obj = new TreeAncestor(n, parent);
     * int param_1 = obj.getKthAncestor(node,k);
     */
}
