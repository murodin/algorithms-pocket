public class DeepestLeavesSum {

    /*
        Given the root of a binary tree, return the sum of values of its deepest leaves.
        Example 1.
        Input: root = [1,2,3,4,5,null,6,7,null,null,null,null,8]
        Output: 15

        Example 2.
        Input: root = [6,7,8,2,7,1,3,9,null,1,4,null,null,null,5]
        Output: 19
     */

    public static void main(String[] args) {

    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
  }

    static class Solution_I {
        // Time: O(2N)
        // Space: O(1)
        static int sum  = 0;
        public static int deepestLeavesSum(TreeNode root) {
            int maxDepth = maxDepth(root);
            findSum(root, 1, maxDepth);
            return sum;
        }

        public static int maxDepth(TreeNode node) {
            if(node == null) return 0;
            return 1 + Math.max(maxDepth(node.left), maxDepth(node.right));
        }

        public static void findSum(TreeNode node, int curr, int depth) {
            if(node != null) {
                if(curr == depth) {
                    sum+=node.val;
                }
                findSum(node.left, curr+1, depth);
                findSum(node.right, curr+1, depth);
            }
        }
    }


    static class Solution_II {
        // Time: O(N)
        // Space: O(1)
        static int sum  = 0;
        static int maxDepth = 0;
        public static int deepestLeavesSum(TreeNode root) {
            findSum(root, 1);
            return sum;
        }

        public static void findSum(TreeNode node, int curr) {
            if(node != null) {
                if(curr > maxDepth) {
                    sum = 0;
                    maxDepth = curr;
                }
                if(curr == maxDepth) {
                    sum+=node.val;
                }
                findSum(node.left, curr+1);
                findSum(node.right, curr+1);
            }
        }
    }
}
