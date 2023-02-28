import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FindDuplicateSubtrees {
    /*
        Given the root of a binary tree, return all duplicate subtrees.
        For each kind of duplicate subtrees, you only need to return the root node of any one of them.
        Two trees are duplicate if they have the same structure with the same node values.

        Example 1.
        Input: root = [1,2,3,4,null,2,4,null,null,4]
        Output: [[2,4],[4]]
        Example 2.
        Input: root = [2,1,1]
        Output: [[1]]
        Example 3.
        Input: root = [2,2,2,3,null,3,null]
        Output: [[2,3],[3]]


        Constraints:

        The number of the nodes in the tree will be in the range [1, 5000]
        -200 <= Node.val <= 200
     */
    public static void main(String[] args) {
        System.out.println("Done.");
    }

    // Time: O(NLogN)
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

    class Solution {
        public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
            List<TreeNode> res=new ArrayList<>();
            HashMap<String,Integer> hm=new HashMap<>();
            helper(res,root,hm);
            return res;
        }
        public String helper(List<TreeNode> res,TreeNode root,HashMap<String,Integer> hm){
            if(root==null)
                return "";
            String left=helper(res,root.left,hm);
            String right=helper(res,root.right,hm);
            int currroot=root.val;
            String stringformed=currroot+"$"+left+"$"+right;
            if(hm.getOrDefault(stringformed,0)==1){
                res.add(root);
            }
            hm.put(stringformed,hm.getOrDefault(stringformed,0)+1);
            return stringformed;
        }
    }
}
