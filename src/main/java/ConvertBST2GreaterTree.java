import java.util.Stack;

public class ConvertBST2GreaterTree {

    /*
        Given the root of a Binary Search Tree (BST),
        convert it to a Greater Tree such that every key of the original BST is changed to the original key plus the sum of all keys greater than the original key in BST.
        As a reminder, a binary search tree is a tree that satisfies these constraints:

        The left subtree of a node contains only nodes with keys less than the node's key.
        The right subtree of a node contains only nodes with keys greater than the node's key.
        Both the left and right subtrees must also be binary search trees.

        Example 1.
        Input: root = [4,1,6,0,2,5,7,null,null,null,3,null,null,null,8]
        Output: [30,36,21,36,35,26,15,null,null,null,33,null,null,null,8]
        Example 2.
        Input: root = [0,null,1]
        Output: [1,null,1]
        Example 3.
        Input: root = [1,0,2]
        Output: [3,3,2]
        Example 4.
        Input: root = [3,2,4,1]
        Output: [7,9,4,10]
     */

    public static void main(String[] args) {
        System.out.println("Worked...");
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

    //Recursive
    // Time: O((N)
    // Space: O(N)
    static class Solution_I {
        static int sum = 0;
        public static TreeNode convertBST(TreeNode root) {
            if(root == null) return null;

            convertBST(root.right);
            //root
            sum += root.val;
            root.val = sum;
            convertBST(root.left);

            return root;
        }
    }

    //Iterative
    // Time: O((N)
    // Space: O(N)
    static class Solution_II {
        static int sum = 0;
        public static TreeNode convertBST(TreeNode root) {
            if(root == null) return null;
            TreeNode node = root;
            Stack<TreeNode> st = new Stack<>();

            while(!st.isEmpty() || node!=null){
                while(node != null){
                    st.add(node);
                    node = node.right;
                }

                node = st.pop();
                sum += node.val;
                node.val = sum;
                node = node.left;
            }

            return root;
        }
    }

    //Iterative
    // Time: O((N)
    // Space: O(1)
    static class Solution_III {
        public static TreeNode convertBST(TreeNode root) {
            if(root == null) return null;
            int sum = 0;
            TreeNode node = root;

            while(node != null){
                // no right tree
                if(node.right == null){
                    sum += node.val;
                    node.val = sum;
                    node = node.left;
                }
                // right tree
                else{
                    TreeNode succ = nextGreater(node);

                    //left is null -- unvisited
                    if(succ.left == null){
                        succ.left = node;
                        node = node.right;
                    }
                    //not null -- visited
                    else{
                        succ.left = null;
                        sum += node.val;
                        node.val = sum;
                        node = node.left;
                    }
                }
            }


            return root;
        }

        static TreeNode nextGreater(TreeNode node){
            TreeNode succ = node.right;
            while(succ.left!=null && succ.left !=node){
                succ=succ.left;
            }
            return succ;
        }
    }
}
