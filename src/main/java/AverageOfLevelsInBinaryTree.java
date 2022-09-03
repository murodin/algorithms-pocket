import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class AverageOfLevelsInBinaryTree {
    /*
        Given the root of a binary tree, return the average value of the nodes on each level in the form of an array.
        Answers within 10-5 of the actual answer will be accepted.

        Example 1.
        Input: root = [3,9,20,null,null,15,7]
        Output: [3.00000,14.50000,11.00000]
        Explanation: The average value of nodes on level 0 is 3, on level 1 is 14.5, and on level 2 is 11.
        Hence return [3, 14.5, 11].
        Example 2.
        Input: root = [3,9,20,15,7]
        Output: [3.00000,14.50000,11.00000]


        Constraints:

        The number of nodes in the tree is in the range [1, 104].
        -231 <= Node.val <= 231 - 1
     */
    public static void main(String[] args) {
        System.out.println("Done.");
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

    // Time: O(NLogN)
    // Space: O(N)
    static class Solution {
        public static List<Double> averageOfLevels(TreeNode root) {
            List<Double> result = new ArrayList<>();

            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);

            while (!queue.isEmpty()) {
                int levelSize = queue.size();
                double levelSum = 0.0;

                for (int i = 0; i < levelSize; i++) {
                    TreeNode currentNode = queue.poll();
                    // add the node's value to the running sum
                    levelSum += currentNode.val;
                    if (currentNode.left != null) queue.offer(currentNode.left);
                    if (currentNode.right != null) queue.offer(currentNode.right);
                }
                // append the current level's average to the result array
                result.add(levelSum / levelSize);
            }
            return result;
        }
    }
}
