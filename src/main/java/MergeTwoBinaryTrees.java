import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class MergeTwoBinaryTrees {
    /*
        You are given two binary trees root1 and root2.
        Imagine that when you put one of them to cover the other, some nodes of the two trees are overlapped while the others are not.
        You need to merge the two trees into a new binary tree.
        The merge rule is that if two nodes overlap, then sum node values up as the new value of the merged node. Otherwise, the NOT null node will be used as the node of the new tree.
        Return the merged tree.
        Note: The merging process must start from the root nodes of both trees.

        Example 1.
        Input: root1 = [1,3,2,5], root2 = [2,1,3,null,4,null,7]
        Output: [3,4,5,5,4,null,7]
        Example 2.
        Input: root1 = [1], root2 = [1,2]
        Output: [2,2]
        Constraints:
        The number of nodes in both trees is in the range [0, 2000].
        -104 <= Node.val <= 104
     */

    public static void main(String[] args) {
        System.out.println("Done...");
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

    //Time: O(N)
    //Space: O(H)
    static class Solution_I {
        public static TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
            //If t1 == NULL -> t2
            //If t2 == NULL -> t2
            // Sum (t1,t2)

            //Preorder Traversal (P -> L -> R)
            if(t1 == null) return t2;
            if(t2 == null) return t1;
            t1.val+=t2.val;
            t1.left = mergeTrees(t1.left, t2.left);
            t1.right = mergeTrees(t1.right, t2.right);
            return t1;
        }
    }

    //Time: O(N)
    //Space: O(H)
    static class Solution_II {
        public static TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
            //If t1 == NULL -> t2
            //If t2 == NULL -> t2
            // Sum (t1,t2)

            if(t1 == null) return t2;
            if(t2 == null) return t1;

            Stack<TreeNode[]> st = new Stack<>();
            st.push(new TreeNode[]{t1,t2});

            //While is not empty process the nodes
            while(!st.isEmpty()) {
                TreeNode[] curr = st.pop();

                //Process the node
                curr[0].val+=curr[1].val;

                //1. curr[0] = null, curr[1] != null -> curr[1]
                //2. curr[1] = null, curr[0] != null -> curr[0]
                //3. both not null, add it stack

                //Left Tree
                if(curr[0].left == null) {
                    curr[0].left = curr[1].left;
                } else if(curr[1].left != null) {
                    st.push(new TreeNode[]{curr[0].left, curr[1].left});
                }

                //Right Tree
                if(curr[0].right == null) {
                    curr[0].right = curr[1].right;
                } else if(curr[1].right != null) {
                    st.push(new TreeNode[]{curr[0].right, curr[1].right});
                }
            }
            return t1;
        }
    }

    //Time: O(N)
    //Space: O(2^H)
    static class Solution_III {
        public static TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
            //If t1 == NULL -> t2
            //If t2 == NULL -> t2
            // Sum (t1,t2)

            if(t1 == null) return t2;
            if(t2 == null) return t1;

            Queue<TreeNode[]> q = new LinkedList<>();
            q.add(new TreeNode[]{t1,t2});

            //While is not empty process the nodes
            while(!q.isEmpty()) {
                TreeNode[] curr = q.remove();
                if(curr[1] != null) {
                    curr[0].val+=curr[1].val;

                    //1. curr[0] = null -> curr[1]
                    //2. curr[0] = null ->, add it queue

                    //Left Tree
                    if(curr[0].left == null) {
                        curr[0].left = curr[1].left;
                    } else {
                        q.add(new TreeNode[]{curr[0].left, curr[1].left});
                    }

                    //Right Tree
                    if(curr[0].right == null) {
                        curr[0].right = curr[1].right;
                    } else {
                        q.add(new TreeNode[]{curr[0].right, curr[1].right});
                    }
                }
            }
            return t1;
        }
    }
}
