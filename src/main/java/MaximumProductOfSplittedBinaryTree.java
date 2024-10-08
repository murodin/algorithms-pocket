public class MaximumProductOfSplittedBinaryTree {
    /*
        Given the root of a binary tree, split the binary tree into two subtrees by removing one edge such that the product of the sums of the subtrees is maximized.
        Return the maximum product of the sums of the two subtrees. Since the answer may be too large, return it modulo 109 + 7.
        Note that you need to maximize the answer before taking the mod and not after taking it.

        Example 1.
        Input: root = [1,2,3,4,5,6]
        Output: 110
        Explanation: Remove the red edge and get 2 binary trees with sum 11 and 10. Their product is 110 (11*10)
        Example 2.
        Input: root = [1,null,2,3,4,null,null,5,6]
        Output: 90
        Explanation: Remove the red edge and get 2 binary trees with sum 15 and 6.Their product is 90 (15*6)


        Constraints:

        The number of nodes in the tree is in the range [2, 5 * 104].
        1 <= Node.val <= 104
     */
    public static void main(String[] args) {
        System.out.println("Done...");
    }

    /**
     * Definition for a binary tree node.
     **/
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

    // Time: O(N)
    // Space: O(1)
    class Solution {
        int mod = 1000000007;
        long max = 0;
        long total;
        public int maxProduct(TreeNode root) {
            this.total = dfs(root);
            traverse(root);
            return (int)(max%mod);
        }

        private int dfs(TreeNode root)
        {
            if(root==null)
                return 0;
            return root.val += dfs(root.left) + dfs(root.right);
        }

        private void traverse(TreeNode root)
        {
            if(root==null)
                return;
            max = Math.max(root.val*(total-root.val),max);
            traverse(root.left);
            traverse(root.right);
        }
    }
}
