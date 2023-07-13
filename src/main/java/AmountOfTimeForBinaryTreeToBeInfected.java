import java.util.*;

public class AmountOfTimeForBinaryTreeToBeInfected {
    /*
        You are given the root of a binary tree with unique values, and an integer start. At minute 0, an infection starts from the node with value start.

        Each minute, a node becomes infected if:

        The node is currently uninfected.
        The node is adjacent to an infected node.
        Return the number of minutes needed for the entire tree to be infected.



        Example 1:


        Input: root = [1,5,3,null,4,10,6,9,2], start = 3
        Output: 4
        Explanation: The following nodes are infected during:
        - Minute 0: Node 3
        - Minute 1: Nodes 1, 10 and 6
        - Minute 2: Node 5
        - Minute 3: Node 4
        - Minute 4: Nodes 9 and 2
        It takes 4 minutes for the whole tree to be infected so we return 4.
        Example 2:


        Input: root = [1], start = 1
        Output: 0
        Explanation: At minute 0, the only node in the tree is infected so we return 0.


        Constraints:

        The number of nodes in the tree is in the range [1, 105].
        1 <= Node.val <= 105
        Each node has a unique value.
        A node with a value of start exists in the tree.
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
        public int amountOfTime(TreeNode root, int start) {
            Map<Integer, List<Integer>> graph = new HashMap<>();
            createGraph(root, graph);
            return maxDistance(graph, new HashSet<>(), start, 0, 0);
        }

        private void createGraph(TreeNode root, Map<Integer, List<Integer>> graph) {
            List<Integer> adjacent = graph.computeIfAbsent(root.val, parameter -> new ArrayList<>());

            if (root.left != null) {
                graph.computeIfAbsent(root.left.val, param -> new ArrayList<>()).add(root.val);
                adjacent.add(root.left.val);
                createGraph(root.left, graph);
            }
            if (root.right != null) {
                graph.computeIfAbsent(root.right.val, param -> new ArrayList<>()).add(root.val);
                adjacent.add(root.right.val);
                createGraph(root.right, graph);
            }
        }

        private int maxDistance(Map<Integer, List<Integer>> graph, Set<Integer> visited, int currentNode, int maxDistance, int currentDistance) {
            if (!visited.contains(currentNode)) {
                visited.add(currentNode);
                maxDistance = Math.max(maxDistance, currentDistance);

                for (int neighbour : graph.get(currentNode)) {
                    maxDistance = Math.max(maxDistance(graph, visited, neighbour, maxDistance, currentDistance + 1), maxDistance);
                }
            }
            return maxDistance;
        }
    }
}
