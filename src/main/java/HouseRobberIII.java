import java.util.HashMap;
import java.util.Map;

public class HouseRobberIII {

    /*
        The thief has found himself a new place for his thievery again. There is only one entrance to this area, called root.
        Besides the root, each house has one and only one parent house.
        After a tour, the smart thief realized that all houses in this place form a binary tree.
        It will automatically contact the police if two directly-linked houses were broken into on the same night.
        Given the root of the binary tree, return the maximum amount of money the thief can rob without alerting the police.


        Example 1.
        Input: root = [3,2,3,null,3,null,1]
        Output: 7
        Explanation: Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.
        Example 2.
        Input: root = [3,4,5,1,3,null,1]
        Output: 9
        Explanation: Maximum amount of money the thief can rob = 4 + 5 = 9.


        Constraints:

        The number of nodes in the tree is in the range [1, 104].
        0 <= Node.val <= 104
     */

    public static void main(String[] args) {
        System.out.println("Hello World");
    }

    /*
     * Definition for a binary tree node.
     */
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
    // Space: O(N)
    static class Solution {
         static Map<TreeNode, Integer> map = new HashMap<>();
        public static int rob(TreeNode root) {
            if(root == null) return 0;

            if(map.containsKey(root)) return map.get(root);

            int sum = root.val;

            if(root.left != null) {
                sum += rob(root.left.left);
                sum += rob(root.left.right);
            }

            if(root.right != null) {
                sum += rob(root.right.left);
                sum += rob(root.left.right);
            }

            int next_sum = rob(root.right) + rob(root.left);
            int res = Math.max(sum, next_sum);
            map.put(root, res);
            return res;
        }
    }
}
