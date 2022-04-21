public class MaximumDifferenceBetweenNodeAndAncestor {
    /*
        Given the root of a binary tree, find the maximum value v for which there exist different nodes a and b where v = |a.val - b.val| and a is an ancestor of b.
        A node a is an ancestor of b if either: any child of a is equal to b or any child of a is an ancestor of b.

        Example 1.
        Input: root = [8,3,10,1,6,null,14,null,null,4,7,13]
        Output: 7
        Explanation: We have various ancestor-node differences, some of which are given below :
        |8 - 3| = 5
        |3 - 7| = 4
        |8 - 1| = 7
        |10 - 13| = 3
        Among all possible differences, the maximum value of 7 is obtained by |8 - 1| = 7.
        Example 2.
        Input: root = [1,null,2,null,0,3]
        Output: 3

        Constraints:

        The number of nodes in the tree is in the range [2, 5000].
        0 <= Node.val <= 105
     */

    public static void main(String[] args) {
        System.out.println("Done...");
    }

    /**
     * Definition for a binary tree node.
     **/
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    // Time: O(N)
    // Space: O(N)
    static class Solution_I {
        static int diff = 0;
        public static int maxAncestorDiff(TreeNode root) {
            if(root == null) return 0;
            helper(root);
            return diff;
        }

        private static int[] helper(TreeNode node) {
            if(node == null) return new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE};

            if(node.left == null && node.right == null) return new int[] {node.val, node.val};

            int[] left = helper(node.left);
            int[] right = helper(node.right);

            int min = Math.min(left[0], right[0]);
            int max = Math.max(left[1], right[1]);

            diff = Math.max(diff, Math.max(Math.abs(min - node.val), Math.abs(max - node.val)));
            min = Math.min(min, node.val);
            max = Math.max(max, node.val);

            return new int[]{min, max};
        }
    }

    // Time: O(N)
    // Space: O(1)
    static class Solution_II {
        static int res;
        public static int maxAncestorDiff(TreeNode root) {
            res = 0;
            check(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
            return res;
        }

        static void check(TreeNode node, int max, int min){
            if(node == null) return;

            max = Math.max(max, node.val);
            min = Math.min(min, node.val);
            res = Math.max(res, max-min);

            check(node.left,max,min);
            check(node.right,max,min);
        }
    }
}
