public class SmallestStringStartingFromLeaf {
    /*
        You are given the root of a binary tree where each node has a value in the range [0, 25] representing the letters 'a' to 'z'.
        Return the lexicographically smallest string that starts at a leaf of this tree and ends at the root.
        As a reminder, any shorter prefix of a string is lexicographically smaller.
        For example, "ab" is lexicographically smaller than "aba".
        A leaf of a node is a node that has no children.

        Example 1.
        Input: root = [0,1,2,3,4,3,4]
        Output: "dba"
        Example 2.
        Input: root = [25,1,3,1,3,0,2]
        Output: "adz"
        Example 3.
        Input: root = [2,2,1,null,1,0,null,0]
        Output: "abc"

        Constraints:

        The number of nodes in the tree is in the range [1, 8500].
        0 <= Node.val <= 25
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

    class Solution {
        String ans = "";
        public String smallestFromLeaf(TreeNode root) {
            StringBuilder sb = new StringBuilder();
            smallestFromLeaf(root, sb);
            return ans;
        }
        public void smallestFromLeaf(TreeNode root, StringBuilder sb) {
            if (root == null)
                return;
            sb.append((char) (root.val + 'a'));
            if (root.left == null && root.right == null) {
                sb.reverse();
                if (ans.equals("") || sb.toString().compareTo(ans) < 0)
                    ans = sb.toString();
                sb.reverse();
            }
            smallestFromLeaf(root.left, sb);
            smallestFromLeaf(root.right, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
