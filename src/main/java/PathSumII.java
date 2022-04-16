import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PathSumII {

    /*
        Given the root of a binary tree and an integer targetSum, return all root-to-leaf paths where the sum of the node values in the path equals targetSum.
         Each path should be returned as a list of the node values, not node references.
        A root-to-leaf path is a path starting from the root and ending at any leaf node. A leaf is a node with no children.

        Example 1.
        Input: root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
        Output: [[5,4,11,2],[5,8,4,5]]
        Explanation: There are two paths whose sum equals targetSum:
        5 + 4 + 11 + 2 = 22
        5 + 8 + 4 + 5 = 22
        Example 2.
        Input: root = [1,2,3], targetSum = 5
        Output: []
        Example 3:

        Input: root = [1,2], targetSum = 0
        Output: []


        Constraints:

        The number of nodes in the tree is in the range [0, 5000].
        -1000 <= Node.val <= 1000
        -1000 <= targetSum <= 1000
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

    // Time: O(N)
    // Space: O(N)
    static class Solution_I {
        public static List<List<Integer>> pathSum(TreeNode root, int targetSum) {
            if (root == null) return Collections.emptyList();
            List<List<Integer>> ans = new ArrayList<>();
            if (root.val == targetSum && root.left == null && root.right == null) {
                List<Integer> cur = new ArrayList<>();
                cur.add(root.val);
                ans.add(cur);
                return ans;
            }
            List<List<Integer>> left = pathSum(root.left, targetSum - root.val);
            List<List<Integer>> right = pathSum(root.right, targetSum - root.val);
            for (int i = 0; i < left.size(); i++) {
                left.get(i).add(0, root.val);
            }
            for (int i = 0; i < right.size(); i++) {
                right.get(i).add(0, root.val);
            }
            ans.addAll(left);
            ans.addAll(right);
            return ans;
        }
    }

    // Time: O(LogN)
    // Space: O(N)
    static class Solution_II {
        static List<List<Integer>>ans=new ArrayList<>();
        public static List<List<Integer>> pathSum(TreeNode root, int targetSum) {
            List<Integer>list=new ArrayList<>();
            helper(root,targetSum,list);
            return ans;
        }
        public static void helper(TreeNode root,int sum,List<Integer>list){
            if(root==null)return;
            list.add(root.val);
            if(sum-root.val==0&&root.left==null&&root.right==null){
                ans.add(new ArrayList<>(list));
                list.remove(list.size()-1);
                return;
            }
            helper(root.left,sum-root.val,list);
            helper(root.right,sum-root.val,list);
            list.remove(list.size()-1);

        }
    }
}
