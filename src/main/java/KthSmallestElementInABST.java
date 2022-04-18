public class KthSmallestElementInABST {
    /*
        Given the root of a binary search tree, and an integer k, return the kth smallest value (1-indexed) of all the values of the nodes in the tree.

        Example 1.
        Input: root = [3,1,4,null,2], k = 1
        Output: 1
        Example 2.
        Input: root = [5,3,6,2,4,null,null,1], k = 3
        Output: 3


        Constraints:

        The number of nodes in the tree is n.
        1 <= k <= n <= 104
        0 <= Node.val <= 104


        Follow up: If the BST is modified often (i.e., we can do insert and delete operations) and you need to find the kth smallest frequently, how would you optimize?
     */

    public static void main(String[] args) {
        System.out.println("Done...");
    }

    /**
     * Definition for a binary tree node.
     **/
     public static class TreeNode {
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

    // Time: O(N) or O(Max(LogN, K)) ıf Tree is balanced
    // Space: O(1)
    static class Solution_I {
        static int counter;
        static int result = -1;

        public static int kthSmallest(TreeNode root, int k) {
            counter = k;
            recurse(root);
            return result;
        }

        private static void recurse(TreeNode root) {
            if(root == null)
                return;
            recurse(root.left);
            if(counter == 1){
                counter = 0;
                result = root.val;
                return;
            }
            --counter;
            recurse(root.right);
        }
    }

    // Time: O(H + K), where H is the height of tree
    // Space: O(H)
    static class Solution_II{
        public static int kthSmallest(TreeNode root, int k) {
            var kSmallest = new int[1];
            inorder(root, new int[]{k}, kSmallest);
            return kSmallest[0];
        }

        private static void inorder(TreeNode root, int[] count, int[] kSmallest) {
            if (root == null || count[0] == 0)
                return;
            // recurse left
            inorder(root.left, count, kSmallest);
            // visit
            if (--count[0] == 0)
                kSmallest[0] = root.val;
            else
                // recurse right
                inorder(root.right, count, kSmallest);
        }
    }
}
