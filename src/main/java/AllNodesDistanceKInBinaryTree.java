import java.util.*;

public class AllNodesDistanceKInBinaryTree {
    /*
        Given the root of a binary tree, the value of a target node target, and an integer k,
        return an array of the values of all nodes that have a distance k from the target node.

        You can return the answer in any order.

        Example 1:


        Input: root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, k = 2
        Output: [7,4,1]
        Explanation: The nodes that are a distance 2 from the target node (with value 5) have values 7, 4, and 1.
        Example 2:

        Input: root = [1], target = 1, k = 3
        Output: []


        Constraints:

        The number of nodes in the tree is in the range [1, 500].
        0 <= Node.val <= 500
        All the values Node.val are unique.
        target is the value of one of the nodes in the tree.
        0 <= k <= 1000
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
         TreeNode(int x) { val = x; }
    }

    class Solution {
        public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
            List<Integer> ans = new ArrayList<>();
            Map<Integer, TreeNode> parent = new HashMap<>();
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);

            while (!queue.isEmpty()) {
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    TreeNode top = queue.poll();

                    assert top != null;
                    if (top.left != null) {
                        parent.put(top.left.val, top);
                        queue.offer(top.left);
                    }

                    if (top.right != null) {
                        parent.put(top.right.val, top);
                        queue.offer(top.right);
                    }
                }
            }

            Map<Integer, Integer> visited = new HashMap<>();
            queue.offer(target);
            while (k > 0 && !queue.isEmpty()) {
                int size = queue.size();

                for (int i = 0; i < size; i++) {
                    TreeNode top = queue.poll();

                    visited.put(top.val, 1);

                    if (top.left != null && !visited.containsKey(top.left.val)) {
                        queue.offer(top.left);
                    }

                    if (top.right != null && !visited.containsKey(top.right.val)) {
                        queue.offer(top.right);
                    }

                    if (parent.containsKey(top.val) && !visited.containsKey(parent.get(top.val).val)) {
                        queue.offer(parent.get(top.val));
                    }
                }

                k--;
            }

            while (!queue.isEmpty()) {
                ans.add(queue.poll().val);
            }
            return ans;
        }
    }

}
