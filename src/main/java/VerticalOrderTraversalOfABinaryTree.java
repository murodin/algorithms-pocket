import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class VerticalOrderTraversalOfABinaryTree {
    /*
        Given the root of a binary tree, calculate the vertical order traversal of the binary tree.
        For each node at position (row, col), its left and right children will be at positions (row + 1, col - 1) and (row + 1, col + 1) respectively.
        The root of the tree is at (0, 0).
        The vertical order traversal of a binary tree is a list of top-to-bottom orderings for each column index starting from
        the leftmost column and ending on the rightmost column. There may be multiple nodes in the same row and same column.
        In such a case, sort these nodes by their values.

        Return the vertical order traversal of the binary tree.

        Example 1.
        Input: root = [3,9,20,null,null,15,7]
        Output: [[9],[3,15],[20],[7]]
        Explanation:
        Column -1: Only node 9 is in this column.
        Column 0: Nodes 3 and 15 are in this column in that order from top to bottom.
        Column 1: Only node 20 is in this column.
        Column 2: Only node 7 is in this column.
        Example 2.
        Input: root = [1,2,3,4,5,6,7]
        Output: [[4],[2],[1,5,6],[3],[7]]
        Explanation:
        Column -2: Only node 4 is in this column.
        Column -1: Only node 2 is in this column.
        Column 0: Nodes 1, 5, and 6 are in this column.
                  1 is at the top, so it comes first.
                  5 and 6 are at the same position (2, 0), so we order them by their value, 5 before 6.
        Column 1: Only node 3 is in this column.
        Column 2: Only node 7 is in this column.
        Example 3:


        Input: root = [1,2,3,4,6,5,7]
        Output: [[4],[2],[1,5,6],[3],[7]]
        Explanation:
        This case is the exact same as example 2, but with nodes 5 and 6 swapped.
        Note that the solution remains the same since 5 and 6 are in the same location and should be ordered by their values.


        Constraints:

        The number of nodes in the tree is in the range [1, 1000].
        0 <= Node.val <= 1000
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

    // Time:
    // Space:
    static class Solution {
        public List<List<Integer>> verticalTraversal(TreeNode root) {
            // map of vertical level(v),  horizontal level(h) ,all nodes at (v,h)
            TreeMap<Integer, TreeMap<Integer, PriorityQueue<Integer>>> map = new TreeMap<>();
            vertical(root, map, 0, 0); // traverse in tree and fill the map
            List<List<Integer>> res = new ArrayList<>();

            for(TreeMap<Integer, PriorityQueue<Integer>> vlevels: map.values()){
                res.add(new ArrayList<>()); // add a new list
                for(PriorityQueue<Integer> hlevels: vlevels.values()){

                    // add all elements of hlevels pq in last added list
                    while(!hlevels.isEmpty()){
                        res.get(res.size()-1).add(hlevels.poll());
                    }
                }
            }
            return res;
        }

        void vertical(TreeNode root, TreeMap<Integer,TreeMap<Integer,PriorityQueue<Integer>>> map, int vlevel, int hlevel){
            if(root == null) return;
            // if vertcal level is not already present in map
            if(!map.containsKey(vlevel)) map.put(vlevel, new TreeMap<>());
            // if horizontal level is not already present in map
            if(!map.get(vlevel).containsKey(hlevel))
                map.get(vlevel).put(hlevel, new PriorityQueue<>());

            // put current node in map
            map.get(vlevel).get(hlevel).add(root.val);

            /* call in left :
                      while calling in left
                                hlevel will increase by 1
                                vlevel will decrease by 1
            */
            vertical(root.left, map, vlevel-1, hlevel+1);

            /* call in right :
                      while calling in right
                                hlevel will increase by 1
                                vlevel will increase by 1
            */
            vertical(root.right, map, vlevel+1, hlevel+1);

        }

    }
}
