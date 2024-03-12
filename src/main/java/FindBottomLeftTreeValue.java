import java.util.LinkedList;
import java.util.Queue;

public class FindBottomLeftTreeValue {
    /*
        Given the root of a binary tree, return the leftmost value in the last row of the tree.

        Example 1:
        Input: root = [2,1,3]
        Output: 1
        Example 2:
        Input: root = [1,2,3,4,null,5,6,null,null,7]
        Output: 7


        Constraints:

        The number of nodes in the tree is in the range [1, 104].
        -231 <= Node.val <= 231 - 1
     */
    public static void main(String[] args) {
        System.out.println("Done...");
    }

    // Time: O(N)
    // Space: O(N)
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

    public class Solution {
        public int findBottomLeftValue(TreeNode root) {
            int last = 0;
            Queue<TreeNode> q = new LinkedList<>();
            q.add(root);

            while (!q.isEmpty()) {
                int count = q.size();
                for (int i = 0; i < count; i++) {
                    TreeNode curr = q.poll();
                    if (i == 0)
                        last = curr.val;  // last leftMost val
                    assert curr != null;
                    if (curr.left != null)
                        q.add(curr.left);
                    if (curr.right != null)
                        q.add(curr.right);
                }
            }
            return last;
        }
    }
}
