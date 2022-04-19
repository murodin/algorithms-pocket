public class RecoverBinarySearchTree {
    /*
        You are given the root of a binary search tree (BST), where the values of exactly two nodes of the tree were swapped by mistake. Recover the tree without changing its structure.
         Example 1.
        Input: root = [1,3,null,null,2]
        Output: [3,1,null,null,2]
        Explanation: 3 cannot be a left child of 1 because 3 > 1. Swapping 1 and 3 makes the BST valid.
        Example 2.
        Input: root = [3,1,4,null,null,2]
        Output: [2,1,4,null,null,3]
        Explanation: 2 cannot be in the right subtree of 3 because 2 < 3. Swapping 2 and 3 makes the BST valid.


        Constraints:

        The number of nodes in the tree is in the range [2, 1000].
        -231 <= Node.val <= 231 - 1


        Follow up: A solution using O(n) space is pretty straight-forward. Could you devise a constant O(1) space solution?
     */

    public static void main(String[] args) {
        System.out.println("Done..");
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

    // Time: O(LogN)
    // Space: O(1)
    static class Solution_I {
        static TreeNode big,small,prev;
        public static void recoverTree(TreeNode root) {
            big=null; small=null; prev=null;
            rec(root);
            if(big!=null && small!=null) {
                int temp=big.val;
                big.val=small.val;
                small.val=temp;
            }
        }

        private static void rec(TreeNode root){
            if(root==null) return ;
            rec(root.left);
            if(big!=null) {
                if(small==null && root.val>big.val) big=root;
                else if(small!=null && root.val<small.val){
                    small=root;
                }else if(small==null && root.val<prev.val){
                    small=root;
                }
            } else {
                big=root;
            }
            prev=root;
            rec(root.right);
        }
    }

    // Time: O(LogN)
    // Space: O(1)
    static class Solution_II {
        public static void recoverTree(TreeNode root) {
            TreeNode[] arr =new TreeNode[4];

            arr[0]=new TreeNode(Integer.MIN_VALUE);

            inorder(root,arr);

            if(arr[1]!=null && arr[3]!=null) {
                int temp=arr[1].val;
                arr[1].val=arr[3].val;
                arr[3].val=temp;
            } else{
                int temp=arr[1].val;
                arr[1].val=arr[2].val;
                arr[2].val=temp;
            }
            return;
        }

        static void inorder(TreeNode root, TreeNode[] arr){
            if(root==null){
                return;
            }
            inorder(root.left,arr);
            if(arr[0]!=null && arr[0].val>root.val){
                if(arr[1]==null && arr[2]==null){
                    arr[1]=arr[0];
                    arr[2]=root;
                } else{
                    arr[3]=root;
                }
            }
            arr[0]=root;
            inorder(root.right,arr);

        }
    }

}
