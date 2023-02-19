import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTreeZigzagLevelOrderTraversal {
    /*
        Given the root of a binary tree, return the zigzag level order traversal of its nodes' values. (i.e., from left to right, then right to left for the next level and alternate between).

        Example 1.
        Input: root = [3,9,20,null,null,15,7]
        Output: [[3],[20,9],[15,7]]
        Example 2.
        Input: root = [1]
        Output: [[1]]
        Example 3.
        Input: root = []
        Output: []


        Constraints:

        The number of nodes in the tree is in the range [0, 2000].
        -100 <= Node.val <= 100
     */
    public static void main(String[] args) {

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

    // Time: O(N)
    // Space: O(N)
    static class Solution {
        public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
            List<List<Integer>> ans = new LinkedList();
            if(root==null){
                return ans;
            }
            Queue<TreeNode> q = new LinkedList();
            q.add(root);
            int level = 1;
            while(!q.isEmpty()){
                List<Integer> l = new ArrayList();
                int c = q.size();
                for(int i=0;i<c;i++){
                    TreeNode cur = q.poll();
                    l.add(cur.val);
                    if(cur.left!=null){
                        q.add(cur.left);
                    }
                    if(cur.right!=null){
                        q.add(cur.right);
                    }
                }
                if(level%2==0){
                    List<Integer> ll = new ArrayList();
                    for(int i =l.size()-1;i>=0;i--){
                        ll.add(l.get(i));
                    }
                    ans.add(ll);
                } else{
                    ans.add(l);
                }
                level++;
            }
            return ans;
        }
    }
}
