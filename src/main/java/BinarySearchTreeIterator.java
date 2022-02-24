import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BinarySearchTreeIterator {

    /*
        Implement the BSTIterator class that represents an iterator over the in-order traversal of a binary search tree (BST):
        BSTIterator(TreeNode root) Initializes an object of the BSTIterator class. The root of the BST is given as part of the constructor.
        The pointer should be initialized to a non-existent number smaller than any element in the BST.
        boolean hasNext() Returns true if there exists a number in the traversal to the right of the pointer, otherwise returns false.
        int next() Moves the pointer to the right, then returns the number at the pointer.
        Notice that by initializing the pointer to a non-existent smallest number, the first call to next() will return the smallest element in the BST.

        You may assume that next() calls will always be valid. That is, there will be at least a next number in the in-order traversal when next() is called.

        Example 1.
        Input
        ["BSTIterator", "next", "next", "hasNext", "next", "hasNext", "next", "hasNext", "next", "hasNext"]
        [[[7, 3, 15, null, null, 9, 20]], [], [], [], [], [], [], [], [], []]
        Output
        [null, 3, 7, true, 9, true, 15, true, 20, false]

        Explanation
        BSTIterator bSTIterator = new BSTIterator([7, 3, 15, null, null, 9, 20]);
        bSTIterator.next();    // return 3
        bSTIterator.next();    // return 7
        bSTIterator.hasNext(); // return True
        bSTIterator.next();    // return 9
        bSTIterator.hasNext(); // return True
        bSTIterator.next();    // return 15
        bSTIterator.hasNext(); // return True
        bSTIterator.next();    // return 20
        bSTIterator.hasNext(); // return False
        Constraints:

        The number of nodes in the tree is in the range [1, 105].
        0 <= Node.val <= 106
        At most 105 calls will be made to hasNext, and next.

        Follow up:

        Could you implement next() and hasNext() to run in average O(1) time and use O(h) memory, where h is the height of the tree?
     */

    public static void main(String[] args) {
        /**
         * Your BSTIterator object will be instantiated and called as such:
         * BSTIterator obj = new BSTIterator(root);
         * int param_1 = obj.next();
         * boolean param_2 = obj.hasNext();
         */
        System.out.println("Hello World...");
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
    class BSTIterator_I {

         List<Integer> list = new ArrayList<>();
         int index = 0;

        public BSTIterator_I(TreeNode root) {
            inorder(root);
        }

        void inorder(TreeNode root) {
            if (root == null) return;
            inorder(root.left);
            list.add(root.val);
            inorder(root.right);
        }

        // Time: O(1)
        // Space: O(N)
        public int next() {
            return list.get(index++);
        }

        // Time: O(1)
        // Space: O(N)
        public boolean hasNext() {
            return index<list.size();
        }
    }

    class BSTIterator_II {

        Stack<TreeNode> order = new Stack<>();
        public BSTIterator_II(TreeNode root) {
            partialInOrder(root);
        }

        private void partialInOrder(TreeNode root) {
            while(root != null) {
                order.push(root);
                root = root.left;
            }
        }

        // Time: O(1)
        // Space: O(N)
        public int next() {
            TreeNode top = order.pop();
            partialInOrder(top.right);
            return top.val;
        }

        // Time: O(1)
        // Space: O(N)
        public boolean hasNext() {
            return !order.isEmpty();
        }
    }
}
