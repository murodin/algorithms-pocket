import java.util.HashSet;
import java.util.Set;

public class BinaryTreeCameras {

    /*
        You are given the root of a binary tree. We install cameras on the tree nodes where each camera at a node can monitor its parent, itself, and its immediate children.
        Return the minimum number of cameras needed to monitor all nodes of the tree.
        Example:
        Input: root = [0,0,null,0,0]
        Output: 1
        Explanation: One camera is enough to monitor all nodes if placed as shown.
    */



    public static void main(String[] args) {
        TreeNode testRoot = new TreeNode(0, new TreeNode(0, new TreeNode(0), new TreeNode(0)), null);
        System.out.println("Min Cameras:" + Solution.minCameraCover(testRoot));
        System.out.println("Min Cameras:" + SolutionWithoutSet.minCameraCover(testRoot));
    }

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

    static class SolutionWithoutSet {
        static int cam = 0;
        public static int minCameraCover(TreeNode root) {
            int ans = dfs(root);
            // (0) -> cam + 1, (1, 2) -> cam
            return ans == 0 ? cam + 1: cam;
        }

        /*
            2 -> Has Camera
            1 -> Covered By Camera
            0 -> No Camera is covering this node
         */
        public static int dfs(TreeNode node) {
            if(node == null) return 1;
            int left = dfs(node.left);
            int right = dfs(node.right);

            if(left == 0 || right == 0) {
                cam++;
                return 2;
            } else if(left == 2 || right == 2) {
                return 1;
            } else return 0;
        }
    }

    static class Solution {
        static int cam;
        static Set<TreeNode> covered;
        public static int minCameraCover(TreeNode root) {
            if(root == null) return 0;
            cam = 0;
            covered = new HashSet<>();
            covered.add(null);
            dfs(root, null);
            return cam;
        }

        private static void dfs(TreeNode node, TreeNode parent) {
            if(node != null) {
                dfs(node.left, node);
                dfs(node.right, node);

                // Check if needed camera
                // Parent is null & node is uncovered
                // |
                // if any of its left or right child are not covered
                if(parent == null && !covered.contains(node)
                    || !covered.contains(node.left) || !covered.contains(node.right)
                ) {
                    cam++;
                    covered.add(node);
                    covered.add(parent);
                    covered.add(node.left);
                    covered.add(node.right);
                }
            }
        }
    }
}
