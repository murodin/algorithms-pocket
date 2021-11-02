import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTreeRightSideView {

    /*
        Given the root of a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.

        Example 1.
        Input: root = [1,2,3,null,5,null,4]
        Output: [1,3,4]
        Example 2.
        Input: root = [1,null,3]
        Output: [1,3]
        Example 3.
        Input: root = []
        Output: []
     */

    public static void main(String[] args) {
        System.out.println("Solution Implemented.");
    }

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
    static class Solution_I {
        public static List<Integer> rightSideView(TreeNode root) {
            List<Integer> result=new ArrayList<>();

            if(root==null){
                return result;
            }

            Queue<TreeNode> q=new LinkedList<>();
            q.add(root);

            while(q.size()>0){
                int count=q.size();

                while(count-->0){
                    TreeNode val=q.remove();
                    if(count==0){
                        result.add(val.val);
                    }
                    if(val.left!=null){
                        q.add(val.left);
                    }
                    if(val.right!=null){
                        q.add(val.right);
                    }

                }
            }
            return result;
        }
    }

    // Time: O(N)
    // Space: O(N)
    static class Solution_II {
        static List<Integer> result=new ArrayList<>();
        public static List<Integer> rightSideView(TreeNode root) {

            levelOrder(root,0);
            return result;
        }

        static void levelOrder(TreeNode node,int level){
            if(node==null){
                return;
            }

            if(result.size()==level){
                result.add(node.val);
            }

            levelOrder(node.right,level+1);
            levelOrder(node.left,level+1);
        }
    }
}
