import java.util.LinkedList;
import java.util.Queue;

public class AddOneRow2Tree {

    /*
        Given the root of a binary tree and two integers val and depth, add a row of nodes with value val at the given depth depth.
        Note that the root node is at depth 1.
        The adding rule is:
        Given the integer depth, for each not null tree node cur at the depth depth - 1, create two tree nodes with value val as cur's left subtree root and right subtree root.
        cur's original left subtree should be the left subtree of the new left subtree root.
        cur's original right subtree should be the right subtree of the new right subtree root.
        If depth == 1 that means there is no depth depth - 1 at all, then create a tree node with value val as the new root of the whole original tree, and
        the original tree is the new root's left subtree.

        Example 1.
        Input: root = [4,2,6,3,1,5], val = 1, depth = 2
        Output: [4,1,1,2,null,null,6,3,1,5]
        Example 2.
        Input: root = [4,2,null,3,1], val = 1, depth = 3
        Output: [4,2,null,1,1,3,null,null,1]

     */

    public static void main(String[] args) {
        TreeNode lr = new TreeNode(15);
        TreeNode ll = new TreeNode(7);
        TreeNode l = new TreeNode(20, ll, lr);
        TreeNode r = new TreeNode(9);
        TreeNode root = new TreeNode(3, l, r);

        System.out.println("Solution I:" + Solution_I.addOneRow(root, 1,2));
        System.out.println("Solution II:" + Solution_II.addOneRow(root, 1,2));
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode() {
        }

        public TreeNode(int val) {
            this.val = val;
        }

        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    // Time: O(NodesInTheDepth)
    // Space: O(NodesInTheDepth)
    static class Solution_I {
        public static TreeNode addOneRow(TreeNode root, int v, int d) {
            if(d == 1){
                TreeNode node = new TreeNode(v);
                node.left = root;
                return node;
            }
            insert(root,v,d,1);
            return root;
        }

        public static void insert(TreeNode node, int val, int depth, int currentDepth) {
            if(node == null) return;
            if(depth-1 == currentDepth) {
                TreeNode t = node.left;
                node.left = new TreeNode(val);
                node.left.left = t;
                t = node.right;
                node.right = new TreeNode(val);
                node.right.right = t;
            }
            else {
                insert(node.left, val, depth, currentDepth+1);
                insert(node.right, val, depth, currentDepth+1);
            }
        }
    }

    // Time: O(NodesInTheDepth)
    // Space: O(NodesInTheQueue)
    static class Solution_II {
        public static TreeNode addOneRow(TreeNode root, int v, int d) {
            if(d == 1){
                TreeNode node = new TreeNode(v);
                node.left = root;
                return node;
            }
            Queue<TreeNode> q = new LinkedList<>();
            q.add(root);
            int currentDepth = 1;
            while(currentDepth < d-1) {
                Queue<TreeNode> temp = new LinkedList<>();
                while(!q.isEmpty()){
                    TreeNode node = q.remove();
                    if(node.left != null) temp.add(node.left);
                    if(node.right != null) temp.add(node.right);
                }
                q = temp;
                currentDepth++;
            }
            while(!q.isEmpty()) {
                TreeNode node = q.remove();
                TreeNode temp = node.left;
                node.left = new TreeNode(v);
                node.left.left = temp;
                temp = node.right;
                node.right = new TreeNode(v);
                node.right.right = temp;
            }
            return root;
        }
    }
}
