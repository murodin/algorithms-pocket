import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class FindLargestValueInEachTreeRow {
    /*
        Given the root of a binary tree, return an array of the largest value in each row of the tree (0-indexed).
        Example 1:
        Input: root = [1,3,2,5,3,null,9]
        Output: [1,3,9]
        Example 2:
        Input: root = [1,2,3]
        Output: [1,3]


        Constraints:

        The number of nodes in the tree will be in the range [0, 104].
        -231 <= Node.val <= 231 - 1
     */
    public static void main(String[] args) {
        System.out.println("Done.");
    }

    /**
     * Definition for a binary tree node.
     */
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
        public List<Integer> largestValues(TreeNode root) {
            List<Integer> maxLevelVal = new ArrayList<>();
            if(root == null)return maxLevelVal;
            Deque<TreeNode> levelQueue = new ArrayDeque<>();
            levelQueue.offerLast(root);
            while(!levelQueue.isEmpty()){
                int sz = levelQueue.size();
                int maxValNode = Integer.MIN_VALUE;
                while(sz-->0){
                    TreeNode currNode = levelQueue.pollFirst();
                    assert currNode != null;
                    maxValNode = Math.max(currNode.val, maxValNode);
                    if(currNode.left != null){
                        levelQueue.offerLast(currNode.left);
                    }
                    if(currNode.right != null){
                        levelQueue.offerLast(currNode.right);
                    }
                }
                maxLevelVal.add(maxValNode);
            }
            return maxLevelVal;
        }
    }
}
