public class CountNodesEqualToAverageOfSubtree {

    /*
        Given the root of a binary tree, return the number of nodes where the value of the node is equal to the average of the values in its subtree.
        Note:
        The average of n elements is the sum of the n elements divided by n and rounded down to the nearest integer.
        A subtree of root is a tree consisting of root and all of its descendants.


        Example 1.
        Input: root = [4,8,5,0,1,null,6]
        Output: 5
        Explanation:
        For the node with value 4: The average of its subtree is (4 + 8 + 5 + 0 + 1 + 6) / 6 = 24 / 6 = 4.
        For the node with value 5: The average of its subtree is (5 + 6) / 2 = 11 / 2 = 5.
        For the node with value 0: The average of its subtree is 0 / 1 = 0.
        For the node with value 1: The average of its subtree is 1 / 1 = 1.
        For the node with value 6: The average of its subtree is 6 / 1 = 6.
        Example 2.
        Input: root = [1]
        Output: 1
        Explanation: For the node with value 1: The average of its subtree is 1 / 1 = 1.


        Constraints:

        The number of nodes in the tree is in the range [1, 1000].
        0 <= Node.val <= 1000
     */

    public static void main(String[] args) {
        System.out.println("Done...");
    }

    /**
     * Definition for a binary tree node.
     **/
     static class TreeNode {
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
    // Space: O(1)
    static class Solution {
        class pair{
            int sum ;
            int non ;
            int asf ;

            pair(int sum , int non , int asf){
                this.sum = sum ;
                this.non = non ;
                this.asf = asf ;
            }
        }

        public pair noOfNodes(TreeNode root){
            if(root == null)
                return new pair(0 , 0 , 0) ;

            pair leftpair = noOfNodes(root.left) ;
            pair rightpair = noOfNodes(root.right) ;

            int currsum = leftpair.sum + rightpair.sum + root.val ;
            int currnon = leftpair.non + rightpair.non + 1 ;
            int avg = currsum/currnon ;

            int currans = leftpair.asf + rightpair.asf ;
            if(avg == root.val)
                currans += 1 ;

            pair currpair = new pair(currsum , currnon , currans) ;

            return currpair ;
        }

        public int averageOfSubtree(TreeNode root) {
            return noOfNodes(root).asf ;
        }
    }
}
